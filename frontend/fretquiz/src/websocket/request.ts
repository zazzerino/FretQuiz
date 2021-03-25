import { ws } from '../websocket/socket';
import { NewGuess } from '../features/game/types';

export type RequestType =
  'LOGIN' | 'CREATE_GAME' | 'GET_GAME_IDS' | 'JOIN_GAME' | 'NEW_GUESS' | 'START_GAME';

export interface Request {
  type: RequestType
}

/**
 * Creates a function that sends the given Request when called.
 */
export function makeSender(request: Request) {
  return () => ws.send(JSON.stringify(request));
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

// game requests

export interface CreateGameRequest extends Request {
  type: 'CREATE_GAME'
}

export function createGameRequest(): CreateGameRequest {
  return { type: 'CREATE_GAME' }
}

export interface GetGameIdsRequest extends Request {
  type: 'GET_GAME_IDS'
}

export function getGameIdsRequest(): GetGameIdsRequest {
  return { type: 'GET_GAME_IDS' }
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

export interface NewGuessRequest extends Request {
  type: 'NEW_GUESS',
  newGuess: NewGuess
}

export function newGuessRequest(newGuess: NewGuess): NewGuessRequest {
  return {
    type: 'NEW_GUESS',
    newGuess
  }
}

export interface StartGameRequest extends Request {
  type: 'START_GAME',
  gameId: string,
  userId: string
}

export function startGameRequest(gameId: string, userId: string): StartGameRequest {
  return {
    type: 'START_GAME',
    gameId,
    userId
  }
}
