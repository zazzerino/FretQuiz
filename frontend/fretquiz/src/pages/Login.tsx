import React from 'react';
import Button from '@material-ui/core/Button';
import Input from '@material-ui/core/Input';
import Typography from '@material-ui/core/Typography';
import { sendLogin } from "../websocket/user";
import { makeStyles } from '@material-ui/core';

const useStyles = makeStyles({
  root: {
    '& > *': {
      marginTop: '1rem',
    }
  }
});

export function Login() {
  const [name, setName] = React.useState('');
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <Typography variant="h2">
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
      >
        Login
      </Button>
    </div>
  );
}
