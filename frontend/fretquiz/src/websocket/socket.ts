import { Response, LoginOkResponse, GameCreatedResponse, GetGameIdsResponse, GameJoinedResponse } from './response';
import { handleLogin } from './user';
import { handleGetGameIds, handleGameCreated, handleGameJoined } from './game';

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

/**
 * Receives incoming messages and dispatches them to the correct handler depending on the message type.
 */
function onMessage(event: MessageEvent) {
  // parse the message as a generic Response so we can get the type
  const message = JSON.parse(event.data) as Response;
  console.log('message received: ' + JSON.stringify(message));

  switch (message.type) {
    case 'LOGIN_OK': return handleLogin(message as LoginOkResponse);
    case 'GAME_CREATED': return handleGameCreated(message as GameCreatedResponse);
    case 'GET_GAME_IDS': return handleGetGameIds(message as GetGameIdsResponse);
    case 'GAME_JOINED': return handleGameJoined(message as GameJoinedResponse);
  }
}
