import React from 'react';
import { useSelector } from 'react-redux';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
// import TableFooter from '@material-ui/core/TableFooter'
// import TablePagination from '@material-ui/core/TablePagination';
import { selectGameInfos } from './gameSlice';
import { minutesSince } from '../utils';

const useStyles = makeStyles({
  table: {
    width: '80%',
    margin: 'auto',
  }
});

export function GamesTable() {
  const classes = useStyles();
  const gameInfos = useSelector(selectGameInfos);

  return (
    <Table className={classes.table}>
      <TableHead>
        <TableRow>
          <TableCell>ID</TableCell>
          <TableCell>Host</TableCell>
          <TableCell>Players</TableCell>
          <TableCell>Status</TableCell>
          <TableCell>Created</TableCell>
        </TableRow>
      </TableHead>
      <TableBody>
        {gameInfos.map(info => {
          const shortId = info.gameId.substring(0, 8);
          const minutes = Math.floor(minutesSince(new Date(info.createdAt)));

          return (
            <TableRow key={info.gameId} data-game-id={info.gameId}>
              <TableCell>{shortId}</TableCell>
              <TableCell>{info.hostName}</TableCell>
              <TableCell>{info.playerCount}</TableCell>
              <TableCell>{info.state}</TableCell>
              <TableCell>{minutes} minutes ago</TableCell>
            </TableRow>
          )
        })}
      </TableBody>
      {/* <TableFooter>
          <TableRow>
            <TablePagination
              component="div"
              count={gameInfos.length}
            />
          </TableRow>
        </TableFooter> */}
    </Table>
  );
}
