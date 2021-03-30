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

  if (!elem) {
    throw new Error(`could not find element with id ${id}`);
  }

  empty(elem);
}
