import React from 'react';
import Button from '@material-ui/core/Button';
import { useSelector } from 'react-redux';
import { sendStartGame } from '../websocket/game';
import { selectGameId } from './gameSlice';

export function StartGameButton() {
  const gameId = useSelector(selectGameId);

  return (
    <div className="StartGameButton">
      <Button
        variant="contained"
        color="primary"
        onClick={() => gameId && sendStartGame(gameId)}
      >
        Start Game
      </Button>
    </div>
  );
}
