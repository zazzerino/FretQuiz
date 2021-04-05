import React from 'react';
import { useSelector } from 'react-redux';
import { selectScores } from '../gameSlice';
import './ScoreTable.css';

export function ScoreTable() {
  const scores = useSelector(selectScores);

  if (!scores) {
    throw new Error('could not load user scores');
  }

  return (
    <div className="ScoreTable">
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Score</th>
          </tr>
        </thead>
        <tbody>
          {scores.map((playerScore, index) => {
            const { player, score } = playerScore;
            return (
              <tr key={index}>
                <td>{player.name}</td>
                <td>{score}</td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
}
