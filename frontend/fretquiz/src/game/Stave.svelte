<script lang="ts">
  import { onMount } from 'svelte';
  import { makeVexObjects } from './vexflow-util';

  let container;
  let context, stave;

  const width = 200;
  const height = 130;

  onMount(() => {
    const vexObjs = makeVexObjects(container, width, height);
    context = vexObjs.context;
    stave = vexObjs.stave;

    stave.draw();
  });
</script>

<div bind:this={container}/>
