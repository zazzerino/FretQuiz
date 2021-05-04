import React from 'react';
import Paper from '@material-ui/core/Paper';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import makeStyles from '@material-ui/core/styles/makeStyles';
import Typography from '@material-ui/core/Typography';
import TextField from '@material-ui/core/TextField';
import { useSelector } from 'react-redux';
import { selectChatMessages, selectGameId } from '../gameSlice';
import { sendChatMessage } from '../../websocket/game';

const useStyles = makeStyles({
  root: {
    width: '80%',
    margin: 'auto',
    marginTop: '2rem',
  },
  list: {
    height: '150px',
    maxHeight: '150px',
    overflow: 'auto',
  },
  input: {
    marginTop: '1rem',
  }
});

export function Chat() {
  const classes = useStyles();
  const gameId = useSelector(selectGameId);
  const messages = useSelector(selectChatMessages);
  const [typedMessage, setTypedMessage] = React.useState('');
  const listRef: any = React.useRef(null);

  React.useEffect(() => {
    if (listRef.current) {
      listRef.current.scrollTop = listRef.current.scrollHeight;
    }
  }, [messages]);

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
      <List
        ref={listRef}
        className={classes.list}>
        {messages.map((message, index) => {
          return (
            <ListItem key={index}>
              {`${message.user.name}: ${message.text}`}
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
        onKeyPress={event => {
          if (event.key === 'Enter') {
            sendChatMessage(gameId, typedMessage);
            setTypedMessage('');
          }
        }}
      />
    </Paper>
  );
}
