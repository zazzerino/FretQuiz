import * as response from './response';
import { handleFlash, handleLogin } from './user';
import { 
  handleGameIds, handleGameCreated, handleGameJoined, 
  handleGameUpdated, handleRoundStarted, handleGuessResult, handleGameOver, handleGameInfos 
} from './game';

const WS_URL = 'ws://localhost:3000/ws';

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
  const message = JSON.parse(event.data) as response.Response;
  console.log('message received:');
  console.log(message);

  switch (message.type) {
    case 'FLASH':
      return handleFlash(message as response.Flash);
    case 'LOGGED_IN':
      return handleLogin(message as response.LoggedIn);
    case 'GAME_IDS':
      return handleGameIds(message as response.GameIds);
    case 'GAME_INFOS':
      return handleGameInfos(message as response.GameInfos);
    case 'GAME_CREATED':
      return handleGameCreated(message as response.GameCreated);
    case 'GAME_JOINED':
      return handleGameJoined(message as response.GameJoined);
    case 'GAME_UPDATED':
      return handleGameUpdated(message as response.GameUpdated);
    case 'ROUND_STARTED':
      return handleRoundStarted(message as response.RoundStarted);
    case 'GUESS_RESULT':
      return handleGuessResult(message as response.GuessResult);
    case 'GAME_OVER':
      return handleGameOver(message as response.GameOver);
  }
}
