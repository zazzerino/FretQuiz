import { Message } from './message';
import { handleLogin } from './user';
import { handleGetGames, handleGameCreated, gameJoined } from './game';

const WS_URL = 'ws://localhost:8080/ws';

export const ws = new WebSocket(WS_URL);

export function initWebSocket() {
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

  switch (message.type) {
    case 'LOGIN_OK':
      return handleLogin(message);

    case 'GAME_CREATED':
      return handleGameCreated(message);

    case 'GET_GAME_IDS':
      return handleGetGames(message);

    case 'GAME_JOINED':
      return gameJoined(message);
  }
}
