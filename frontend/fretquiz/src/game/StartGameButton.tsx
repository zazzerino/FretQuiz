import React from 'react';
import Button from '@material-ui/core/Button';
import { useSelector } from 'react-redux';
import { sendStartCountdown } from '../websocket/game';
import { selectGameId } from './gameSlice';

export function StartGameButton() {
  const gameId = useSelector(selectGameId);

  return (
    <div className="StartGameButton">
      <Button
        variant="contained"
        color="primary"
        onClick={() => gameId && sendStartCountdown(gameId)}
      >
        Start Game
      </Button>
    </div>
  );
}
