import { Game, GameInfo, Guess } from '../game/types';
import { User } from "../user/userSlice";

export type ResponseType =
  'FLASH'
  | 'LOGGED_IN'
  | 'LOGGED_OUT'
  | 'GAME_IDS'
  | 'GAME_INFOS'
  | 'GAME_CREATED'
  | 'GAME_JOINED'
  | 'GAME_STARTED'
  | 'GUESS_RESULT'
  | 'PLAYER_JOINED'
  | 'GAME_UPDATED'
  | 'ROUND_STARTED'
  | 'GAME_OVER'
  | 'GAME_COUNTDOWN';

export interface Response {
  type: ResponseType
}

export interface Flash extends Response {
  type: 'FLASH',
  message: string
}

// user messages

export interface LoggedIn extends Response {
  type: 'LOGGED_IN',
  user: User
}

export interface LoggedOut extends Response {
  type: 'LOGGED_OUT'
}

// game messages

export interface GameCreated extends Response {
  type: 'GAME_CREATED',
  game: Game
}

export interface GameIds extends Response {
  type: 'GAME_IDS',
  gameIds: string[]
}

export interface GameInfos extends Response {
  type: 'GAME_INFOS',
  gameInfos: GameInfo[]
}

export interface GameJoined extends Response {
  type: 'GAME_JOINED',
  game: Game
}

export interface GameStarted extends Response {
  type: 'GAME_STARTED',
  game: Game
}

export interface GuessResult extends Response {
  type: 'GUESS_RESULT',
  guess: Guess,
  game: Game
}

export interface PlayerJoined extends Response {
  type: 'PLAYER_JOINED'
  message: string
}

export interface GameUpdated extends Response {
  type: 'GAME_UPDATED',
  game: Game
}

export interface RoundStarted extends Response {
  type: 'ROUND_STARTED',
  game: Game
}

export interface GameOver extends Response {
  type: 'GAME_OVER',
  game: Game
}

export interface GameCountdown extends Response {
  type: 'GAME_COUNTDOWN',
  secondsLeft: number
}
