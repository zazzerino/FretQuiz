import React from 'react';
import { CreateGameButton } from '../game/CreateGameButton';
import { GameListBox } from '../game/GameListBox';

export function Games() {
  return (
    <div className="Games">
      <GameListBox />
      <CreateGameButton />
    </div>
  );
}
