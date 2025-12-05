package org.jmitchell238.aoc.aoc2023;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import org.jmitchell238.aoc.aoc2023.utilities.Utilities;

public class UtilitiesUses {

    private static final String RESULT_STRING = "\t\tResult: ";
    private static final String ONE_THROUGH_FIVE_STRING = "1, 2, 3, 4, 5";
    private static final String MAP_STRING = "\tmap = ";
    private static final String RESULT_VALUE_FOR_KEY_STRING = "\t\tResult: Value for key '";
    private static final String NOTE_VALUE_FOR_KEY_STRING = "\t\tNote: Value for key '";
    private static final String WAS_ADDED_TO_MAP_STRING = "' was added to map";

    public static void main(String[] args) {
        System.out.println("Utilities Uses Examples\n");

        examplesOneThroughTen();

        examplesElevenThroughTwenty();
    }

    private static void examplesOneThroughTen() {
        // Example 1: splitToStringArray(input, ",", true)
        splitToStringArrayByStringDelimeter();
        System.out.println();

        // Example 2: splitToStringArray(input, new char[]{','}, true)
        splitToStringArrayByCharArrayDelimeter();
        System.out.println();

        // Example 3: splitToIntArray(input, ",")
        splitToIntArrayByStringDelimeter();
        System.out.println();

        // Example 4: splitToIntArray(input, new char[]{','})
        splitToIntArrayByCharArrayDelimeter();
        System.out.println();

        // Eample 5: splitToLongArray(input, ",")
        splitToLongArrayByStringDelimeter();
        System.out.println();

        // Example 6: splitToLongArray(input, new char[]{','})
        splitToLongArrayByCharArrayDelimeter();
        System.out.println();

        // Example 7: splitToDoubleArray(input, ",")
        splitToDoubleArrayByStringDelimeter();
        System.out.println();

        // Example 8: splitToDoubleArray(input, new char[]{','})
        splitToDoubleArrayByCharArrayDelimeter();
        System.out.println();

        // Example 9: read(map, "three")
        readWithoutKeyValuePresent();
        System.out.println();

        // Example 10: read(map, "two")
        readWithKeyValuePresent();
        System.out.println();
    }

    private static void examplesElevenThroughTwenty() {
        // Example 11: createStack("example")
        createStack();
        System.out.println();

        // Example 12: getOrComputeWithFunction(map, key, key -> key.length()) - Without existing key
        getOrComputeWithFunctionWithoutExistingKeyValuePair();
        System.out.println();

        // Example 13: getOrComputeWithFunction(map, key, k -> k.length()) - With existing key
        getOrComputeWithFunctionWithExistingKeyValuePair();
        System.out.println();

        // Example 14: getOrComputeWithFunctionAddToValue(map, key, function, combiner) - Without
        // existing key
        getOrComputeWithFunctionAddToValueWithoutExistingKeyValuePair();
        System.out.println();

        // Example 15: getOrComputeWithFunctionAddToValue(map, key, function, combiner) - With existing
        // key
        getOrAddToMapWithoutExistingKeyValuePair();
        System.out.println();

        // Example 16: getOrAddToMap(map, new AbstractMap.SimpleEntry<>(key, value)) - With existing key
        getOrAddToMapWithExistingKeyValuePair();
        System.out.println();

        // Example 17: getOrAddToMapAddToValue(map, new AbstractMap.SimpleEntry<>(key, value), combiner)
        // - Without existing key
        getOrAddToMapAddToValueWithoutExistingKeyValuePair();
        System.out.println();

        // Example 18: getOrAddToMapAddToValue(map, new AbstractMap.SimpleEntry<>(key, value), combiner)
        // - With existing key
        getOrAddToMapAddToValueWithExistingKeyValuePair();
        System.out.println();
    }

    // Example 1 usage:
    public static void splitToStringArrayByStringDelimeter() {
        String input = "one, two, , three";

        System.out.println("Example 1: splitToStringArray(input, \",\", true)");
        System.out.printf("\tsplitToStringArray(%s, \",\", true)%n", input);

        String[] result = Utilities.splitToStringArray(input, ",", true);

        System.out.println(RESULT_STRING + Arrays.toString(result));
    }

    // Example 2 usage:
    public static void splitToStringArrayByCharArrayDelimeter() {
        String input = "one, two, , three";
        char[] delimiter = {','};

        System.out.println("Example 2: splitToStringArray(input, new char[]{','}, true)");
        System.out.printf("\tsplitToStringArray(%s, new char[]{','}, true)%n", input);

        String[] result = Utilities.splitToStringArray(input, delimiter, true);

        System.out.println(RESULT_STRING + Arrays.toString(result));
    }

