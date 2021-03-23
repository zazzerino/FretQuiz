import { NewGuess } from "../features/game/gameSlice";

export type RequestType = 'LOGIN' | 'CREATE_GAME' | 'GET_GAME_IDS' | 'JOIN_GAME' | 'NEW_GUESS';

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

// game requests

export interface CreateGameRequest extends Request {
  type: 'CREATE_GAME'
}

export function createGameRequest(): CreateGameRequest {
  return { type: 'CREATE_GAME' }
}

export interface GetGameIdsRequest {
  type: 'GET_GAME_IDS'
}

export function getGameIdsRequest(): GetGameIdsRequest {
  return { type: 'GET_GAME_IDS' }
}

export interface JoinGameRequest {
  type: 'JOIN_GAME',
  playerId: string,
  gameId: string
}

export function joinGameRequest(playerId: string, gameId: string): JoinGameRequest {
  return {
    type: 'JOIN_GAME',
    playerId,
    gameId
  }
}

export interface NewGuessRequest {
  type: 'NEW_GUESS',
  newGuess: NewGuess
}

export function newGuessRequest(newGuess: NewGuess): NewGuessRequest {
  return {
    type: 'NEW_GUESS',
    newGuess
  }
}
