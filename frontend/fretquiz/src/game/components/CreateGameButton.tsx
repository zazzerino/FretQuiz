import React from 'react';
import Button from '@material-ui/core/Button';
import { sendCreateGame } from '../../websocket/game';

export function CreateGameButton() {
  return (
    <Button
      variant="contained"
      color="primary"
      onClick={sendCreateGame}
    >
      New Game
    </Button>
  );
}
