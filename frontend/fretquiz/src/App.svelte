<script lang="ts">
  import { currentPage } from "./stores";
  import Navbar from "./Navbar.svelte";
  import Footer from "./Footer.svelte";
  import CreateGameButton from "./game/CreateGameButton.svelte";
</script>

<div>
  <Navbar/>
  <svelte:component this={$currentPage}/>
  <br/>
  <CreateGameButton/>
  <Footer/>
</div>

<style>
	div {
		text-align: center;
		padding: 1em;
		max-width: 240px;
		margin: 0 auto;
	}

	@media (min-width: 640px) {
		div {
			max-width: none;
		}
	}
</style>