    // Example 3 usage:
    private static void splitToIntArrayByStringDelimeter() {
        String input = ONE_THROUGH_FIVE_STRING;

        System.out.println("Example 3: splitToIntArray(input, \",\")");
        System.out.printf("\tsplitToIntArray(%s, \",\")%n", input);

        int[] result = Utilities.splitToIntArray(input, ",");

        System.out.println(RESULT_STRING + Arrays.toString(result));
    }

    // Example 4 usage:
    private static void splitToIntArrayByCharArrayDelimeter() {
        String input = ONE_THROUGH_FIVE_STRING;
        char[] delimiter = {','};

        System.out.println("Example 4: splitToIntArray(input, new char[]{','})");
        System.out.printf("\tsplitToIntArray(%s, new char[]{','})%n", input);

        int[] result = Utilities.splitToIntArray(input, delimiter);

        System.out.println(RESULT_STRING + Arrays.toString(result));
    }

    // Example 5 usage:
    private static void splitToLongArrayByStringDelimeter() {
        String input = ONE_THROUGH_FIVE_STRING;

        System.out.println("Example 5: splitToLongArray(input, \",\")");
        System.out.printf("\tsplitToLongArray(%s, \",\")%n", input);

        long[] result = Utilities.splitToLongArray(input, ",");

        System.out.println(RESULT_STRING + Arrays.toString(result));
    }

    // Example 6 usage:
    private static void splitToLongArrayByCharArrayDelimeter() {
        String input = ONE_THROUGH_FIVE_STRING;
        char[] delimiter = {','};

        System.out.println("Example 6: splitToLongArray(input, new char[]{','})");
        System.out.printf("\tsplitToLongArray(%s, new char[]{','})%n", input);

        long[] result = Utilities.splitToLongArray(input, delimiter);

        System.out.println(RESULT_STRING + Arrays.toString(result));
    }

    // Example 7 usage:
    private static void splitToDoubleArrayByStringDelimeter() {
        String input = "1.1, 2.2, 3.3, 4.4, 5.5";

        System.out.println("Example 7: splitToDoubleArray(input, \",\")");
        System.out.printf("\tsplitToDoubleArray(%s, \",\")%n", input);

        double[] result = Utilities.splitToDoubleArray(input, ",");

        System.out.println(RESULT_STRING + Arrays.toString(result));
    }

    // Example 8 usage:
    private static void splitToDoubleArrayByCharArrayDelimeter() {
        String input = "1.1, 2.2, 3.3, 4.4, 5.5";
        char[] delimiter = {','};

        System.out.println("Example 8: splitToDoubleArray(input, new char[]{','})");
        System.out.printf("\tsplitToDoubleArray(%s, new char[]{','})%n", input);

        double[] result = Utilities.splitToDoubleArray(input, delimiter);

        System.out.println(RESULT_STRING + Arrays.toString(result));
    }

