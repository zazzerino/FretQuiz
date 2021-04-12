import React from 'react';
import { useSelector } from 'react-redux';
import { Fretboard } from './Fretboard';
import { NextRoundButton } from './NextRoundButton';
import { Stave } from './Stave';
import { selectRoundIsOver } from './gameSlice';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles({
  root: {
    '& button': {
      marginTop: '1rem'
    }
  }
});

export function GameCanvas() {
  const styles = useStyles();
  const roundIsOver = useSelector(selectRoundIsOver);

  return (
    <div className={styles.root}>
      <Stave />
      <Fretboard />
      {roundIsOver && <NextRoundButton />}
    </div>
  );
}
