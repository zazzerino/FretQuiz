import React from 'react';
import { useSelector } from 'react-redux';
import Typography from '@material-ui/core/Typography';
import Checkbox from '@material-ui/core/Checkbox';
import { sendToggleAccidental } from '../websocket/game';
import { selectAccidentalsToUse, selectGameId } from './gameSlice';
import { Accidental } from './types';
import { makeStyles } from '@material-ui/core/styles';

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
      <Checkbox
        checked={isUsed}
        onChange={() => sendToggleAccidental(gameId, accidental)}
      />
      <label htmlFor={accidental}>
        {name}
      </label>
    </div>
  );
}

const useStyles = makeStyles({
  checkboxes: {
    display: 'flex',
    justifyContent: 'center',
  }
});

export function AccidentalSelect() {
  const styles = useStyles();

  const gameId = useSelector(selectGameId);
  const accidentalsToUse = useSelector(selectAccidentalsToUse);

  if (gameId == null || accidentalsToUse == null) {
    return null;
  }

  const usingFlats = accidentalsToUse?.includes('FLAT');
  const usingSharps = accidentalsToUse?.includes('SHARP');

  return (
    <div className="AccidentalSelect">
      <Typography variant="subtitle1">
        Accidentals To Use:
      </Typography>
      <div className={styles.checkboxes}>
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
