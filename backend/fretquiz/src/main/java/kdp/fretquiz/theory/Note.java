package kdp.fretquiz.theory;

import kdp.fretquiz.Util;

import java.util.Map;
import java.util.regex.Pattern;

public record Note(WhiteKey whiteKey,
                   Accidental accidental,
                   Octave octave) {

    public String name() {
        return whiteKey.val + accidental.val + octave.val;
    }

    /**
     * Parses a note name like "Cb4" into a Note[whiteKey=C, accidental=FLAT, octave=FOUR]
     * @param name a string with a letter A-G, an optional accidental ('b' or '#'), and an octave number
     * @return a Note
     */
    public static Note fromString(String name) {
        var noteRegex = "([A-Z])(#{1,2}|b{1,2})?(\\d)";
        var pattern = Pattern.compile(noteRegex);
        var matcher = pattern.matcher(name);

        if (!matcher.matches()) {
            throw new IllegalArgumentException();
        }

        // for the note 'E##3': matcher.group(1) == 'E', matcher.group(2) == '##', matcher.group(3) == '3'

        var whiteKey = WhiteKey.valueOf(matcher.group(1));

        // find the accidental, if it exists
        var match2 = matcher.group(2);
        // if there was no accidental, set it to be an empty string instead of null so it can be parsed correctly
        var accidental = Accidental.fromString(match2 == null ? "" : match2);

        var octave = Octave.fromString(matcher.group(3));

        return new Note(whiteKey, accidental, octave);
    }

    public int pitchClass() {
        return whiteKey.halfStepsFromC() + accidental.halfStepOffset();
    }

    public int midiNum() {
        return pitchClass() + (12 * (octave.val + 1));
    }

    public void fromMidiNum() {
    }

    public boolean isEnharmonicWith(Note note) {
        return midiNum() == note.midiNum();
    }

    /**
     * Returns the note a half step higher.
     */
    public Note next() {
        var key = whiteKey;
        var acc = accidental;

        // If we're at pitchClass == 11 (the notes "B", "A##", "Cb"), increment the octave. Otherwise, the octave stays the same.
        final var oct = pitchClass() == 11 ? octave.next() : octave;

        if (accidental == Accidental.NONE) {
            if (whiteKey == WhiteKey.B || whiteKey == WhiteKey.E) {
                key = whiteKey.next();
            } else {
                acc = Accidental.SHARP;
            }
        } else if (accidental == Accidental.SHARP) {
            key = whiteKey.next();
            acc = (whiteKey == WhiteKey.B || whiteKey == WhiteKey.E) ?
                    Accidental.SHARP :
                    Accidental.NONE;
        } else if (accidental == Accidental.FLAT) {
            acc = Accidental.NONE;
        }

        return new Note(key, acc, oct);
    }

//    public Note previous() {
//    }

    /**
     * Returns the note that is the given number of half-steps higher.
     * @param halfSteps must be a positive number
     */
    public Note transpose(int halfSteps) {
        if (halfSteps < 0) {
            throw new IllegalArgumentException("halfSteps must be a positive number");
        }

        var note = new Note(whiteKey, accidental, octave);

        while (halfSteps > 0) {
            note = note.next();
            halfSteps--;
        }

        return note;
    }

    /**
     * Returns a random note.
     */
    public static Note random() {
        var whiteKey = Util.randomElement(WhiteKey.values());
        var accidental = Util.randomElement(Accidental.values());
        var octave = Util.randomElement(Octave.values());

        return new Note(whiteKey, accidental, octave);
    }

    public static Note randomBetween(Note low, Note high) {
        var lowMidi = low.midiNum();
        var highMidi = high.midiNum();

        Note note;
        int midi;

        // TODO search for a better way, the right way
        do {
            note = Note.random();
            midi = note.midiNum();
        } while (midi < lowMidi || midi > highMidi);

        return note;
    }
}
