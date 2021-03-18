package kdp.fretquiz;

import java.util.*;

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
     * Combines two lists into a map.
     * Each entry has an element from list1 as the key, and an element from list2 as the value.
     */
    public static <T, U> Map<T, U> zip(List<T> list1, List<U> list2) {
        Map<T, U> map = new HashMap<>();

        var iter1 = list1.iterator();
        var iter2 = list2.iterator();

        while (iter1.hasNext() && iter2.hasNext()) {
            map.put(iter1.next(), iter2.next());
        }

        return map;
    }

    /**
     * Copies a record and updates any fields listed in `overrides`.
     * Taken from https://sormuras.github.io/blog/2020-05-05-records-copy.html
     */
    public static <R extends Record> R copy(R template, Map<String, Object> overrides) {
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
}
