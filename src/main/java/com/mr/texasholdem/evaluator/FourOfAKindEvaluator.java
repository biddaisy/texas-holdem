package com.mr.texasholdem.evaluator;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.hand.Hand;
import com.mr.texasholdem.hand.FourOfAKind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FourOfAKindEvaluator extends AbstractHandEvaluator{

  @Override
  public Hand evaluate(Card[] cards) {
    return findFourOfAKind(asList(cards));
  }

  @Override
  public int priority() {
    return Hand.FOUR_OF_A_KIND_VALUE;
  }

  protected FourOfAKind findFourOfAKind(List<Card> cards) {
    List<FourOfAKind> fourOfAKinds = evaluateFourOfAKinds(cards, new ArrayList<>());
    return !fourOfAKinds.isEmpty() ? Collections.max(fourOfAKinds) : null;
  }

  private List<FourOfAKind> evaluateFourOfAKinds(List<Card> cards, List<FourOfAKind> fourOfAKinds) {
    int size = cards.size();
    Card card1 = cards.get(0);
    Card card2 = null;
    Card card3 = null;
    for (int a = 1; a < size; a++) {
      Card card = cards.get(a);
      if (card1.equalsByRank(card)) {
        if (card2 == null) {
          card2 = card;
        }
        else if (card3 == null){
          card3 = card;
        }
        else {
          cards.remove(card2);
          cards.remove(card3);
          cards.remove(card);
          fourOfAKinds.add(new FourOfAKind(card1, card2, card3, card));
          break;
        }
      }
    }
    cards.remove(card1);
    if (cards.size() > 3) {
      return evaluateFourOfAKinds(cards, fourOfAKinds);
    }
    return fourOfAKinds;
  }

}