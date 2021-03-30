import { store } from '../app/store';
import { GameCreated, GameIds, GameJoined, GameUpdated, GuessResult, RoundStarted } from './response';
import { setClickedFret, setCurrentGame, setGameIds, setGuess } from "../features/game/gameSlice";
import { 
  createGame, getGameIds, joinGame, playerGuess, startGame, nextRound
} from './request';
import { ClientGuess } from '../features/game/types';
import { ws } from './websocket';

// game request senders

export function sendCreateGame() {
  ws.send(JSON.stringify(createGame()));
}

export function sendGetGameIds() {
  ws.send(JSON.stringify(getGameIds()));
}

export const sendGuess = (clientGuess: ClientGuess) => {
  ws.send(JSON.stringify(playerGuess(clientGuess)));
}

export const sendJoinGame = (gameId: string, userId: string) => {
  ws.send(JSON.stringify(joinGame(gameId, userId)));
}

export const sendStartGame = (gameId: string) => {
  ws.send(JSON.stringify(startGame(gameId)));
}

export const sendNextRound = (gameId: string, playerId: string) => {
  ws.send(JSON.stringify(nextRound(gameId, playerId)));
}

// handle responses

export function handleGameCreated(message: GameCreated) {
  const game = message.game;
  store.dispatch(setCurrentGame(game));
}

export function handleGameIds(message: GameIds) {
  const gameIds = message.gameIds;
  store.dispatch(setGameIds(gameIds));
}

export function handleGameJoined(message: GameJoined) {
  const game = message.game;
  store.dispatch(setCurrentGame(game));
}

export function handleGuessResult(message: GuessResult) {
  store.dispatch(setGuess(message.guess));
}

export function handleGameUpdated(message: GameUpdated) {
  const game = message.game;
  store.dispatch(setCurrentGame(game));
}

export function handleRoundStarted(message: RoundStarted) {
  const game = message.game;
  store.dispatch(setClickedFret(null));
  store.dispatch(setGuess(null));
  store.dispatch(setCurrentGame(game));
}
