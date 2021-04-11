import React from 'react';
import { CreateGameButton } from '../game/CreateGameButton';
import { ScoreTable } from '../game/ScoreTable';

export function GameOver() {
  return (
    <div className="GameOver">
      <h2>Game Over</h2>
      <ScoreTable />
      <CreateGameButton />
    </div>
  );
}
