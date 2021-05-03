import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { createSelector } from "reselect";
import { FretCoord } from "fretboard-diagram";
import { RootState } from "../store";
import { ChatMessage, Game, GameInfo, Guess } from "./types";
import { selectUserId } from "../user/userSlice";

interface GameSliceState {
  gameInfos: GameInfo[],
  currentGame: Game | null,
  clickedFret: FretCoord | null,
  guess: Guess | null,
  isCountingDown: boolean,
  secondsLeft: number | null,
  chatMessages: ChatMessage[],
}

const initialState: GameSliceState = {
  gameInfos: [],
  currentGame: null,
  clickedFret: null,
  guess: null,
  isCountingDown: false,
  secondsLeft: null,
  chatMessages: [],
}

const gameSlice = createSlice({
  name: 'game',
  initialState,
  reducers: {
    updateGameInfos: (state: GameSliceState, action: PayloadAction<GameInfo[]>) => {
      state.gameInfos = action.payload;
    },
    updateGame: (state: GameSliceState, action: PayloadAction<Game | null>) => {
      state.currentGame = action.payload;
    },
    playerGuessed: (state: GameSliceState, action: PayloadAction<Guess | null>) => {
      state.guess = action.payload;
    },
    fretClicked: (state: GameSliceState, action: PayloadAction<FretCoord | null>) => {
      state.clickedFret = action.payload;
    },
    startRound: (state: GameSliceState, action: PayloadAction<Game>) => {
      state.clickedFret = null;
      state.guess = null;
      state.isCountingDown = false;
      state.secondsLeft = null;
      state.currentGame = action.payload;
    },
    gameOver: (state: GameSliceState, _action) => {
      state.clickedFret = null;
      state.guess = null;
    },
    gameCountdown: (state: GameSliceState, action: PayloadAction<number>) => {
      state.isCountingDown = true;
      state.secondsLeft = action.payload;
    },
    gameStarted: (state: GameSliceState, action: PayloadAction<Game>) => {
      state.isCountingDown = false;
      state.secondsLeft = null;
      state.currentGame = action.payload;
    },
    updateChat: (state: GameSliceState, action: PayloadAction<ChatMessage[]>) => {
      state.chatMessages = action.payload;
    }
  }
});

export const selectGameInfos = (state: RootState) => state.game.gameInfos;

export const selectCurrentGame = (state: RootState) => state.game.currentGame;

export const selectGameId = (state: RootState) => state.game.currentGame?.id;

export const selectHostId = (state: RootState) => state.game.currentGame?.hostId;

export const selectUserIsHost = createSelector(
  selectUserId,
  selectHostId,
  (userId, hostId) => userId === hostId
);

export const selectNoteToGuess = (state: RootState) => state.game.currentGame?.currentRound?.noteToGuess;

export const selectGameState = (state: RootState) => state.game.currentGame?.state;

export const selectGuess = (state: RootState) => state.game.guess;

export const selectGuessIsCorrect = (state: RootState) => state.game.guess?.isCorrect;

export const selectCorrectFret = (state: RootState) => state.game.guess?.correctFret;

export const selectClickedFret = (state: RootState) => state.game.clickedFret;

export const selectScores = (state: RootState) => state.game.currentGame?.scores;

export const selectPlayers = (state: RootState) => state.game.currentGame?.players;

export const selectPlayerNames = createSelector(selectPlayers, players => players?.map(player => player.name));

export const selectReadyToStart = createSelector(selectGameState, state => state === 'INIT');

export const selectGameIsPlaying = createSelector(selectGameState, state => state === 'PLAYING');

export const selectRoundIsOver = createSelector(selectGameState, state => state === 'ROUND_OVER');

export const selectGameIsOver = createSelector(selectGameState, state => state === 'GAME_OVER');

export const selectOpts = (state: RootState) => state.game.currentGame?.opts;

export const selectStringCount = createSelector(selectOpts, opts => opts?.fretboard.tuning.notes.length);

export const selectStringsToUse = createSelector(selectOpts, opts => opts?.strings);

export const selectAccidentalsToUse = createSelector(selectOpts, opts => opts?.accidentals);

export const selectRoundCount = createSelector(selectOpts, opts => opts?.roundCount);

export const selectIsCountingDown = (state: RootState) => state.game.isCountingDown;

export const selectSecondsLeft = (state: RootState) => state.game.secondsLeft;

export const selectChatMessages = (state: RootState) => state.game.chatMessages.map(msg => msg.text);

export const { 
  updateGameInfos, fretClicked, playerGuessed, updateGame, startRound, 
  gameOver, gameCountdown, gameStarted, updateChat,
} = gameSlice.actions;

export default gameSlice.reducer;
