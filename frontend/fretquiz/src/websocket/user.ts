import { store } from '../store';
import { ws } from './socket';
import { FlashMessage, LoggedIn } from './response';
import { setUser } from "../user/userSlice";
import { login } from './request';

export function handleFlashMessage(message: FlashMessage) {
  console.log('flash message received: ' + JSON.stringify(message.message));
}

export function sendLogin(name: string) {
  ws.send(JSON.stringify(login(name)));
}

export function handleLogin(message: LoggedIn) {
  const user = message.user;
  store.dispatch(setUser(user));
}
