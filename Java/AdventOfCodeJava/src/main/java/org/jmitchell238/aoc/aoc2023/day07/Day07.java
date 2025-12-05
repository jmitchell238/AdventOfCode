package org.jmitchell238.aoc.aoc2023.day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Day07 {
    private static final Boolean DEBUGGING = false;
    private static final Boolean VERBOSE = false;
    private static final TreeMap<char[], Integer> fiveOfAKind = new TreeMap<>(new ArrayComparator());
    private static final TreeMap<char[], Integer> fourOfAKind = new TreeMap<>(new ArrayComparator());
    private static final TreeMap<char[], Integer> fullHouse = new TreeMap<>(new ArrayComparator());
    private static final TreeMap<char[], Integer> threeOfAKind = new TreeMap<>(new ArrayComparator());
    private static final TreeMap<char[], Integer> twoPairs = new TreeMap<>(new ArrayComparator());
    private static final TreeMap<char[], Integer> onePair = new TreeMap<>(new ArrayComparator());
    private static final TreeMap<char[], Integer> highCard = new TreeMap<>(new ArrayComparator());

    private Boolean isPart1 = true;

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

    public void main(String[] args) throws FileNotFoundException {
        Day07Run();
    }

    public boolean getIsPart1() {
        return isPart1;
    }

    public void setIsPart1(boolean isPart1) {
        this.isPart1 = isPart1;
    }

    public void Day07Run() throws FileNotFoundException {
        System.out.println("\n--- Day 7: Camel Cards ---\n");

        String input = "src/main/java/org/jmitchell238/aoc/aoc2023/day07/input.txt";
        String inputTest = "src/main/java/org/jmitchell238/aoc/aoc2023/day07/input_test.txt";

        long partOneAnswer = part1(inputTest);
        System.out.println("Part 1: Answer: " + partOneAnswer);

        long partTwoAnswer = part2(inputTest);
        System.out.println("Part 2: Answer: " + partTwoAnswer);
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
        try {
            Scanner scanner = new Scanner(new File(filePath));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (DEBUGGING) System.out.println(line);

                String[] lineSplit = line.split(" ");
                if (DEBUGGING) System.out.print("Hand: " + lineSplit[0] + " | Bid: " + lineSplit[1] + " | \n");

                char[] hand = new char[5];
                for (int i = 0; i < 5; i++) {
                    hand[i] = lineSplit[0].charAt(i);
                }

                if (DEBUGGING) {
                    for (int i = 0; i < 5; i++) {
                        System.out.print(hand[i]);
                        if (i < 4) System.out.print(", ");
                        if (i == 4) System.out.println();
                    }
                }

                int handValue = Integer.parseInt(lineSplit[1]);
                if (DEBUGGING) System.out.println(handValue);

                String type = getHandType(hand);

                addHandToMap(hand, handValue, type);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found, " + filePath);
            e.printStackTrace();
        }
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
                        cardCountMap.values().stream().max(Integer::compare).get();
                char highestCardCountKey;
                if (highestCardCount == 1) {
                    highestCardCountKey = cardCountMap.keySet().stream()
                            .max(Comparator.comparingInt(cardValues::get))
                            .get();
                    cardCountMap.put(highestCardCountKey, cardCountMap.get(highestCardCountKey) + jokerCount);
                } else {
                    highestCardCountKey = cardCountMap.entrySet().stream()
                            .filter(entry -> entry.getValue() == highestCardCount)
                            .findFirst()
                            .get()
                            .getKey();
                    cardCountMap.put(highestCardCountKey, cardCountMap.get(highestCardCountKey) + jokerCount);
                }
            }
        }

        return cardCountMap;
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
                System.out.println("Error: Hand type not found");
                break;
        }
    }

    private long getTotalValueFromHands() {
        long totalValue = 0L;

        long multiplier = 1L;
        totalValue += getTotalValueFromHandType(highCard, multiplier);
        if (!highCard.isEmpty()) multiplier += highCard.size();
        totalValue += getTotalValueFromHandType(onePair, multiplier);
        if (!onePair.isEmpty()) multiplier += onePair.size();
        totalValue += getTotalValueFromHandType(twoPairs, multiplier);
        if (!twoPairs.isEmpty()) multiplier += twoPairs.size();
        totalValue += getTotalValueFromHandType(threeOfAKind, multiplier);
        if (!threeOfAKind.isEmpty()) multiplier += threeOfAKind.size();
        totalValue += getTotalValueFromHandType(fullHouse, multiplier);
        if (!fullHouse.isEmpty()) multiplier += fullHouse.size();
        totalValue += getTotalValueFromHandType(fourOfAKind, multiplier);
        if (!fourOfAKind.isEmpty()) multiplier += fourOfAKind.size();
        totalValue += getTotalValueFromHandType(fiveOfAKind, multiplier);

        return totalValue;
    }

    private long getTotalValueFromHandType(TreeMap<char[], Integer> handType, long multiplier) {
        long totalValue = 0L;

        long[] handValues = handType.values().stream().mapToLong(i -> i).toArray();

        if (handType.isEmpty()) return totalValue;

        for (int i = 0; i < handValues.length; i++) {
            totalValue += handValues[i] * multiplier;
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
