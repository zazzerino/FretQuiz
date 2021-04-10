import React from 'react';
import { useSelector } from 'react-redux';
import { useHistory } from 'react-router';
import { sendStartGame } from '../../websocket/game';
import { selectGameId } from '../gameSlice';
import { AccidentalSelect } from './AccidentalSelect';
import { StringSelect } from './StringSelect';

function GameLink() {
  const history = useHistory();
  const gameId = useSelector(selectGameId);

  if (gameId == null) {
    return null;
  }

  const onClick = () => {
    sendStartGame(gameId);
    history.push('/game');
  }

  return (
    <button onClick={onClick}>
      Start Playing
    </button>
  );
}

export function Settings() {
  return (
    <div className="Settings">
      <h2>Settings</h2>
      <StringSelect />
      <AccidentalSelect />
      <GameLink />
    </div>
  );
}
