import * as React from 'react';
import { useSelector } from 'react-redux';
import { emptyElementWithId } from '../../utils';
import { selectNoteToGuess } from '../gameSlice';
import { makeVexObjects, drawNote } from './vexflow-utils';

export function Stave() {
  const id = 'stave-element';
  const [width, height] = [200, 130];

  const note = useSelector(selectNoteToGuess);

  React.useEffect(() => {
    const elem = document.getElementById(id);

    if (!elem) {
      throw new Error(`could not find element with id ${id}`);
    }

    const { context, stave } = makeVexObjects(elem, width, height);

    stave.draw();

    if (note) {
      drawNote(context, stave, note);
    }

    return () => emptyElementWithId(id);
  });

  return (
    <div className="Stave">
      <div id={id} />
    </div>
  );
}
