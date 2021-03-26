import { store } from '../app/store';
import { GameCreatedResponse, GameIdsResponse, GameJoinedResponse, GameUpdatedResponse } from './response';
import { setCurrentGame, setGameIds } from "../features/game/gameSlice";
import { 
  createGameRequest, getGameIdsRequest, joinGameRequest, playerGuessRequest, startGameRequest 
} from './request';
import { ClientGuess } from '../features/game/types';
import { ws } from './socket';

// send requests

export function sendCreateGame() {
  ws.send(JSON.stringify(createGameRequest()));
}

export function sendGetGameIds() {
  ws.send(JSON.stringify(getGameIdsRequest()));
}

export function sendGuess(clientGuess: ClientGuess) {
  ws.send(JSON.stringify(playerGuessRequest(clientGuess)));
}

export function sendJoinGame(gameId: string, userId: string) {
  ws.send(JSON.stringify(joinGameRequest(gameId, userId)));
}

export function sendStartGame(gameId: string) {
  ws.send(JSON.stringify(startGameRequest(gameId)));
}

// handle responses

export function handleGameCreated(message: GameCreatedResponse) {
  const game = message.game;
  store.dispatch(setCurrentGame(game));
}

export function handleGameIds(message: GameIdsResponse) {
  const gameIds = message.gameIds;
  store.dispatch(setGameIds(gameIds));
}

export function handleGameJoined(message: GameJoinedResponse) {
  const game = message.game;
  store.dispatch(setCurrentGame(game));
}

export function handleGameUpdated(message: GameUpdatedResponse) {
  const game = message.game;
  store.dispatch(setCurrentGame(game));
}
