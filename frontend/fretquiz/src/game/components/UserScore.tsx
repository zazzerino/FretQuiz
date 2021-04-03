import React from 'react';
import { useSelector } from 'react-redux';
import { selectUserScores } from '../gameSlice';

export function UserScore() {
  const userScores = useSelector(selectUserScores) !!;

  // if (!userScores) {
  //   throw new Error('could not load user scores');
  // }

  return (
    <div className="UserScore">
      <table>
        <thead>
          <tr>
            <th>Id</th>
            <th>Score</th>
          </tr>
        </thead>
        <tbody>
          {userScores.map((userScore, index) => {
            const { userId, score } = userScore;
            return (
              <tr key={index}>
                <td>{userId}</td>
                <td>{score}</td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
}
