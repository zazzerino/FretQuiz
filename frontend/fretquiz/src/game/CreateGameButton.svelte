<script lang="ts">
  import { sendCreateGame } from '../websocket/game';
</script>

<button on:click={sendCreateGame}>
  Create Game
</button>
