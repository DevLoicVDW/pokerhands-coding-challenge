package com.sprinthive.pokerhands.handrank;

import com.sprinthive.pokerhands.Card;
import com.sprinthive.pokerhands.CardRank;

import java.util.Collections;
import java.util.List;

/*
 public class BadPokerHandRanker implements HandRanker {

    public HandRank findBestHandRank(List<Card> cards) {
        if (cards.size() != 5) {
            return new NotRankableHandRanker(cards);
        }
        Collections.sort(cards);
        Collections.reverse(cards);
        // High card
        return new HighCardHandRank(cards);
    }
}
 */

import com.sprinthive.pokerhands.Suit;


public class PokerHandRanker implements HandRanker {

    public HandRank findBestHandRank(List<Card> cards) {
        if (cards.size() != 5) {
            return new NotRankableHandRanker(cards);
        }

        // Sort the cards by rank
        Collections.sort(cards);
        Collections.reverse(cards);


        // Check for special cases: straight, flush, straight flush, or royal flush
        boolean isFlush = isFlush(cards);
        boolean isStraight = isStraight(cards);

        if (isStraight) {
            // Straight
            CardRank highCardRank = cards.get(0).getRank(); // Assuming cards are sorted
            return new HandRank<>(HandStrength.STRAIGHT) {
                @Override
                protected int compareSameRank(HandRank other) {
                    return highCardRank.compareTo(((StraightHandRank) other).highCardRank);
                }

                @Override
                public String describeHand() {
                    return "Straight, " + highCardRank + " high";
                }
            };
        } else {
            return new HighCardHandRank(cards);
        }

        // Check for other hand ranks: four of a kind, full house, three of a kind, two pair, or pair
        // ...

    }

    private boolean isFlush(List<Card> cards) {
        // Check if all cards have the same suit
        Suit firstSuit = cards.get(0).getSuit();
        for (Card card : cards) {
            if (card.getSuit() != firstSuit) {
                return false;
            }
        }

        return true;
    }
    

    private boolean isStraight(List<Card> cards) {
        int firstRankValue = cards.get(0).getRank().getValue();
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getRank().getValue() != firstRankValue - i) {
                return false;
            }
        }
        return true;
    }

    private boolean isRoyalFlush(List<Card> cards) {
        if (cards.get(0).getRank().getValue() != 10) {
            return false;
        }
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getRank().getValue() != 10 + i) {
                return false;
            }
        }
        return isFlush(cards);
    }

    // Other methods for checking different hand ranks...
}
