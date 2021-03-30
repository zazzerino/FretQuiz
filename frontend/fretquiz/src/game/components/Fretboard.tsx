import * as React from 'react';
import { store } from '../../app/store';
import { FretboardDiagram } from 'fretboard-diagram';
import { useSelector } from 'react-redux';
import { selectUserId } from '../../user/userSlice';
import {
  selectClickedFret, selectCorrectFret, selectGameId, selectGameState, selectGuess, setClickedFret
} from '../gameSlice';
import { FretCoord } from '../types';
import { sendGuess } from '../../websocket/game';
import { emptyElementWithId } from '../../utils';

interface Dot extends FretCoord {
  color?: string
}

// colors of the dots drawn on the fretboard will change depending on the user's guess
const defaultColor = 'white';
const correctColor = 'lime';
const incorrectColor = 'deeppink';

function sendUserGuess(gameId: string, playerId: string, clickedFret: FretCoord) {
  const guess = { gameId, playerId, clickedFret }

  store.dispatch(setClickedFret(clickedFret));
  sendGuess(guess);
}

function dotsToDraw(
  isCorrect: boolean | undefined,
  clickedFret: FretCoord | null,
  correctFret: FretCoord | undefined
): Dot[] {
  let color: string;

  if (isCorrect === undefined) {
    color = defaultColor;
  } else if (isCorrect === true) {
    color = correctColor;
  } else {
    color = incorrectColor;
  }

  const dots: Dot[] = clickedFret ? [{ ...clickedFret, color }] : [];

  if (correctFret) {
    dots.push({ ...correctFret, color: correctColor });
  }

  return dots;
}

export function Fretboard() {
  const id = 'fretboard-div';

  const userId = useSelector(selectUserId);
  const gameId = useSelector(selectGameId);

  const isPlaying = useSelector(selectGameState) === 'PLAYING';
  const isCorrect = useSelector(selectGuess)?.isCorrect;
  const clickedFret = useSelector(selectClickedFret);
  const correctFret = useSelector(selectCorrectFret);

  React.useEffect(() => {
    const dots = dotsToDraw(isCorrect, clickedFret, correctFret);

    const onClick = (clickedFret: FretCoord) => {
      gameId && isPlaying && sendUserGuess(gameId, userId, clickedFret)
    };

    new FretboardDiagram({
      id,
      dots,
      drawDotOnHover: isPlaying,
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
