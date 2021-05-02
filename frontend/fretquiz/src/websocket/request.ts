import { ClientGuess, Accidental } from '../game/types';

export type RequestType =
  'LOGIN'
  | 'LOGOUT'
  | 'GET_GAME_IDS'
  | 'CREATE_GAME'
  | 'JOIN_GAME'
  | 'START_GAME'
  | 'PLAYER_GUESS'
  | 'NEXT_ROUND'
  | 'TOGGLE_STRING'
  | 'TOGGLE_ACCIDENTAL'
  | 'SET_ROUND_COUNT'
  | 'START_COUNTDOWN'
  | 'START_ROUND_COUNTDOWN';

export interface Request {
  type: RequestType
}

// user requests

export interface Login extends Request {
  type: 'LOGIN',
  name: string
}

export function login(name: string): Login {
  return { type: 'LOGIN', name };
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
  userId: string
}

export function joinGame(gameId: string, userId: string): JoinGame {
  return { type: 'JOIN_GAME', gameId, userId: userId }
}

export interface StartGame extends Request {
  type: 'START_GAME',
  gameId: string
}

export function startGame(gameId: string): StartGame {
  return { type: 'START_GAME', gameId }
}

export interface PlayerGuess extends Request {
  type: 'PLAYER_GUESS',
  clientGuess: ClientGuess
}

export function playerGuess(clientGuess: ClientGuess): PlayerGuess {
  return { type: 'PLAYER_GUESS', clientGuess }
}

export interface NextRound extends Request {
  type: 'NEXT_ROUND',
  gameId: string,
  userId: string
}

export function nextRound(gameId: string, userId: string): NextRound {
  return { type: 'NEXT_ROUND', gameId, userId: userId }
}

export interface ToggleString extends Request {
  type: 'TOGGLE_STRING',
  gameId: string,
  string: number
}

export function toggleString(gameId: string, string: number): ToggleString {
  return { type: 'TOGGLE_STRING', gameId, string }
}

export interface ToggleAccidental extends Request {
  type: 'TOGGLE_ACCIDENTAL',
  gameId: string,
  accidental: Accidental
}

export function toggleAccidental(gameId: string, accidental: Accidental): ToggleAccidental {
  return { type: 'TOGGLE_ACCIDENTAL', gameId, accidental };
}

export interface SetRoundCount extends Request {
  type: 'SET_ROUND_COUNT',
  gameId: string, 
  roundCount: number
}

export function setRoundCount(gameId: string, roundCount: number): SetRoundCount {
  return { type: 'SET_ROUND_COUNT', gameId, roundCount };
}

export interface StartCountdown extends Request {
  type: 'START_COUNTDOWN',
  gameId: string
}

export function startCountdown(gameId: string): StartCountdown {
  return { type: 'START_COUNTDOWN', gameId }
}

export interface StartRoundCountdown extends Request {
  type: 'START_ROUND_COUNTDOWN',
  gameId: string,
}

export function startRoundCountdown(gameId: string): StartRoundCountdown {
  return { type: 'START_ROUND_COUNTDOWN', gameId }
}
