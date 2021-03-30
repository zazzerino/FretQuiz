import * as React from 'react';
import { useSelector } from 'react-redux';
import { selectCurrentGame } from '../features/game/gameSlice';
import { selectUser } from '../features/user/userSlice';

export function Footer() {
  const user = useSelector(selectUser);
  const game = useSelector(selectCurrentGame);

  return (
    <div className="Footer">
      <p>{`user: ${JSON.stringify(user)}`}</p>
      {
        game ?
          <p>{`game: ${JSON.stringify(game)}`}</p> :
          null
      }
    </div>
  );
}
