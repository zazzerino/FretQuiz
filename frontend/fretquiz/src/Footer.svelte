<script lang="ts">
  import { user } from './stores';
</script>

<p>
  user: {JSON.stringify({id: $user.id, name: $user.name})}
</p>
