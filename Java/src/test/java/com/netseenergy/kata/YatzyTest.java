package com.netseenergy.kata;

import org.junit.Test;

import static com.netseenergy.kata.Yatzy.*;
import static org.junit.Assert.assertEquals;

public class YatzyTest {

    @Test
    public void chance_should_be_sum_of_all_dices() {
        assertEquals(15, chance(2, 3, 4, 5, 1));
        assertEquals(16, chance(3, 3, 4, 5, 1));
        assertEquals(5, chance(1, 1, 1, 1, 1));
    }

    @Test
    public void dices_should_have_scores_50() {
        assertEquals(50, yatzy(1, 1, 1, 1, 1));
        assertEquals(50, yatzy(2, 2, 2, 2, 2));
        assertEquals(50, yatzy(3, 3, 3, 3, 3));
        assertEquals(50, yatzy(4, 4, 4, 4, 4));
        assertEquals(50, yatzy(5, 5, 5, 5, 5));
        assertEquals(50, yatzy(6, 6, 6, 6, 6));
    }

    @Test
    public void dices_should_have_scores_0() {
        assertEquals(0, yatzy(6, 6, 6, 6, 3));
        assertEquals(0, yatzy(1, 1, 1, 1, 4));
        assertEquals(0, yatzy(3, 3, 3, 3, 1));
    }

    @Test
    public void should_sum_ones() {
        assertEquals(1, ones(1, 2, 3, 4, 5));
        assertEquals(2, ones(1, 2, 1, 4, 5));
        assertEquals(0, ones(6, 2, 2, 4, 5));
        assertEquals(4, ones(1, 2, 1, 1, 1));
    }

    @Test
    public void should_sum_twos() {
        assertEquals(4, twos(1, 2, 3, 2, 6));
        assertEquals(10, twos(2, 2, 2, 2, 2));
    }

    @Test
    public void should_sum_threes() {
        assertEquals(6, threes(1, 2, 3, 2, 3));
        assertEquals(12, threes(2, 3, 3, 3, 3));
    }

    @Test
    public void should_sum_fours() {
        assertEquals(12, fours(4, 4, 4, 5, 5));
        assertEquals(8, fours(4, 4, 5, 5, 5));
        assertEquals(4, fours(4, 5, 5, 5, 5));
    }

    @Test
    public void should_sum_fives() {
        assertEquals(10, fives(4, 4, 4, 5, 5));
        assertEquals(15, fives(4, 4, 5, 5, 5));
        assertEquals(20, fives(4, 5, 5, 5, 5));
    }

    @Test
    public void should_sum_sixes() {
        assertEquals(0, sixes(4, 4, 4, 5, 5));
        assertEquals(6, sixes(4, 4, 6, 5, 5));
        assertEquals(18, sixes(6, 5, 6, 6, 5));
    }

    @Test
    public void should_sum_one_pair() {
        assertEquals(6, pair(3, 4, 3, 5, 6));
        assertEquals(10, pair(5, 3, 3, 3, 5));
        assertEquals(12, pair(5, 3, 6, 6, 5));
        assertEquals(0, pair(1, 2, 3, 6, 5));
    }

    @Test
    public void should_sum_two_pairs() {
        assertEquals(16, twoPair(3, 3, 5, 4, 5));
        assertEquals(16, twoPair(3, 3, 5, 5, 5));
        assertEquals(0, twoPair(1, 1, 2, 3, 4));
    }

    @Test
    public void should_sum_three_of_a_kind() {
        assertEquals(9, threeOfAKind(3, 3, 3, 4, 5));
        assertEquals(15, threeOfAKind(5, 3, 5, 4, 5));
        assertEquals(9, threeOfAKind(3, 3, 3, 3, 5));
        assertEquals(0, threeOfAKind(3, 3, 4, 5, 6));
    }

    @Test
    public void should_sum_four_of_a_knd() {
        assertEquals(12, fourOfAKind(3, 3, 3, 3, 5));
        assertEquals(20, fourOfAKind(5, 5, 5, 4, 5));
        assertEquals(12, fourOfAKind(3, 3, 3, 3, 3));
        assertEquals(0, fourOfAKind(2, 2, 2, 5, 5));
    }

    @Test
    public void should_check_small_straight() {
        assertEquals(15, smallStraight(1, 2, 3, 4, 5));
        assertEquals(15, smallStraight(2, 3, 4, 5, 1));
        assertEquals(0, smallStraight(1, 2, 2, 4, 5));
    }

    @Test
    public void should_check_large_straight() {
        assertEquals(20, largeStraight(6, 2, 3, 4, 5));
        assertEquals(20, largeStraight(2, 3, 4, 5, 6));
        assertEquals(0, largeStraight(1, 2, 2, 4, 5));
    }

    @Test
    public void should_check_full_house() {
        assertEquals(18, fullHouse(6, 2, 2, 2, 6));
        assertEquals(8, fullHouse(1, 1, 2, 2, 2));
        assertEquals(0, fullHouse(2, 3, 4, 5, 6));
        assertEquals(0, fullHouse(2, 2, 3, 3, 4));
        assertEquals(0, fullHouse(4, 4, 4, 4, 4));
    }
}
