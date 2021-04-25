import React from 'react';
import List from '@material-ui/core/List';
import ListItemText from '@material-ui/core/ListItemText';
import Typography from '@material-ui/core/Typography';
import { useSelector } from 'react-redux';
import { selectHostId, selectPlayers } from './gameSlice';

export function PlayerList() {
  const players = useSelector(selectPlayers);
  const hostId = useSelector(selectHostId);

  if (players == null || hostId == null) {
    return null;
  }

  return (
    <div className="PlayerList">
      <Typography variant="h4">
        Players
      </Typography>
      <List>
        {players.map((player, index) => {
          const playerIsHost = player.id === hostId;
          const name = playerIsHost
            ? player.name + ' (host)'
            : player.name;

          return <ListItemText key={index}>{name}</ListItemText>
        })}
      </List>
    </div>
  );
}
