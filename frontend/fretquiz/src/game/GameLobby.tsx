import React from 'react';
import { useSelector } from 'react-redux';
import { selectGameId } from './gameSlice';
import { PlayerList } from './PlayerList';

export function GameLobby() {
  const gameId = useSelector(selectGameId);

  if (gameId == null) {
    return null;
  }

  return (
    <div className="GameLobby">
      <h2>Game Lobby</h2>
      <p>{`game id: ${gameId}`}</p>
      <PlayerList />
    </div>
  );
}
