package org.jmitchell238.aoc.aoc2023.utilities;

import java.awt.Point;
import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import lombok.Getter;

public class Utilities {

    private Utilities() {}

    public static char[] splitToCharArray(String str) {
        return str.toCharArray();
    }

    /**
     * Splits a string into an array of substrings based on the specified delimiter.
     *
     * <p>This method takes a {@code String} as the delimiter and uses the {@code String.split} method
     * to split the input string based on the provided delimiter. The {@code removeEmpty} parameter
     * determines whether empty entries should be removed from the result.
     *
     * @param str The input string to be split.
     * @param split The delimiter used to split the input string.
     * @param removeEmpty {@code true} to remove empty entries, {@code false} otherwise.
     * @return An array of substrings after splitting the input string.
     */
    public static String[] splitToStringArray(String str, String split, boolean removeEmpty) {
        return removeEmpty
                ? Arrays.stream(str.split(split, -1))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .toArray(String[]::new)
                : Arrays.stream(str.split(split, -1)).map(String::trim).toArray(String[]::new);
    }

    /**
     * Splits a string into an array of substrings based on the specified character array delimiter.
     *
     * <p>This method takes a {@code char[]} as the delimiter and converts it to a {@code String} for
     * use in the {@code String.split} method. The rest of the logic is similar to the first method,
     * with the {@code removeEmpty} parameter determining whether empty entries should be removed from
     * the result.
     *
     * @param str The input string to be split.
     * @param split The character array delimiter used to split the input string.
     * @param removeEmpty {@code true} to remove empty entries, {@code false} otherwise.
     * @return An array of substrings after splitting the input string based on the character array
     *     delimiter.
     */
    public static String[] splitToStringArray(String str, char[] split, boolean removeEmpty) {
        String delimiter = new String(split);
        return removeEmpty
                ? Arrays.stream(str.split(delimiter, -1))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .toArray(String[]::new)
                : Arrays.stream(str.split(delimiter, -1)).map(String::trim).toArray(String[]::new);
    }

    /**
     * Splits a string into an array of integers based on the specified delimiter.
     *
     * <p>This method takes a {@code String} as the delimiter and uses the {@code String.split} method
     * to split the input string based on the provided delimiter. The resulting array of substrings is
     * then converted to an array of integers using {@code Integer.parseInt}.
     *
     * @param string The input string to be split.
     * @param split The delimiter used to split the input string.
     * @return An array of integers after splitting and parsing the input string.
     */
    public static int[] splitToIntArray(String string, String split) {
        String[] stringArray = string.split(split, -1);
        return Arrays.stream(stringArray)
                .mapToInt(str -> Integer.parseInt(str.trim()))
                .toArray();
    }

    /**
     * Splits a string into an array of integers based on the specified character array delimiter.
     *
     * <p>This method takes a {@code char[]} as the delimiter, converts it to a {@code String} for use
     * in the {@code String.split} method, and then splits the input string based on the character
     * array delimiter. The resulting array of substrings is then converted to an array of integers
     * using {@code Integer.parseInt}.
     *
     * @param string The input string to be split.
     * @param split The character array delimiter used to split the input string.
     * @return An array of integers after splitting and parsing the input string based on the
     *     character array delimiter.
     */
    public static int[] splitToIntArray(String string, char... split) {
        String delimiter = new String(split);
        String[] stringArray = string.split(delimiter, -1);
        return Arrays.stream(stringArray)
                .mapToInt(str -> Integer.parseInt(str.trim()))
                .toArray();
    }

    /**
     * Splits a string into an array of longs based on the specified delimiter.
     *
     * <p>This method takes a {@code String} as the delimiter and uses the {@code String.split} method
     * to split the input string based on the provided delimiter. The resulting array of substrings is
     * then converted to an array of longs using {@code Long.parseLong}.
     *
     * @param string The input string to be split.
     * @param split The delimiter used to split the input string.
     * @return An array of longs after splitting and parsing the input string.
     */
    public static long[] splitToLongArray(String string, String split) {
        String[] stringArray = string.split(split, -1);
        return Arrays.stream(stringArray)
                .mapToLong(str -> Long.parseLong(str.trim()))
                .toArray();
    }

