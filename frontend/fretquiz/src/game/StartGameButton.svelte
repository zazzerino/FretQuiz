<script lang="ts">
  import { sendStartGame } from "../websocket/game";
  import { user, game } from "../stores";

  // only send the start request if the user and game are already set
  const onClick = () => $user && $game && sendStartGame($game.id);
</script>

<button on:click={onClick}>
  Start Game
</button>
