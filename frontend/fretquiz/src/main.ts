import router from "page";
import App from "./App.svelte";
import Home from "./Home.svelte";
import About from "./About.svelte";
import Login from "./user/Login.svelte";
import Game from "./game/Game.svelte";
import GameList from './game/GameList.svelte';
import { page } from "./stores";
import { initWebSocket } from './websocket/websocket';

initWebSocket();
setupRoutes();

function setupRoutes() {
  router('/', () => page.set(Home));
  router('/about', () => page.set(About));
  router('/login', () => page.set(Login))
  router('/game', () => page.set(Game));
  router('/games', () => page.set(GameList));
  router();
}

const app = new App({
  target: document.body
});

export default app;
