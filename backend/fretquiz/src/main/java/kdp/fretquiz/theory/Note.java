package kdp.fretquiz.theory;

import java.util.regex.Pattern;

public record Note(WhiteKey whiteKey,
                   Accidental accidental,
                   Octave octave) {

    public String name() {
        return whiteKey.toString() + accidental.toString() + octave;
    }

    public static Note fromName(String name) {
        var noteRegex = "([A-Z])(#{1,2}|b{1,2})?(\\d)";
        var pattern = Pattern.compile(noteRegex);
        var matcher = pattern.matcher(name);

        if (!matcher.matches()) {
            throw new IllegalArgumentException();
        }

        var whiteKey = WhiteKey.valueOf(matcher.group(1));

        var match2 = matcher.group(2);
        var accidental = Accidental.fromName(match2 == null ? "" : match2);

        var octave = Octave.fromStr(matcher.group(3));

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
}
