package org.jmitchell238.aoc.aoc2023.day07;

import static org.jmitchell238.aoc.generalutilities.LogHelper.logOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import org.jmitchell238.aoc.generalutilities.LogLevel;

@SuppressWarnings({"java:S106", "java:S1118", "java:S1940", "java:S2589", "java:S100", "java:S3776", "java:S127"})
public class Day07 {
    // Configuration flags
    @SuppressWarnings("ConstantConditions")
    private static final boolean ENABLE_DEBUG_LOGGING = false;

    @SuppressWarnings({"ConstantConditions", "unused"})
    private static final boolean ENABLE_VERBOSE_LOGGING = false;

    private static final TreeMap<char[], Integer> fiveOfAKind = new TreeMap<>(new ArrayComparator());
    private static final TreeMap<char[], Integer> fourOfAKind = new TreeMap<>(new ArrayComparator());
    private static final TreeMap<char[], Integer> fullHouse = new TreeMap<>(new ArrayComparator());
    private static final TreeMap<char[], Integer> threeOfAKind = new TreeMap<>(new ArrayComparator());
    private static final TreeMap<char[], Integer> twoPairs = new TreeMap<>(new ArrayComparator());
    private static final TreeMap<char[], Integer> onePair = new TreeMap<>(new ArrayComparator());
    private static final TreeMap<char[], Integer> highCard = new TreeMap<>(new ArrayComparator());

    private boolean isPart1 = true;

    private static final Map<Character, Integer> cardValues = new HashMap<>();

    static {
        cardValues.put('A', 14);
        cardValues.put('K', 13);
        cardValues.put('Q', 12);
        cardValues.put('T', 10);
        cardValues.put('9', 9);
        cardValues.put('8', 8);
        cardValues.put('7', 7);
        cardValues.put('6', 6);
        cardValues.put('5', 5);
        cardValues.put('4', 4);
        cardValues.put('3', 3);
        cardValues.put('2', 2);
    }

    public static void reset() {
        fiveOfAKind.clear();
        fourOfAKind.clear();
        fullHouse.clear();
        threeOfAKind.clear();
        twoPairs.clear();
        onePair.clear();
        highCard.clear();
        cardValues.remove('J');
    }

    // Java 25-friendly main; suppress unused args
    @SuppressWarnings({"unused", "java:S1172"})
    public static void main(String[] args) {
        new Day07().runDay07();
    }

    public boolean getIsPart1() {
        return isPart1;
    }

    public void setIsPart1(boolean isPart1) {
        this.isPart1 = isPart1;
    }

    public void runDay07() {
        logOutput(LogLevel.INFO, true, "\n--- Day 7: Camel Cards ---\n");

        String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2023/day07/input_test.txt";

        long partOneAnswer = part1(inputTest);
        logOutput(LogLevel.INFO, true, "Part 1: Answer: " + partOneAnswer);

        long partTwoAnswer = part2(inputTest);
        logOutput(LogLevel.INFO, true, "Part 2: Answer: " + partTwoAnswer);
    }

    public long part1(String filePath) {
        setIsPart1(true);
        cardValues.put('J', 11);

        processInput(filePath);

        return getTotalValueFromHands();
    }

    public long part2(String filePath) {
        setIsPart1(false);
        cardValues.put('J', 1);

        processInput(filePath);

        return getTotalValueFromHands();
    }

    private void processInput(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, line);

                String[] lineSplit = line.split(" ");
                logOutput(
                        LogLevel.DEBUG,
                        ENABLE_DEBUG_LOGGING,
                        "Hand: " + lineSplit[0] + " | Bid: " + lineSplit[1] + " | ");

                char[] hand = parseHandFromString(lineSplit[0]);

