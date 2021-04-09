package kdp.fretquiz.theory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NoteTest {

    @Test
    void name() {
        var note = new Note(WhiteKey.C, Accidental.NONE, Octave.FOUR);
        assertEquals("C4", note.name());

        note = new Note(WhiteKey.A, Accidental.FLAT, Octave.SIX);
        assertEquals("Ab6", note.name());
    }

    @Test
    void fromString() {
        var note = Note.from("D#5");
        assertEquals(new Note(WhiteKey.D, Accidental.SHARP, Octave.FIVE), note);
    }

    @Test
    void pitchClass() {
        var note = Note.from("F#2");
        assertEquals(6, note.pitchClass());
    }

    @Test
    void midiNum() {
        var note = Note.from("C4");
        assertEquals(60, note.midiNum());

        note = Note.from("B3");
        assertEquals(59, note.midiNum());
    }

    @Test
    void isEnharmonicWith() {
        var note1 = Note.from("Cb5");
        var note2 = Note.from("B4");
        assertTrue(note1.isEnharmonicWith(note2));

        var note3 = Note.from("B#4");
        assertFalse(note1.isEnharmonicWith(note3));
    }

    @Test
    void next() {
        var note = Note.from("C4");
        assertEquals(Note.from("C#4"), note.next());

        note = Note.from("C#4");
        assertEquals(Note.from("D4"), note.next());
    }

    @Test
    void transpose() {
        var note = Note.from("Ab3");
        assertEquals(Note.from("A#3"), note.transpose(2));
    }
}