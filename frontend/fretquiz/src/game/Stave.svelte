<script lang="ts">
  import { onMount } from 'svelte';
  import { game } from '../stores';
  import { makeVexObjects } from './vexflow-util';

  let container;
  let context, stave;

  const width = 200;
  const height = 130;

  // const note = $game.cu

  onMount(() => {
    const vexObjs = makeVexObjects(container, width, height);
    context = vexObjs.context;
    stave = vexObjs.stave;

    stave.draw();

    // if ($game && $game.rou)
  });
</script>

<div bind:this={container}/>
