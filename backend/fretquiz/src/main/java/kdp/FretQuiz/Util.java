package kdp.FretQuiz;

import java.util.Random;
import java.util.UUID;

public class Util {

    /**
     * Selects a random element from an array.
     */
    public static <T> T randomElement(T[] array) {
        var random = new Random();
        var index = random.nextInt(array.length);

        return array[index];
    }

    public static String randomId() {
        return UUID.randomUUID().toString();
    }
}
