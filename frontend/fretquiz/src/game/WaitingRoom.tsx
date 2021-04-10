import React from 'react';
import { useSelector } from 'react-redux';
import { StartGameButton } from './StartGameButton';
import { selectGameId, selectReadyToStart } from './gameSlice';
import { PlayerList } from './PlayerList';

export function GameLobby() {
  const gameId = useSelector(selectGameId);
  const readyToStart = useSelector(selectReadyToStart);

  if (gameId == null) {
    return null;
  }

  return (
    <div className="GameLobby">
      <h2>Game Lobby</h2>
      <h4>Game Id</h4>
      <p>{gameId}</p>
      <PlayerList />
      {readyToStart && <StartGameButton />}
    </div>
  );
}
