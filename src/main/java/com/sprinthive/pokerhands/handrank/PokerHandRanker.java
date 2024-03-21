package com.sprinthive.pokerhands.handrank;

import com.sprinthive.pokerhands.Card;
import com.sprinthive.pokerhands.CardRank;
import com.sprinthive.pokerhands.Suit;

import java.util.Collections;
import java.util.List;


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
        CardRank highCardRank = cards.get(0).getRank(); // Assuming cards are sorted
        Suit highCardSuit = cards.get(0).getSuit();

        if (isFlush && isStraight) {
            if (isRoyalFlush(cards)) {
                return new RoyalFlushHandRank(highCardSuit);
            } else {
                return new StraightFlushHandRank(highCardRank);
            }
        } else if (isFlush) {
            return new FlushHandRank(cards);
        } else if (isStraight) {
            return new StraightHandRank(highCardRank);
        }

        // Check for other hand ranks: four of a kind, full house, three of a kind, two pair, or pair
        // ...
        
        return new HighCardHandRank(cards);
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
