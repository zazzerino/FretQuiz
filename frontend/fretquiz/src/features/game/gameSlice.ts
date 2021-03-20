import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "../../app/store";

export type FretCoordString = string;

export interface FretCoord {
  string: number,
  fret: number
}

export interface Note {
  whiteKey: string,
  accidental: string,
  octave: string
}

type PlayerId = string;
type GameId = string;

interface Player {
  id: PlayerId
}

interface Fretboard {
  startFret: number,
  endFret: number,
  notes: any
}

interface GameOpts {
  roundLength: number,
  secondsRemaining: number,
  fretboard: Fretboard
}

interface Guess {
  playerId: PlayerId,
  noteToGuess: Note,
  clickedFret: FretCoordString,
  fretboard: any
}

export interface NewGuess {
  gameId: GameId,
  playerId: PlayerId,
  clickedFret: FretCoord
}

export interface Game {
  id: string,
  opts: GameOpts,
  players: Player[],
  noteToGuess: Note,
  guesses: Guess[]
}

interface GameState {
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
    setGame: (state: GameState, action: PayloadAction<Game>) => {
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

export const selectNoteToGuess = (state: RootState) => state.game.currentGame?.noteToGuess;

export const { setGame, setGameIds } = gameSlice.actions;

export default gameSlice.reducer;
