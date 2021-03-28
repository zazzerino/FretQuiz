import page from "page";
import App from "./App.svelte";
import Home from "./Home.svelte";
import About from "./About.svelte";
import Login from "./user/Login.svelte";
import Game from "./game/Game.svelte";
import GameList from './game/GameList.svelte';
import { currentPage } from "./stores";
import { initWebSocket } from './websocket/websocket';

initWebSocket();
setupRoutes();

function setupRoutes() {
  page('/', () => currentPage.set(Home));
  page('/about', () => currentPage.set(About));
  page('/login', () => currentPage.set(Login))
  page('/game', () => currentPage.set(Game));
  page('/games', () => currentPage.set(GameList));
  page();
}

const app = new App({
  target: document.body
});

export default app;
