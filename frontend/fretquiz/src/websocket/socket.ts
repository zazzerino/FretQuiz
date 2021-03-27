import { 
  Response, LoggedInResponse, GameCreatedResponse, GameIdsResponse, GameJoinedResponse, GameUpdatedResponse, RoundStartedResponse
} from './response';
import { handleLogin } from './user';
import { handleGameIds, handleGameCreated, handleGameJoined, handleGameUpdated, handleRoundStarted } from './game';

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
 * Receives incoming messages and dispatches them to the correct handler.
 */
function onMessage(event: MessageEvent) {
  // parse the message as a generic Response so we can get the type
  const message = JSON.parse(event.data) as Response;
  console.log('message received: ' + JSON.stringify(message));

  switch (message.type) {
    case 'LOGGED_IN': return handleLogin(message as LoggedInResponse);
    case 'GAME_IDS': return handleGameIds(message as GameIdsResponse);
    case 'GAME_CREATED': return handleGameCreated(message as GameCreatedResponse);
    case 'GAME_JOINED': return handleGameJoined(message as GameJoinedResponse);
    case 'GAME_UPDATED': return handleGameUpdated(message as GameUpdatedResponse);
    case 'ROUND_STARTED': return handleRoundStarted(message as RoundStartedResponse);
  }
}
