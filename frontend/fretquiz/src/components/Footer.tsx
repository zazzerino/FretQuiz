import React from 'react';
import Divider from '@material-ui/core/Divider';
import { makeStyles, Typography } from '@material-ui/core';
import { useSelector } from 'react-redux';
import { selectCurrentGame } from '../game/gameSlice';
import { selectUser } from '../user/userSlice';

function UserDisplay() {
  const user = useSelector(selectUser);

  return (
    <Typography variant="body2">
      {`user: ${user.name}`}
    </Typography>
  );
}

function GameDisplay() {
  const game = useSelector(selectCurrentGame);

  if (game == null) {
    return null;
  }

  return (
    <Typography variant="body2">
      {`game: ${game.id.substring(0, 8)}`}
    </Typography>
  );
}

const useStyles = makeStyles({
  footer: {
    marginTop: "2rem"
  }
});

export function Footer() {
  const styles = useStyles();

  return (
    <div className={styles.footer}>
      <Divider />
      <UserDisplay />
      <GameDisplay />
    </div>
  );
}