    // Example 9 usage:
    private static void readWithoutKeyValuePresent() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);

        System.out.println("Example 9: read(map, \"three\")");
        System.out.println(MAP_STRING + map);
        System.out.println("\tread(map, \"three\")");

        String key = "three";
        Integer result = Utilities.read(map, key);

        System.out.println(RESULT_VALUE_FOR_KEY_STRING + key + "': " + result);
    }

    // Example 10 usage:
    private static void readWithKeyValuePresent() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);

        System.out.println("Example 10: read(map, \"two\")");
        System.out.println(MAP_STRING + map);
        System.out.println("\tread(map, \"two\")");

        String key = "two";
        Integer result = Utilities.read(map, key);

        System.out.println(RESULT_VALUE_FOR_KEY_STRING + key + "': " + result);
    }

    // Example 11 usage:
    private static void createStack() {
        String input = "example";

        System.out.printf("Example 11: createStack(\"%s\")%n", input);

        Deque<Character> result = Utilities.createStack(input);

        System.out.println(RESULT_STRING + result);
    }

    // Example 12 usage:
    private static void getOrComputeWithFunctionWithoutExistingKeyValuePair() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);

        System.out.println(
                "Example 12: getOrComputeWithFunction(map, key, key -> key.length()) - Without existing key");
        System.out.println(MAP_STRING + map);
        System.out.println("\tgetOrCompute(map, \"eight\", k -> k.length())");

        String key = "eight";
        Integer result = Utilities.getOrComputeWithFunction(map, key, k -> k.length());

        System.out.println(RESULT_VALUE_FOR_KEY_STRING + key + "': " + result);
    }

    // Example 13 usage:
    private static void getOrComputeWithFunctionWithExistingKeyValuePair() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("nine", 9);

        System.out.println("Example 13: getOrComputeWithFunction(map, key, k -> k.length()) - With existing key");
        System.out.println(MAP_STRING + map);
        System.out.println("\tgetOrCompute(map, \"nine\", k -> k.length()) - length of \"nine\" is 4");

        String key = "nine";
        Integer result = Utilities.getOrComputeWithFunction(map, key, k -> k.length());

        System.out.println(RESULT_VALUE_FOR_KEY_STRING + key + "': " + result);
        System.out.println(MAP_STRING + map);
        System.out.println(NOTE_VALUE_FOR_KEY_STRING + key + "' was not changed");
    }

    // Example 14 usage:
    private static void getOrComputeWithFunctionAddToValueWithoutExistingKeyValuePair() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("nine", 9);
        String key = "four";
        BinaryOperator<Integer> combiner = Integer::sum;

        System.out.println(
                "Example 14: getOrComputeWithFunctionAddToValue(map, key, function, combiner) - Without existing key");
        System.out.println(MAP_STRING + map);
        System.out.println(
                "\tgetOrCompute(map, \"four\", k -> k.length(), BinaryOperator<Integer> combiner) - length of \"four\" is 4");

        Integer result = Utilities.getOrComputeWithFunctionAddToValue(map, key, k -> k.length(), combiner);

        System.out.println(RESULT_VALUE_FOR_KEY_STRING + key + "': " + result);
        System.out.println(MAP_STRING + map);
        System.out.println(NOTE_VALUE_FOR_KEY_STRING + key + WAS_ADDED_TO_MAP_STRING);
    }

    // Example 15 usage:
    private static void getOrAddToMapWithoutExistingKeyValuePair() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("four", 4);
        map.put("nine", 9);
        String key = "four";
        int value = 42;

        System.out.println(
                "Example 15: getOrAddToMap(map, new AbstractMap.SimpleEntry<>(key, value)) - Without existing key");
        System.out.println(MAP_STRING + map);
        System.out.printf("\tgetOrAddToMap(map, new AbstractMap.SimpleEntry<>(%s, %s))", key, value);

        Integer result = Utilities.getOrAddToMap(map, new AbstractMap.SimpleEntry<>(key, value));

        System.out.println(RESULT_VALUE_FOR_KEY_STRING + key + "': " + result);
        System.out.println(MAP_STRING + map);
        System.out.println(NOTE_VALUE_FOR_KEY_STRING + key + WAS_ADDED_TO_MAP_STRING);
    }

    // Example 16 usage:
    private static void getOrAddToMapWithExistingKeyValuePair() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("nine", 9);
        String key = "two";
        int value = 42;

        System.out.println(
                "Example 16: getOrAddToMap(map, new AbstractMap.SimpleEntry<>(key, value)) - With existing key");
        System.out.println(MAP_STRING + map);
        System.out.printf("\tgetOrAddToMap(map, new AbstractMap.SimpleEntry<>(%s, %s))", key, value);

        Integer result = Utilities.getOrAddToMap(map, new AbstractMap.SimpleEntry<>(key, value));

        System.out.println(RESULT_VALUE_FOR_KEY_STRING + key + "': " + result);
        System.out.println(MAP_STRING + map);
        System.out.println(NOTE_VALUE_FOR_KEY_STRING + key + "' was not changed");
    }

    // Example 17 usage:
    private static void getOrAddToMapAddToValueWithoutExistingKeyValuePair() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        String key = "four";
        int value = 4;

        System.out.println(
                "Example 17: getOrAddToMapAddToValue(map, new AbstractMap.SimpleEntry<>(key, value), combiner) - Without existing key");
        System.out.println(MAP_STRING + map);
        System.out.printf(
                "\tgetOrAddToMapAddToValue(map, new AbstractMap.SimpleEntry<>(%s, %s), combiner)", key, value);

        Integer result =
                Utilities.getOrAddToMapAddToValue(map, new AbstractMap.SimpleEntry<>(key, value), Integer::sum);

        System.out.println(RESULT_VALUE_FOR_KEY_STRING + key + "': " + result);
        System.out.println(MAP_STRING + map);
        System.out.println(NOTE_VALUE_FOR_KEY_STRING + key + WAS_ADDED_TO_MAP_STRING);
    }

    // Example 18 usage:
    private static void getOrAddToMapAddToValueWithExistingKeyValuePair() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        String key = "two";
        int value = 42;

        System.out.println(
                "Example 18: getOrAddToMapAddToValue(map, new AbstractMap.SimpleEntry<>(key, value), combiner) - With existing key");
        System.out.println(MAP_STRING + map);
        System.out.printf(
                "\tgetOrAddToMapAddToValue(map, new AbstractMap.SimpleEntry<>(%s, %s), combiner)", key, value);

        Integer result =
                Utilities.getOrAddToMapAddToValue(map, new AbstractMap.SimpleEntry<>(key, value), Integer::sum);

        System.out.println(RESULT_VALUE_FOR_KEY_STRING + key + "': " + result);
        System.out.println(MAP_STRING + map);
        System.out.println(NOTE_VALUE_FOR_KEY_STRING + key + "' was added to existing value for key in map");
    }
}
