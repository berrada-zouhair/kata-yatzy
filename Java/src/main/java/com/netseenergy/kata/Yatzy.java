package com.netseenergy.kata;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.Collections.frequency;
import static java.util.stream.Collectors.toSet;

public class Yatzy {

    private Yatzy() {
    }

    public static int chance(int d1, int d2, int d3, int d4, int d5) {
        return IntStream.of(new int[]{d1, d2, d3, d4, d5}).sum();
    }

    public static int yatzy(int d1, int d2, int d3, int d4, int d5) {
        long countDistinctElements = IntStream.of(new int[]{d1, d2, d3, d4, d5})
                .distinct()
                .count();
        return countDistinctElements == 1 ? 50 : 0;
    }

    private static int sumSameDices(int seekedNumber, int... dices) {
        return (int) IntStream.of(dices)
                .filter(dice -> dice == seekedNumber)
                .count() * seekedNumber;
    }

    public static int ones(int d1, int d2, int d3, int d4, int d5) {
        return sumSameDices(1, d1, d2, d3, d4, d5);
    }

    public static int twos(int d1, int d2, int d3, int d4, int d5) {
        return sumSameDices(2, d1, d2, d3, d4, d5);
    }

    public static int threes(int d1, int d2, int d3, int d4, int d5) {
        return sumSameDices(3, d1, d2, d3, d4, d5);
    }

    public static int fours(int d1, int d2, int d3, int d4, int d5) {
        return sumSameDices(4, d1, d2, d3, d4, d5);
    }

    public static int fives(int d1, int d2, int d3, int d4, int d5) {
        return sumSameDices(5, d1, d2, d3, d4, d5);
    }

    public static int sixes(int d1, int d2, int d3, int d4, int d5) {
        return sumSameDices(6, d1, d2, d3, d4, d5);
    }

    public static int pair(int d1, int d2, int d3, int d4, int d5) {
        List<Integer> dices = Arrays.asList(d1, d2, d3, d4, d5);
        return dices.stream()
                .mapToInt(Integer::intValue)
                .filter(dice -> frequency(dices, dice) > 1)
                .max()
                .orElse(0) * 2;
    }

    public static int twoPair(int d1, int d2, int d3, int d4, int d5) {
        List<Integer> dices = Arrays.asList(d1, d2, d3, d4, d5);
        Set<Integer> twoPairs = dices.stream()
                .mapToInt(Integer::intValue)
                .filter(dice -> frequency(dices, dice) > 1)
                .boxed()
                .collect(toSet());
        return twoPairs.size() == 2 ? twoPairs.stream().mapToInt(Integer::intValue).sum() * 2 : 0;
    }

    private static int nOfAKind(int n, int d1, int d2, int d3, int d4, int d5) {
        List<Integer> dices = Arrays.asList(d1, d2, d3, d4, d5);
        return dices.stream()
                .mapToInt(Integer::intValue)
                .filter(dice -> frequency(dices, dice) >= n)
                .findFirst()
                .orElse(0) * n;
    }

    public static int threeOfAKind(int d1, int d2, int d3, int d4, int d5) {
        return nOfAKind(3, d1, d2, d3, d4, d5);
    }

    public static int fourOfAKind(int d1, int d2, int d3, int d4, int d5) {
        return nOfAKind(4, d1, d2, d3, d4, d5);
    }

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5) {
        HashSet<Integer> dices = new HashSet<>(Arrays.asList(d1, d2, d3, d4, d5));
        return dices.equals(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5))) ? 15 : 0;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {
        HashSet<Integer> dices = new HashSet<>(Arrays.asList(d1, d2, d3, d4, d5));
        return dices.equals(new HashSet<>(Arrays.asList(2, 3, 4, 5, 6))) ? 20 : 0;
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {
        HashSet<Integer> dices = new HashSet<>(Arrays.asList(d1, d2, d3, d4, d5));
        if (dices.size() == 1) { // checks if all dices are identical
            return 0;
        }
        boolean isTwoAndTreeKind = nOfAKind(3, d1, d2, d3, d4, d5) > 0
                && nOfAKind(2, d1, d2, d3, d4, d5) > 0;
        return isTwoAndTreeKind ? chance(d1, d2, d3, d4, d5) : 0;
    }
}



