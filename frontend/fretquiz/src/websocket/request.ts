import { ClientGuess } from '../features/game/types';

export type RequestType =
  'LOGIN'
  | 'LOGOUT'
  | 'GET_GAME_IDS'
  | 'CREATE_GAME'
  | 'JOIN_GAME'
  | 'START_GAME'
  | 'PLAYER_GUESS'
  | 'NEXT_ROUND';

export interface Request {
  type: RequestType
}

// user requests

export interface Login extends Request {
  type: 'LOGIN',
  name: string
}

export function login(name: string): Login {
  return {
    type: 'LOGIN',
    name
  };
}

export interface Logout extends Request {
  type: 'LOGOUT'
}

export function logout(): Logout {
  return { type: 'LOGOUT' }
}

// game requests

export interface GetGameIds extends Request {
  type: 'GET_GAME_IDS'
}

export function getGameIds(): GetGameIds {
  return { type: 'GET_GAME_IDS' }
}

export interface CreateGame extends Request {
  type: 'CREATE_GAME'
}

export function createGame(): CreateGame {
  return { type: 'CREATE_GAME' }
}

export interface JoinGame extends Request {
  type: 'JOIN_GAME',
  gameId: string,
  playerId: string
}

export function joinGame(gameId: string, playerId: string): JoinGame {
  return {
    type: 'JOIN_GAME',
    gameId,
    playerId
  }
}

export interface StartGame extends Request {
  type: 'START_GAME',
  gameId: string
}

export function startGame(gameId: string): StartGame {
  return {
    type: 'START_GAME',
    gameId
  }
}

export interface PlayerGuess extends Request {
  type: 'PLAYER_GUESS',
  clientGuess: ClientGuess
}

export function playerGuess(clientGuess: ClientGuess): PlayerGuess {
  return {
    type: 'PLAYER_GUESS',
    clientGuess
  }
}

export interface NextRound extends Request {
  type: 'NEXT_ROUND',
  gameId: string,
  playerId: string
}

export function nextRound(gameId: string, playerId: string): NextRound {
  return {
    type: 'NEXT_ROUND',
    gameId,
    playerId
  }
}
