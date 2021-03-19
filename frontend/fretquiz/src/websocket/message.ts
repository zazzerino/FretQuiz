import { Game } from "../features/game/gameSlice";
import { User } from "../features/user/userSlice";

export type MessageType =
  'BROADCAST' | 'LOGIN_OK' | 'GAME_CREATED' | 'GET_GAMES' | 'GUESS';

export interface Message {
  type: MessageType
}

export interface GameCreatedMessage extends Message {
  type: 'GAME_CREATED',
  game: Game
}

export interface GetGamesMessage extends Message {
  type: 'GET_GAMES',
  games: Game[]
}

export interface LoginMessage extends Message {
  type: 'LOGIN_OK',
  user: User
}

export interface GuessMessage extends Message {
  type: 'GUESS',

}
