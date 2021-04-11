import React from 'react';
import Typography from '@material-ui/core/Typography';
import { CreateGameButton } from '../game/CreateGameButton';
import { ScoreTable } from '../game/ScoreTable';
import { makeStyles } from '@material-ui/core';

const useStyles = makeStyles({
  root: {
    '& > *': {
      marginTop: '1rem',
    }
  }
});

export function GameOver() {
  const styles = useStyles();

  return (
    <div className={styles.root}>
      <Typography variant="h4">
        Game Over
      </Typography>
      <ScoreTable />
      <CreateGameButton />
    </div>
  );
}
