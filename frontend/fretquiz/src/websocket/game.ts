import { ws } from './socket';
import { store } from '../store';
import * as request from './request';
import * as response from './response';
import { Accidental, ClientGuess } from '../game/types';
import { setClickedFret, setCurrentGame, setGameIds, setGuess } from '../game/gameSlice';

function send(request: request.Request) {
  ws.send(JSON.stringify(request));
}

// game request senders

export function sendCreateGame() {
  send(request.createGame());
}

export function sendGetGameIds() {
  send(request.getGameIds());
}

export const sendGuess = (clientGuess: ClientGuess) => {
  send(request.playerGuess(clientGuess));
}

export const sendJoinGame = (gameId: string, userId: string) => {
  send(request.joinGame(gameId, userId));
}

export const sendStartGame = (gameId: string) => {
  send(request.startGame(gameId));
}

export const sendNextRound = (gameId: string, playerId: string) => {
  send(request.nextRound(gameId, playerId));
}

export const sendToggleString = (gameId: string, string: number) => {
  send(request.toggleString(gameId, string));
}

export const sendToggleAccidental = (gameId: string, accidental: Accidental) => {
  send(request.toggleAccidental(gameId, accidental));
}

// handle responses

export function handleGameCreated(message: response.GameCreated) {
  const game = message.game;
  store.dispatch(setCurrentGame(game));
}

export function handleGameIds(message: response.GameIds) {
  const gameIds = message.gameIds;
  store.dispatch(setGameIds(gameIds));
}

export function handleGameJoined(message: response.GameJoined) {
  const game = message.game;
  store.dispatch(setCurrentGame(game));
}

export function handleGuessResult(message: response.GuessResult) {
  const guess = message.guess;
  store.dispatch(setGuess(guess));
}

export function handleGameUpdated(message: response.GameUpdated) {
  const game = message.game;
  store.dispatch(setCurrentGame(game));
}

export function handleRoundStarted(message: response.RoundStarted) {
  const game = message.game;
  store.dispatch(setClickedFret(null));
  store.dispatch(setGuess(null));
  store.dispatch(setCurrentGame(game));
}

export function handleGameOver(message: response.GameOver) {
  store.dispatch(setClickedFret(null));
  store.dispatch(setGuess(null));
}
