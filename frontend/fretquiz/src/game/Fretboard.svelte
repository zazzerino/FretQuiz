<script lang="ts">
  import { onMount } from 'svelte';
  import { FretboardDiagram } from 'fretboard-diagram';

  let container: HTMLDivElement;
  const divId = 'fretboard-div';

  onMount(() => {
    const div = document.createElement('div');
    div.id = divId;
    container.appendChild(div);

    new FretboardDiagram({
      id: divId
    });
  });
</script>

<div bind:this={container}/>
