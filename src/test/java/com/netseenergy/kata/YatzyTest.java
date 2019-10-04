package com.netseenergy.kata;

import org.junit.Test;

import static com.netseenergy.kata.Yatzy.*;
import static org.junit.Assert.assertEquals;

public class YatzyTest {

    @Test
    public void chance_scores_sum_of_all_dice() {
        assertEquals(15, chance(2, 3, 4, 5, 1));
        assertEquals(16, chance(3, 3, 4, 5, 1));
        assertEquals(5, chance(1, 1, 1, 1, 1));
    }

    @Test
    public void yatzy_scores_50() {
        assertEquals(50, yatzy(1, 1, 1, 1, 1));
        assertEquals(50, yatzy(2, 2, 2, 2, 2));
        assertEquals(50, yatzy(3, 3, 3, 3, 3));
        assertEquals(50, yatzy(4, 4, 4, 4, 4));
        assertEquals(50, yatzy(5, 5, 5, 5, 5));
        assertEquals(50, yatzy(6, 6, 6, 6, 6));
    }

    @Test
    public void yatzy_scores_0() {
        assertEquals(0, yatzy(6, 6, 6, 6, 3));
        assertEquals(0, yatzy(1, 1, 1, 1, 4));
        assertEquals(0, yatzy(3, 3, 3, 3, 1));
    }

    @Test
    public void sum_ones() {
        assertEquals(1, ones(1, 2, 3, 4, 5));
        assertEquals(2, ones(1, 2, 1, 4, 5));
        assertEquals(0, ones(6, 2, 2, 4, 5));
        assertEquals(4, ones(1, 2, 1, 1, 1));
    }

    @Test
    public void sum_twos() {
        assertEquals(4, twos(1, 2, 3, 2, 6));
        assertEquals(10, twos(2, 2, 2, 2, 2));
    }

    @Test
    public void sum_threes() {
        assertEquals(6, threes(1, 2, 3, 2, 3));
        assertEquals(12, threes(2, 3, 3, 3, 3));
    }

    @Test
    public void sum_fours() {
        assertEquals(12, fours(4, 4, 4, 5, 5));
        assertEquals(8, fours(4, 4, 5, 5, 5));
        assertEquals(4, fours(4, 5, 5, 5, 5));
    }

    @Test
    public void sum_fives() {
        assertEquals(10, fives(4, 4, 4, 5, 5));
        assertEquals(15, fives(4, 4, 5, 5, 5));
        assertEquals(20, fives(4, 5, 5, 5, 5));
    }

    @Test
    public void sum_sixes() {
        assertEquals(0, sixes(4, 4, 4, 5, 5));
        assertEquals(6, sixes(4, 4, 6, 5, 5));
        assertEquals(18, sixes(6, 5, 6, 6, 5));
    }

    @Test
    public void one_pair() {
        assertEquals(6, Yatzy.score_pair(3, 4, 3, 5, 6));
        assertEquals(10, Yatzy.score_pair(5, 3, 3, 3, 5));
        assertEquals(12, Yatzy.score_pair(5, 3, 6, 6, 5));
    }

    @Test
    public void two_Pair() {
        assertEquals(16, Yatzy.two_pair(3, 3, 5, 4, 5));
        assertEquals(16, Yatzy.two_pair(3, 3, 5, 5, 5));
    }

    @Test
    public void three_of_a_kind() {
        assertEquals(9, Yatzy.three_of_a_kind(3, 3, 3, 4, 5));
        assertEquals(15, Yatzy.three_of_a_kind(5, 3, 5, 4, 5));
        assertEquals(9, Yatzy.three_of_a_kind(3, 3, 3, 3, 5));
    }

    @Test
    public void four_of_a_knd() {
        assertEquals(12, Yatzy.four_of_a_kind(3, 3, 3, 3, 5));
        assertEquals(20, Yatzy.four_of_a_kind(5, 5, 5, 4, 5));
        assertEquals(9, Yatzy.three_of_a_kind(3, 3, 3, 3, 3));
    }

    @Test
    public void smallStraight() {
        assertEquals(15, Yatzy.smallStraight(1, 2, 3, 4, 5));
        assertEquals(15, Yatzy.smallStraight(2, 3, 4, 5, 1));
        assertEquals(0, Yatzy.smallStraight(1, 2, 2, 4, 5));
    }

    @Test
    public void largeStraight() {
        assertEquals(20, Yatzy.largeStraight(6, 2, 3, 4, 5));
        assertEquals(20, Yatzy.largeStraight(2, 3, 4, 5, 6));
        assertEquals(0, Yatzy.largeStraight(1, 2, 2, 4, 5));
    }

    @Test
    public void fullHouse() {
        assertEquals(18, Yatzy.fullHouse(6, 2, 2, 2, 6));
        assertEquals(0, Yatzy.fullHouse(2, 3, 4, 5, 6));
    }
}
