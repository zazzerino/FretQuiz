import * as React from 'react';
import { useSelector } from 'react-redux';
import { selectGameId } from '../features/game/gameSlice';
import { selectUser } from '../features/user/userSlice';

export function Footer() {
  const user = useSelector(selectUser);
  const { id, name } = user;

  const gameId = useSelector(selectGameId);

  return (
    <div className="Footer">
      <p>{`id: ${id}, name: ${name}`}</p>
      {
        gameId ?
          <p>{`game id: ${gameId}`}</p> :
          null
      }
    </div>
  );
}
