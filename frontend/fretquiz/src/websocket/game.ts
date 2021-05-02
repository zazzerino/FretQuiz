import { ws } from './websocket';
import { store } from '../store';
import * as request from './request';
import * as response from './response';
import { Accidental, ClientGuess } from '../game/types';
import { 
  updateGameIds, setGuess, updateGameInfos,
  updateGame, startRound, gameOver, gameCountdown, gameStarted,
} from '../game/gameSlice';
import { correctSound, incorrectSound } from '../sounds';

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

export const sendSetRoundCount = (gameId: string, roundCount: number) => {
  send(request.setRoundCount(gameId, roundCount));
}

export const sendStartCountdown = (gameId: string) => {
  send(request.startCountdown(gameId));
}

export const sendStartRoundCountdown = (gameId: string) => {
  send(request.startRoundCountdown(gameId));
}

// handle responses

export function handleGameCreated(message: response.GameCreated) {
  const game = message.game;
  store.dispatch(updateGame(game));
}

export function handleGameIds(message: response.GameIds) {
  const gameIds = message.gameIds;
  store.dispatch(updateGameIds(gameIds));
}

export function handleGameInfos(message: response.GameInfos) {
  const gameInfos = message.gameInfos;
  store.dispatch(updateGameInfos(gameInfos));
}

export function handleGameJoined(message: response.GameJoined) {
  const game = message.game;
  store.dispatch(updateGame(game));
}

export function handleGuessResult(message: response.GuessResult) {
  const guess = message.guess;

  guess.isCorrect
    ? correctSound.play()
    : incorrectSound.play();

  store.dispatch(setGuess(guess));
}

export function handleGameUpdated(message: response.GameUpdated) {
  const game = message.game;
  store.dispatch(updateGame(game));
}

export function handleRoundStarted(message: response.RoundStarted) {
  const game = message.game;
  store.dispatch(startRound(game));
}

export function handleGameOver(message: response.GameOver) {
  const game = message.game;
  store.dispatch(gameOver(game));
}

export function handleGameCountdown(message: response.GameCountdown) {
  const secondsLeft = message.secondsLeft;
  store.dispatch(gameCountdown(secondsLeft));
}

export function handleGameStarted(message: response.GameStarted) {
  const game = message.game;
  store.dispatch(gameStarted(game));
}
