import React from 'react';
import { useSelector } from 'react-redux';
import { selectScores } from '../gameSlice';
import './PlayerScore.css';

export function ScoreTable() {
  const userScores = useSelector(selectScores);

  if (!userScores) {
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
          {userScores.map((userScore, index) => {
            const { player, score } = userScore;
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
