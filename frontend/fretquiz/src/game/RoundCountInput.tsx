import React from 'react';
import TextField from '@material-ui/core/TextField';
import { useSelector } from 'react-redux';
import { selectGameId, selectRoundCount } from './gameSlice';
import { sendSetRoundCount } from '../websocket/game';

export function RoundCountInput() {
  const gameId = useSelector(selectGameId);
  const roundCount = useSelector(selectRoundCount);

  if (gameId == null) {
    return null;
  }

  const onChange = (event: any) => {
    sendSetRoundCount(gameId, parseInt(event.target.value));
  }

  return (
    <TextField
      label="Rounds"
      type="number"
      value={roundCount}
      onChange={onChange}
    />
  );
}
