import React from 'react';
import { CreateGameButton } from '../game/components/CreateGameButton';
import { GameListBox } from '../game/GameListBox';

export function Games() {
  return (
    <div className="Games">
      <GameListBox />
      <CreateGameButton />
    </div>
  );
}
