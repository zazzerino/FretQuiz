import { loginRequest } from '../websocket/request';
import type { LoggedInResponse } from '../websocket/response';
import { ws } from '../websocket/websocket';
import { user } from '../stores';

export function sendLogin(name: string) {
  const request = loginRequest(name);
  ws.send(JSON.stringify(request));
}

export function handleLogin(message: LoggedInResponse) {
  user.set(message.user);
}
