import type { ClientGuess } from "../game/types";
import { 
  createGameRequest, getGameIdsRequest, joinGameRequest, 
  nextRoundRequest, playerGuessRequest, startGameRequest 
} from "./request";
import type { GameCreatedResponse, GameIdsResponse, GameJoinedResponse } from "./response";
import { ws } from "./websocket";
import { gameIds, currentGame } from '../stores';

// game request senders

export function sendCreateGame() {
  const request = createGameRequest();
  ws.send(JSON.stringify(request));
}

export function sendGetGameIds() {
  const request = getGameIdsRequest();
  ws.send(JSON.stringify(request));
} 

export function sendGuess(clientGuess: ClientGuess) {
  const request = playerGuessRequest(clientGuess);
  ws.send(JSON.stringify(request));
}

export function sendJoinGame(gameId: string, userId: string) {
  const request = joinGameRequest(gameId, userId);
  ws.send(JSON.stringify(request));
}

export function sendStartGame(gameId: string) {
  const request = startGameRequest(gameId);
  ws.send(JSON.stringify(request));
}

export function sendNextRound(gameId: string, playerId: string) {
  const request = nextRoundRequest(gameId, playerId);
  ws.send(JSON.stringify(request));
}

// game response handlers

export function handleGameCreated(message: GameCreatedResponse) {
  currentGame.set(message.game);
}

export function handleGameIds(message: GameIdsResponse) {
  gameIds.set(message.gameIds);
}

export function handleGameJoined(message: GameJoinedResponse) {
  currentGame.set(message.game);
}

// export function handleGuessResult(message: GuessResultResponse) {
//   store.dispatch(setGuess(message.guess));
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
