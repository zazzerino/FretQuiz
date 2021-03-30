<script lang="ts">
  import { user, game } from './stores';
</script>

<p>
  user: {JSON.stringify($user)}
</p>

{#if $game}
<p>
  game: {JSON.stringify($game)}
</p>
{/if}
