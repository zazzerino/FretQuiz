import React from 'react';
import Paper from '@material-ui/core/Paper';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import makeStyles from '@material-ui/core/styles/makeStyles';
import Typography from '@material-ui/core/Typography';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import { useSelector } from 'react-redux';
import { selectChatMessages, selectGameId } from '../gameSlice';
import { sendChatMessage } from '../../websocket/game';

const useStyles = makeStyles({
  root: {
    width: '80%',
    margin: 'auto',
    marginTop: '2rem',
  },
  input: {
    marginTop: '2rem',
  },
  button: {
    display: 'block',
    margin: 'auto',
  }
});

export function Chat() {
  const classes = useStyles();
  const messages = useSelector(selectChatMessages);
  const gameId = useSelector(selectGameId);

  const [typedMessage, setTypedMessage] = React.useState('');

  if (gameId == null) {
    return null;
  }

  return (
    <Paper
      className={classes.root}
      variant="outlined"
    >
      <Typography variant="h6">
        Chat
      </Typography>
      <List>
        {messages.map((text, index) => {
          return (
            <ListItem key={index}>
              {text}
            </ListItem>
          )
        })}
      </List>
      <TextField
        className={classes.input}
        label="Type your message..."
        variant="outlined"
        value={typedMessage}
        onChange={event => setTypedMessage(event.target.value)}
      />
      <Button
        className={classes.button}
        variant="contained"
        color="primary"
        onClick={() => sendChatMessage(gameId, typedMessage)}
      >
        Send Message
      </Button>
    </Paper>
  );
}
