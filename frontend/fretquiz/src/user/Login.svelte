<script lang="ts">
  import { sendLogin } from '../websocket/user'

  let name: string;
</script>

<h2>Login</h2>

<label for="name-input">Name</label>
<input id="name-input" bind:value={name}/>

<button on:click={() => sendLogin(name)}>
  Login
</button>
