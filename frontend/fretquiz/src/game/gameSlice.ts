import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { createSelector } from "reselect";
import { FretCoord } from "fretboard-diagram";
import { RootState } from "../store";
import { Game, Guess } from "./types";

interface GameSliceState {
  gameIds: string[],
  currentGame: Game | null,
  clickedFret: FretCoord | null,
  guess: Guess | null
}

const initialState: GameSliceState = {
  gameIds: [],
  currentGame: null,
  clickedFret: null,
  guess: null
}

const gameSlice = createSlice({
  name: 'game',
  initialState,
  reducers: {
    setCurrentGame: (state: GameSliceState, action: PayloadAction<Game | null>) => {
      state.currentGame = action.payload;
    },
    setGameIds: (state: GameSliceState, action: PayloadAction<string[]>) => {
      state.gameIds = action.payload;
    },
    setClickedFret: (state: GameSliceState, action: PayloadAction<FretCoord | null>) => {
      state.clickedFret = action.payload;
    },
    setGuess: (state: GameSliceState, action: PayloadAction<Guess | null>) => {
      state.guess = action.payload;
    }
  }
});

export const selectGameIds = (state: RootState) => state.game.gameIds;

export const selectCurrentGame = (state: RootState) => state.game.currentGame;

export const selectGameId = (state: RootState) => state.game.currentGame?.id;

export const selectNoteToGuess = (state: RootState) => state.game.currentGame?.currentRound?.noteToGuess;

export const selectGameState = (state: RootState) => state.game.currentGame?.state;

export const selectGuess = (state: RootState) => state.game.guess;

export const selectGuessIsCorrect = (state: RootState) => state.game.guess?.isCorrect;

export const selectCorrectFret = (state: RootState) => state.game.guess?.correctFret;

export const selectClickedFret = (state: RootState) => state.game.clickedFret;

export const selectReadyToStart = createSelector(selectGameState, state => state === 'INIT');

export const selectGameIsPlaying = createSelector(selectGameState, state => state === 'PLAYING');

export const selectRoundIsOver = createSelector(selectGameState, state => state === 'ROUND_OVER');

export const selectGameIsOver = createSelector(selectGameState, state => state === 'GAME_OVER');

export const { setCurrentGame, setGameIds, setClickedFret, setGuess } = gameSlice.actions;

export default gameSlice.reducer;
