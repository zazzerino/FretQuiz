import React from 'react';
import { useSelector } from 'react-redux';
import { CreateGameButton } from '../game/components/CreateGameButton';
import { Fretboard } from '../game/components/Fretboard';
import { NextRoundButton } from '../game/components/NextRoundButton';
import { StartGameButton } from '../game/components/StartGameButton';
import { Stave } from '../game/components/Stave';
import { ScoreTable } from '../game/components/ScoreTable';
import { selectGameId, selectGameIsOver, selectGameState, selectReadyToStart, selectRoundIsOver } from '../game/gameSlice';
import { useHistory } from 'react-router';
import { GameLobby } from '../game/GameLobby';

// function RoundOver() {
//   return (
//     <div className="RoundOver">
//       <NextRoundButton />
//     </div>
//   );
// }

// function SettingsLink() {
//   const history = useHistory();

//   return (
//     <div className="SettingsLink">
//       <button onClick={() => history.push('/settings')}>
//         Change Settings
//       </button>
//     </div>
//   );
// }

// function GameCanvas() {
//   const gameId = useSelector(selectGameId);
//   const readyToStart = useSelector(selectReadyToStart);
//   const roundIsOver = useSelector(selectRoundIsOver);

//   return (
//     <div className="GameCanvas">
//       <Stave />
//       <Fretboard />
//       {!gameId && <CreateGameButton />}
//       {gameId && <SettingsLink />}
//       {readyToStart && <StartGameButton />}
//       {roundIsOver && <RoundOver />}
//     </div>
//   );
// }

function GameOver() {
  return (
    <div className="GameOver">
      <h2>Game Over</h2>
      <ScoreTable />
      <CreateGameButton />
    </div>
  )
}

export function Game() {
  const state = useSelector(selectGameState);

  return (
    <div className="Game">
      {state === 'INIT' && <GameLobby />}
    </div>
  );
}

// export function Game() {
//   const gameIsOver = useSelector(selectGameIsOver);
  
//   return (
//     <div className="Game">
//       {gameIsOver
//         ? <GameOver />
//         : <GameCanvas />}
//     </div>
//   );
// }
