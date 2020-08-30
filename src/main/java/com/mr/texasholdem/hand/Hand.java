package com.mr.texasholdem.hand;

import java.util.Arrays;
import java.util.Objects;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.card.CardRankComparator;
import com.mr.texasholdem.card.Rank;

public class Hand implements Comparable<Hand> {
  public static final int HIGH_CARD_VALUE = 1;

  public static final int PAIR_VALUE = 2;

  public static final int TWO_PAIRS_VALUE = 3;

  public static final int THREE_OF_A_KIND_VALUE = 4;

  public static final int STRAIGHT_VALUE = 5;

  public static final int FLUSH_VALUE = 6;

  public static final int FULL_HOUSE_VALUE = 7;

  public static final int FOUR_OF_A_KIND_VALUE = 8;

  public static final int STRAIGHT_FLUSH_VALUE = 9;

  private final int value;

  private final Rank rank;

  public Hand(int value, Rank rank) {
    this.value = value;
    this.rank = rank;
  }

  public int getValue() {
    return value;
  }

  public Rank getRank() {
    return rank;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Hand hand = (Hand) o;
    return value == hand.value && rank == hand.rank;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, rank);
  }

  @Override
  public int compareTo(Hand o) {
    int res = value - o.value;
    return res == 0 ? rank.compareTo(o.rank) : res;
  }

  protected static Card[] sortHandByRank(Card[] cards) {
    if (cards == null || cards.length != 5) {
      throw new IllegalArgumentException("Hand must have 5 cards");
    }
    Arrays.sort(cards, new CardRankComparator());
    return cards;
  }

}
