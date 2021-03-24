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

export interface Guess {
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
