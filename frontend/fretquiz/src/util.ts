/**
 * Empties an element by removing its children.
 */
export function empty(elem: Element) {
  while (elem.firstChild) {
    elem.removeChild(elem.firstChild);
  }
}