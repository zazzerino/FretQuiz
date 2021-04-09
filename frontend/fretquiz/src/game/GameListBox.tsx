import React from 'react';
import { useSelector } from 'react-redux';
import { selectUserId } from '../user/userSlice';
import { sendJoinGame } from '../websocket/game';
import { selectGameIds } from './gameSlice';

interface GameOptionProps {
  gameId: string,
  userId: string
}

function GameOption(props: GameOptionProps) {
  const { gameId, userId } = props;

  return (
    <option
      value={gameId}
      onClick={() => sendJoinGame(gameId, userId)}
    >
      {gameId}
    </option>
  );
}

export function GameListBox() {
  const gameIds = useSelector(selectGameIds);
  const userId = useSelector(selectUserId);

  return (
    <div className="GameListBox">
      <label htmlFor="game-select">
        Join a game:
      </label>
      <br />
      <select id="game-select" name="games">
        {gameIds.map((gameId, index) => {
          return <GameOption key={index} gameId={gameId} userId={userId} />;
        })}
      </select>
    </div>
  );
}
