package kdp.fretquiz.theory;

public record Note(WhiteKey whiteKey,
                   Accidental accidental,
                   Octave octave) {

    public String name() {
        return whiteKey.toString() + accidental.toString() + octave;
    }

    public int pitchClass() {
        var keyOffset = whiteKey.halfStepsFromC();
        var accOffset = accidental.offset();

        return keyOffset + accOffset;
    }

    public int midiNum() {
        return pitchClass() + (12 * (octave.val + 1));
    }

    public void fromMidiNum() {

    }

    /**
     * Returns the note a half step higher.
     */
    public Note next() {
        var key = whiteKey;
        var acc = accidental;

        final var oct = pitchClass() == 11 ? octave().next() : octave;

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
