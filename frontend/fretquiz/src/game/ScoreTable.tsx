import React from 'react';
import { useSelector } from 'react-redux';
import { selectScores } from './gameSlice';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles({
  root: {
    width: '80%',
    margin: 'auto',
  }
});

export function ScoreTable() {
  const styles = useStyles();
  const scores = useSelector(selectScores);

  if (scores == null) {
    return null;
  }

  return (
    <Table className={styles.root}>
      <TableHead>
        <TableRow>
          <TableCell>Name</TableCell>
          <TableCell>Score</TableCell>
        </TableRow>
      </TableHead>
      <TableBody>
        {scores.map((playerScore, index) => {
          const { player, score } = playerScore;
          return (
            <TableRow key={index}>
              <TableCell>{player.name}</TableCell>
              <TableCell>{score}</TableCell>
            </TableRow>
          );
        })}
      </TableBody>
    </Table>
  );
}
