import React from 'react';
import { useSelector } from 'react-redux';
import { Fretboard } from '../game/Fretboard';
import { NextRoundButton } from '../game/NextRoundButton';
import { Stave } from '../game/Stave';
import { selectRoundIsOver } from '../game/gameSlice';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles({
  root: {
    '& button': {
      marginTop: '2rem'
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
