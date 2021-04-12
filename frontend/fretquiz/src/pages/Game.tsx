import React from 'react';
import { useSelector } from 'react-redux';
import { selectGameState } from '../game/gameSlice';
import { WaitingRoom } from '../game/WaitingRoom';
import { GameCanvas } from '../game/GameCanvas';
import { GameOver } from '../game/GameOver';

export function Game() {
  const state = useSelector(selectGameState);

  return (
    <div className="Game">
      {state === 'INIT' && <WaitingRoom />}
      {(state === 'PLAYING' || state === 'ROUND_OVER') && <GameCanvas />}
      {state === 'GAME_OVER' && <GameOver />}
    </div>
  );
}
