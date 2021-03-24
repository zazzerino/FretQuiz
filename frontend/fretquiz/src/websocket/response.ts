import { Game } from '../features/game/types';
import { User } from "../features/user/userSlice";

export type ResponseType =
  'LOGIN_OK' | 'LOGOUT_OK' | 'GAME_CREATED' | 'GET_GAME_IDS' | 
  'GUESS_RESPONSE' | 'GAME_JOINED';

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

export interface GetGameIdsResponse extends Response {
  type: 'GET_GAME_IDS',
  gameIds: string[]
}

export interface GameJoinedResponse extends Response {
  type: 'GAME_JOINED',
  game: Game
}
