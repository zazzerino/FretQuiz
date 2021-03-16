package kdp.fretquiz.theory;

import kdp.fretquiz.Util;

import java.util.regex.Pattern;

public record Note(WhiteKey whiteKey,
                   Accidental accidental,
                   Octave octave) {

    public String name() {
        return whiteKey.val + accidental.val + octave.val;
    }

    /**
     * Parses a notename like "Cb4" into a Note[whiteKey=C, accidental=FLAT, octave=FOUR]
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

        final var oct = pitchClass() == 11 ? octave.next() : octave;

        if (accidental == Accidental.NONE) {
            if (whiteKey == WhiteKey.B || whiteKey == WhiteKey.E) {
                key = whiteKey.next();
            } else {
                acc = accidental.next();
            }
        } else if (accidental == Accidental.SHARP) {
            key = whiteKey.next();
            if (whiteKey == WhiteKey.B || whiteKey == WhiteKey.E) {
                acc = Accidental.SHARP;
            } else {
                acc = Accidental.NONE;
            }
        } else if (accidental == Accidental.FLAT) {
            acc = accidental.next();
        }

        return new Note(key, acc, oct);
    }

    /**
     * Returns the note that is the given number of half-steps higher.
     * @param halfSteps must be a positive number
     */
    public Note transpose(int halfSteps) {
        var note = this;

        if (halfSteps < 0) {
            throw new IllegalArgumentException("halfSteps must be a positive number");
        }

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
}
