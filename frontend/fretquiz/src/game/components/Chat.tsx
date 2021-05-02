import React from 'react';
import Paper from '@material-ui/core/Paper';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import makeStyles from '@material-ui/core/styles/makeStyles';
import Typography from '@material-ui/core/Typography';
import TextField from '@material-ui/core/TextField';

const useStyles = makeStyles({
  root: {
    width: '80%',
    margin: 'auto',
    marginTop: '2rem',
  }
});

export function Chat() {
  const classes = useStyles();

  return (
    <Paper
      className={classes.root}
      variant="outlined"
    >
      <Typography variant="h6">
        Chat
      </Typography>
      <List>
        <ListItem>hello</ListItem>
      </List>
      <TextField label="Type your message..." variant="outlined" />
    </Paper>
  );
}
