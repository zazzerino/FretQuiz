import { Game, Guess } from '../features/game/types';
import { User } from "../features/user/userSlice";

export type ResponseType =
  'LOGGED_IN'
  | 'LOGGED_OUT'
  | 'GAME_IDS'
  | 'GAME_CREATED'
  | 'GAME_JOINED'
  | 'GAME_STARTED'
  | 'GUESS_RESULT'
  | 'PLAYER_JOINED'
  | 'GAME_UPDATED'
  | 'ROUND_STARTED';

export interface Response {
  type: ResponseType
}

// user messages

export interface LoggedInResponse extends Response {
  type: 'LOGGED_IN',
  user: User
}

export interface LoggedOutResponse extends Response {
  type: 'LOGGED_OUT'
}

// game messages

export interface GameCreatedResponse extends Response {
  type: 'GAME_CREATED',
  game: Game
}

export interface GameIdsResponse extends Response {
  type: 'GAME_IDS',
  gameIds: string[]
}

export interface GameJoinedResponse extends Response {
  type: 'GAME_JOINED',
  game: Game
}

export interface GameStartedResponse extends Response {
  type: 'GAME_STARTED',
  game: Game
}

export interface GuessResultResponse extends Response {
  type: 'GUESS_RESULT',
  // isCorrect: boolean,
  guess: Guess,
  game: Game
}

export interface PlayerJoinedResponse extends Response {
  type: 'PLAYER_JOINED'
  message: string
}

export interface GameUpdatedResponse extends Response {
  type: 'GAME_UPDATED',
  game: Game
}

export interface RoundStartedResponse extends Response {
  type: 'ROUND_STARTED',
  game: Game
}
