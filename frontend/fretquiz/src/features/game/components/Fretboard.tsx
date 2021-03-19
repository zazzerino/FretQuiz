import * as React from 'react';
import { FretboardDiagram } from 'fretboard-diagram';
import { useSelector } from 'react-redux';
import { selectUserId } from '../../user/userSlice';
import { FretCoord, selectGameId } from '../gameSlice';

const correctColor = 'lime';
const incorrectColor = 'deeppink';

export function Fretboard() {
  const userId = useSelector(selectUserId);
  const gameId = useSelector(selectGameId);

  React.useEffect(() => {
    new FretboardDiagram({
      id: 'fretboard-ref',
      drawDotOnHover: true,
      onClick: (coord: FretCoord) => {
        console.log('clicked ' + JSON.stringify(coord));
      }
    });
  });

  return (
    <div className="Fretboard">
      <div id="fretboard-ref" />
    </div>
  )
}
