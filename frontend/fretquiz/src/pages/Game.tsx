import React from 'react';
import { useSelector } from 'react-redux';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import {
  selectGameState, selectIsCountingDown, selectSecondsLeft
} from '../game/gameSlice';
import { WaitingRoom } from '../game/components/WaitingRoom';
import { GameCanvas } from '../game/components/GameCanvas';
import { GameOver } from '../game/components/GameOver';
import { CreateGameButton } from '../game/components/CreateGameButton';

const useStyles = makeStyles({
  createGameButton: {
    marginTop: '2rem'
  }
});

export function Game() {
  const styles = useStyles();
  const state = useSelector(selectGameState);

  const isCountingDown = useSelector(selectIsCountingDown);
  const secondsLeft = useSelector(selectSecondsLeft);

  if (isCountingDown) {
    return (
      <Typography
        variant="body1"
        style={{ marginTop: "2rem" }}
      >
        Starting in {secondsLeft}...
      </Typography>
    );
  }

  switch (state) {
    case null:
    case undefined:
      return (
        <div className={styles.createGameButton}>
          <CreateGameButton />
        </div>
      );
    case 'INIT':
      return <WaitingRoom />
    case 'GAME_OVER':
      return <GameOver />
    case 'PLAYING':
    case 'ROUND_OVER':
      return <GameCanvas />
  }
}
