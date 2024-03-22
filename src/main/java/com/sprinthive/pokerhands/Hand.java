package com.sprinthive.pokerhands;

import com.sprinthive.pokerhands.handrank.PokerHandRanker;
import com.sprinthive.pokerhands.handrank.HandRank;
import com.sprinthive.pokerhands.handrank.HandRanker;

import java.util.List;

public class Hand implements Comparable<Hand> {

    // This should be injected with an IOC container, but let's keep it simple.
    // Just replace BadPokerHandRanker with your own implementation
    private static final HandRanker handRanker = new PokerHandRanker();
    private final HandRank handRank;

    public Hand(List<Card> cards) {
        this.handRank = handRanker.findBestHandRank(cards);
        if (handRank == null) {
            throw new IllegalArgumentException("Failed to determine hand rank.");
        }
    }

    public String describeHandRank() {
        return handRank.describeHand();
    }

    public int compareTo(Hand other) {
        return handRank.compareTo(other.handRank);
    }
}