                // Directly log; logOutput respects ENABLE_DEBUG_LOGGING
                logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, formatHandForLog(hand));

                int handValue = Integer.parseInt(lineSplit[1]);
                logOutput(LogLevel.DEBUG, ENABLE_DEBUG_LOGGING, Integer.toString(handValue));

                String type = getHandType(hand);

                addHandToMap(hand, handValue, type);
            }
        } catch (FileNotFoundException _) {
            String errorMessage = "File not found, " + filePath;
            logOutput(LogLevel.ERROR, true, errorMessage);
        }
    }

    // Helper: parse a 5-card hand from string
    private static char[] parseHandFromString(String handString) {
        char[] hand = new char[5];
        for (int i = 0; i < 5; i++) {
            hand[i] = handString.charAt(i);
        }
        return hand;
    }

    // Helper: format hand for debug logging
    private static String formatHandForLog(char[] hand) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hand.length; i++) {
            sb.append(hand[i]);
            if (i < hand.length - 1) sb.append(", ");
        }
        return sb.toString();
    }

    private Map<Character, Integer> countOccurrences(char[] hand) {
        Map<Character, Integer> cardCountMap = new HashMap<>();

        for (char card : hand) {
            cardCountMap.put(card, cardCountMap.getOrDefault(card, 0) + 1);
        }

        if (getIsPart1() || !cardCountMap.containsKey('J')) {
            return cardCountMap;
        } else {
            if (cardCountMap.containsKey('J')) {
                int jokerCount = cardCountMap.get('J');
                if (jokerCount == 5) {
                    return cardCountMap;
                }
                cardCountMap.remove('J');

                int highestCardCount =
                        cardCountMap.values().stream().max(Integer::compare).orElse(0);
                char highestCardCountKey = resolveHighestCardKey(cardCountMap, highestCardCount);
                cardCountMap.put(highestCardCountKey, cardCountMap.get(highestCardCountKey) + jokerCount);
            }
        }

        return cardCountMap;
    }

    // Helper: resolve which card to boost by jokerCount
    private static char resolveHighestCardKey(Map<Character, Integer> cardCountMap, int highestCardCount) {
        if (highestCardCount == 1) {
            return cardCountMap.keySet().stream()
                    .max(java.util.Comparator.comparingInt(cardValues::get))
                    .orElse('A');
        } else {
            return cardCountMap.entrySet().stream()
                    .filter(entry -> entry.getValue() == highestCardCount)
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse('A');
        }
    }

    private String getHandType(char[] hand) {
        Map<Character, Integer> cardCountMap = countOccurrences(hand);

        if (cardCountMap.containsValue(5)) {
            return "Five of a Kind";
        } else if (cardCountMap.containsValue(4)) {
            return "Four of a Kind";
        } else if (cardCountMap.containsValue(3) && cardCountMap.containsValue(2)) {
            return "Full House";
        } else if (cardCountMap.containsValue(3)) {
            return "Three of a Kind";
        } else if (cardCountMap.containsValue(2) && cardCountMap.size() == 3) {
            return "Two Pairs";
        } else if (cardCountMap.containsValue(2)) {
            return "One Pair";
        } else {
            return "High Card";
        }
    }

    private void addHandToMap(char[] hand, int handValue, String type) {
        switch (type) {
            case "Five of a Kind":
                fiveOfAKind.put(hand, handValue);
                break;
            case "Four of a Kind":
                fourOfAKind.put(hand, handValue);
                break;
            case "Full House":
                fullHouse.put(hand, handValue);
                break;
            case "Three of a Kind":
                threeOfAKind.put(hand, handValue);
                break;
            case "Two Pairs":
                twoPairs.put(hand, handValue);
                break;
            case "One Pair":
                onePair.put(hand, handValue);
                break;
            case "High Card":
                highCard.put(hand, handValue);
                break;
            default:
                logOutput(LogLevel.ERROR, true, "Error: Hand type not found");
                break;
        }
    }

    private long getTotalValueFromHands() {
        long totalValue = 0L;
        long multiplier = 1L;

        // Process hand types in ranking order
        List<TreeMap<char[], Integer>> handTypesInOrder =
                List.of(highCard, onePair, twoPairs, threeOfAKind, fullHouse, fourOfAKind, fiveOfAKind);

        for (TreeMap<char[], Integer> handType : handTypesInOrder) {
            totalValue += getTotalValueFromHandType(handType, multiplier);
            int size = handType.size();
            if (size > 0) {
                multiplier += size;
            }
        }

        return totalValue;
    }

    private long getTotalValueFromHandType(TreeMap<char[], Integer> handType, long multiplier) {
        long totalValue = 0L;

        long[] handValues = handType.values().stream().mapToLong(i -> i).toArray();

        if (handType.isEmpty()) return totalValue;

        for (long value : handValues) {
            totalValue += value * multiplier;
            multiplier++;
        }

        return totalValue;
    }

    static class ArrayComparator implements Comparator<char[]> {
        @Override
        public int compare(char[] arr1, char[] arr2) {
            int minLength = Math.min(arr1.length, arr2.length);

            for (int i = 0; i < minLength; i++) {
                char char1 = arr1[i];
                int char1Value = cardValues.get(char1);
                char char2 = arr2[i];
                int char2Value = cardValues.get(char2);

                if (char1Value != char2Value) {
                    return Integer.compare(char1Value, char2Value);
                }
            }

            // If one array is a prefix of the other, consider the shorter one as smaller
            return Integer.compare(arr1.length, arr2.length);
        }
    }
}
