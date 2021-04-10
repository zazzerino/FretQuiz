import React from 'react';
import Divider from '@material-ui/core/Divider';
import { Typography } from '@material-ui/core';
import { useSelector } from 'react-redux';
import { selectCurrentGame } from '../game/gameSlice';
import { selectUser } from '../user/userSlice';
import "./Footer.css";

function UserDisplay() {
  const user = useSelector(selectUser);

  return (
    <Typography variant="body2">
      {`user: ${user.name}`}
    </Typography>
  );
}

function GameDisplay() {
  const game = useSelector(selectCurrentGame);

  if (game == null) {
    return null;
  }

  return (
    <Typography variant="body2">
      {`game: ${game.id}`}
    </Typography>
  );
}

export function Footer() {
  return (
    <div className="Footer">
      <Divider />
      <UserDisplay />
      <GameDisplay />
    </div>
  );
}
