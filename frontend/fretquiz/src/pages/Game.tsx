import React from 'react';
import { useSelector } from 'react-redux';
import { selectGameState } from '../game/gameSlice';
import { WaitingRoom } from '../game/WaitingRoom';
import { GameCanvas } from '../game/GameCanvas';
import { GameOver } from '../game/GameOver';
import { CreateGameButton } from '../game/CreateGameButton';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles({
  createGameBtn: {
    marginTop: '2rem'
  }
});

export function Game() {
  const styles = useStyles();
  const state = useSelector(selectGameState);

  if (state == null) {
    return (
      <div className={styles.createGameBtn}>
        <CreateGameButton />
      </div>
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
