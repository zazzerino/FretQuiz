import { Game } from '../features/game/types';
import { User } from "../features/user/userSlice";

export type ResponseType =
  'LOGIN_OK' | 'LOGOUT_OK' | 'GAME_CREATED' | 'GAME_IDS' | 
  'GUESS_RESPONSE' | 'GAME_JOINED' | 'GAME_UPDATED';

export interface Response {
  type: ResponseType
}

// user messages

export interface LoginOkResponse extends Response {
  type: 'LOGIN_OK',
  user: User
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

export interface GameUpdatedResponse extends Response {
  type: 'GAME_UPDATED',
  game: Game
}
