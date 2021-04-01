import React from 'react';
import { sendCreateGame } from '../../websocket/game';

export function CreateGameButton() {
  return (
    <button onClick={sendCreateGame}>
      Create Game
    </button>
  );
}
