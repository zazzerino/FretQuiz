import React from 'react';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import { AccidentalSelect } from './AccidentalSelect';
import { StringSelect } from './StringSelect';

const useStyles = makeStyles({
  root: {
    '& h4': {
      marginTop: '1rem'
    }
  }
});

export function Settings() {
  const styles = useStyles();

  return (
    <div className={styles.root}>
      <Typography variant="h4">
        Settings
      </Typography>
      <StringSelect />
      <AccidentalSelect />
    </div>
  );
}