    /**
     * Splits a string into an array of longs based on the specified character array delimiter.
     *
     * <p>This method takes a {@code char[]} as the delimiter, converts it to a {@code String} for use
     * in the {@code String.split} method, and then splits the input string based on the character
     * array delimiter. The resulting array of substrings is then converted to an array of longs using
     * {@code Long.parseLong}.
     *
     * @param string The input string to be split.
     * @param split The character array delimiter used to split the input string.
     * @return An array of longs after splitting and parsing the input string based on the character
     *     array delimiter.
     */
    public static long[] splitToLongArray(String string, char... split) {
        String delimiter = new String(split);
        String[] stringArray = string.split(delimiter, -1);
        return Arrays.stream(stringArray)
                .mapToLong(str -> Long.parseLong(str.trim()))
                .toArray();
    }

    /**
     * Splits a string into an array of doubles based on the specified delimiter.
     *
     * <p>This method takes a {@code String} as the delimiter and uses the {@code String.split} method
     * to split the input string based on the provided delimiter. The resulting array of substrings is
     * then converted to an array of doubles using {@code Double.parseDouble}.
     *
     * @param string The input string to be split.
     * @param split The delimiter used to split the input string.
     * @return An array of doubles after splitting and parsing the input string.
     */
    public static double[] splitToDoubleArray(String string, String split) {
        String[] stringArray = string.split(split, -1);
        return Arrays.stream(stringArray).mapToDouble(Double::parseDouble).toArray();
    }

    /**
     * Splits a string into an array of doubles based on the specified character array delimiter.
     *
     * <p>This method takes a {@code char[]} as the delimiter, converts it to a {@code String} for use
     * in the {@code String.split} method, and then splits the input string based on the character
     * array delimiter. The resulting array of substrings is then converted to an array of doubles
     * using {@code Double.parseDouble}.
     *
     * @param str The input string to be split.
     * @param split The character array delimiter used to split the input string.
     * @return An array of doubles after splitting and parsing the input string based on the character
     *     array delimiter.
     */
    public static double[] splitToDoubleArray(String str, char... split) {
        String delimiter = new String(split);
        String[] stringArray = str.split(delimiter, -1);
        return Arrays.stream(stringArray).mapToDouble(Double::parseDouble).toArray();
    }

    /**
     * Retrieves the value associated with the specified key from a map.
     *
     * <p>This method retrieves the value associated with the specified key from the provided map. If
     * the key is not present in the map, the method returns {@code null}.
     *
     * @param <K> The type of keys in the map.
     * @param <V> The type of values in the map.
     * @param map The map from which to retrieve the value.
     * @param key The key whose associated value is to be retrieved.
     * @return The value associated with the specified key, or {@code null} if the key is not present
     *     in the map.
     */
    public static <K, V> V read(Map<K, V> map, K key) {
        return map.getOrDefault(key, null);
    }

    /**
     * Creates a stack from a string.
     *
     * <p>This method takes a string and creates a stack where each character of the string is pushed
     * onto the stack in the order they appear in the string.
     *
     * @param str The input string used to create the stack.
     * @return A stack containing the characters from the input string.
     */
    public static Deque<Character> createStack(String str) {
        char[] chars = str.toCharArray();
        Deque<Character> deque = new ArrayDeque<>();
        for (char ch : chars) {
            deque.push(ch);
        }
        return deque;
    }

    /**
     * Gets the value associated with the specified key from a map, or computes and adds it if absent.
     *
     * <p>This method checks if the specified key is present in the map. If the key is present, it
     * returns the associated value. If the key is not present, it computes a new value using the
     * provided function, adds the key-value pair to the map, and returns the computed value.
     *
     * @param <K> The type of keys in the map.
     * @param <V> The type of values in the map.
     * @param map The map from which to retrieve or compute the value.
     * @param key The key whose associated value is to be retrieved or computed.
     * @param function The function used to compute a new value if the key is not present in the map.
     * @return The value associated with the specified key, or the computed value if the key is not
     *     present.
     */
    public static <K, V> V getOrComputeWithFunction(Map<K, V> map, K key, Function<K, V> function) {
        return map.computeIfAbsent(key, function);
    }

