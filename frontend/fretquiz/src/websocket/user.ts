import { store } from '../app/store';
import { ws } from './socket';
import { Message, LoginMessage } from './message';
import { setUser } from "../features/user/userSlice";

export function sendLogin(name: string) {
  const message = JSON.stringify({
    type: 'LOGIN',
    name
  });

  ws.send(message);
}

export function handleLogin(msg: Message) {
  const message = msg as LoginMessage;
  const user = message.user;

  store.dispatch(setUser(user));
}
