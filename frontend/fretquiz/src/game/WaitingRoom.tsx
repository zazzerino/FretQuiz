import React from 'react';
import { useSelector } from 'react-redux';
import { StartGameButton } from './StartGameButton';
import { selectGameId, selectReadyToStart } from './gameSlice';
import { PlayerList } from './PlayerList';
import { makeStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';
import { Settings } from './Settings';

const useStyles = makeStyles({
  root: {
    '& > *': {
      marginTop: '1rem',
    }
  }
});

export function WaitingRoom() {
  const styles = useStyles();

  const gameId = useSelector(selectGameId);
  const readyToStart = useSelector(selectReadyToStart);

  if (gameId == null) {
    return null;
  }

  return (
    <div className={styles.root}>
      <Typography variant='h2'>
        Waiting Room
      </Typography>
      <Typography variant="h6">
        Game Id
      </Typography>
      <Typography>
        {gameId}
      </Typography>
      <PlayerList />
      <Settings />
      {readyToStart && <StartGameButton />}
    </div>
  );
}
