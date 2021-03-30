<script lang="ts">
  import { onMount } from 'svelte';
  import { game, noteToGuess} from '../stores';
  import { drawNote, makeVexObjects } from './vexflow-util';

  let container;
  let context, stave;

  const width = 200;
  const height = 130;

  onMount(() => {
    const vexObjs = makeVexObjects(container, width, height);
    context = vexObjs.context;
    stave = vexObjs.stave;
    stave.draw();

    if ($noteToGuess) {
      drawNote($noteToGuess, context, stave);
    }
  });
</script>

<div bind:this={container}/>
