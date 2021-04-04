import React from 'react';
import { useSelector } from 'react-redux';
import { selectCurrentGame } from '../game/gameSlice';
import { selectUser } from '../user/userSlice';

function UserDisplay() {
  const user = useSelector(selectUser);
  return <p>{`user: ${user.name}`}</p>
}

function GameDisplay() {
  const game = useSelector(selectCurrentGame);

  if (!game) {
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
