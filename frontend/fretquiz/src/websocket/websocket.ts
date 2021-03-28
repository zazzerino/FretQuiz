import { handleGameIds } from './game';
import type { Response } from './response';
import type { LoggedInResponse, GameIdsResponse } from './response';
import { handleLogin } from './user';

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
  console.log('message received: ' + JSON.stringify(message));

  switch (message.type) {
    case 'LOGGED_IN':
      return handleLogin(message as LoggedInResponse);

    case 'GAME_IDS':
      return handleGameIds(message as GameIdsResponse);
  }

  // switch (message.type) {
  //   case 'LOGGED_IN': return handleLogin(message as LoggedInResponse);
  //   case 'GAME_IDS': return handleGameIds(message as GameIdsResponse);
  //   case 'GAME_CREATED': return handleGameCreated(message as GameCreatedResponse);
  //   case 'GAME_JOINED': return handleGameJoined(message as GameJoinedResponse);
  //   case 'GAME_UPDATED': return handleGameUpdated(message as GameUpdatedResponse);
  //   case 'ROUND_STARTED': return handleRoundStarted(message as RoundStartedResponse);
  //   case 'GUESS_RESULT': return handleGuessResult(message as GuessResultResponse);
  // }
}
