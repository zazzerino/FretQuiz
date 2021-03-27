import * as React from 'react';
import { store } from '../../../app/store';
import { FretboardDiagram } from 'fretboard-diagram';
import { useSelector } from 'react-redux';
import { selectUserId } from '../../user/userSlice';
import { selectGameId, selectGameState, selectGuessResult, setClickedFret } from '../gameSlice';
import { FretCoord } from '../types';
import { sendGuess } from '../../../websocket/game';

function onClick(gameId: string, playerId: string, clickedFret: FretCoord) {
  const guess = {
    gameId,
    playerId,
    clickedFret
  }

  sendGuess(guess);
  store.dispatch(setClickedFret(clickedFret));
}

export function Fretboard() {
  const divId = 'fretboard-div';
  const correctColor = 'lime';
  const incorrectColor = 'deeppink';

  const userId = useSelector(selectUserId);
  const gameId = useSelector(selectGameId);
  const isPlaying = useSelector(selectGameState) == 'PLAYING';
  const guessResult = useSelector(selectGuessResult);

  // const [clickedFret, setClickedFret] = React.useState<FretCoord>();

  React.useEffect(() => {
    // const color = guessResult ? correctColor : incorrectColor;
    // const dots = clickedFret ? [{...clickedFret, color}] : [];

    // selects the element with the given `id` and draws a fretboard diagram there
    new FretboardDiagram({
      id: divId,

      drawDotOnHover: isPlaying,
      // dots,

      onClick: (clickedFret: FretCoord) => gameId && isPlaying && onClick(gameId, userId, clickedFret),
    });
  });

  return (
    <div className="Fretboard">
      <div id={divId} />
    </div>
  )
}
