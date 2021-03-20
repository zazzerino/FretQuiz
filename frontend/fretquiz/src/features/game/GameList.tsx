import * as React from 'react';
import { useSelector } from 'react-redux';
import { Game, selectGameIds } from './gameSlice';

interface GameLinkProps {
  gameId: string
}

function GameLink(props: GameLinkProps) {
  const { gameId } = props;

  return (
    <li
      onClick={() => {
        console.log('clicked ' + gameId)
      }}>
      {gameId}
    </li>
  );
}

export function GameList() {
  const gameIds = useSelector(selectGameIds);

  return (
    <div className="GameList">
      <h2>Games</h2>
      <ul>
        {
          gameIds.map((id, index) => {
            return <GameLink key={index} gameId={id} />
          })
        }
      </ul>
    </div>
  );
}
