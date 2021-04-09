import React from 'react';
import { useSelector } from 'react-redux';
import { selectUserId } from '../../user/userSlice';
import { sendNextRound } from '../../websocket/game';
import { selectGameId } from '../gameSlice';

export function NextRoundButton() {
  const userId = useSelector(selectUserId);
  const gameId = useSelector(selectGameId);

  if (!userId || !gameId) {
    throw new Error('userId or gameId is null');
  }

  return (
    <div className="NextRoundButton">
      <button onClick={() => sendNextRound(gameId, userId)}>
        Next Round
      </button>
    </div>
  );
}
