package com.sprinthive.pokerhands.handrank;

import com.sprinthive.pokerhands.CardRank;

public class StraightFlushHandRank extends HandRank<StraightFlushHandRank> {

    private final CardRank highCardRank;

    public StraightFlushHandRank(CardRank highCardRank) {
        super(HandStrength.STRAIGHT_FLUSH);
        if (highCardRank == null) {
            throw new IllegalArgumentException("highCardRank may not be null for a straight.");
        }
        this.highCardRank = highCardRank;
    }

    @Override
    protected int compareSameRank(StraightFlushHandRank other) {
        return highCardRank.compareTo((other).highCardRank);
    }

    @Override
    public String describeHand() {
        return "Straight flush, " + highCardRank + " high";
    }
}
