import React from 'react';
import { useSelector } from 'react-redux';
import { selectUserId } from '../../user/userSlice';
import { sendNextRound } from '../../websocket/game';
import { selectGameId } from '../gameSlice';

export function NextRoundButton() {
  const userId = useSelector(selectUserId);
  const gameId = useSelector(selectGameId);

  const onClick = () => gameId && userId && sendNextRound(gameId, userId);

  return (
    <div className="NextRoundButton">
      <button onClick={onClick}>
        Next Round
      </button>
    </div>
  );
}
