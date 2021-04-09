import React from 'react';
import { useSelector } from 'react-redux';
import { selectCurrentGame } from '../game/gameSlice';
import { selectUser } from '../user/userSlice';
import "./Footer.css";

function UserDisplay() {
  const user = useSelector(selectUser);
  return <p>{`user: ${user.name}`}</p>
}

function GameDisplay() {
  const game = useSelector(selectCurrentGame);

  if (game == null) {
    return null;
  }

  return <p>{`game: ${game.id}`}</p>;
}

export function Footer() {
  return (
    <div className="Footer">
      <UserDisplay />
      <GameDisplay />
    </div>
  );
}
