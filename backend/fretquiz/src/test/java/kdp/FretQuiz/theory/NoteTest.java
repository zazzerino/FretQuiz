package kdp.FretQuiz.theory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        var note = Note.fromString("D#5");
        assertEquals(new Note(WhiteKey.D, Accidental.SHARP, Octave.FIVE), note);
    }

    @Test
    void pitchClass() {
        var note = Note.fromString("F#2");
        assertEquals(6, note.pitchClass());
    }

    @Test
    void midiNum() {
        var note = Note.fromString("C4");
        assertEquals(60, note.midiNum());

        note = Note.fromString("B3");
        assertEquals(59, note.midiNum());
    }

    @Test
    void isEnharmonicWith() {
        var note1 = Note.fromString("Cb5");
        var note2 = Note.fromString("B4");
        assertTrue(note1.isEnharmonicWith(note2));

        var note3 = Note.fromString("B#4");
        assertFalse(note1.isEnharmonicWith(note3));
    }

    @Test
    void next() {
        var note = Note.fromString("C4");
        assertEquals(Note.fromString("C#4"), note.next());

        note = Note.fromString("C#4");
        assertEquals(Note.fromString("D4"), note.next());
    }

    @Test
    void transpose() {
        var note = Note.fromString("Ab3");
        assertEquals(Note.fromString("A#3"), note.transpose(2));
    }
}