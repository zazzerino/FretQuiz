import * as React from 'react';
import { useSelector } from 'react-redux';
import { Fretboard } from './components/Fretboard';
import { NextRoundButton } from './components/NextRoundButton';
import { Stave } from './components/Stave';
import { selectGameState } from './gameSlice';

function GameElements() {
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

export function GamePage() {
  const isRoundOver = useSelector(selectGameState) === 'ROUND_OVER';

  return (
    <div className="GamePage">
      <GameElements />
      {isRoundOver && <RoundOverDisplay />}
    </div>
  );
}
