import * as React from 'react';
import { useSelector } from 'react-redux';
import { sendJoinGame } from '../../websocket/game';
import { selectUserId } from '../user/userSlice';
import { Game, selectGameIds } from './gameSlice';

interface GameLinkProps {
  gameId: string,
  userId: string
}

function GameLink(props: GameLinkProps) {
  const { gameId, userId } = props;

  return (
    <li
      onClick={() => {
        sendJoinGame(userId, gameId);
      }}
    >
      {gameId}
    </li>
  );
}

export function GameList() {
  const gameIds = useSelector(selectGameIds);
  const userId = useSelector(selectUserId);

  return (
    <div className="GameList">
      <h2>Games</h2>
      <ul>
        {
          gameIds.map((gameId, index) => {
            return <GameLink key={index} gameId={gameId} userId={userId} />
          })
        }
      </ul>
    </div>
  );
}
