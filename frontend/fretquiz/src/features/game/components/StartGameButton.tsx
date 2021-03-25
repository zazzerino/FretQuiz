import * as React from 'react';
import { useSelector } from 'react-redux';
import { sendStartGame } from '../../../websocket/game';
import { selectGameId } from '../gameSlice';

export function StartGameButton() {
  const gameId = useSelector(selectGameId);

  return (
    <div className="StartGameButton">
      <button onClick={() => {
        if (gameId) {
          sendStartGame(gameId)
        } else {
          console.log('must join game first');
        }
      }}>
        Start Game
      </button>
    </div>
  );
}
