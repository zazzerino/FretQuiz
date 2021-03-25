import { store } from '../app/store';
import { GameCreatedResponse, GameIdsResponse, GameJoinedResponse, GameUpdatedResponse } from './response';
import { setCurrentGame, setGameIds } from "../features/game/gameSlice";
import { 
  makeSender, createGameRequest, getGameIdsRequest, joinGameRequest, newGuessRequest, startGameRequest 
} from './request';
import { NewGuess } from '../features/game/types';

// send requests

export const sendCreateGame = makeSender(createGameRequest());

export const sendGetGameIds = makeSender(getGameIdsRequest());

export const sendGuess = (guess: NewGuess) => makeSender(newGuessRequest(guess))();

export const sendJoinGame = (gameId: string, userId: string) => {
  makeSender(joinGameRequest(gameId, userId))();
};

export const sendStartGame = (gameId: string) => {
  makeSender(startGameRequest(gameId))();
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
