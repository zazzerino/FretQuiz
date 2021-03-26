import * as React from 'react';
import { FretboardDiagram } from 'fretboard-diagram';
import { useSelector } from 'react-redux';
import { selectUserId } from '../../user/userSlice';
import { selectGameId, selectGameState } from '../gameSlice';
import { FretCoord } from '../types';
import { sendGuess } from '../../../websocket/game';

const correctColor = 'lime';
const incorrectColor = 'deeppink';

export function Fretboard() {
  const userId = useSelector(selectUserId);

  const gameId = useSelector(selectGameId);
  const gameState = useSelector(selectGameState);

  React.useEffect(() => {
    const isPlaying = gameState == 'PLAYING';

    // selects the element with the given `id` and draws a fretboard diagram there
    new FretboardDiagram({
      id: 'fretboard-canvas',
      drawDotOnHover: isPlaying,
      onClick: (coord: FretCoord) => {
        if (gameId && isPlaying) {
          const guess = { 
            gameId, 
            playerId: userId, 
            clickedFret: coord 
          };

          sendGuess(guess);
        }
      }
    });
  });

  return (
    <div className="Fretboard">
      <div id="fretboard-canvas" />
    </div>
  )
}
