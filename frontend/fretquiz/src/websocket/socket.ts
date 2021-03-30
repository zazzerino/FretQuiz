import { 
  Response, LoggedIn, GameCreated, GameIds, GameJoined, GameUpdated, RoundStarted, GuessResult, FlashMessage
} from './response';
import { handleFlashMessage, handleLogin } from './user';
import { 
  handleGameIds, handleGameCreated, handleGameJoined, handleGameUpdated, handleRoundStarted, handleGuessResult 
} from './game';

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
  const message = JSON.parse(event.data) as Response;
  console.log('message received:');
  console.log(message);

  switch (message.type) {
    case 'FLASH_MESSAGE': return handleFlashMessage(message as FlashMessage);
    case 'LOGGED_IN': return handleLogin(message as LoggedIn);
    case 'GAME_IDS': return handleGameIds(message as GameIds);
    case 'GAME_CREATED': return handleGameCreated(message as GameCreated);
    case 'GAME_JOINED': return handleGameJoined(message as GameJoined);
    case 'GAME_UPDATED': return handleGameUpdated(message as GameUpdated);
    case 'ROUND_STARTED': return handleRoundStarted(message as RoundStarted);
    case 'GUESS_RESULT': return handleGuessResult(message as GuessResult);
  }
}
