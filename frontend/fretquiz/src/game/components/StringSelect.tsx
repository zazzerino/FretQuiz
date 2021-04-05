import React from 'react';
import { useSelector } from 'react-redux';
import { Redirect } from 'react-router';
import { sendToggleString } from '../../websocket/game';
import { selectGameId, selectStringCount, selectStringsToUse } from '../gameSlice';
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

  if (!gameId || !stringsToUse || !stringCount) {
    throw new Error(`useSelector in StringSelect returned null or undefined`);
  }

  return (
    <div className="StringSelect">
      <h4>Strings To Use:</h4>
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
