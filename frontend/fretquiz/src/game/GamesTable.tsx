import React from 'react';
import { useSelector } from 'react-redux';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from  '@material-ui/core/Paper';
// import TableFooter from '@material-ui/core/TableFooter'
// import TablePagination from '@material-ui/core/TablePagination';
import { selectGameInfos } from './gameSlice';

const useStyles = makeStyles({
  table: {}
});

export function GamesTable() {
  const classes = useStyles();
  const gameInfos = useSelector(selectGameInfos);

  return (
    <TableContainer component={Paper}>
      <Table className={classes.table}>
        <TableHead>
          <TableRow>
            <TableCell>Game ID</TableCell>
            <TableCell>Host</TableCell>
            <TableCell>Players</TableCell>
            <TableCell>Status</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {gameInfos.map(info => (
            <TableRow key={info.gameId}>
              <TableCell>{info.gameId}</TableCell>
              <TableCell>{info.hostName}</TableCell>
              <TableCell>{info.playerCount}</TableCell>
              <TableCell>{info.state}</TableCell>
            </TableRow>
          ))}
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
    </TableContainer>
  );
}
