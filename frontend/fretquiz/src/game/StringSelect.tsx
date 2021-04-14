import React from 'react';
import { useSelector } from 'react-redux';
import Typography from '@material-ui/core/Typography';
import Checkbox from '@material-ui/core/Checkbox';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import { makeStyles } from '@material-ui/core/styles';
import { sendToggleString } from '../websocket/game';
import { selectGameId, selectStringCount, selectStringsToUse } from './gameSlice';

interface StringCheckboxProps {
  gameId: string,
  string: number,
  stringsToUse: number[]
}

function StringCheckbox(props: StringCheckboxProps) {
  const { gameId, string, stringsToUse } = props;
  const usingString = stringsToUse?.includes(string);

  return (
    <FormControlLabel
      control={
        <Checkbox
          checked={usingString}
          onChange={() => sendToggleString(gameId, string)}
        />
      }
      label={string.toString()}
    />
  );
}

const useStyles = makeStyles({
  checkboxes: {
    display: 'flex',
    justifyContent: 'center',
  }
});

export function StringSelect() {
  const styles = useStyles();

  const gameId = useSelector(selectGameId);
  const stringCount = useSelector(selectStringCount);
  const stringsToUse = useSelector(selectStringsToUse);

  if (gameId == null || stringCount == null || stringsToUse == null) {
    return null;
  }

  return (
    <div className="StringSelect">
      <Typography variant="subtitle1">
        Strings To Use:
      </Typography>
      <div className={styles.checkboxes}>
        {[...Array(stringCount).keys()].map(index => {
          return <StringCheckbox
            key={index}
            gameId={gameId}
            stringsToUse={stringsToUse}
            string={index + 1}
          />
        })}
      </div>
    </div>
  );
}
