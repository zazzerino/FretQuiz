import type { ClientGuess } from "../game/types";
import { 
  createGame, getGameIds, joinGame, 
  nextRound, playerGuess, startGame 
} from "./request";
import type { GameCreated, GameIds, GameJoined } from "./response";
import { ws } from "./websocket";
import { gameIds, game } from '../stores';

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

// export function handleGuessResult(message: GuessResultResponse) {
  // store.dispatch(setGuess(message.guess));
// }

// export function handleGameUpdated(message: GameUpdatedResponse) {
//   const game = message.game;
//   store.dispatch(setCurrentGame(game));
// }

// export function handleRoundStarted(message: RoundStartedResponse) {
//   const game = message.game;
//   store.dispatch(setClickedFret(null));
//   store.dispatch(setGuess(null));
//   store.dispatch(setCurrentGame(game));
// }
