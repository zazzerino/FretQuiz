import { Writable, writable } from 'svelte/store';
import Home from './Home.svelte';
import { defaultUser } from './user/user';
import type { FretCoord, Game, Guess, Note } from './game/types';

/**
 * The current page to show. 
 * Changes when the user navigates (or is redirected) to another url.
 */
export const page = writable(Home);

/**
 * The current user.
 * On connect, users are logged in as an anonymous user.
 */
export const user = writable(defaultUser);

/**
 * A list of current game ids.
 */
export const gameIds: Writable<string[]> = writable([]);

/**
 * The game that the user is connected to (or null).
 */
export const game: Writable<Game | null> = writable(null);

/**
 * The user's most recent guess (or null).
 */
export const guess: Writable<Guess | null> = writable(null);

export const clickedFret: Writable<FretCoord | null> = writable(null);
