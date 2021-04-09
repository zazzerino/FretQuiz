import React from 'react';
import { useSelector } from 'react-redux';
import { selectPlayerNames } from './gameSlice';
import './PlayerList.css';

export function PlayerList() {
  const names = useSelector(selectPlayerNames);

  if (names == null) {
    return null;
  }

  return (
    <div className="PlayerList">
      <h4>Players</h4>
       {names.map((name, index) => {
         return <li key={index}>{name}</li>
       })}
    </div>
  );
}
