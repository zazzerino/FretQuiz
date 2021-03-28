import Vex from 'vexflow';
import type { Note } from './types';

export function makeVexObjects(elem: HTMLElement, width: number, height: number) {
  const renderer = new Vex.Flow.Renderer(elem, Vex.Flow.Renderer.Backends.SVG);
  renderer.resize(width, height);

  const context = renderer.getContext();

  const stave = new Vex.Flow.Stave(0, 0, width-1);
  stave.addClef('treble');
  stave.setContext(context);

  return { renderer, context, stave };
}

export function drawNote(note: Note, context: Vex.IRenderContext, stave: Vex.Flow.Stave) {
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

  Vex.Flow.Formatter.FormatAndDraw(context, stave, [staveNote]);
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
    default: throw new Error(`${octave} is not a valid octave name.`);
  }
}

/**
 * Formats a note into a string usable by vexflow.
 */
function formatNote(note: Note): string {
  const accidental = accidentalSymbol(note.accidental);
  const octave = octaveNumber(note.octave);

  return note.whiteKey + accidental + '/' + octave;
}
