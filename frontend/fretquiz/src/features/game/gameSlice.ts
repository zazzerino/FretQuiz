import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "../../app/store";

type FretCoord = string;

// type Note = string;

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
  clickedFret: FretCoord,
  fretboard: any
}

export interface Game {
  id: string,
  opts: GameOpts,
  players: Player[],
  noteToGuess: Note,
  guesses: Guess[]
}

interface GameState {
  // games: Record<GameId, Game>,
  games: Game[],
  currentGame?: Game
}

const initialState: GameState = {
  games: []
}

const gameSlice = createSlice({
  name: 'game',

  initialState,

  reducers: {
    setCurrentGame: (state: GameState, action: PayloadAction<Game>) => {
      state.currentGame = action.payload;
    },
    setGames: (state: GameState, action: PayloadAction<Game[]>) => {
      state.games = action.payload;
    },
  }
});

export const selectGames = (state: RootState) => state.game.games;
export const selectCurrentGame = (state: RootState) => state.game.currentGame;

export const selectNoteToGuess = (state: RootState) => state.game.currentGame?.noteToGuess;

export const { setCurrentGame, setGames } = gameSlice.actions;

export default gameSlice.reducer;
