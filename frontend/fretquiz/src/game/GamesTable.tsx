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
import { selectUserId } from '../user/userSlice';
import { JoinGameButton } from './JoinGameButton';
import { GameInfo } from './types';

function formatGameInfo(info: GameInfo) {
  const shortId = info.gameId.substring(0, 8);
  const minutes = Math.floor(minutesSince(new Date(info.createdAt)));
  const state = info.state.toLowerCase().replace('_', ' ');
  const playerCount = info.playerCount;
  const hostName = info.hostName;

  return { shortId, minutes, state, playerCount, hostName };
}

const useStyles = makeStyles({
  table: {
    width: '80%',
    margin: 'auto',
  }
});

export function GamesTable() {
  const classes = useStyles();

  const userId = useSelector(selectUserId);
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
          <TableCell>Join</TableCell>
          <TableCell></TableCell>
        </TableRow>
      </TableHead>
      <TableBody>
        {gameInfos.map(info => {
          const { shortId, hostName, playerCount, state, minutes } = formatGameInfo(info);
          return (
            <TableRow key={info.gameId}>
              <TableCell>{shortId}</TableCell>
              <TableCell>{hostName}</TableCell>
              <TableCell>{playerCount}</TableCell>
              <TableCell>{state}</TableCell>
              <TableCell>{minutes} minutes ago</TableCell>
              <TableCell>
                <JoinGameButton gameId={info.gameId} userId={userId} />
              </TableCell>
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
