package org.jmitchell238.aoc.aoc2023.utilities;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Point;
import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import org.jmitchell238.aoc.aoc2023.utilities.Utilities.Direction;
import org.jmitchell238.aoc.aoc2023.utilities.Utilities.Directions;
import org.jmitchell238.aoc.aoc2023.utilities.Utilities.DistanceUtils;
import org.jmitchell238.aoc.aoc2023.utilities.Utilities.Tuple;
import org.jmitchell238.aoc.aoc2023.utilities.Utilities.Tuple3;
import org.jmitchell238.aoc.aoc2023.utilities.Utilities.Tuple4;
import org.junit.jupiter.api.Test;

class UtilitiesTests {

    @Test
    void splitToStringArray_WithStringDelimiterAndRemoveEmptyFalse_ReturnsCorrectAnswer() {
        // Arrange
        String input = "one, two, , three";
        String delimiter = ",";
        Boolean removeEmptyStrings = false;
        String[] expected = {"one", "two", "", "three"};

        // Assert
        String[] actual = Utilities.splitToStringArray(input, delimiter, removeEmptyStrings);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void splitToStringArray_WithStringDelimiterAndRemoveEmptyTrue_ReturnsCorrectAnswer() {
        // Arrange
        String input = "one, two, , three";
        String delimiter = ",";
        Boolean removeEmptyStrings = true;
        String[] expected = {"one", "two", "three"};

        // Assert
        String[] actual = Utilities.splitToStringArray(input, delimiter, removeEmptyStrings);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void splitToStringArray_WithCharDelimiterAndRemoveEmptyFalse_ReturnsCorrectAnswer() {
        // Arrange
        String input = "one, two, , three";
        char[] delimiter = {','};
        Boolean removeEmptyStrings = false;
        String[] expected = {"one", "two", "", "three"};

        // Assert
        String[] actual = Utilities.splitToStringArray(input, delimiter, removeEmptyStrings);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void splitToStringArray_WithCharDelimiterAndRemoveEmptyTrue_ReturnsCorrectAnswer() {
        // Arrange
        String input = "one, two, , three";
        char[] delimiter = {','};
        Boolean removeEmptyStrings = true;
        String[] expected = {"one", "two", "three"};

        // Assert
        String[] actual = Utilities.splitToStringArray(input, delimiter, removeEmptyStrings);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void splitToIntArray_WithStringDelimiter_ReturnsCorrectAnswer() {
        // Arrange
        String input = "1, 2, 3";
        String delimiter = ",";
        int[] expected = {1, 2, 3};

        // Assert
        int[] actual = Utilities.splitToIntArray(input, delimiter);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void splitToIntArray_WithCharDelimiter_ReturnsCorrectAnswer() {
        // Arrange
        String input = "1, 2, 3";
        char[] delimiter = {','};
        int[] expected = {1, 2, 3};

        // Assert
        int[] actual = Utilities.splitToIntArray(input, delimiter);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void splitToLongArray_WithStringDelimiter_ReturnsCorrectAnswer() {
        // Arrange
        String input = "1, 2, 3";
        String delimiter = ",";
        long[] expected = {1, 2, 3};

        // Assert
        long[] actual = Utilities.splitToLongArray(input, delimiter);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void splitToLongArray_WithCharDelimiter_ReturnsCorrectAnswer() {
        // Arrange
        String input = "1, 2, 3";
        char[] delimiter = {','};
        long[] expected = {1, 2, 3};

        // Assert
        long[] actual = Utilities.splitToLongArray(input, delimiter);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void splitToDoubleArray_WithStringDelimiter_ReturnsCorrectAnswer() {
        // Arrange
        String input = "1, 2, 3";
        String delimiter = ",";
        double[] expected = {1, 2, 3};

        // Assert
        double[] actual = Utilities.splitToDoubleArray(input, delimiter);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void splitToDoubleArray_WithCharDelimiter_ReturnsCorrectAnswer() {
        // Arrange
        String input = "1, 2, 3";
        char[] delimiter = {','};
        double[] expected = {1, 2, 3};

        // Assert
        double[] actual = Utilities.splitToDoubleArray(input, delimiter);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void read_CalledWihtoutKeyValuePresent_ReturnsCorrectValue() {
        // Arrange
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);

        // Act
        Integer actual = Utilities.read(map, "three");

        // Assert
        assertThat(actual).isNull();
    }

    @Test
    void read_CalledWithKeyValuePresent_ReturnsCorrectValue() {
        // Arrange
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);

        // Act
        Integer actual = Utilities.read(map, "two");

        // Assert
        assertThat(actual).isEqualTo(2);
    }

    @Test
    void createStack_Called_ReturnsCorrectAnswer() {
        // Arrange
        String input = "example";
        Deque<Character> expected = new ArrayDeque<>() {
            {
                push('e');
                push('x');
                push('a');
                push('m');
                push('p');
                push('l');
                push('e');
            }
        };

        // Assert
        Deque<Character> actual = Utilities.createStack(input);

        // Act
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getOrComputeWithFunction_CalledWithoutExistingKeyValuePair_ReturnsValueInserted() {
        // Arrange
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);

        // Act
        Integer actual = Utilities.getOrComputeWithFunction(map, "three", String::length);

        // Assert
        assertThat(actual).isEqualTo(5);
    }

    @Test
    void getOrComputeWithFunction_CalledWithExistingKeyValuePair_ReturnsExistingValue() {
        // Arrange
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("eight", 3);

        // Act
        Integer actual = Utilities.getOrComputeWithFunction(map, "eight", String::length);

        // Assert
        assertThat(actual).isEqualTo(3);
    }

    @Test
    void getOrComputeWithFunctionAddToValue_CalledWithoutExistingKeyValuePair_ReturnsValueInserted() {
        // Arrange
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        BinaryOperator<Integer> combiner = Integer::sum;

        // Act
        Integer actual = Utilities.getOrComputeWithFunctionAddToValue(map, "three", String::length, combiner);

        // Assert
        assertThat(actual).isEqualTo(5);
    }

    @Test
    void getOrComputeWithFunctionAddToValue_CalledWithExistingKeyValuePair_ReturnsSummedValue() {
        // Arrange
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("eight", 4);
        BinaryOperator<Integer> combiner = Integer::sum;

        // Act
        Integer actual = Utilities.getOrComputeWithFunctionAddToValue(map, "eight", String::length, combiner);

        // Assert
        assertThat(actual).isEqualTo(9);
    }

    @Test
    void getOrAddToMap_CalledWithoutExistingKeyValuePair_ReturnsCorrectValue() {
        // Arrange
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        AbstractMap.SimpleEntry<String, Integer> entry = new AbstractMap.SimpleEntry<>("three", 3);

        // Act
        Integer actual = Utilities.getOrAddToMap(map, entry);

        // Assert
        assertThat(actual).isEqualTo(3);
    }

    @Test
    void getOrAddToMap_CalledWithExistingKeyValuePair_ReturnsCorrectValue() {
        // Arrange
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        AbstractMap.SimpleEntry<String, Integer> entry = new AbstractMap.SimpleEntry<>("two", 3);

        // Act
        Integer actual = Utilities.getOrAddToMap(map, entry);

        // Assert
        assertThat(actual).isEqualTo(2);
    }

    @Test
    void getOrAddToMapAddToValue_WithoutExistingKeyValuePair_ReturnsCorrectVlaue() {
        // Arrange
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        AbstractMap.SimpleEntry<String, Integer> entry = new AbstractMap.SimpleEntry<>("three", 3);
        BinaryOperator<Integer> combiner = Integer::sum;

        // Act
        Integer actual = Utilities.getOrAddToMapAddToValue(map, entry, combiner);

        // Assert
        assertThat(actual).isEqualTo(3);
    }

    @Test
    void getOrAddToMapAddToValue_WithExistingKeyValuePair_ReturnsCorrectVlaue() {
        // Arrange
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        AbstractMap.SimpleEntry<String, Integer> entry = new AbstractMap.SimpleEntry<>("two", 3);
        BinaryOperator<Integer> combiner = Integer::sum;

        // Act
        Integer actual = Utilities.getOrAddToMapAddToValue(map, entry, combiner);

        // Assert
        assertThat(actual).isEqualTo(5);
    }

    @Test
    void toLines_CalledWithMultiLineString_ReturnsListOfStrings() {
        // Arrange
        String input = "one\ntwo\nthree";
        List<String> expected = List.of("one", "two", "three");

        // Assert
        List<String> actual = Utilities.toLines(input);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void asIntegers_CalledWithListOfStrings_ReturnsListOfIntegers() {
        // Arrange
        List<String> input = List.of("1", "2", "3");
        List<Integer> expected = List.of(1, 2, 3);

        // Assert
        List<Integer> actual = Utilities.asIntegers(input);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void asIntegers_CalledWithCharArray_ReturnsListOfIntegers() {
        // Arrange
        char[] input = {'1', '2', '3'};
        List<Integer> expected = List.of(1, 2, 3);

        // Assert
        List<Integer> actual = Utilities.asIntegers(input);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void tryAsIntegers_CalledWithListOfStrings_ReturnsCorrectList() {
        // Arrange
        List<String> input = List.of("1", "2", "3");
        List<Integer> expected = List.of(1, 2, 3);

        // Assert
        List<Integer> actual = Utilities.tryAsIntegers(input);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void tryAsIntegers_CalledWithBadList_ReturnsEmptyList() {
        // Arrange
        List<String> input = List.of("1", "2", "3", "a");
        List<Integer> expected = List.of(1, 2, 3);

        // Assert
        List<Integer> actual = Utilities.tryAsIntegers(input);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void distinctCharacterCount_CalledWithInputAndOther_ReturnsCorrectCountofDistinctCharactersSummed() {
        // Arrange
        String input = "abcde";
        String other = "axcye";
        int expected = 7;

        // Assert
        int actual = Utilities.distinctCharacterCount(input, other);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void mismatchedIndexCount_CalledWithInputAndOther_ReturnsCorrectCountofMismatchedIndexes() {
        // Arrange
        String input = "fghij";
        String other = "fguij";
        int expected = 1;

        // Assert
        int actual = Utilities.mismatchedIndexCount(input, other);

        // Act
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void withIndexes_CalledWithIterable_ReturnsCorrectTuples() {
        // Arrange
        List<String> values = Arrays.asList("apple", "banana", "cherry");
        List<Tuple<Integer, String>> expectedTuples =
                Arrays.asList(new Tuple<>(0, "apple"), new Tuple<>(1, "banana"), new Tuple<>(2, "cherry"));

        // Act
        Iterable<Tuple<Integer, String>> result = Utilities.withIndexes(values);

        // Assert
        List<Tuple<Integer, String>> resultTuples = new ArrayList<>();
        result.forEach(resultTuples::add);

        assertThat(resultTuples).usingRecursiveComparison().isEqualTo(expectedTuples);
    }

    @Test
    void nonNull_CalledWithTuples_ReturnsListWithoutNullValues() {
        // Arrange
        List<Tuple<Integer, Integer>> inputTuples = Arrays.asList(
                new Tuple<>(0, 10), new Tuple<>(1, null), new Tuple<>(2, 20), new Tuple<>(3, null), new Tuple<>(4, 30));

        List<Integer> expectedOutput = Arrays.asList(10, 20, 30);

        // Act
        List<Integer> result = Utilities.nonNull(inputTuples);

        // Assert
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedOutput);
    }

    @Test
    void asInt64s_CalledWithArrayOfStrings_ReturnsListOfLongIntegers() {
        // Arrange
        String[] inputStrings = {"10", "20", "30"};
        List<Long> expectedOutput = Arrays.asList(10L, 20L, 30L);

        // Act
        List<Long> result = Utilities.asInt64s(inputStrings);

        // Assert
        assertThat(result).isEqualTo(expectedOutput);
    }

    @Test
    void as_CalledWithArrayOfStringsAndMappingFunctionsNormalTuple_ReturnsTupleOfSpecifiedTypes() {
        // Arrange
        String[] inputStrings = {"10", "apple"};

        // Mapping functions for the types
        Function<String, Integer> parseInt = Integer::parseInt;
        Function<String, String> identity = Function.identity();

        // Act
        Tuple<Integer, String> result = Utilities.as(inputStrings, parseInt, identity);

        // Assert
        Tuple<Integer, String> expectedTuple = new Tuple<>(10, "apple");
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedTuple);
    }

    @Test
    void as_CalledWithArrayOfStringsAndMappingFunctionsTripleTuple_ReturnsTupleOfThreeSpecifiedTypes() {
        // Arrange
        String[] inputStrings = {"10", "apple", "true"};

        // Mapping functions for the types
        Function<String, Integer> parseInt = Integer::parseInt;
        Function<String, String> identity = Function.identity();
        Function<String, Boolean> parseBoolean = Boolean::parseBoolean;

        // Act
        Tuple3<Integer, String, Boolean> result = Utilities.as(inputStrings, parseInt, identity, parseBoolean);

        // Assert
        Tuple3<Integer, String, Boolean> expectedTuple = new Tuple3<>(10, "apple", true);
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedTuple);
    }

    @Test
    void as_CalledWithArrayOfStringsAndMappingFunctionsQuadTuple_ReturnsTupleOfFourSpecifiedTypes() {
        // Arrange
        String[] inputStrings = {"10", "apple", "true", "3.14"};

        // Mapping functions for the types
        Function<String, Integer> parseInt = Integer::parseInt;
        Function<String, String> identity = Function.identity();
        Function<String, Boolean> parseBoolean = Boolean::parseBoolean;
        Function<String, Double> parseDouble = Double::parseDouble;

        // Act
        Tuple4<Integer, String, Boolean, Double> result =
                Utilities.as(inputStrings, parseInt, identity, parseBoolean, parseDouble);

        // Assert
        Tuple4<Integer, String, Boolean, Double> expectedTuple = new Tuple4<>(10, "apple", true, 3.14);
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedTuple);
    }

    @Test
    void asValueTuple_CalledWithIterable_ReturnsTupleOfTwoElements() {
        // Arrange
        List<Integer> inputList = Arrays.asList(10, 20);

        // Act
        Tuple<Integer, Integer> result = Utilities.asValueTuple(inputList);

        // Assert
        Tuple<Integer, Integer> expectedTuple = new Tuple<>(10, 20);
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedTuple);
    }

    @Test
    void splitBy_CalledWithStringAndDelimiter_ReturnsIterableOfSubstrings() {
        // Arrange
        String inputString = "apple,banana,orange";
        String delimiter = ",";

        // Act
        Iterable<String> result = Utilities.splitBy(inputString, delimiter);

        // Convert Iterable to List for easier comparison
        List<String> resultList = iterableToList(result);

        // Assert
        List<String> expectedList = Arrays.asList("apple", "banana", "orange");
        assertThat(resultList).isEqualTo(expectedList);
    }

    private static <T> List<T> iterableToList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        for (T element : iterable) {
            list.add(element);
        }
        return list;
    }

    @Test
    void mapAsBool_CalledWithArrayOfStringsAndCharacter_Returns2DArrayOfBooleans() {
        // Arrange
        String[] inputLines = {"truefalse", "falsetrue", "falsetrue"};
        char mapAsTrue = 't';

        // Act
        Boolean[][] result = Utilities.mapAsBool(inputLines, mapAsTrue);

        // Assert
        Boolean[][] expectedArray = {
            {true, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, true, false, false, false},
            {false, false, false, false, false, true, false, false, false}
        };
        assertThat(result).isEqualTo(expectedArray);
    }

    @Test
    void justTrue_CalledWith2DArrayOfBooleans_ReturnsListOfTrueIndices() {
        // Arrange
        boolean[][] inputMap = {
            {true, false, false, false, false},
            {false, true, true, false, false},
            {false, false, false, true, false},
            {false, false, false, false, true}
        };

        // Act
        List<Map.Entry<Integer, Integer>> result = Utilities.justTrue(inputMap);

        // Assert
        List<Map.Entry<Integer, Integer>> expectedList =
                List.of(Map.entry(0, 0), Map.entry(1, 1), Map.entry(1, 2), Map.entry(2, 3), Map.entry(3, 4));
        assertThat(result).isEqualTo(expectedList);
    }

    @Test
    void with_CalledWithListAndValue_ReturnsModifiedList() {
        // Arrange
        List<String> inputList = new ArrayList<>();
        String valueToAdd = "example";

        // Act
        List<String> result = Utilities.with(inputList, valueToAdd);

        // Assert
        assertThat(result.get(0)).isEqualTo(valueToAdd);
        assertThat(result).hasSize(1);
    }

    @Test
    void with_CalledWithListAndMultipleValues_ReturnsModifiedList() {
        // Arrange
        List<String> inputList = new ArrayList<>();
        String value1 = "example1";
        String value2 = "example2";
        String value3 = "example3";

        // Act
        List<String> result = Utilities.with(inputList, value1, value2, value3);

        // Assert
        assertThat(result).hasSize(3);
        assertThat(result.get(0)).isEqualTo(value1);
        assertThat(result.get(1)).isEqualTo(value2);
        assertThat(result.get(2)).isEqualTo(value3);
    }

    @Test
    void getModWithOffset_CalledWithListOfOffsetValuePairs_ReturnsCalculatedModularValueWithOffset() {
        // Arrange
        List<Map.Entry<Integer, Integer>> inputValues = Arrays.asList(
                new AbstractMap.SimpleEntry<>(2, 3),
                new AbstractMap.SimpleEntry<>(3, 5),
                new AbstractMap.SimpleEntry<>(2, 7));

        // Act
        long result = Utilities.getModWithOffset(inputValues);

        // Assert
        assertThat(result).isEqualTo(82);
    }

    @Test
    void gcf_CalledWithTwoIntegers_ReturnsGreatestCommonFactor() {
        // Arrange
        int a = 24;
        int b = 36;

        // Act
        int result = Utilities.gcf(a, b);

        // Assert
        assertThat(result).isEqualTo(12);
    }

    @Test
    void gcf_CalledWithTwoLongs_ReturnsGreatestCommonFactor() {
        // Arrange
        long a = 24;
        long b = 36;

        // Act
        long result = Utilities.gcf(a, b);

        // Assert
        assertThat(result).isEqualTo(12);
    }

    @Test
    void lcm_CalledWithTwoIntegers_ReturnsLeastCommonMultiple() {
        // Arrange
        int a = 12;
        int b = 18;

        // Act
        int result = Utilities.lcm(a, b);

        // Assert
        assertThat(result).isEqualTo(36);
    }

    @Test
    void lcm_CalledWithTwoLongs_ReturnsLeastCommonMultiple() {
        // Arrange
        long a = 12;
        long b = 18;

        // Act
        long result = Utilities.lcm(a, b);

        // Assert
        assertThat(result).isEqualTo(36);
    }

    @Test
    void lcmSix_CalledWithSixLongs_ReturnsLeastCommonMultiple() {
        // Arrange
        long a = 12;
        long b = 18;
        long c = 24;
        long d = 36;
        long e = 48;
        long f = 72;

        // Act
        long result = Utilities.lcmSix(a, b, c, d, e, f);

        // Assert
        assertThat(result).isEqualTo(144);
    }

    @Test
    void getPermutations_CalledWithItemsAndCount_ReturnsAllPermutations() {
        // Arrange
        List<Integer> items = Arrays.asList(1, 2, 3);
        int count = 2;

        // Act
        List<List<Integer>> result = Utilities.getPermutations(items, count);

        // Assert
        assertThat(result)
                .hasSize(6) // n! / (n - r)! = 3! / (3 - 2)! = 6
                .containsAll(List.of( // Ensure some expected permutations are present
                        List.of(1, 2), List.of(1, 3), List.of(2, 1), List.of(2, 3), List.of(3, 1), List.of(3, 2)));
    }

    @Test
    void directionsArrays_HaveCorrectValues() {
        // Assert
        assertThat(Directions.Horizontal).isEqualTo(new Point[] {Direction.RIGHT, Direction.LEFT});
        assertThat(Directions.Vertical).isEqualTo(new Point[] {Direction.UP, Direction.DOWN});
        assertThat(Directions.Diagonal)
                .isEqualTo(
                        new Point[] {Direction.UP_LEFT, Direction.UP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT});
        assertThat(Directions.HorizontalAndVertical)
                .isEqualTo(new Point[] {Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT});
        assertThat(Directions.HorizontalVerticalAndDiagonal).isEqualTo(new Point[] {
            Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT,
            Direction.UP_LEFT, Direction.UP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT
        });
    }

    @Test
    void direction_HaveCorrectValues() {
        // Assert
        assertThat(new Point(0, -1)).isEqualTo(Direction.UP);
        assertThat(new Point(1, 0)).isEqualTo(Direction.RIGHT);
        assertThat(new Point(0, 1)).isEqualTo(Direction.DOWN);
        assertThat(new Point(-1, 0)).isEqualTo(Direction.LEFT);

        assertThat(new Point(-1, -1)).isEqualTo(Direction.UP_LEFT);
        assertThat(new Point(1, -1)).isEqualTo(Direction.UP_RIGHT);
        assertThat(new Point(-1, 1)).isEqualTo(Direction.DOWN_LEFT);
        assertThat(new Point(1, 1)).isEqualTo(Direction.DOWN_RIGHT);
    }

    @Test
    void manhattanDistance_CalculatesCorrectDistance() {
        // Arrange
        java.awt.Point sensor = new java.awt.Point(3, 5);
        java.awt.Point beacon = new java.awt.Point(7, 2);

        // Act
        int result = DistanceUtils.manhattanDistance(sensor, beacon);

        // Assert
        assertThat(result).isEqualTo(7);
    }
}
