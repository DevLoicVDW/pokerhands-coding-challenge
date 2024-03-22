package com.sprinthive.pokerhands.handrank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sprinthive.pokerhands.CardRank;

public class ThreeOfAKindHandRank extends HandRank<ThreeOfAKindHandRank> {
    private final CardRank cardRank;
    private final List<CardRank> restRanks;

    public ThreeOfAKindHandRank(CardRank cardRank, List<CardRank> restRanks) {
        super(HandStrength.THREE_OF_A_KIND);
        if (cardRank == null) {
            throw new IllegalArgumentException("cardRank may not be null for a three of a kind hand");
        }
        if (restRanks == null) {
            throw new IllegalArgumentException("restRanks may not be null for a three of a kind hand");
        }
        this.cardRank = cardRank;
        this.restRanks = restRanks;
    }

    @Override
    protected int compareSameRank(ThreeOfAKindHandRank otherHandRank) {
        int cmp = cardRank.compareTo(otherHandRank.cardRank);

        if (cmp == 0) {
            // Three of a kind ranks are equal, compare kickers
            List<CardRank> thisKickers = new ArrayList<>(restRanks);
            List<CardRank> otherKickers = new ArrayList<>(otherHandRank.restRanks);
            Collections.sort(thisKickers);
            Collections.sort(otherKickers);

            for (int i = 0; i < thisKickers.size(); i++) {
                cmp = thisKickers.get(i).compareTo(otherKickers.get(i));
                if (cmp != 0) {
                    return cmp;
                }
            }
        }
        return cmp;
    }

    @Override
    public String describeHand() {
        return "3 of a kind of " + cardRank + "s";
    }
}
