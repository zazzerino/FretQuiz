<script lang="ts">
  import { sendStartGame } from "../websocket/game";
  import { user, game } from "../stores";
  const onClick = () => $user && $game && sendStartGame($game.id);
</script>

<button on:click={onClick}>
  Start Game
</button>
