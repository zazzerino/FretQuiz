import React from 'react';
import { useSelector } from 'react-redux';
import { selectUserScores } from '../gameSlice';
import './PlayerScore.css';

export function ScoreTable() {
  const userScores = useSelector(selectUserScores);

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
            const { name } = player;
            return (
              <tr key={index}>
                <td>{name}</td>
                <td>{score}</td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
}
