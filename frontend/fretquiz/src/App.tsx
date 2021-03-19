import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import './App.css';
import { Footer } from './components/Footer';
import { Home } from './components/Home';
import { Navbar } from './components/Navbar';
import { GameList } from './features/game/GameList';
import { GamePage } from './features/game/GamePage';
import { Login } from './features/user/Login';
import { sendCreateGame, sendGetGames } from './websocket/game';

function App() {
  
  // React.useEffect(() => {
    // setTimeout(() => sendGetGames(), 1000);
  // });

  return (
    <div className="App">
      <BrowserRouter>
        <Navbar />
        <Switch>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/games">
            <GameList />
          </Route>
          <Route path="/game">
            <GamePage />
          </Route>
          <Route path="/">
            <Home />
          </Route>
        </Switch>

        <button onClick={() => {
          sendGetGames();
        }}>
          Get Games
        </button>
        <button onClick={() => {
          sendCreateGame();
        }}>
          Create Game
        </button>

        <Footer />
      </BrowserRouter>
    </div>
  );
}

export default App;
