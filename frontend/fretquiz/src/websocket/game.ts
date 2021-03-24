import { store } from '../app/store';
import { ws } from './socket';
import { GameCreatedResponse, GetGameIdsResponse, GameJoinedResponse } from './response';
import { setCurrentGame, setGameIds } from "../features/game/gameSlice";
import { createGameRequest, getGameIdsRequest, joinGameRequest, newGuessRequest } from './request';
import { NewGuess } from '../features/game/types';

export function sendCreateGame() {
  const message = JSON.stringify(createGameRequest());
  ws.send(message);
}

export function handleGameCreated(message: GameCreatedResponse) {
  const game = message.game;
  store.dispatch(setCurrentGame(game));
}

export function sendGetGameIds() {
  const message = JSON.stringify(getGameIdsRequest());
  ws.send(message);
}

export function handleGetGameIds(message: GetGameIdsResponse) {
  const gameIds = message.gameIds;
  store.dispatch(setGameIds(gameIds));
}

export function sendGuess(guess: NewGuess) {
  const message = JSON.stringify(newGuessRequest(guess));
  ws.send(message);
}

export function sendJoinGame(userId: string, gameId: string) {
  const message = JSON.stringify(joinGameRequest(userId, gameId));
  ws.send(message);
}

export function handleGameJoined(message: GameJoinedResponse) {
  const game = message.game;
  store.dispatch(setCurrentGame(game));
}
