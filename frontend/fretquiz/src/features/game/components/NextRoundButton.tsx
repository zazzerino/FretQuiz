import * as React from 'react';
import { useSelector } from 'react-redux';
import { sendNextRound } from '../../../websocket/game';
import { selectUserId } from '../../user/userSlice';
import { selectGameId } from '../gameSlice';

export function NextRoundButton() {
  const userId = useSelector(selectUserId);
  const gameId = useSelector(selectGameId);

  const onClick = () => {
    if (gameId) {
      sendNextRound(gameId, userId)
    }
  };

  return (
    <div className="NextRoundButton">
      <button onClick={onClick}>
        Next Round
      </button>
    </div>
  );
}
