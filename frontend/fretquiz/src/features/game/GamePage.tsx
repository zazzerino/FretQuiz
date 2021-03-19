import * as React from 'react';
import { Fretboard } from './components/Fretboard';
import { Stave } from './components/Stave';

export function GamePage() {
  return (
    <div className="GamePage">
      <Stave />
      <Fretboard />
    </div>
  );
}
