import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import './App.css';
import { Footer } from './components/Footer';
import { Home } from './pages/Home';
import { Navbar } from './components/Navbar';
import { CreateGameButton } from './game/components/CreateGameButton';
import { StartGameButton } from './game/components/StartGameButton';
import { GameList } from './game/GameList';
import { Game } from './pages/Game';
import { Login } from './user/Login';

function App() {
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
            <Game />
          </Route>
          <Route path="/">
            <Home />
          </Route>
        </Switch>
        <CreateGameButton />
        <StartGameButton />
        <Footer />
      </BrowserRouter>
    </div>
  );
}

export default App;
