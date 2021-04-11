import React from 'react';
import { useSelector } from 'react-redux';
import { StartGameButton } from './StartGameButton';
import { selectGameId, selectReadyToStart } from './gameSlice';
import { PlayerList } from './PlayerList';
import { makeStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles({
  root: {}
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
      <Typography variant='h4'>
        Waiting Room
      </Typography>
      <Typography variant="h6">
        Game Id
      </Typography>
      <Typography>{gameId}</Typography>
      <PlayerList />
      {readyToStart && <StartGameButton />}
    </div>
  );
}
