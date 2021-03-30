import * as React from 'react';
import { useSelector } from 'react-redux';
import { Fretboard } from '../game/components/Fretboard';
import { NextRoundButton } from '../game/components/NextRoundButton';
import { Stave } from '../game/components/Stave';
import { selectGameState } from '../game/gameSlice';

function GameCanvas() {
  return (
    <div className="GameElements">
      <Stave />
      <Fretboard />
    </div>
  );
}

function RoundOverDisplay() {
  return (
    <div className="RoundOverDisplay">
      <p>Round Over</p>
      <NextRoundButton />
    </div>
  );
}

export function Game() {
  const roundIsOver = useSelector(selectGameState) === 'ROUND_OVER';

  return (
    <div className="GamePage">
      <GameCanvas />
      {roundIsOver && <RoundOverDisplay />}
    </div>
  );
}
