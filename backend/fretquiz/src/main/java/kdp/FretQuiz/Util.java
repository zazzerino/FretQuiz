package kdp.FretQuiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

public class Util {

    /**
     * Selects a random element from an array.
     */
    public static <T> T randomElement(List<T> list) {
        final var index = new Random().nextInt(list.size());
        return list.get(index);
    }

    public static String randomId() {
        return UUID.randomUUID().toString();
    }

    public static List<Integer> range(int start, int endExclusive) {
        return IntStream
                .range(start, endExclusive)
                .boxed()
                .toList();
    }

    /**
     * Toggles an element's inclusion in a list. Returns a new list.
     */
    public static <T> List<T> toggle(List<T> list, T element) {
        final var copy = new ArrayList<>(list);

        if (copy.contains(element)) {
            copy.remove(element);
        } else {
            copy.add(element);
        }

        return copy;
    }
}
