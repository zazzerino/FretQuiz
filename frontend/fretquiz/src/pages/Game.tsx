import React from 'react';
import { useSelector } from 'react-redux';
import { CreateGameButton } from '../game/components/CreateGameButton';
import { Fretboard } from '../game/components/Fretboard';
import { NextRoundButton } from '../game/components/NextRoundButton';
import { StartGameButton } from '../game/components/StartGameButton';
import { Stave } from '../game/components/Stave';
import {
  selectGameId, selectGameIsOver, selectReadyToStart, selectRoundIsOver
} from '../game/gameSlice';

function RoundOverDisplay() {
  return (
    <div className="RoundOverDisplay">
      <NextRoundButton />
      <p>Round Over</p>
    </div>
  );
}

function GameCanvas() {
  const gameId = useSelector(selectGameId);
  const readyToStart = useSelector(selectReadyToStart);
  const roundIsOver = useSelector(selectRoundIsOver);

  return (
    <div className="GameCanvas">
      <Stave />
      <Fretboard />
      {!gameId && <CreateGameButton />}
      {readyToStart && <StartGameButton />}
      {roundIsOver && <RoundOverDisplay />}
    </div>
  );
}

function GameOverDisplay() {
  return (
    <div className="GameOverDisplay">
      <h2>Game Over</h2>
      <CreateGameButton />
    </div>
  )
}

export function Game() {
  const gameIsOver = useSelector(selectGameIsOver);

  return (
    <div className="Game">
      {gameIsOver
        ? <GameOverDisplay />
        : <GameCanvas />}
    </div>
  );
}
