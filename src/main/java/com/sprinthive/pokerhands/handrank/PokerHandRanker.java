package com.sprinthive.pokerhands.handrank;

import com.sprinthive.pokerhands.Card;
import com.sprinthive.pokerhands.CardRank;
import com.sprinthive.pokerhands.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        } else if (isFourOfAKind(cards)) {
            Map<CardRank, Integer> rankCounts = new HashMap<>();
            for (Card card : cards) {
                rankCounts.put(card.getRank(), rankCounts.getOrDefault(card.getRank(), 0) + 1);
            }

            CardRank fourOfAKindRank = null;
            for (Map.Entry<CardRank, Integer> entry : rankCounts.entrySet()) {
                if (entry.getValue() == 4) {
                    fourOfAKindRank = entry.getKey();
                    break;
                }
            }
            if (fourOfAKindRank != null) {
                return new FourOfAKindHandRank(fourOfAKindRank);
            }
        } else if (isFullHouse(cards)) {
            Map<CardRank, Integer> rankCounts = new HashMap<>();
            for (Card card : cards) {
                rankCounts.put(card.getRank(), rankCounts.getOrDefault(card.getRank(), 0) + 1);
            }
    
            CardRank tripsRank = null;
            CardRank pairRank = null;
            for (Map.Entry<CardRank, Integer> entry : rankCounts.entrySet()) {
                if (entry.getValue() == 3) {
                    tripsRank = entry.getKey();
                } else if (entry.getValue() == 2) {
                    pairRank = entry.getKey();
                }
            }
    
            if (tripsRank != null && pairRank != null) {
                return new FullHouseHandRank(tripsRank, pairRank);
            }
        } else if (isFlush) {
            return new FlushHandRank(cards);
        } else if (isStraight) {
            return new StraightHandRank(highCardRank);
        } else if (isThreeOfAKind(cards)) {
            Map<CardRank, Integer> rankCounts = new HashMap<>();
            for (Card card : cards) {
                rankCounts.put(card.getRank(), rankCounts.getOrDefault(card.getRank(), 0) + 1);
            }

            CardRank threeOfAKindRank = null;
            for (Map.Entry<CardRank, Integer> entry : rankCounts.entrySet()) {
                if (entry.getValue() == 3) {
                    threeOfAKindRank = entry.getKey();
                    break;
                }
            }
            if (threeOfAKindRank != null) {
                return new ThreeOfAKindHandRank(threeOfAKindRank);
            }
        } else if (isTwoPair(cards)) {
            CardRank highPairRank = null;
            CardRank lowPairRank = null;
            CardRank kickerRank = null;
            Map<CardRank, Integer> rankCounts = new HashMap<>();
            for (Card card : cards) {
                rankCounts.put(card.getRank(), rankCounts.getOrDefault(card.getRank(), 0) + 1);
            }
            for (Map.Entry<CardRank, Integer> entry : rankCounts.entrySet()) {
                if (entry.getValue() == 2) {
                    if (highPairRank == null) {
                        highPairRank = entry.getKey();
                    } else {
                        lowPairRank = entry.getKey();
                    }
                } else {
                    kickerRank = entry.getKey();
                }
            }
            // Determine which pair is higher
            if (highPairRank.compareTo(lowPairRank) < 0) {
                // Swap ranks if highPairRank is lower than lowPairRank
                CardRank temp = highPairRank;
                highPairRank = lowPairRank;
                lowPairRank = temp;
            }
            return new TwoPairHandRank(highPairRank, lowPairRank, kickerRank);
        } else if (isOnePair(cards)) {
            CardRank pairRank = null;
            List<CardRank> restRanks = new ArrayList<>();
            Map<CardRank, Integer> rankCounts = new HashMap<>();
            for (Card card : cards) {
                rankCounts.put(card.getRank(), rankCounts.getOrDefault(card.getRank(), 0) + 1);
            }
            for (Map.Entry<CardRank, Integer> entry : rankCounts.entrySet()) {
                if (entry.getValue() == 2) {
                    pairRank = entry.getKey();
                } else {
                    restRanks.add(entry.getKey());
                }
            }
            return new OnePairHandRank(pairRank, restRanks);
        }
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
        if (cards.get(0).getRank().getValue() != 14) {
            return false;
        }
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getRank().getValue() != 14 - i) {
                return false;
            }
        }

        return isFlush(cards);
    }
    
    private boolean isOnePair(List<Card> cards) {
        Map<CardRank, Integer> rankCounts = new HashMap<>();
        for (Card card : cards) {
            rankCounts.put(card.getRank(), rankCounts.getOrDefault(card.getRank(), 0) + 1);
        }

        int pairCount = 0;
        for (int count : rankCounts.values()) {
            if (count == 2) {
                pairCount++;
            }
        }

        return pairCount == 1;
    }

    private boolean isTwoPair(List<Card> cards) {
        Map<CardRank, Integer> rankCounts = new HashMap<>();
        for (Card card : cards) {
            rankCounts.put(card.getRank(), rankCounts.getOrDefault(card.getRank(), 0) + 1);
        }

        int pairCount = 0;
        for (int count : rankCounts.values()) {
            if (count == 2) {
                pairCount++;
            }
        }

        return pairCount == 2;
    }

    private boolean isThreeOfAKind(List<Card> cards) {
        Map<CardRank, Integer> rankCounts = new HashMap<>();
        for (Card card : cards) {
            rankCounts.put(card.getRank(), rankCounts.getOrDefault(card.getRank(), 0) + 1);
        }
    
        for (int count : rankCounts.values()) {
            if (count == 3) {
                return true; // Found three cards of the same rank
            }
        }
    
        return false; // Did not find three cards of the same rank
    }

    private boolean isFourOfAKind(List<Card> cards) {
        Map<CardRank, Integer> rankCounts = new HashMap<>();
        for (Card card : cards) {
            rankCounts.put(card.getRank(), rankCounts.getOrDefault(card.getRank(), 0) + 1);
        }
    
        for (int count : rankCounts.values()) {
            if (count == 4) {
                return true; // Found four cards of the same rank
            }
        }
    
        return false; // Did not find four cards of the same rank
    }

    private boolean isFullHouse(List<Card> cards) {
        Map<CardRank, Integer> rankCounts = new HashMap<>();
        for (Card card : cards) {
            rankCounts.put(card.getRank(), rankCounts.getOrDefault(card.getRank(), 0) + 1);
        }
    
        boolean hasThreeOfAKind = false;
        boolean hasPair = false;
        for (int count : rankCounts.values()) {
            if (count == 3) {
                hasThreeOfAKind = true;
            } else if (count == 2) {
                hasPair = true;
            }
        }
    
        return hasThreeOfAKind && hasPair;
    }

}
