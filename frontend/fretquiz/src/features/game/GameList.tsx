import * as React from 'react';
import { useSelector } from 'react-redux';
import { selectGames } from './gameSlice';

export function GameList() {
  const games = useSelector(selectGames);

  return (
    <div className="GameList">
      <h2>Games</h2>
      <ul>
        {
          games.map((game, index) => {
            return <li key={index}>
              {game.id}
            </li>
          })
        }
      </ul>
    </div>
  );
}
