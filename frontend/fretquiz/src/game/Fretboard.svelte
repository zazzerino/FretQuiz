<script lang="ts">
  import { onMount } from 'svelte';
  import { FretboardDiagram } from 'fretboard-diagram';
  import type { ClientGuess, FretCoord } from './types';
  import { sendGuess } from '../websocket/game';
  import { game, user } from '../stores';

  let container: HTMLElement;
  const divId = 'fretboard-div';

  const defaultColor = 'white';
  const correctColor = 'lime';
  const incorrectColor = 'deeppink';

  const handleClick = (gameId: string, playerId: string, clickedFret: FretCoord) => {
    const guess: ClientGuess = { gameId, playerId, clickedFret };
    sendGuess(guess);
  }

  onMount(() => {
    const div = document.createElement('div');
    div.id = divId;
    container.appendChild(div);

    new FretboardDiagram({
      id: divId,
      onClick: (coord: FretCoord) => handleClick($game.id, $user.id, coord)
    });
  });
</script>

<div bind:this={container}/>
