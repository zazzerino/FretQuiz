import * as React from 'react';
import { sendLogin } from '../../websocket/socket';

export function Login() {
  const [name, setName] = React.useState('');

  return (
    <div className="Login">
      <h4>Login</h4>
      <input
        id="name-input"
        value={name}
        onChange={e => setName(e.target.value)}
        placeholder="Type your username"
      />
      <button
        id="login-button"
        onClick={() => sendLogin(name)}
      >
        Login
      </button>
    </div>
  );
}
