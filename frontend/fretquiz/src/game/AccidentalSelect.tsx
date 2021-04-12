import React from 'react';
import { useSelector } from 'react-redux';
import { sendToggleAccidental } from '../websocket/game';
import { selectAccidentalsToUse, selectGameId } from './gameSlice';
import { Accidental } from './types';
import './AccidentalSelect.css';

function accidentalName(accidental: Accidental): string {
  switch (accidental) {
    case 'FLAT':
      return 'Flats';
    case 'NONE':
      return 'Naturals';
    case 'SHARP':
      return 'Sharps';
    default:
      throw new Error('invalid argument');
  }
}

interface AccidentalCheckboxProps {
  accidental: Accidental,
  isUsed: boolean,
  gameId: string
}

function AccidentalCheckbox(props: AccidentalCheckboxProps) {
  const { accidental, isUsed, gameId } = props;
  const name = accidentalName(accidental);

  return (
    <div className="AccidentalCheckbox">
      <input
        type="checkbox"
        name={accidental}
        checked={isUsed}
        onChange={() => sendToggleAccidental(gameId, accidental)}
      />
      <label htmlFor={accidental}>{name}</label>
    </div>
  );
}

export function AccidentalSelect() {
  const gameId = useSelector(selectGameId);
  const accidentalsToUse = useSelector(selectAccidentalsToUse);

  if (gameId == null || accidentalsToUse == null) {
    return null;
  }

  const usingFlats = accidentalsToUse?.includes('FLAT');
  const usingSharps = accidentalsToUse?.includes('SHARP');

  return (
    <div className="AccidentalSelect">
      <h4>Accidentals To Use:</h4>
      <div className="accidental-checkboxes">
        <AccidentalCheckbox
          accidental="FLAT"
          isUsed={usingFlats}
          gameId={gameId}
        />
        <AccidentalCheckbox
          accidental="SHARP"
          isUsed={usingSharps}
          gameId={gameId}
        />
      </div>
    </div>
  );
}
