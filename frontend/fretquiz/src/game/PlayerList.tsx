import React from 'react';
import List from '@material-ui/core/List';
import ListItemText from '@material-ui/core/ListItemText';
import Typography from '@material-ui/core/Typography';
import { useSelector } from 'react-redux';
import { selectPlayerNames } from './gameSlice';

export function PlayerList() {
  const names = useSelector(selectPlayerNames);

  if (names == null) {
    return null;
  }

  return (
    <div className="PlayerList">
      <Typography variant="h6">
        Players
      </Typography>
      <List>
        {names.map((name, index) => {
          return <ListItemText key={index}>{name}</ListItemText>;
        })}
      </List>
    </div>
  );
}
