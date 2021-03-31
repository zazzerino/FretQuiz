import React from 'react';
import { CreateGameButton } from '../game/components/CreateGameButton';
import { GameList } from '../game/components/GameList';

export function Games() {
  return (
    <div className="Games">
      <GameList />
      <CreateGameButton />
    </div>
  );
}
