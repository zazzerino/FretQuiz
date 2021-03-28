import { Writable, writable } from 'svelte/store';
import Home from './Home.svelte';
import { defaultUser } from './user/user';
import type { Game } from './game/types';

export const currentPage = writable(Home);

export const user = writable(defaultUser);

export const gameIds: Writable<string[]> = writable([]);

export const currentGame: Writable<Game | null> = writable(null);
