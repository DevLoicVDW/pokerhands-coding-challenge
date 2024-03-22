package com.sprinthive.pokerhands.handrank;

import com.sprinthive.pokerhands.Card;
import com.sprinthive.pokerhands.CardRank;
import com.sprinthive.pokerhands.Suit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class PokerHandRanker implements HandRanker {
    // findBestHandRank can be optimized if needed and if the benefits outweigh the cost. 
    public HandRank findBestHandRank(List<Card> cards) {
        if (cards.size() != 5) {
            return new NotRankableHandRanker(cards);
        }

        // Sort the cards by rank in descending order
        Collections.sort(cards);
        Collections.reverse(cards);

        // These variables are used a lot so it's worth the initial hit of minor computation and storage usage. Same case for rankCounts on line 35.
        boolean isFlush = isFlush(cards);
        boolean isStraight = isStraight(cards);
        CardRank highCardRank = cards.get(0).getRank(); // Assuming cards are sorted in descending order
        Suit highCardSuit = cards.get(0).getSuit();

        // Count the occurrences of each card rank in the hand.
        // Assuming cards is a List<Card> containing the hand J-J-J-SIX-SIX,
        // After the loop, rankCounts will contain:
        // {JACK=3, SIX=2}
        Map<CardRank, Integer> rankCounts = new HashMap<>();
        for (Card card : cards) {
            rankCounts.put(card.getRank(), rankCounts.getOrDefault(card.getRank(), 0) + 1);
        }

        if (isFlush && isStraight) {
            // Special case of Royal Flush, or 10-J-Q-K-A, which is a special straight flush.
            if (isRoyalFlush(cards)) {
                return new RoyalFlushHandRank(highCardSuit);
            } else {
                // Special case for A-2-3-4-5 straight flush where the FIVE is meant to be the highest card.
                if (isWheel(cards)) {
                    return new StraightFlushHandRank(cards.get(1).getRank());
                }
                // General case for a straight flush with royal flush and wheel filtered out.
                return new StraightFlushHandRank(highCardRank);
            }
        } else if (isFourOfAKind(cards)) {
            CardRank fourOfAKindRank = null;
            CardRank kickerRank = null;
            // Iterate over the rankCounts to find the quad and kicker ranks.
            for (Map.Entry<CardRank, Integer> entry : rankCounts.entrySet()) {
                if (entry.getValue() >= 4) {
                    fourOfAKindRank = entry.getKey(); // Found the rank of the quad
                } else if (entry.getValue() == 1) {
                    kickerRank = entry.getKey(); // Found the rank of the kicker
                }
            }
            if (fourOfAKindRank != null) {
                if (kickerRank != null) {
                    // General case where the kicker rank is different from the quad rank.
                    return new FourOfAKindHandRank(fourOfAKindRank, kickerRank);
                } else if (kickerRank == null) {
                    // Case where it is possible to have 5 of a kind. As if playing with two decks or jokers.
                    // Game will need to be updated to include these modes.
                    // Hand of K-K-K-K-K is still only regarded as a KING-four-of-a-kind with a kicker rank of KING. 
                    return new FourOfAKindHandRank(fourOfAKindRank, fourOfAKindRank);
                }
            }
        } else if (isFullHouse(cards)) {
            CardRank tripsRank = null;
            CardRank pairRank = null;
            // Iterate over rankCounts to find the trips and pair ranks
            for (Map.Entry<CardRank, Integer> entry : rankCounts.entrySet()) {
                if (entry.getValue() == 3) {
                    tripsRank = entry.getKey(); // Found the rank of the trips
                } else if (entry.getValue() == 2) {
                    pairRank = entry.getKey(); // Found the rank of the pair
                }
            }
            
            // If both trips and pair ranks are found, return a new FullHouseHandRank.
            // pairRank needs to be checked here to ensure that three-of-a-kind hands pass 
            // to the next else-if step instead of getting falsely identified as a full-house
            // and lead to an error when constructing the full-house-hand object. 
            if (pairRank != null && tripsRank != null) {
                return new FullHouseHandRank(tripsRank, pairRank);
            }
        } else if (isFlush) {
            return new FlushHandRank(cards);
        } else if (isStraight) {
            // Special case with A-2-3-4-5 hand (called a wheel) where the FIVE is the high card in the straight.
            if (isWheel(cards)) {
                return new StraightHandRank(cards.get(1).getRank());
            }
            // Rest of the straight cases
            return new StraightHandRank(highCardRank);
        } else if (isThreeOfAKind(cards)) {
            CardRank threeOfAKindRank = null;
            List<CardRank> restRanks = new ArrayList<>();
            // Iterate over rankCounts to find the three-of-a-kind rank
            for (Map.Entry<CardRank, Integer> entry : rankCounts.entrySet()) {
                if (entry.getValue() == 3) {
                    threeOfAKindRank = entry.getKey(); // Found the three-of-a-kind rank
                } else {
                    restRanks.add(entry.getKey()); // Found the ranks of the rest
                }
            }
            return new ThreeOfAKindHandRank(threeOfAKindRank, restRanks);
        } else if (isTwoPair(cards)) {
            CardRank firstPairRank = null;
            CardRank secondPairRank = null;
            CardRank kickerRank = null;
            // Iterate over rankCounts to find the first pair, second pair and kicker ranks.
            for (Map.Entry<CardRank, Integer> entry : rankCounts.entrySet()) {
                if (entry.getValue() == 2) {
                    if (firstPairRank == null) {
                        firstPairRank = entry.getKey(); // Found the rank of the first pair
                    } else {
                        secondPairRank = entry.getKey(); // Found the rank of the second pair
                    }
                } else {
                    kickerRank = entry.getKey(); // Found the rank of the kicker
                }
            }
            // Determine which pair is higher
            // TwoPairHandRank( highRankPairRank, lowRankPairRank, kickerRank )
            if (firstPairRank.compareTo(secondPairRank) > 0) {
                return new TwoPairHandRank(firstPairRank, secondPairRank, kickerRank);
            } else {
                return new TwoPairHandRank(secondPairRank, firstPairRank, kickerRank);
            }
            
        } else if (isOnePair(cards)) {
            CardRank pairRank = null;
            List<CardRank> restRanks = new ArrayList<>();
            // Iterate over rankCounts to find the pair rank and the ranks of the rest of the cards.
            for (Map.Entry<CardRank, Integer> entry : rankCounts.entrySet()) {
                if (entry.getValue() == 2) {
                    pairRank = entry.getKey(); // Found the rank of the pair
                } else {
                    restRanks.add(entry.getKey()); // Found the ranks of the rest
                }
            }
            return new OnePairHandRank(pairRank, restRanks);
        }
        return new HighCardHandRank(cards);
    }


    /**
     * Checks if a hand of cards is a flush, i.e., all cards have the same suit.
     *
     * @param cards The list of cards representing the hand.
     * @return True if the hand is a flush, false otherwise.
     */
    private boolean isFlush(List<Card> cards) {
        // Get the suit of the first card in the hand
        Suit firstSuit = cards.get(0).getSuit();

        // Iterate through all cards in the hand
        for (Card card : cards) {
            // If any card has a different suit than the first card, it's not a flush
            if (card.getSuit() != firstSuit) {
                return false;
            }
        }

        return true;
    }


    /**
     * Checks if a hand of cards is a straight, i.e., all cards have consecutive ranks.
     *
     * @param cards The list of cards representing the hand.
     * @return True if the hand is a straight, false otherwise.
     */
    private boolean isStraight(List<Card> cards) {
        // Check for the special case of an A-2-3-4-5 hand (called a wheel)
        // Wheel would currently be stored as A-5-4-3-2 
        if (cards.get(0).getRank() == CardRank.ACE &&
        cards.get(1).getRank() == CardRank.FIVE) {
            // If the hand is a wheel, it is a straight so isStraight() returns true.
            return isWheel(cards);
        }

        // Check if the ranks of the cards are consecutive
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getRank().getValue() != cards.get(i - 1).getRank().getValue() - 1) {
                return false;
            }
        }

        return true;
    }


    /**
     * Checks if a hand of cards is a wheel, i.e., A-2-3-4-5.
     *
     * @param cards The list of cards representing the hand.
     * @return True if the hand is a wheel, false otherwise.
     */
    private boolean isWheel(List<Card> cards) {
        // Create a set of ranks from the cards in the hand
        // Set used for efficiency. ( O(1) for checking the presence of elements )
        // Also automatically removes duplicates
        Set<CardRank> rankSet = cards.stream().map(Card::getRank).collect(Collectors.toSet());
        
        // Check if the set contains all ranks from A to 5
        // rankSet.size() == 5 ensures there are no duplicate ranks in cards
        return rankSet.size() == 5 && rankSet.containsAll(Arrays.asList(CardRank.ACE, CardRank.TWO, CardRank.THREE, CardRank.FOUR, CardRank.FIVE));
    }


    /**
     * Checks if the given hand of cards is a royal flush.
     * A royal flush consists of a 10, Jack, Queen, King, and Ace of the same suit.
     * isRoyalFlush() assumes the hand is a flush and is a straight.
     *
     * @param cards The hand of cards to check.
     * @return True if the hand is a royal flush, false otherwise.
     */
    private boolean isRoyalFlush(List<Card> cards) {
        // Check if the hand contains a 10, Jack, Queen, King, and Ace of the same suit
        Set<CardRank> royalFlushRanks = new HashSet<>(Arrays.asList(CardRank.TEN, CardRank.JACK, CardRank.QUEEN, CardRank.KING, CardRank.ACE));
        Set<Card> royalFlush = cards.stream().filter(card -> royalFlushRanks.contains(card.getRank())).collect(Collectors.toSet());

        // Check if all five cards of the royal flush are present in the hand
        return royalFlush.size() == 5 ;
    }


    /**
     * Checks if the hand contains exactly one pair.
     *
     * @param cards The list of cards representing the hand.
     * @return True if the hand contains exactly one pair, false otherwise.
     */
    private boolean isOnePair(List<Card> cards) {
        Map<CardRank, Integer> rankCounts = new HashMap<>();

        // Count the occurrences of each card rank in the hand
        for (Card card : cards) {
            rankCounts.put(card.getRank(), rankCounts.getOrDefault(card.getRank(), 0) + 1);
        }

        // Count the number of pairs found in the hand
        int pairCount = 0;
        for (int count : rankCounts.values()) {
            if (count == 2) {
                pairCount++;
            }
        }

        // Return true if exactly one pair is found in the hand, false otherwise
        return pairCount == 1;
    }


    /**
     * Checks if the hand contains exactly two pairs.
     *
     * @param cards The list of cards representing the hand.
     * @return True if the hand contains exactly two pairs, false otherwise.
     */
    private boolean isTwoPair(List<Card> cards) {
        Map<CardRank, Integer> rankCounts = new HashMap<>();

        // Count the occurrences of each card rank in the hand
        for (Card card : cards) {
            rankCounts.put(card.getRank(), rankCounts.getOrDefault(card.getRank(), 0) + 1);
        }

        // Count the number of pairs found in the hand
        int pairCount = 0;
        for (int count : rankCounts.values()) {
            if (count == 2) {
                pairCount++;
            }
        }

        // Return true if exactly two pairs are found in the hand, false otherwise
        return pairCount == 2;
    }


    /**
     * Checks if the hand contains three cards of the same rank.
     *
     * @param cards The list of cards in the hand.
     * @return True if the hand contains three cards of the same rank, false otherwise.
     */
    private boolean isThreeOfAKind(List<Card> cards) {
        Map<CardRank, Integer> rankCounts = new HashMap<>();

        // Count the occurrences of each card rank in the hand
        for (Card card : cards) {
            rankCounts.put(card.getRank(), rankCounts.getOrDefault(card.getRank(), 0) + 1);
        }
    
        // Count the number of trips found in the hand
        for (int count : rankCounts.values()) {
            if (count == 3) {
                return true; // Found three cards of the same rank
            }
        }
    
        return false; // Did not find three cards of the same rank
    }


    /**
     * Checks if the hand contains four cards of the same rank.
     *
     * @param cards The list of cards in the hand.
     * @return True if the hand contains four cards of the same rank, false otherwise.
     */
    private boolean isFourOfAKind(List<Card> cards) {
        Map<CardRank, Integer> rankCounts = new HashMap<>();

        // Count the occurrences of each card rank in the hand
        for (Card card : cards) {
            rankCounts.put(card.getRank(), rankCounts.getOrDefault(card.getRank(), 0) + 1);
        }
    
        for (int count : rankCounts.values()) {
            // Include count == 5 for if game is expanded to include joker or second deck
            // If count == 5, it is still only considered as four-of-a-kind with a kicker of the same suit
            if (count == 4 || count == 5) {
                return true; // Found four cards of the same rank
            }
        }
    
        return false; // Did not find four cards of the same rank
    }


    /**
     * Checks if the hand contains a full house, which is a three of a kind and a pair.
     *
     * @param cards The list of cards representing the hand.
     * @return True if the hand contains a full house, false otherwise.
     */
    private boolean isFullHouse(List<Card> cards) {
        boolean hasThreeOfAKind = false;
        boolean hasPair = false;
        Map<CardRank, Integer> rankCounts = new HashMap<>();

        // Count the occurrences of each card rank in the hand
        for (Card card : cards) {
            rankCounts.put(card.getRank(), rankCounts.getOrDefault(card.getRank(), 0) + 1);
        }

        // Check if the hand has a three of a kind and a pair
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
