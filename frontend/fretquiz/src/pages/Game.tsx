import React from 'react';
import { useSelector } from 'react-redux';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import { 
  selectGameState, selectIsCountingDown, selectSecondsLeft 
} from '../game/gameSlice';
import { WaitingRoom } from '../game/WaitingRoom';
import { GameCanvas } from '../game/GameCanvas';
import { GameOver } from '../game/GameOver';
import { CreateGameButton } from '../game/CreateGameButton';

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

  if (state == null) {
    return (
      <div className={styles.createGameButton}>
        <CreateGameButton />
      </div>
    );
  }

  if (isCountingDown) {
    return (
      <Typography
        variant="body1"
        style={{marginTop: "2rem"}}
      >
        Starting in {secondsLeft}...
      </Typography>
    );
  }

  return (
    <div className="Game">
      {state === 'INIT' && <WaitingRoom />}
      {(state === 'PLAYING' || state === 'ROUND_OVER') && <GameCanvas />}
      {state === 'GAME_OVER' && <GameOver />}
    </div>
  );
}
