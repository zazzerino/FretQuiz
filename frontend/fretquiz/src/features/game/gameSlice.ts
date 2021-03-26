import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "../../app/store";
import { Game } from './types';

export interface GameState {
  gameIds: string[],
  currentGame?: Game
}

const initialState: GameState = {
  gameIds: []
}

const gameSlice = createSlice({
  name: 'game',
  initialState,
  reducers: {
    setCurrentGame: (state: GameState, action: PayloadAction<Game>) => {
      state.currentGame = action.payload;
    },
    setGameIds: (state: GameState, action: PayloadAction<string[]>) => {
      state.gameIds = action.payload;
    },
  }
});

export const selectGameIds = (state: RootState) => state.game.gameIds;

export const selectCurrentGame = (state: RootState) => state.game.currentGame;

export const selectGameId = (state: RootState) => state.game.currentGame?.id;

export const selectNoteToGuess = (state: RootState) => state.game.currentGame?.currentRound.noteToGuess;

export const selectGameState = (state: RootState) => state.game.currentGame?.state;

export const { setCurrentGame, setGameIds } = gameSlice.actions;

export default gameSlice.reducer;
