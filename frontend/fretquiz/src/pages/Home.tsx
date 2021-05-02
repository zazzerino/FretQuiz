import React from 'react';
import Typography from '@material-ui/core/Typography';
import { CreateGameButton } from '../game/components/CreateGameButton';
import { GamesTable } from '../game/components/GamesTable';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles({
  root: {
    '& > *': {
      marginTop: '1rem',
    }
  }
});

export function Home() {
  const styles = useStyles();

  return (
    <div className={styles.root}>
      <Typography variant="h2">
        FretQuiz
      </Typography>
      <GamesTable />
      <CreateGameButton />
    </div>
  );
}
