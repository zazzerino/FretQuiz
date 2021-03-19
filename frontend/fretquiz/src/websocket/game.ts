import { store } from '../app/store';
import { ws } from './socket';
import { Message, GameCreatedMessage, GetGamesMessage } from './message';
import { setCurrentGame, setGames, NewGuess } from "../features/game/gameSlice";

export function sendCreateGame() {
  const message = JSON.stringify({
    type: 'CREATE_GAME'
  });

  ws.send(message);
}

export function handleGameCreated(msg: Message) {
  const message = msg as GameCreatedMessage;
  const game = message.game;

  store.dispatch(setCurrentGame(game));
}

export function sendGetGames() {
  const message = JSON.stringify({
    type: 'GET_GAMES'
  });

  ws.send(message);
}

export function handleGetGames(msg: Message) {
  const message = msg as GetGamesMessage;
  const games = message.games;

  store.dispatch(setGames(games));
}

export function sendGuess(guess: NewGuess) {
  const message = JSON.stringify({
    type: 'GUESS',
    guess
  });

  ws.send(message);
}
