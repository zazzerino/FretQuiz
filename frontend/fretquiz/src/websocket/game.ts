import { store } from '../app/store';
import { ws } from './socket';
import { 
  Message, GameCreatedMessage, GetGameIdsMessage, GameJoinedMessage 
} from './message';
import { setGame, setGameIds, NewGuess } from "../features/game/gameSlice";

export function sendCreateGame() {
  const message = JSON.stringify({
    type: 'CREATE_GAME'
  });

  ws.send(message);
}

export function handleGameCreated(msg: Message) {
  const message = msg as GameCreatedMessage;
  const game = message.game;

  store.dispatch(setGame(game));
}

export function sendGetGameIds() {
  const message = JSON.stringify({
    type: 'GET_GAME_IDS'
  });

  ws.send(message);
}

export function handleGetGames(msg: Message) {
  const message = msg as GetGameIdsMessage;
  const gameIds = message.gameIds;

  store.dispatch(setGameIds(gameIds));
}

export function sendGuess(guess: NewGuess) {
  const message = JSON.stringify({
    type: 'GUESS',
    guess
  });

  ws.send(message);
}

export function sendJoinGame(userId: string, gameId: string) {
  const message = JSON.stringify({
    type: 'JOIN_GAME',
    userId,
    gameId
  });

  ws.send(message);
}

export function gameJoined(msg: Message) {
  const message = msg as GameJoinedMessage;
  const game = message.game;

  store.dispatch(setGame(game));
}
