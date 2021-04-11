/**
 * Empties an element by removing its children.
 */
export function empty(elem: Element) {
  while (elem.firstChild) {
    elem.removeChild(elem.firstChild);
  }
}

/**
 * Empties an element with the given `id`.
 */
export function emptyElementWithId(id: string) {
  const elem = document.getElementById(id);

  if (elem) {
    empty(elem);
  }
}

/**
 * @returns The number of seconds since `date`.
 */
export function secondsSince(date: Date): number {
  const now = new Date();
  return (now.getTime() - date.getTime()) / 1000;
}

/**
 * @returns The number of minutes since `date`.
 */
export function minutesSince(date: Date): number {
  return secondsSince(date) / 60;
}
