import { login } from '../websocket/request';
import type { FlashMessage, LoggedIn } from '../websocket/response';
import { ws } from '../websocket/websocket';
import { user } from '../stores';

export function handleFlashMessage(message: FlashMessage) {
  console.log('message received: ' + message.message);
}

export function sendLogin(name: string) {
  ws.send(JSON.stringify(login(name)));
}

export function handleLogin(message: LoggedIn) {
  user.set(message.user);
}