    /**
     * Gets the value associated with the specified key from a map, or computes and adds it if absent.
     * If the key is present, adds the computed value to the existing value using the specified
     * combiner.
     *
     * <p>This method checks if the specified key is present in the map. If the key is present, it
     * computes a new value using the provided function and adds it to the existing value using the
     * specified combiner. If the key is not present, it computes a new value using the provided
     * function, adds the key-value pair to the map, and returns the computed value.
     *
     * @param <K> The type of keys in the map.
     * @param <V> The type of values in the map.
     * @param map The map from which to retrieve or compute the value.
     * @param key The key whose associated value is to be retrieved or computed.
     * @param function The function used to compute a new value if the key is not present in the map.
     * @param combiner The combiner used to add the computed value to the existing value if the key is
     *     present.
     * @return The value associated with the specified key, or the computed value if the key is not
     *     present.
     */
    public static <K, V> V getOrComputeWithFunctionAddToValue(
            Map<K, V> map, K key, Function<K, V> function, BinaryOperator<V> combiner) {
        return map.compute(key, (k, v) -> (v == null) ? function.apply(k) : combiner.apply(v, function.apply(k)));
    }

    /**
     * Gets the value associated with the specified key from a map, or adds a default value if absent.
     *
     * <p>This method checks if the specified key is present in the map. If the key is present, it
     * returns the associated value. If the key is not present, it adds the key-value pair to the map
     * using the provided entry, and returns the default value.
     *
     * @param <K> The type of keys in the map.
     * @param <V> The type of values in the map.
     * @param map The map from which to retrieve or add the value.
     * @param entry The entry containing the key and the default value to be added if the key is not
     *     present.
     * @return The value associated with the specified key, or the default value if the key is not
     *     present.
     */
    public static <K, V> V getOrAddToMap(Map<K, V> map, AbstractMap.SimpleEntry<K, V> entry) {
        return map.computeIfAbsent(entry.getKey(), k -> entry.getValue());
    }

    /**
     * Gets the value associated with the specified key from a map, or adds a default value if absent.
     * If the key is present, adds the default value to the existing value using the specified
     * combiner.
     *
     * <p>This method checks if the specified key is present in the map. If the key is present, it
     * adds the default value to the existing value using the specified combiner and returns the
     * result. If the key is not present, it adds the key-value pair to the map using the provided
     * entry, and returns the default value.
     *
     * @param <K> The type of keys in the map.
     * @param <V> The type of values in the map.
     * @param map The map from which to retrieve or add the value.
     * @param entry The entry containing the key and the default value to be added if the key is not
     *     present.
     * @param combiner The combiner used to add the default value to the existing value if the key is
     *     present.
     * @return The value associated with the specified key, or the result of adding the default value
     *     to the existing value.
     */
    public static <K, V> V getOrAddToMapAddToValue(
            Map<K, V> map, AbstractMap.SimpleEntry<K, V> entry, BinaryOperator<V> combiner) {
        return map.compute(
                entry.getKey(), (k, v) -> (v == null) ? entry.getValue() : combiner.apply(v, entry.getValue()));
    }

    /**
     * Splits a string into an array of lines, excluding empty lines.
     *
     * @param input The input string.
     * @return An array of lines.
     */
    public static List<String> toLines(String input) {
        return Arrays.stream(input.split("[\\r\\n]+")).toList();
    }

    /**
     * Converts a list of strings to a list of integers.
     *
     * @param input The list of strings.
     * @return A list of integers.
     */
    public static List<Integer> asIntegers(List<String> input) {
        return input.stream().map(Integer::parseInt).toList();
    }

    /**
     * Converts an array of characters to a list of integers.
     *
     * @param input The array of characters.
     * @return A list of integers.
     */
    public static List<Integer> asIntegers(char[] input) {
        return new String(input).chars().mapToObj(Character::getNumericValue).toList();
    }

