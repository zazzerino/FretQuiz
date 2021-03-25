import { store } from '../app/store';
import { LoginOkResponse } from './response';
import { setUser } from "../features/user/userSlice";
import { makeSender, loginRequest } from './request';

export const sendLogin = (name: string) => makeSender(loginRequest(name))();

export function handleLogin(message: LoginOkResponse) {
  const user = message.user;
  store.dispatch(setUser(user));
}
