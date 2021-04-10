import React from 'react';
import Button from '@material-ui/core/Button';
import Input from '@material-ui/core/Input';
import { sendLogin } from "../websocket/user";
import { Typography } from '@material-ui/core';

export function Login() {
  const [name, setName] = React.useState('');

  return (
    <div className="Login">
      <Typography 
        variant="h4"
        style={{margin: "1rem"}}
      >
        Login
      </Typography>
      <Input
        id="name-input"
        value={name}
        onChange={event => setName(event.target.value)}
        placeholder="Type your username"
      />
      <br />
      <Button
        id="login-button"
        variant="contained"
        color="primary"
        onClick={() => sendLogin(name)}
        style={{marginTop: "2rem"}}
      >
        Login
      </Button>
    </div>
  );
}
