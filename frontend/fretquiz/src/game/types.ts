export interface FretCoord {
  string: number,
  fret: number
}

export interface Note {
  whiteKey: string,
  accidental: string,
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

export type Accidental = 'FLAT' | 'NONE' | 'SHARP';

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

export type State = 'INIT' | 'PLAYING' | 'ROUND_OVER' | 'GAME_OVER';

export interface Player {
  id: string,
  name: string
}

export interface PlayerScore {
  player: Player,
  score: number
}

export interface Game {
  id: string,
  opts: Opts,
  playerIds: string[],
  hostId: string,
  rounds: Round[],
  currentRound: Round | null,
  isOver: boolean,
  state: State,
  scores: PlayerScore[]
}
