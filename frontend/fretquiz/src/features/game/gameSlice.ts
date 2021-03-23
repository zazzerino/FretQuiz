import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "../../app/store";

export type NoteName = string;

export type PlayerId = string;

export type GameId = string;

export interface FretCoord {
  string: number,
  fret: number
}

export interface Note {
  whiteKey: string,
  accidental: string,
  octave: string
}

export interface Player {
  id: PlayerId
}

interface Guess {
  playerId: PlayerId,
  noteToGuess: Note,
  clickedFret: FretCoord,
  fretboard: any
}

export interface NewGuess {
  gameId: GameId,
  playerId: PlayerId,
  clickedFret: FretCoord
}

export interface Fretboard {
  tuning: string[],
  startFret: number,
  endFret: number,
  notes: any
}

export interface Opts {
  roundLength: number,
  fretboard: Fretboard
}

export interface Round {
  noteToGuess: Note,
  guesses: Guess[]
}

export interface Game {
  id: GameId,
  opts: Opts,
  players: Player[],
  rounds: Round[],
  currentRound: Round
}

export interface GameState {
  gameIds: GameId[],
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

export const { setCurrentGame, setGameIds } = gameSlice.actions;

export default gameSlice.reducer;
