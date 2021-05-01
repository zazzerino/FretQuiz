export interface FretCoord {
  string: number,
  fret: number
}

export type Accidental = 'FLAT' | 'NONE' | 'SHARP';

export interface Note {
  whiteKey: string,
  accidental: Accidental,
  octave: string
}

export interface Guess {
  playerId: string,
  noteToGuess: Note,
  clickedFret: FretCoord,
  correctFret: FretCoord,
  isCorrect: boolean
}

export interface ClientGuess {
  gameId: string,
  playerId: string,
  clickedFret: FretCoord
}

interface Tuning {
  notes: string[]
}

export interface Fretboard {
  tuning: Tuning,
  startFret: number,
  endFret: number,
  notes: any
}

export interface Opts {
  fretboard: Fretboard,
  roundCount: number,
  strings: number[],
  accidentals: Accidental[]
}

export interface Round {
  noteToGuess: Note,
  secondsElapsed: number,
  guesses: Guess[]
}

export type State =
  'INIT' | 'PLAYING' | 'ROUND_OVER' | 'GAME_OVER';

export interface Player {
  id: string,
  name: string
}

export interface PlayerScore {
  player: Player,
  score: number
}

export interface GameInfo {
  gameId: string,
  createdAt: string,
  hostName: string,
  playerCount: number,
  state: State
}

export interface Game {
  id: string,
  createdAt: string,
  opts: Opts,
  players: Player[],
  hostId: string,
  rounds: Round[],
  currentRound: Round | null,
  isOver: boolean,
  state: State,
  scores: PlayerScore[]
}
