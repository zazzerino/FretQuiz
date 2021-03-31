import React from 'react';
import { useSelector } from 'react-redux';
import { sendJoinGame } from '../../websocket/game';
import { selectUserId } from '../../user/userSlice';
import { selectGameIds } from '../gameSlice';

interface GameListItemProps { 
  gameId: string, 
  userId: string 
}

/**
 * Displays a game id. 
 * When the user clicks the id, they are connected to the game with that id.
 */
function GameListItem(props: GameListItemProps) {
  const { gameId, userId } = props;

  return (
    <li onClick={() => sendJoinGame(gameId, userId)}>
      {gameId}
    </li>
  );
}

/**
 * A list of the current game ids.
 * Each item connects the user to the game when clicked.
 */
export function GameList() {
  const gameIds = useSelector(selectGameIds);
  const userId = useSelector(selectUserId);

  return (
    <div className="GameList">
      <h2>Games</h2>
      <ul>
        {gameIds.map((gameId, index) => {
          return <GameListItem key={index} gameId={gameId} userId={userId} />
        })}
      </ul>
    </div>
  );
}
