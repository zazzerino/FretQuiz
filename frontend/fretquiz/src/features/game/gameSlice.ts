import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { FretCoord } from "fretboard-diagram";
import { RootState } from "../../app/store";
import { Game } from './types';

export interface GameSliceState {
  gameIds: string[],
  currentGame: Game | null,
  clickedFret: FretCoord | null,
  guessResult: boolean | null
}

const initialState: GameSliceState = {
  gameIds: [],
  currentGame: null,
  clickedFret: null,
  guessResult: null
}

const gameSlice = createSlice({
  name: 'game',
  initialState,
  reducers: {
    setCurrentGame: (state: GameSliceState, action: PayloadAction<Game>) => {
      state.currentGame = action.payload;
    },
    setGameIds: (state: GameSliceState, action: PayloadAction<string[]>) => {
      state.gameIds = action.payload;
    },
    setClickedFret: (state: GameSliceState, action: PayloadAction<FretCoord>) => {
      state.clickedFret = action.payload;
    },
    setGuessResult: (state: GameSliceState, action: PayloadAction<boolean>) => {
      state.guessResult = action.payload;
    }
  }
});

export const selectGameIds = (state: RootState) => state.game.gameIds;

export const selectCurrentGame = (state: RootState) => state.game.currentGame;

export const selectGameId = (state: RootState) => state.game.currentGame?.id;

export const selectNoteToGuess = (state: RootState) => state.game.currentGame?.currentRound.noteToGuess;

export const selectGameState = (state: RootState) => state.game.currentGame?.state;

export const selectGuessResult = (state: RootState) => state.game.guessResult;

export const selectClickedFret = (state: RootState) => state.game.clickedFret;

export const { setCurrentGame, setGameIds, setClickedFret, setGuessResult } = gameSlice.actions;

export default gameSlice.reducer;
