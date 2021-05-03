import { ws } from './websocket';
import { store } from '../store';
import * as request from './request';
import * as response from './response';
import { Accidental, ClientGuess } from '../game/types';
import { 
  playerGuessed, updateGameInfos,
  updateGame, startRound, gameOver, gameCountdown, gameStarted, updateChat,
} from '../game/gameSlice';
import { correctSound, incorrectSound } from '../sounds';

// helper functions

/**
 * Sends a stringified JSON request to the backend.
 */
const send = (request: request.Request) => {
  ws.send(JSON.stringify(request));
}

/**
 * Updates the redux store.
 */
const dispatch = store.dispatch;

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
  send(request.startGameCountdown(gameId));
}

export const sendStartRoundCountdown = (gameId: string) => {
  send(request.startRoundCountdown(gameId));
}

export const sendChatMessage = (gameId: string, text: string) => {
  send(request.chatMessage(gameId, text));
}

// handle responses

export function handleGameCreated(message: response.GameCreated) {
  const game = message.game;
  dispatch(updateGame(game));
}

export function handleGameInfos(message: response.GameInfos) {
  const gameInfos = message.gameInfos;
  dispatch(updateGameInfos(gameInfos));
}

export function handleGameJoined(message: response.GameJoined) {
  const game = message.game;
  dispatch(updateGame(game));
}

export function handleGuessResult(message: response.GuessResult) {
  const guess = message.guess;

  guess.isCorrect
    ? correctSound.play()
    : incorrectSound.play();

  dispatch(playerGuessed(guess));
}

export function handleGameUpdated(message: response.GameUpdated) {
  const game = message.game;
  dispatch(updateGame(game));
}

export function handleRoundStarted(message: response.RoundStarted) {
  const game = message.game;
  dispatch(startRound(game));
}

export function handleGameOver(message: response.GameOver) {
  const game = message.game;
  dispatch(gameOver(game));
}

export function handleGameCountdown(message: response.GameCountdown) {
  const secondsLeft = message.secondsLeft;
  dispatch(gameCountdown(secondsLeft));
}

export function handleGameStarted(message: response.GameStarted) {
  const game = message.game;
  dispatch(gameStarted(game));
}

export function handleUpdateChat(message: response.UpdateChat) {
  const messages = message.messages;
  dispatch(updateChat(messages));
}
