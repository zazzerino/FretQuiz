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
  id: string
}

export interface Guess {
  playerId: string,
  noteToGuess: Note,
  clickedFret: FretCoord,
  fretboard: Fretboard
}

export interface ClientGuess {
  gameId: string,
  playerId: string,
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

export type State = 'INIT' | 'PLAYING' | 'ROUND_OVER' | 'GAME_OVER';

export interface Game {
  id: string,
  opts: Opts,
  players: Player[],
  rounds: Round[],
  currentRound: Round,
  state: State
}
