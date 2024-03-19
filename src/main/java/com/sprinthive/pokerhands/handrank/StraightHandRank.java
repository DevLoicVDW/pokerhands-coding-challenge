package com.sprinthive.pokerhands.handrank;

import com.sprinthive.pokerhands.CardRank;

public class StraightHandRank extends HandRank {

    protected final CardRank highCardRank;

    public StraightHandRank(CardRank highCardRank) {
        super(HandStrength.STRAIGHT);
        if (highCardRank == null) {
            throw new IllegalArgumentException("highCardRank may not be null for a straight");
        }
        this.highCardRank = highCardRank;
    }

    @Override
    public String describeHand() {
        return "Straight, " + highCardRank + " high";
    }

    @Override
    protected int compareSameRank(HandRank other) {
        return highCardRank.compareTo(((StraightHandRank) other).highCardRank);
    }
}
