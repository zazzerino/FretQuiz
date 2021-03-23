import { Response, LoginOkResponse, GameCreatedResponse, GetGameIdsResponse, GameJoinedResponse } from './response';
import { handleLogin } from './user';
import { handleGetGameIds, handleGameCreated, handleGameJoined } from './game';

/**
 * The url to connect to with the websocket.
 */
const WS_URL = 'ws://localhost:8080/ws';

/**
 * The WebSocket connection object.
 */
export const ws = new WebSocket(WS_URL);

/**
 * This function sets up the websocket event handlers.
 */
export function initWebSocket() {
  ws.onopen = onOpen;
  ws.onmessage = onMessage;
  ws.onclose = onClose;
  ws.onerror = onError;
}

/**
 * Called when the websocket connection is established.
 */
function onOpen() {
  console.log('websocket connection established');
}

/**
 * Called when the websocket connection is closed.
 */
function onClose() {
  console.log('websocket connection closed');
}

function onError() {
  console.log('there was an error');
}

/**
 * Receives incoming messages and dispatches them to the correct handler.
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