    /**
     * Tries to convert a list of strings to a list of integers, returning null for non-convertible
     * elements.
     *
     * @param input The list of strings.
     * @return A list of integers excluding null values for non-convertible elements.
     */
    public static List<Integer> tryAsIntegers(List<String> input) {
        return input.stream()
                .map(str -> {
                    try {
                        return Integer.parseInt(str);
                    } catch (NumberFormatException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }

    /**
     * Returns the total number of distinct characters between two strings.
     *
     * @param input The first string.
     * @param other The second string.
     * @return The total number of distinct characters between the two strings.
     */
    public static int distinctCharacterCount(String input, String other) {
        Set<Character> combinedChars = new HashSet<>();
        for (char ch : input.toCharArray()) {
            combinedChars.add(ch);
        }
        for (char ch : other.toCharArray()) {
            combinedChars.add(ch);
        }
        return combinedChars.size();
    }

    /**
     * Counts the number of indexes between two strings that have mismatched characters.
     *
     * @param input The first string.
     * @param other The second string.
     * @return The count of mismatched indexes.
     */
    public static int mismatchedIndexCount(String input, String other) {
        int minLength = Math.min(input.length(), other.length());
        int mismatchCount = 0;

        for (int i = 0; i < minLength; i++) {
            if (input.charAt(i) != other.charAt(i)) {
                mismatchCount++;
            }
        }

        // Add the remaining characters beyond the minimum length
        mismatchCount += Math.abs(input.length() - other.length());

        return mismatchCount;
    }

    /**
     * Returns an iterable of tuples containing the index and value of each element in the input.
     *
     * @param values The input values.
     * @param <T> The type of elements in the input.
     * @return An iterable of tuples containing the index and value.
     */
    public static <T> Iterable<Tuple<Integer, T>> withIndexes(Iterable<T> values) {
        int[] index = {0};
        return () -> values.iterator().hasNext()
                ? new Iterator<>() {
                    private final java.util.Iterator<T> iterator = values.iterator();

                    @Override
                    public boolean hasNext() {
                        return iterator.hasNext();
                    }

                    @Override
                    public Tuple<Integer, T> next() {
                        return new Tuple<>(index[0]++, iterator.next());
                    }
                }
                : Collections.emptyIterator();
    }

    /**
     * Filters out nullable values and converts tuples with non-null values to a list of integers.
     *
     * @param values Tuples containing an index and a nullable integer value.
     * @return A list of non-null integer values.
     */
    public static List<Integer> nonNull(List<Tuple<Integer, Integer>> values) {
        return values.stream()
                .filter(x -> x.getValue2() != null)
                .map(Tuple::getValue2)
                .toList();
    }

    /**
     * Converts an array of strings to a list of long integers.
     *
     * @param input The array of strings.
     * @return A list of long integers.
     */
    public static List<Long> asInt64s(String[] input) {
        return Arrays.stream(input).map(Long::parseLong).toList();
    }

    /**
     * Converts an array of strings to a tuple of specified types using provided mapping functions.
     *
     * @param input The array of strings.
     * @param p1 Mapping function for the first type.
     * @param p2 Mapping function for the second type.
     * @param <T1> The type of the first element in the tuple.
     * @param <T2> The type of the second element in the tuple.
     * @return A tuple of specified types.
     */
    public static <T1, T2> Tuple<T1, T2> as(String[] input, Function<String, T1> p1, Function<String, T2> p2) {
        return new Tuple<>(p1.apply(input[0]), p2.apply(input[1]));
    }

    /**
     * Converts an array of strings to a tuple of three specified types using provided mapping
     * functions.
     *
     * @param input The array of strings.
     * @param p1 Mapping function for the first type.
     * @param p2 Mapping function for the second type.
     * @param p3 Mapping function for the third type.
     * @param <T1> The type of the first element in the tuple.
     * @param <T2> The type of the second element in the tuple.
     * @param <T3> The type of the third element in the tuple.
     * @return A tuple of three specified types.
     */
    public static <T1, T2, T3> Tuple3<T1, T2, T3> as(
            String[] input, Function<String, T1> p1, Function<String, T2> p2, Function<String, T3> p3) {
        return new Tuple3<>(p1.apply(input[0]), p2.apply(input[1]), p3.apply(input[2]));
    }

    /**
     * Converts an array of strings to a tuple of specified types using provided mapping functions.
     *
     * @param input The array of strings.
     * @param p1 Mapping function for the first type.
     * @param p2 Mapping function for the second type.
     * @param p3 Mapping function for the third type.
     * @param p4 Mapping function for the fourth type.
     * @param <T1> The type of the first element in the tuple.
     * @param <T2> The type of the second element in the tuple.
     * @param <T3> The type of the third element in the tuple.
     * @param <T4> The type of the fourth element in the tuple.
     * @return A tuple of specified types.
     */
    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> as(
            String[] input,
            Function<String, T1> p1,
            Function<String, T2> p2,
            Function<String, T3> p3,
            Function<String, T4> p4) {
        return new Tuple4<>(p1.apply(input[0]), p2.apply(input[1]), p3.apply(input[2]), p4.apply(input[3]));
    }

    /**
     * Converts an iterable to a tuple of two elements.
     *
     * @param input The iterable.
     * @param <T> The type of elements in the iterable.
     * @return A tuple of two elements.
     */
    public static <T> Tuple<T, T> asValueTuple(Iterable<T> input) {
        java.util.Iterator<T> iterator = input.iterator();
        return new Tuple<>(iterator.next(), iterator.next());
    }

    /**
     * Splits a string by a specified delimiter.
     *
     * @param contents The input string.
     * @param splitBy The delimiter to split the string by.
     * @return An iterable of substrings.
     */
    public static Iterable<String> splitBy(String contents, String splitBy) {
        int splitLength = splitBy.length();
        int previousIndex = 0;
        int ix = contents.indexOf(splitBy);
        List<String> result = new ArrayList<>();
        while (ix >= 0) {
            result.add(contents.substring(previousIndex, ix));
            previousIndex = ix + splitLength;
            ix = contents.indexOf(splitBy, previousIndex);
        }
        String remain = contents.substring(previousIndex);
        if (!remain.isEmpty()) result.add(remain);
        return result;
    }

    /**
     * Converts an array of strings to a 2D array of booleans, where a specified character represents
     * true.
     *
     * @param lines The array of strings.
     * @param mapAsTrue The character representing true.
     * @return A 2D array of booleans.
     */
    public static Boolean[][] mapAsBool(String[] lines, char mapAsTrue) {
        return Arrays.stream(lines)
                .map(row -> row.chars().mapToObj(x -> x == mapAsTrue).toArray(Boolean[]::new))
                .toArray(Boolean[][]::new);
    }

    //  public static char[][] mapAsChar(String[] lines) {
    //    return Arrays.stream(lines)
    //        .map(row -> row.chars().mapToObj(x -> x == mapAsTrue).toArray(Boolean[]::new))
    //        .toArray(Boolean[][]::new);
    //  }

    /**
     * Finds the indices of true values in a 2D array of booleans.
     *
     * @param map The 2D array of booleans.
     * @return Indices of true values as tuples (row, col).
     */
    public static List<Map.Entry<Integer, Integer>> justTrue(boolean[][] map) {
        List<Map.Entry<Integer, Integer>> result = new ArrayList<>();
        for (int row = 0; row < map.length; ++row) {
            for (int col = 0; col < map[row].length; ++col) {
                if (map[row][col]) {
                    result.add(Map.entry(row, col));
                }
            }
        }
        return result;
    }

    /**
     * Adds a value to a list and returns the list.
     *
     * @param list The list.
     * @param value The value to add.
     * @param <T> The type of elements in the list.
     * @return The modified list.
     */
    public static <T> List<T> with(List<T> list, T value) {
        list.add(value);
        return list;
    }

    /**
     * Adds multiple values to a list and returns the list.
     *
     * @param list The list.
     * @param values The values to add.
     * @param <T> The type of elements in the list.
     * @return The modified list.
     */
    @SafeVarargs
    public static <T> List<T> with(List<T> list, T... values) {
        list.addAll(Arrays.asList(values));
        return list;
    }

    /**
     * Finds the smallest number that, when added to each offset in a list of offset-value pairs,
     * results in multiples of the corresponding values. Essentially, it solves a puzzle where you're
     * looking for a base number that, with different specified add-ons, fits neatly into different
     * sized containers (divisibility). For example, you want a number that, when 2 is added, fits
     * exactly into containers of size 3, when 3 is added, fits exactly into containers of size 5, and
     * so on.
     *
     * <p>This method sequentially tests numbers, increasing by a common factor each time until all
     * conditions are met for all offset-value pairs in the list. This common factor ensures that
     * previous fittings continue to work while searching for the next one.
     *
     * @param values The sequence of offset-value pairs (add-ons and container sizes).
     * @return The smallest base number that, when each offset is added to it, is exactly divisible by
     *     the corresponding value, resulting in no leftover space.
     */
    public static long getModWithOffset(List<Map.Entry<Integer, Integer>> values) {
        long value = 0; // Start with an initial value of 0, we're looking for the smallest number that fits.
        long step = 1; // Begin with the smallest common factor, so as not to miss any potential fits.

        for (Map.Entry<Integer, Integer> rec : values) {
            // Keep adding the step to 'value' until (value + offset) is divisible by 'value'.
            // This makes sure 'value' plus this 'offset' fits exactly into a container of this size
            // ('value').
            while ((value + rec.getKey()) % rec.getValue() != 0) {
                value += step; // Increase 'value' by the common factor to test the next potential number.
            }
            // After finding a fit, multiply the common factor by 'value' to maintain all previous fits
            // while searching for the next one. This works because 'value' is guaranteed to be a prime
            // number,
            // hence the lowest common multiple is just the product of the two.
            step *= rec.getValue();
        }
        // Return the discovered number that fits all conditions.
        return value;
    }

    /**
     * Calculates the greatest common factor of two integers.
     *
     * @param a The first integer.
     * @param b The second integer.
     * @return The greatest common factor.
     */
    public static int gcf(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * Calculates the greatest common factor of two longs.
     *
     * @param a The first long.
     * @param b The second long.
     * @return The greatest common factor.
     */
    public static long gcf(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * Calculates the least common multiple of two integers.
     *
     * @param a The first integer.
     * @param b The second integer.
     * @return The least common multiple.
     */
    public static int lcm(int a, int b) {
        return (a / gcf(a, b)) * b;
    }

    /**
     * Calculates the least common multiple of two longs.
     *
     * @param a The first long.
     * @param b The second long.
     * @return The least common multiple.
     */
    public static long lcm(long a, long b) {
        return ((a / gcf(a, b)) * b);
    }

    /**
     * Calculates the least common multiple of six longs.
     *
     * @param a The first long.
     * @param b The second long.
     * @param c The third long.
     * @param d The fourth long.
     * @param e The fifth long.
     * @param f The sixth long.
     * @return The least common multiple.
     */
    public static long lcmSix(long a, long b, long c, long d, long e, long f) {
        return lcm(lcm(lcm(lcm(lcm(a, b), c), d), e), f);
    }

    /**
     * Generates all permutations of a given size from a collection of items.
     *
     * @param items The collection of items.
     * @param count The size of permutations.
     * @param <T> The type of items.
     * @return All permutations of the given size.
     */
    public static <T> List<List<T>> getPermutations(List<T> items, int count) {
        return getPermutationsHelper(items, count, 0);
    }

    private static <T> List<List<T>> getPermutationsHelper(List<T> items, int count, int start) {
        if (count == 0) {
            return Collections.singletonList(Collections.emptyList());
        }

        List<List<T>> result = new ArrayList<>();
        for (int i = start; i < items.size(); i++) {
            T currentItem = items.get(i);
            List<T> remainingItems = new ArrayList<>(items.subList(0, i));
            remainingItems.addAll(items.subList(i + 1, items.size()));

            List<List<T>> permutations = getPermutationsHelper(remainingItems, count - 1, 0);

            for (List<T> permutation : permutations) {
                List<T> current = new ArrayList<>();
                current.add(currentItem);
                current.addAll(permutation);
                result.add(current);
            }
        }

        return result;
    }

    // Tuple class to hold two values
    @Getter
    public static class Tuple<A, B> {
        private final A value1;
        private final B value2;

        public Tuple(A value1, B value2) {
            this.value1 = value1;
            this.value2 = value2;
        }
    }

    // Tuple class to hold three values
    @Getter
    public static class Tuple3<A, B, C> {
        private final A value1;
        private final B value2;
        private final C value3;

        public Tuple3(A value1, B value2, C value3) {
            this.value1 = value1;
            this.value2 = value2;
            this.value3 = value3;
        }
    }

    // Tuple class to hold four values
    @Getter
    public static class Tuple4<A, B, C, D> {
        private final A value1;
        private final B value2;
        private final C value3;
        private final D value4;

        public Tuple4(A value1, B value2, C value3, D value4) {
            this.value1 = value1;
            this.value2 = value2;
            this.value3 = value3;
            this.value4 = value4;
        }
    }

    /** Utility class for representing directions in a 2D grid. */
    public static class Direction {

        private Direction() {}

        public static final int UP_X = 0;
        public static final int UP_Y = -1;
        public static final int RIGHT_X = 1;
        public static final int RIGHT_Y = 0;
        public static final int DOWN_X = 0;
        public static final int DOWN_Y = 1;
        public static final int LEFT_X = -1;
        public static final int LEFT_Y = 0;

        /** Represents the UP direction. */
        protected static final java.awt.Point UP = new java.awt.Point(UP_X, UP_Y);

        /** Represents the RIGHT direction. */
        protected static final Point RIGHT = new Point(RIGHT_X, RIGHT_Y);

        /** Represents the DOWN direction. */
        protected static final Point DOWN = new Point(DOWN_X, DOWN_Y);

        /** Represents the LEFT direction. */
        protected static final Point LEFT = new Point(LEFT_X, LEFT_Y);

        /** Represents the UP_LEFT direction. */
        protected static final java.awt.Point UP_LEFT = new java.awt.Point(UP.x + LEFT.x, UP.y + LEFT.y);

        /** Represents the UP_RIGHT direction. */
        protected static final java.awt.Point UP_RIGHT = new java.awt.Point(UP.x + RIGHT.x, UP.y + RIGHT.y);

        /** Represents the DOWN_LEFT direction. */
        protected static final java.awt.Point DOWN_LEFT = new java.awt.Point(DOWN.x + LEFT.x, DOWN.y + LEFT.y);

        /** Represents the DOWN_RIGHT direction. */
        protected static final java.awt.Point DOWN_RIGHT = new java.awt.Point(DOWN.x + RIGHT.x, DOWN.y + RIGHT.y);
    }

    /** Utility class for grouping different sets of directions in a 2D grid. */
    public static class Directions {

        private Directions() {}

        /** Represents horizontal directions (RIGHT and LEFT). */
        protected static final java.awt.Point[] Horizontal = {Direction.RIGHT, Direction.LEFT};

        /** Represents vertical directions (UP and DOWN). */
        protected static final java.awt.Point[] Vertical = {Direction.UP, Direction.DOWN};

        /** Represents diagonal directions (UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT). */
        protected static final java.awt.Point[] Diagonal = {
            Direction.UP_LEFT, Direction.UP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT
        };

        /** Represents horizontal and vertical directions (UP, RIGHT, DOWN, LEFT). */
        protected static final java.awt.Point[] HorizontalAndVertical = {
            Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT
        };

        /**
         * Represents horizontal, vertical, and diagonal directions (UP, RIGHT, DOWN, LEFT, UP_LEFT,
         * UP_RIGHT, DOWN_LEFT, DOWN_RIGHT).
         */
        protected static final java.awt.Point[] HorizontalVerticalAndDiagonal = {
            Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT,
            Direction.UP_LEFT, Direction.UP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT
        };
    }

    /** Utility class for calculating Manhattan Distance between two points in a 2D grid. */
    public static class DistanceUtils {

        private DistanceUtils() {}

        /**
         * Calculates the Manhattan Distance between two points.
         *
         * @param sensor The coordinates of the sensor.
         * @param beacon The coordinates of the beacon.
         * @return The Manhattan Distance between the sensor and the beacon.
         */
        public static int manhattanDistance(java.awt.Point sensor, java.awt.Point beacon) {
            return Math.abs(sensor.x - beacon.x) + Math.abs(sensor.y - beacon.y);
        }
    }
}
