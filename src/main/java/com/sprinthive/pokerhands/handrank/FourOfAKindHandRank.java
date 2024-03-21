package com.sprinthive.pokerhands.handrank;

import com.sprinthive.pokerhands.CardRank;

public class FourOfAKindHandRank extends HandRank<FourOfAKindHandRank> {
    private final CardRank cardRank;
    private final CardRank kicker;

    public FourOfAKindHandRank(CardRank cardRank, CardRank kicker) {
        super(HandStrength.FOUR_OF_A_KIND);
        if (cardRank == null) {
            throw new IllegalArgumentException("cardRank may not be null for a four of a kind.");
        }
        if (kicker == null) {
            throw new IllegalArgumentException("kicker may not be null for a four of a kind.");
        }
        this.cardRank = cardRank;
        this.kicker = kicker;
    }

    @Override
    protected int compareSameRank(FourOfAKindHandRank otherHandRank) {
        int cardRankComparison = cardRank.compareTo(otherHandRank.cardRank);
        if (cardRankComparison != 0) {
            return cardRankComparison;
        }

        // If the four of a kind ranks are the same, compare the kickers
        return kicker.compareTo(otherHandRank.kicker);
    }

    public String describeHand() {
        return "4 of a kind of " + cardRank + "s";
    }
}
