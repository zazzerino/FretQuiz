<script lang="ts">
  import { page } from "./stores";
  import Navbar from "./Navbar.svelte";
  import Footer from "./Footer.svelte";
  import CreateGameButton from "./game/CreateGameButton.svelte";
import StartGameButton from "./game/StartGameButton.svelte";
</script>

<div>
  <Navbar/>
  <svelte:component this={$page}/>
  <br/>
  <CreateGameButton/>
  <br/>
  <StartGameButton/>
  <Footer/>
</div>

<style>
	div {
		text-align: center;
		padding: 1em;
		/* max-width: 240px; */
		margin: 0 auto;
	}

	/* @media (min-width: 640px) {
		div {
			max-width: none;
		}
	} */
</style>