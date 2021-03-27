import { store } from '../app/store';
import { GameCreatedResponse, GameIdsResponse, GameJoinedResponse, GameUpdatedResponse, GuessResultResponse, RoundStartedResponse } from './response';
import { setClickedFret, setCurrentGame, setGameIds, setGuessResult } from "../features/game/gameSlice";
import { 
  createGameRequest, getGameIdsRequest, joinGameRequest, playerGuessRequest, Request, startGameRequest, nextRoundRequest
} from './request';
import { ClientGuess } from '../features/game/types';
import { ws } from './socket';

// send requests

function sendString(request: Request) {
  ws.send(JSON.stringify(request));
}

export const sendCreateGame = () => sendString(createGameRequest());

export const sendGetGameIds = () => sendString(getGameIdsRequest());

export const sendGuess = (clientGuess: ClientGuess) => {
  sendString(playerGuessRequest(clientGuess))
};

export const sendJoinGame = (gameId: string, userId: string) => {
  sendString(joinGameRequest(gameId, userId));
};

export const sendStartGame = (gameId: string) => {
  sendString(startGameRequest(gameId))
};

export const sendNextRound = (gameId: string, playerId: string) => {
  sendString(nextRoundRequest(gameId, playerId));
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
  const isCorrect = message.isCorrect;
  store.dispatch(setGuessResult(isCorrect));
}

export function handleGameUpdated(message: GameUpdatedResponse) {
  const game = message.game;
  store.dispatch(setCurrentGame(game));
}

export function handleRoundStarted(message: RoundStartedResponse) {
  const game = message.game;
  store.dispatch(setCurrentGame(game));
}
