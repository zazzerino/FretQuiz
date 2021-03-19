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

function accidentalSymbol(accidental: string): string {
  switch (accidental) {
    case 'SHARP': return '#';
    case 'FLAT': return 'b';
    default: return '';
  }
}

function octaveNumber(octave: string): number {
  switch (octave) {
    case 'ONE': return 1;
    case 'TWO': return 2;
    case 'THREE': return 3;
    case 'FOUR': return 4;
    case 'FIVE': return 5;
    case 'SIX': return 6;
    case 'SEVEN': return 7;
    case 'EIGHT': return 8;
    case 'NINE': return 9;
    default: return 0;
  }
}

function formatNote(note: Note): string {
  const accidental = accidentalSymbol(note.accidental);
  const octave = octaveNumber(note.octave);

  // return note.whiteKey + note.accidental + '/' + note.octave;
  return note.whiteKey + accidental + '/' + octave;
}

function makeVexObjects(
  elem: HTMLElement,
  width: number,
  height: number
): VexObjects {

  const renderer = new Vex.Flow.Renderer(elem, Vex.Flow.Renderer.Backends.SVG);
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
    duration: 'w',
    // @ts-ignore
    align_center: true
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
        console.log("there's a note " + JSON.stringify(formatNote(note)));
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
