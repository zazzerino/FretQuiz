import { store } from '../app/store';
import { ws } from './socket';
import { LoginOkResponse } from './response';
import { setUser } from "../features/user/userSlice";
import { loginRequest } from './request';

export function sendLogin(name: string) {
  const message = JSON.stringify(loginRequest(name));
  ws.send(message);
}

export function handleLogin(message: LoginOkResponse) {
  const user = message.user;
  store.dispatch(setUser(user));
}
