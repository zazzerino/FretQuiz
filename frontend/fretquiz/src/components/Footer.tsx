import * as React from 'react';
import { useSelector } from 'react-redux';
import { selectCurrentGame } from '../game/gameSlice';
import { selectUser } from '../user/userSlice';

function UserDisplay() {
  const user = useSelector(selectUser);
  return <p>{`user: ${JSON.stringify(user)}`}</p>
}

function GameDisplay() {
  const game = useSelector(selectCurrentGame);

  if (game) {
    return <p>{`game: ${JSON.stringify(game)}`}</p>
  }

  return null;
}

export function Footer() {
  return (
    <div className="Footer">
      <UserDisplay />
      <GameDisplay />
    </div>
  );
}
