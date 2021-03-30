import { store } from '../app/store';
import { GameCreatedResponse, GameIdsResponse, GameJoinedResponse, GameUpdatedResponse, GuessResultResponse, RoundStartedResponse } from './response';
import { setClickedFret, setCurrentGame, setGameIds, setGuess } from "../features/game/gameSlice";
import { 
  createGameRequest, getGameIdsRequest, joinGameRequest, playerGuessRequest, Request, startGameRequest, nextRoundRequest
} from './request';
import { ClientGuess } from '../features/game/types';
import { ws } from './socket';

// send requests

function sendRequest(request: Request) {
  ws.send(JSON.stringify(request));
}

export const sendCreateGame = () => sendRequest(createGameRequest());

export const sendGetGameIds = () => sendRequest(getGameIdsRequest());

export const sendGuess = (clientGuess: ClientGuess) => {
  sendRequest(playerGuessRequest(clientGuess))
};

export const sendJoinGame = (gameId: string, userId: string) => {
  sendRequest(joinGameRequest(gameId, userId));
};

export const sendStartGame = (gameId: string) => {
  sendRequest(startGameRequest(gameId))
};

export const sendNextRound = (gameId: string, playerId: string) => {
  sendRequest(nextRoundRequest(gameId, playerId));
};

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

export function handleGuessResult(message: GuessResultResponse) {
  store.dispatch(setGuess(message.guess));
}

export function handleGameUpdated(message: GameUpdatedResponse) {
  const game = message.game;
  store.dispatch(setCurrentGame(game));
}

export function handleRoundStarted(message: RoundStartedResponse) {
  const game = message.game;
  store.dispatch(setClickedFret(null));
  store.dispatch(setGuess(null));
  store.dispatch(setCurrentGame(game));
}
