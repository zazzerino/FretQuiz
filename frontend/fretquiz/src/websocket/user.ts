import { login } from '../websocket/request';
import type { LoggedIn } from '../websocket/response';
import { ws } from '../websocket/websocket';
import { user } from '../stores';

export function sendLogin(name: string) {
  ws.send(JSON.stringify(login(name)));
}

export function handleLogin(message: LoggedIn) {
  user.set(message.user);
}
