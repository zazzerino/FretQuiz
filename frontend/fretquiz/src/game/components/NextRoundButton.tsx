import React from 'react';
import Button from '@material-ui/core/Button';
import { useSelector } from 'react-redux';
import { selectUserId } from '../../user/userSlice';
import { sendStartRoundCountdown } from '../../websocket/game';
import { selectGameId } from '../gameSlice';

export function NextRoundButton() {
  const userId = useSelector(selectUserId);
  const gameId = useSelector(selectGameId);

  if (userId == null || gameId == null) {
    return null;
  }

  return (
    <div className="NextRoundButton">
      <Button
        variant="contained"
        color="primary"
        onClick={() => sendStartRoundCountdown(gameId)}
      >
        Next Round
      </Button>
    </div>
  );
}
