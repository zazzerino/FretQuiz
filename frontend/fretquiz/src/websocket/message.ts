import { Game } from "../features/game/gameSlice";
import { User } from "../features/user/userSlice";

export type MessageType =
  'BROADCAST' | 'LOGIN_OK' | 'GAME_CREATED' | 'GET_GAME_IDS' | 'GUESS' | 'GAME_JOINED';

export interface Message {
  type: MessageType
}

// user messages

export interface LoginMessage extends Message {
  type: 'LOGIN_OK',
  user: User
}

// game messages

export interface GameCreatedMessage extends Message {
  type: 'GAME_CREATED',
  game: Game
}

export interface GetGameIdsMessage extends Message {
  type: 'GET_GAME_IDS',
  gameIds: string[]
}
export interface GameJoinedMessage extends Message {
  type: 'GAME_JOINED',
  game: Game
}
