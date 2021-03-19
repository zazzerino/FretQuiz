import React from 'react';
import { useDispatch } from 'react-redux';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import './App.css';
import { Footer } from './components/Footer';
import { Home } from './components/Home';
import { Navbar } from './components/Navbar';
import { GameList } from './features/game/GameList';
import { GamePage } from './features/game/GamePage';
// import { sendCreateGame, fetchAllGames, setGamesAsync } from './features/game/gameSlice';
import { Login } from './features/user/Login';
import { sendCreateGame, sendGetGames } from './websocket/socket';

function App() {
  const dispatch = useDispatch();

  // React.useEffect(() => {
  //   dispatch(setGamesAsync());
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
          sendCreateGame();
        }}>
          Create Game
        </button>
        <button onClick={() => {
          sendGetGames();
        }}>
          Get Games
        </button>
        <Footer />
      </BrowserRouter>
    </div>
  );
}

export default App;
