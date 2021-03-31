import * as React from 'react';
import { useSelector } from 'react-redux';
import { Fretboard } from '../game/components/Fretboard';
import { NextRoundButton } from '../game/components/NextRoundButton';
import { StartGameButton } from '../game/components/StartGameButton';
import { Stave } from '../game/components/Stave';
import { selectReadyToStart, selectRoundIsOver } from '../game/gameSlice';

function GameCanvas() {
  return (
    <div className="GameCanvas">
      <Stave />
      <Fretboard />
    </div>
  );
}

function RoundOverDisplay() {
  return (
    <div className="RoundOverDisplay">
      <NextRoundButton />
      <p>Round Over</p>
    </div>
  );
}

export function Game() {
  const readyToStart = useSelector(selectReadyToStart);
  // const roundIsOver = useSelector(selectGameState) === 'ROUND_OVER';
  const roundIsOver = useSelector(selectRoundIsOver);

  return (
    <div className="Game">
      <GameCanvas />
      {readyToStart && <StartGameButton />}
      {roundIsOver && <RoundOverDisplay />}
    </div>
  );
}
