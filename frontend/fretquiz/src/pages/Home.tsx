import { Typography } from '@material-ui/core';
import React from 'react';
import { CreateGameButton } from '../game/CreateGameButton';
import { GameListBox } from '../game/GameListBox';

export function Home() {
  return (
    <div className="Home">
      <Typography
        variant="h2"
        style={{ margin: "1rem" }}
      >
        FretQuiz
      </Typography>
      <GameListBox />
      <CreateGameButton />
    </div>
  );
}
