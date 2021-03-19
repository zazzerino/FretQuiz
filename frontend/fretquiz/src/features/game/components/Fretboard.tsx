import * as React from 'react';
import { FretboardDiagram } from 'fretboard-diagram';

const correctColor = 'lime';
const incorrectColor = 'deeppink';

export function Fretboard() {
  
  React.useEffect(() => {
    new FretboardDiagram({
      id: 'fretboard-ref',
      drawDotOnHover: true
    });
  });

  return (
    <div className="Fretboard">
      <div id="fretboard-ref" />
    </div>
  )
}
