import React from 'react';
import { useSelector } from 'react-redux';
import { CreateGameButton } from '../game/components/CreateGameButton';
import { Fretboard } from '../game/components/Fretboard';
import { NextRoundButton } from '../game/components/NextRoundButton';
import { Stave } from '../game/components/Stave'; 
import { ScoreTable } from '../game/components/ScoreTable';
import { selectGameState, selectRoundIsOver } from '../game/gameSlice';
import { GameLobby } from '../game/GameLobby';

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
      {state === 'INIT' && <GameLobby />}
      {(state === 'PLAYING' || state === 'ROUND_OVER') && <GameCanvas />}
      {state === 'GAME_OVER' && <GameOver />}
    </div>
  );
}
