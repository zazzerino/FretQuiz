import { store } from "../app/store";
import { Game, setCurrentGame, setGames } from "../features/game/gameSlice";
import { User, setUser } from "../features/user/userSlice";

let ws: WebSocket;

const WS_URL = 'ws://localhost:8080/ws';

type MessageType = 'BROADCAST' | 'LOGIN_OK' | 'GAME_CREATED' | 'GET_GAMES';

interface Message {
  type: MessageType
}

interface LoginMessage extends Message {
  type: 'LOGIN_OK',
  user: User
}

interface GameCreatedMessage extends Message {
  type: 'GAME_CREATED',
  game: Game
}

interface GetGamesMessage extends Message {
  type: 'GET_GAMES',
  games: Game[]
}

export function initWebSocket() {
  ws = new WebSocket(WS_URL);

  ws.onopen = onOpen;
  ws.onmessage = onMessage;
  ws.onclose = onClose;
  ws.onerror = onError;
}

function onOpen() {
  console.log('websocket connection established');
}

function onClose() {
  console.log('websocket connection closed');
}

function onError() {
  console.log('there was an error');
}

function onMessage(event: MessageEvent) {
  const message = JSON.parse(event.data) as Message;
  console.log('message received: ' + JSON.stringify(message));

  handleMessage(message);
}

function handleMessage(message: Message) {
  switch (message.type) {
    case 'LOGIN_OK':
      handleLogin(message as LoginMessage);
      break;

    case 'GAME_CREATED':
      handleGameCreated(message as GameCreatedMessage);
      break;

    case 'GET_GAMES':
      handleGetGames(message as GetGamesMessage);
      break;
  }
}

export function sendLogin(name: string) {
  const message = JSON.stringify({
    type: 'LOGIN',
    name
  });

  ws.send(message);
}

export function handleLogin(message: LoginMessage) {
  const user = message.user;
  store.dispatch(setUser(user));
}

export function sendCreateGame() {
  const message = JSON.stringify({
    type: 'CREATE_GAME'
  });

  ws.send(message);
}

export function sendGetGames() {
  const message = JSON.stringify({
    type: 'GET_GAMES'
  });

  ws.send(message);
}

export function handleGameCreated(message: GameCreatedMessage) {
  const game = message.game;
  store.dispatch(setCurrentGame(game));
}

export function handleGetGames(message: GetGamesMessage) {
  const games = message.games;
  store.dispatch(setGames(games));
}
