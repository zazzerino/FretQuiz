import React from 'react';
import { useSelector } from 'react-redux';
import { CreateGameButton } from '../game/CreateGameButton';
import { Fretboard } from '../game/Fretboard';
import { NextRoundButton } from '../game/NextRoundButton';
import { Stave } from '../game/Stave'; 
import { ScoreTable } from '../game/ScoreTable';
import { selectGameState, selectRoundIsOver } from '../game/gameSlice';
import { WaitingRoom } from '../game/WaitingRoom';

function GameCanvas() {
  const roundIsOver = useSelector(selectRoundIsOver);

  return (
    <div className="GameCanvas">
      <Stave />
      <Fretboard />
      {roundIsOver && <NextRoundButton />}
    </div>
  );
}

function GameOver() {
  return (
    <div className="GameOver">
      <h2>Game Over</h2>
      <ScoreTable />
      <CreateGameButton />
    </div>
  )
}

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
