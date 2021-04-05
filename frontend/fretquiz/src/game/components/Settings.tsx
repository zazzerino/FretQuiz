import React from 'react';
import { AccidentalSelect } from './AccidentalSelect';
import { StringSelect } from './StringSelect';

export function Settings() {
  return (
    <div className="Settings">
      <h2>Settings</h2>
      <StringSelect />
      <AccidentalSelect />
    </div>
  );
}
