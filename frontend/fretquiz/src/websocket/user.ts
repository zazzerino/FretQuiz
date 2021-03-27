import { store } from '../app/store';
import { ws } from './socket';
import { LoggedInResponse, LoggedOutResponse } from './response';
import { setUser } from "../features/user/userSlice";
import { loginRequest } from './request';

export function sendLogin(name: string) {
  ws.send(JSON.stringify(loginRequest(name)));
}

export function handleLogin(message: LoggedInResponse) {
  const user = message.user;
  store.dispatch(setUser(user));
}
