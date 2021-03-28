<script lang="ts">
  import { gameIds } from '../stores';
  import { sendJoinGame } from '../websocket/game';
  import { user } from '../stores';
</script>

<h2>Games</h2>
<ul>
  {#each $gameIds as gameId}
    <li on:click={() => sendJoinGame(gameId, $user.id)}>
      {gameId}
    </li>
  {/each}
</ul>
