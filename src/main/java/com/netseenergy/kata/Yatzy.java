package com.netseenergy.kata;

import java.util.Arrays;
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
        int[] tallies;
        tallies = new int[6];
        tallies[d1 - 1] += 1;
        tallies[d2 - 1] += 1;
        tallies[d3 - 1] += 1;
        tallies[d4 - 1] += 1;
        tallies[d5 - 1] += 1;
        if (tallies[0] == 1 &&
                tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1)
            return 15;
        return 0;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {
        int[] tallies;
        tallies = new int[6];
        tallies[d1 - 1] += 1;
        tallies[d2 - 1] += 1;
        tallies[d3 - 1] += 1;
        tallies[d4 - 1] += 1;
        tallies[d5 - 1] += 1;
        if (tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1
                && tallies[5] == 1)
            return 20;
        return 0;
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {
        int[] tallies;
        boolean _2 = false;
        int i;
        int _2_at = 0;
        boolean _3 = false;
        int _3_at = 0;


        tallies = new int[6];
        tallies[d1 - 1] += 1;
        tallies[d2 - 1] += 1;
        tallies[d3 - 1] += 1;
        tallies[d4 - 1] += 1;
        tallies[d5 - 1] += 1;

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 2) {
                _2 = true;
                _2_at = i + 1;
            }

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 3) {
                _3 = true;
                _3_at = i + 1;
            }

        if (_2 && _3)
            return _2_at * 2 + _3_at * 3;
        else
            return 0;
    }
}



