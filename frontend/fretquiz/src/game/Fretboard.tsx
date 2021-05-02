import React from 'react';
import { store } from '../store';
import { FretboardDiagram, Dot } from 'fretboard-diagram';
import { useSelector } from 'react-redux';
import { selectUserId } from '../user/userSlice';
import {
  selectClickedFret, selectCorrectFret, selectGameId, 
  selectGameIsPlaying, selectGuessIsCorrect, fretClicked
} from './gameSlice';
import { ClientGuess, FretCoord } from './types';
import { sendGuess } from '../websocket/game';
import { emptyElementWithId } from '../utils';

const defaultColor = 'white';
const correctColor = 'lime';
const incorrectColor = 'deeppink';

function dotColor(isCorrect: boolean | undefined) {
  switch (isCorrect) {
    case undefined:
      return defaultColor;
    case true:
      return correctColor;
    case false:
      return incorrectColor;
  }
}

function sendClientGuess(guess: ClientGuess) {
  const { clickedFret } = guess;
  store.dispatch(fretClicked(clickedFret));
  sendGuess(guess);
}

function dotsToDraw(
  guessIsCorrect: boolean | undefined,
  clickedFret: FretCoord | null,
  correctFret: FretCoord | undefined
): Dot[] {
  const color = dotColor(guessIsCorrect);
  const dots: Dot[] = clickedFret ? [{ ...clickedFret, color }] : [];

  if (correctFret) {
    dots.push({ ...correctFret, color: correctColor });
  }

  return dots;
}

export function Fretboard() {
  const id = 'fretboard-element';

  const userId = useSelector(selectUserId);
  const gameId = useSelector(selectGameId);

  const isPlaying = useSelector(selectGameIsPlaying);
  const clickedFret = useSelector(selectClickedFret);
  const guessIsCorrect = useSelector(selectGuessIsCorrect);
  const correctFret = useSelector(selectCorrectFret);

  React.useEffect(() => {
    const dots = dotsToDraw(guessIsCorrect, clickedFret, correctFret);

    const onClick = (clickedFret: FretCoord) => {
      gameId && isPlaying && sendClientGuess({ gameId, playerId: userId, clickedFret })
    };

    new FretboardDiagram({
      id,
      dots,
      drawDotOnHover: isPlaying,
      showStringNames: true,
      showFretNums: true,
      onClick
    });

    return () => emptyElementWithId(id);
  });

  return (
    <div className="Fretboard">
      <div id={id} />
    </div>
  )
}
