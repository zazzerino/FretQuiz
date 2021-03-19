import * as React from 'react';
import { useSelector } from 'react-redux';
import Vex from 'vexflow';
import { Note, selectNoteToGuess } from '../gameSlice';

type VexObjects = {
  renderer: Vex.Flow.Renderer,
  context: Vex.IRenderContext,
  stave: Vex.Flow.Stave
}

function empty(elem: Element) {
  while (elem.firstChild) {
    elem.removeChild(elem.firstChild);
  }
}

function formatNote(note: Note): string {
  return note.whiteKey + note.accidental + '/' + note.octave;
}

function accidentalSymbol(accidental: string): string {
  switch (accidental) {
    case 'SHARP': return '#';
    case 'FLAT': return 'b';
    default: return '';
  }
}

function makeVexObjects(
  elem: HTMLElement,
  width: number,
  height: number
): VexObjects {

  const renderer = new Vex.Flow.Renderer(elem, 3);
  renderer.resize(width, height);

  const context = renderer.getContext();

  const stave = new Vex.Flow.Stave(0, 0, width - 1);
  stave.addClef('treble');
  stave.setContext(context);

  return { renderer, context, stave };
}

function drawNote(vexObjs: VexObjects, note: Note) {
  const notename = formatNote(note);

  const staveNote = new Vex.Flow.StaveNote({
    keys: [notename],
    duration: 'w'
  });

  const accidental = accidentalSymbol(note.accidental);

  if (accidental !== '') {
    staveNote.addAccidental(0, new Vex.Flow.Accidental(accidental));
  }

  Vex.Flow.Formatter.FormatAndDraw(vexObjs.context, vexObjs.stave, [staveNote]);
}

export function Stave() {
  const width = 200;
  const height = 130;

  const note = useSelector(selectNoteToGuess);

  React.useEffect(() => {
    const elem = document.getElementById('stave-ref');

    if (elem) {
      empty(elem); // remove the previous stave

      const objs = makeVexObjects(elem, width, height);
      objs.stave.draw();

      if (note) {
        drawNote(objs, note);
      }
    }
  });

  return (
    <div className="Stave">
      <div id="stave-ref" />
    </div>
  );
}
