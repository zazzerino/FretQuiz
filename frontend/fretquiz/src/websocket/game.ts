import type { ClientGuess } from "../game/types";
import { 
  createGame, getGameIds, joinGame, 
  nextRound, playerGuess, startGame 
} from "./request";
import type { GameCreated, GameIds, GameJoined, GameUpdated, GuessResult, RoundStarted } from "./response";
import { ws } from "./websocket";
import { gameIds, game, guess, clickedFret } from '../stores';

// game request senders

export function sendCreateGame() {
  ws.send(JSON.stringify(createGame()));
}

export function sendGetGameIds() {
  ws.send(JSON.stringify(getGameIds()));
} 

export function sendGuess(clientGuess: ClientGuess) {
  ws.send(JSON.stringify(playerGuess(clientGuess)));
}

export function sendJoinGame(gameId: string, userId: string) {
  ws.send(JSON.stringify(joinGame(gameId, userId)));
}

export function sendStartGame(gameId: string) {
  ws.send(JSON.stringify(startGame(gameId)));
}

export function sendNextRound(gameId: string, playerId: string) {
  ws.send(JSON.stringify(nextRound(gameId, playerId)));
}

// game response handlers

export function handleGameIds(message: GameIds) {
  gameIds.set(message.gameIds);
}

export function handleGameCreated(message: GameCreated) {
  game.set(message.game);
}

export function handleGameJoined(message: GameJoined) {
  game.set(message.game);
}

export function handleGuessResult(message: GuessResult) {
  guess.set(message.guess);
  game.set(message.game);
}

export function handleGameUpdated(message: GameUpdated) {
  game.set(message.game);
}

export function handleRoundStarted(message: RoundStarted) {
  clickedFret.set(null);
  guess.set(null);
  game.set(message.game)
}
