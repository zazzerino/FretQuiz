import { Game } from "../features/game/gameSlice";
import { User } from "../features/user/userSlice";

export type MessageType =
  'LOGIN_OK' | 'LOGOUT_OK' | 'GAME_CREATED' | 'GAME_IDS' | 
  'GUESS_RESPONSE' | 'GAME_JOINED';

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

export interface GameIdsMessage extends Message {
  type: 'GAME_IDS',
  gameIds: string[]
}
export interface GameJoinedMessage extends Message {
  type: 'GAME_JOINED',
  game: Game
}
