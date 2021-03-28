import type { ClientGuess } from '../game/types';

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

export interface LoginRequest extends Request {
  type: 'LOGIN',
  name: string
}

export function loginRequest(name: string): LoginRequest {
  return {
    type: 'LOGIN',
    name
  };
}

export interface LogoutRequest extends Request {
  type: 'LOGOUT'
}

export function logoutRequest(): LogoutRequest {
  return { type: 'LOGOUT' }
}

// game requests

export interface GetGameIdsRequest extends Request {
  type: 'GET_GAME_IDS'
}

export function getGameIdsRequest(): GetGameIdsRequest {
  return { type: 'GET_GAME_IDS' }
}

export interface CreateGameRequest extends Request {
  type: 'CREATE_GAME'
}

export function createGameRequest(): CreateGameRequest {
  return { type: 'CREATE_GAME' }
}

export interface JoinGameRequest extends Request {
  type: 'JOIN_GAME',
  gameId: string,
  playerId: string
}

export function joinGameRequest(gameId: string, playerId: string): JoinGameRequest {
  return {
    type: 'JOIN_GAME',
    gameId,
    playerId
  }
}

export interface StartGameRequest extends Request {
  type: 'START_GAME',
  gameId: string
}

export function startGameRequest(gameId: string): StartGameRequest {
  return {
    type: 'START_GAME',
    gameId
  }
}

export interface PlayerGuessRequest extends Request {
  type: 'PLAYER_GUESS',
  clientGuess: ClientGuess
}

export function playerGuessRequest(clientGuess: ClientGuess): PlayerGuessRequest {
  return {
    type: 'PLAYER_GUESS',
    clientGuess
  }
}

export interface NextRoundRequest extends Request {
  type: 'NEXT_ROUND',
  gameId: string,
  playerId: string
}

export function nextRoundRequest(gameId: string, playerId: string): NextRoundRequest {
  return {
    type: 'NEXT_ROUND',
    gameId,
    playerId
  }
}
