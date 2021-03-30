import { store } from '../app/store';
import { ws } from './websocket';
import { FlashMessage, LoggedIn } from './response';
import { setUser } from "../features/user/userSlice";
import { login } from './request';

export function handleFlashMessage(message: FlashMessage) {
  console.log('message received: ' + JSON.stringify(message.message));
}

export function sendLogin(name: string) {
  ws.send(JSON.stringify(login(name)));
}

export function handleLogin(message: LoggedIn) {
  const user = message.user;
  store.dispatch(setUser(user));
}
