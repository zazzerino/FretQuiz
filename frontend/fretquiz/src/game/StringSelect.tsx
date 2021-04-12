import { Typography } from '@material-ui/core';
import React from 'react';
import { useSelector } from 'react-redux';
import { sendToggleString } from '../websocket/game';
import { selectGameId, selectStringCount, selectStringsToUse } from './gameSlice';
import './StringSelect.css';

interface StringCheckboxProps {
  gameId: string,
  string: number,
  stringsToUse: number[]
}

function StringCheckbox(props: StringCheckboxProps) {
  const { gameId, string, stringsToUse } = props;
  const name = `string-${string}`;
  const usingString = stringsToUse?.includes(string);

  return (
    <div className="StringCheckbox">
      <input
        type="checkbox"
        name={name}
        checked={usingString}
        onChange={() => sendToggleString(gameId, string)}
      />
      <label htmlFor={name}>
        {`${string}`}
      </label>
    </div>
  );
}

export function StringSelect() {
  const gameId = useSelector(selectGameId);
  const stringCount = useSelector(selectStringCount);
  const stringsToUse = useSelector(selectStringsToUse);

  if (gameId == null || stringCount == null || stringsToUse == null) {
    return null;
  }

  return (
    <div className="StringSelect">
      {/* <h4>Strings To Use:</h4> */}
      <Typography variant="h6">
        String To Use:
      </Typography>
      <div className="string-checkboxes">
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
