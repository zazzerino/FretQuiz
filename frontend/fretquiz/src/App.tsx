import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import './App.css';
import { Footer } from './components/Footer';
import { Home } from './components/Home';
import { Navbar } from './components/Navbar';
import { CreateGameButton } from './features/game/components/CreateGameButton';
import { StartGameButton } from './features/game/components/StartGameButton';
import { GameList } from './features/game/GameList';
import { GamePage } from './features/game/GamePage';
import { Login } from './features/user/Login';

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
            <GamePage />
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
