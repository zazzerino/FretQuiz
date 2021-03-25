package kdp.FretQuiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
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

    /**
     * Copies a record and updates any fields listed in `overrides`.
     * Taken from https://sormuras.github.io/blog/2020-05-05-records-copy.html
     */
    public static <R extends Record> R copyRecord(R template, Map<String, Object> overrides) {
        try {
            var types = new ArrayList<Class<?>>();
            var values = new ArrayList<>();
            for (var component : template.getClass().getRecordComponents()) {
                types.add(component.getType());
                var name = component.getName();
                var overridden = overrides.containsKey(name);
                values.add(overridden ? overrides.get(name) : component.getAccessor().invoke(template));
            }
            var canonical = template.getClass().getDeclaredConstructor(types.toArray(Class[]::new));
            @SuppressWarnings("unchecked")
            var result = (R) canonical.newInstance(values.toArray(Object[]::new));
            return result;
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("Reflection failed: " + e, e);
        }
    }

    public static <R extends Record> R copyRecord(R template) {
        return copyRecord(template, Collections.emptyMap());
    }
}
