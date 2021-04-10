import React from 'react';
import { useSelector } from 'react-redux';
import { CreateGameButton } from '../game/components/CreateGameButton';
import { GameListBox } from '../game/GameListBox';
import { selectGameIds } from '../game/gameSlice';

export function Home() {
  return (
    <div className="Home">
      <h2>FretQuiz</h2>
      <GameListBox />
      <CreateGameButton />
    </div>
  );
}
