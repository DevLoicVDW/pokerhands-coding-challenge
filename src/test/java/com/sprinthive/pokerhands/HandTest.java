package com.sprinthive.pokerhands;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HandTest {

    @Test
    public void testInvalidHandRank() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.ACE, Suit.CLUBS));
        cards.add(new Card(CardRank.KING, Suit.CLUBS));
        Hand handTwo = new Hand(cards);
        assertEquals("Can not rank a hand with 2 card(s).", handTwo.describeHandRank());
        cards.add(new Card(CardRank.TWO, Suit.CLUBS));
        cards.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        cards.add(new Card(CardRank.JACK, Suit.CLUBS));
        cards.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand handSix = new Hand(cards);
        assertEquals("Can not rank a hand with 6 card(s).", handSix.describeHandRank());
        assertEquals(0, handTwo.compareTo(handSix));
    }

    @Test
    public void testHandRankRoyalFlush() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.ACE, Suit.CLUBS));
        cards.add(new Card(CardRank.KING, Suit.CLUBS));
        cards.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        cards.add(new Card(CardRank.JACK, Suit.CLUBS));
        cards.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("Royal flush of clubs", hand.describeHandRank());
    }

    @Test
    public void testHandRankStraightAceHigh() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.ACE, Suit.CLUBS));
        cards.add(new Card(CardRank.KING, Suit.CLUBS));
        cards.add(new Card(CardRank.QUEEN, Suit.HEARTS));
        cards.add(new Card(CardRank.JACK, Suit.CLUBS));
        cards.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("Straight, ace high", hand.describeHandRank());
    }


    @Test
    public void testHandRankStraightFlush() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.NINE, Suit.CLUBS));
        cards.add(new Card(CardRank.KING, Suit.CLUBS));
        cards.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        cards.add(new Card(CardRank.JACK, Suit.CLUBS));
        cards.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("Straight flush, king high", hand.describeHandRank());
    }

    @Test
    public void testHandRank4OfAKind() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.NINE, Suit.CLUBS));
        cards.add(new Card(CardRank.NINE, Suit.HEARTS));
        cards.add(new Card(CardRank.NINE, Suit.DIAMONDS));
        cards.add(new Card(CardRank.NINE, Suit.SPADES));
        cards.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("4 of a kind of nines", hand.describeHandRank());
    }

    @Test
    public void testHandRankFullHouse() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.NINE, Suit.CLUBS));
        cards.add(new Card(CardRank.NINE, Suit.HEARTS));
        cards.add(new Card(CardRank.NINE, Suit.DIAMONDS));
        cards.add(new Card(CardRank.TEN, Suit.SPADES));
        cards.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("Full house, nines over tens", hand.describeHandRank());
    }

    @Test
    public void testHandRankFlush() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.NINE, Suit.CLUBS));
        cards.add(new Card(CardRank.THREE, Suit.CLUBS));
        cards.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        cards.add(new Card(CardRank.JACK, Suit.CLUBS));
        cards.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("Flush, queen high", hand.describeHandRank());
    }

    @Test
    public void testHandRankStraight() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.NINE, Suit.CLUBS));
        cards.add(new Card(CardRank.KING, Suit.HEARTS));
        cards.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        cards.add(new Card(CardRank.JACK, Suit.CLUBS));
        cards.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("Straight, king high", hand.describeHandRank());
    }

    @Test
    public void testHandRank3OfAKind() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.NINE, Suit.CLUBS));
        cards.add(new Card(CardRank.NINE, Suit.HEARTS));
        cards.add(new Card(CardRank.NINE, Suit.DIAMONDS));
        cards.add(new Card(CardRank.TEN, Suit.SPADES));
        cards.add(new Card(CardRank.TWO, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("3 of a kind of nines", hand.describeHandRank());
    }

    @Test
    public void testHandRank2Pairs() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.NINE, Suit.CLUBS));
        cards.add(new Card(CardRank.NINE, Suit.HEARTS));
        cards.add(new Card(CardRank.TEN, Suit.DIAMONDS));
        cards.add(new Card(CardRank.TEN, Suit.SPADES));
        cards.add(new Card(CardRank.TWO, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("Two pair, tens and nines", hand.describeHandRank());
    }

    @Test
    public void testHandRank1Pairs() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.NINE, Suit.CLUBS));
        cards.add(new Card(CardRank.NINE, Suit.HEARTS));
        cards.add(new Card(CardRank.TEN, Suit.DIAMONDS));
        cards.add(new Card(CardRank.SIX, Suit.SPADES));
        cards.add(new Card(CardRank.TWO, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("One pair of nines", hand.describeHandRank());
    }

    @Test
    public void testHandRankHighCard() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.THREE, Suit.CLUBS));
        cards.add(new Card(CardRank.NINE, Suit.HEARTS));
        cards.add(new Card(CardRank.TEN, Suit.DIAMONDS));
        cards.add(new Card(CardRank.SIX, Suit.SPADES));
        cards.add(new Card(CardRank.TWO, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("High card ten of diamonds", hand.describeHandRank());
    }

    @Test
    public void testHandRank4OfAKindEdge() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.NINE, Suit.CLUBS));
        cards.add(new Card(CardRank.NINE, Suit.HEARTS));
        cards.add(new Card(CardRank.NINE, Suit.DIAMONDS));
        cards.add(new Card(CardRank.NINE, Suit.SPADES));
        cards.add(new Card(CardRank.NINE, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("4 of a kind of nines", hand.describeHandRank());
    }

    @Test
    public void testHandRankStraightWheelFlush0() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.FIVE, Suit.CLUBS));
        cards.add(new Card(CardRank.FOUR, Suit.CLUBS));
        cards.add(new Card(CardRank.THREE, Suit.CLUBS));
        cards.add(new Card(CardRank.TWO, Suit.CLUBS));
        cards.add(new Card(CardRank.ACE, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("Straight flush, five high", hand.describeHandRank());
    }
    
    @Test
    public void testHandRankStraightWheelFlush1() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.ACE, Suit.CLUBS));
        cards.add(new Card(CardRank.TWO, Suit.CLUBS));
        cards.add(new Card(CardRank.THREE, Suit.CLUBS));
        cards.add(new Card(CardRank.FOUR, Suit.CLUBS));
        cards.add(new Card(CardRank.FIVE, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("Straight flush, five high", hand.describeHandRank());
    }
    
    @Test
    public void testHandRankMixedSuitStraightWheelEdge() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.ACE, Suit.CLUBS));
        cards.add(new Card(CardRank.TWO, Suit.SPADES));
        cards.add(new Card(CardRank.THREE, Suit.HEARTS));
        cards.add(new Card(CardRank.FOUR, Suit.DIAMONDS));
        cards.add(new Card(CardRank.FIVE, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("Straight, five high", hand.describeHandRank());
    }

    @Test
    public void testHandRankFourAces() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.ACE, Suit.CLUBS));
        cards.add(new Card(CardRank.ACE, Suit.SPADES));
        cards.add(new Card(CardRank.ACE, Suit.HEARTS));
        cards.add(new Card(CardRank.FIVE, Suit.CLUBS));
        cards.add(new Card(CardRank.ACE, Suit.DIAMONDS));
        Hand hand = new Hand(cards);
        assertEquals("4 of a kind of aces", hand.describeHandRank());
    }

    @Test
    public void testHandRankLowRankFullHouse() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.TWO, Suit.CLUBS));
        cards.add(new Card(CardRank.TWO, Suit.SPADES));
        cards.add(new Card(CardRank.THREE, Suit.DIAMONDS));
        cards.add(new Card(CardRank.TWO, Suit.HEARTS));
        cards.add(new Card(CardRank.THREE, Suit.DIAMONDS));
        Hand hand = new Hand(cards);
        assertEquals("Full house, twos over threes", hand.describeHandRank());
    }

    @Test
    public void testHandRankLowRankFlush() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.TWO, Suit.DIAMONDS));
        cards.add(new Card(CardRank.THREE, Suit.DIAMONDS));
        cards.add(new Card(CardRank.FOUR, Suit.DIAMONDS));
        cards.add(new Card(CardRank.SIX, Suit.DIAMONDS));
        cards.add(new Card(CardRank.SEVEN, Suit.DIAMONDS));
        Hand hand = new Hand(cards);
        assertEquals("Flush, seven high", hand.describeHandRank());
    }

    @Test
    public void testCompareStraightToStraight() {
        ArrayList<Card> lowStraightList = new ArrayList<Card>(5);
        lowStraightList.add(new Card(CardRank.NINE, Suit.CLUBS));
        lowStraightList.add(new Card(CardRank.EIGHT, Suit.HEARTS));
        lowStraightList.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        lowStraightList.add(new Card(CardRank.JACK, Suit.CLUBS));
        lowStraightList.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand lowStraightHand = new Hand(lowStraightList);
        assertEquals("Straight, queen high", lowStraightHand.describeHandRank());

        ArrayList<Card> highStraightList = new ArrayList<Card>(5);
        highStraightList.add(new Card(CardRank.NINE, Suit.CLUBS));
        highStraightList.add(new Card(CardRank.KING, Suit.HEARTS));
        highStraightList.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        highStraightList.add(new Card(CardRank.JACK, Suit.CLUBS));
        highStraightList.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand highStraightHand = new Hand(highStraightList);
        assertEquals("Straight, king high", highStraightHand.describeHandRank());
        assertTrue(lowStraightHand.compareTo(highStraightHand) < 0);
    }

    @Test
    public void testCompareFlushToFlush() {
        ArrayList<Card> highFlushList = new ArrayList<Card>(5);
        highFlushList.add(new Card(CardRank.THREE, Suit.CLUBS));
        highFlushList.add(new Card(CardRank.FIVE, Suit.CLUBS));
        highFlushList.add(new Card(CardRank.SEVEN, Suit.CLUBS));
        highFlushList.add(new Card(CardRank.NINE, Suit.CLUBS));
        highFlushList.add(new Card(CardRank.JACK, Suit.CLUBS));
        Hand highFlushHand = new Hand(highFlushList);
        assertEquals("Flush, jack high", highFlushHand.describeHandRank());

        ArrayList<Card> lowFlushList = new ArrayList<Card>(5);
        lowFlushList.add(new Card(CardRank.TWO, Suit.CLUBS));
        lowFlushList.add(new Card(CardRank.FOUR, Suit.CLUBS));
        lowFlushList.add(new Card(CardRank.SIX, Suit.CLUBS));
        lowFlushList.add(new Card(CardRank.EIGHT, Suit.CLUBS));
        lowFlushList.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand lowStraightHand = new Hand(lowFlushList);
        assertEquals("Flush, ten high", lowStraightHand.describeHandRank());
        assertTrue(highFlushHand.compareTo(lowStraightHand) > 0);
    }

    @Test
    public void testCompareHighCardToHighCard() {
        ArrayList<Card> highList = new ArrayList<Card>(5);
        highList.add(new Card(CardRank.NINE, Suit.CLUBS));
        highList.add(new Card(CardRank.THREE, Suit.HEARTS));
        highList.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        highList.add(new Card(CardRank.JACK, Suit.HEARTS));
        highList.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand highHand = new Hand(highList);
        assertEquals("High card queen of clubs", highHand.describeHandRank());

        ArrayList<Card> lowList = new ArrayList<Card>(5);
        lowList.add(new Card(CardRank.NINE, Suit.CLUBS));
        lowList.add(new Card(CardRank.FIVE, Suit.HEARTS));
        lowList.add(new Card(CardRank.FOUR, Suit.SPADES));
        lowList.add(new Card(CardRank.JACK, Suit.CLUBS));
        lowList.add(new Card(CardRank.TEN, Suit.DIAMONDS));
        Hand lowHand = new Hand(lowList);
        assertEquals("High card jack of clubs", lowHand.describeHandRank());
        assertTrue(highHand.compareTo(lowHand) > 0);
    }

    @Test
    public void testCompareOnePairToOnePair() {
        // Pairs have different ranks
        ArrayList<Card> highPairList1 = new ArrayList<>(5);
        highPairList1.add(new Card(CardRank.NINE, Suit.CLUBS));
        highPairList1.add(new Card(CardRank.NINE, Suit.HEARTS));
        highPairList1.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        highPairList1.add(new Card(CardRank.JACK, Suit.HEARTS));
        highPairList1.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand highPairHand1 = new Hand(highPairList1);
        assertEquals("One pair of nines", highPairHand1.describeHandRank());

        ArrayList<Card> lowPairList1 = new ArrayList<>(5);
        lowPairList1.add(new Card(CardRank.EIGHT, Suit.CLUBS));
        lowPairList1.add(new Card(CardRank.EIGHT, Suit.DIAMONDS));
        lowPairList1.add(new Card(CardRank.FOUR, Suit.SPADES));
        lowPairList1.add(new Card(CardRank.JACK, Suit.CLUBS));
        lowPairList1.add(new Card(CardRank.TEN, Suit.HEARTS));
        Hand lowPairHand1 = new Hand(lowPairList1);
        assertEquals("One pair of eights", lowPairHand1.describeHandRank());
        assertTrue(highPairHand1.compareTo(lowPairHand1) > 0);
    
        // Pairs have the same rank
        ArrayList<Card> highPairList2 = new ArrayList<>(5);
        highPairList2.add(new Card(CardRank.NINE, Suit.CLUBS));
        highPairList2.add(new Card(CardRank.NINE, Suit.HEARTS));
        highPairList2.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        highPairList2.add(new Card(CardRank.JACK, Suit.HEARTS));
        highPairList2.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand highPairHand2 = new Hand(highPairList2);
        assertEquals("One pair of nines", highPairHand2.describeHandRank());

        ArrayList<Card> lowPairList2 = new ArrayList<>(5);
        lowPairList2.add(new Card(CardRank.NINE, Suit.CLUBS));
        lowPairList2.add(new Card(CardRank.NINE, Suit.DIAMONDS));
        lowPairList2.add(new Card(CardRank.FOUR, Suit.SPADES));
        lowPairList2.add(new Card(CardRank.JACK, Suit.CLUBS));
        lowPairList2.add(new Card(CardRank.TEN, Suit.HEARTS));
        Hand lowPairHand2 = new Hand(lowPairList2);
        assertEquals("One pair of nines", lowPairHand2.describeHandRank());
        assertTrue(highPairHand2.compareTo(lowPairHand2) > 0);
    }

    @Test
    public void testCompareTwoPairToTwoPair() {
        // Same higher pair rank, different lower pair rank
        ArrayList<Card> highPairList1 = new ArrayList<>(5);
        highPairList1.add(new Card(CardRank.KING, Suit.CLUBS));
        highPairList1.add(new Card(CardRank.KING, Suit.HEARTS));
        highPairList1.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        highPairList1.add(new Card(CardRank.QUEEN, Suit.HEARTS));
        highPairList1.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand highPairHand1 = new Hand(highPairList1);
        assertEquals("Two pair, kings and queens", highPairHand1.describeHandRank());

        ArrayList<Card> highPairList2 = new ArrayList<>(5);
        highPairList2.add(new Card(CardRank.KING, Suit.CLUBS));
        highPairList2.add(new Card(CardRank.KING, Suit.HEARTS));
        highPairList2.add(new Card(CardRank.JACK, Suit.CLUBS));
        highPairList2.add(new Card(CardRank.JACK, Suit.HEARTS));
        highPairList2.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand highPairHand2 = new Hand(highPairList2);
        assertEquals("Two pair, kings and jacks", highPairHand2.describeHandRank());
        assertTrue(highPairHand1.compareTo(highPairHand2) > 0);

        // Different higher pair rank
        ArrayList<Card> diffHighPairList = new ArrayList<>(5);
        diffHighPairList.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        diffHighPairList.add(new Card(CardRank.QUEEN, Suit.HEARTS));
        diffHighPairList.add(new Card(CardRank.JACK, Suit.CLUBS));
        diffHighPairList.add(new Card(CardRank.JACK, Suit.HEARTS));
        diffHighPairList.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand diffHighPairHand = new Hand(diffHighPairList);
        assertEquals("Two pair, queens and jacks", diffHighPairHand.describeHandRank());
        assertTrue(diffHighPairHand.compareTo(highPairHand1) < 0);

        // Same higher pair rank and same lower pair rank
        ArrayList<Card> samePairsList = new ArrayList<>(5);
        samePairsList.add(new Card(CardRank.KING, Suit.CLUBS));
        samePairsList.add(new Card(CardRank.KING, Suit.HEARTS));
        samePairsList.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        samePairsList.add(new Card(CardRank.QUEEN, Suit.HEARTS));
        samePairsList.add(new Card(CardRank.NINE, Suit.CLUBS));
        Hand samePairsHand = new Hand(samePairsList);
        assertEquals("Two pair, kings and queens", samePairsHand.describeHandRank());
        assertTrue(samePairsHand.compareTo(highPairHand1) < 0);
    }

    @Test
    public void testCompareThreeOfAKindToThreeOfAKind() {
        // Same three of a kind rank, different kickers
        ArrayList<Card> tripsList1 = new ArrayList<>(5);
        tripsList1.add(new Card(CardRank.ACE, Suit.CLUBS));
        tripsList1.add(new Card(CardRank.ACE, Suit.HEARTS));
        tripsList1.add(new Card(CardRank.ACE, Suit.DIAMONDS));
        tripsList1.add(new Card(CardRank.KING, Suit.CLUBS));
        tripsList1.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand tripsHand1 = new Hand(tripsList1);
        assertEquals("3 of a kind of aces", tripsHand1.describeHandRank());

        ArrayList<Card> tripsList2 = new ArrayList<>(5);
        tripsList2.add(new Card(CardRank.ACE, Suit.CLUBS));
        tripsList2.add(new Card(CardRank.ACE, Suit.HEARTS));
        tripsList2.add(new Card(CardRank.ACE, Suit.SPADES));
        tripsList2.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        tripsList2.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand tripsHand2 = new Hand(tripsList2);
        assertEquals("3 of a kind of aces", tripsHand2.describeHandRank());

        assertTrue(tripsHand1.compareTo(tripsHand2) > 0);

        // Different three of a kind rank
        ArrayList<Card> diffTripsList = new ArrayList<>(5);
        diffTripsList.add(new Card(CardRank.KING, Suit.CLUBS));
        diffTripsList.add(new Card(CardRank.KING, Suit.HEARTS));
        diffTripsList.add(new Card(CardRank.KING, Suit.DIAMONDS));
        diffTripsList.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        diffTripsList.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand diffTripsHand = new Hand(diffTripsList);
        assertEquals("3 of a kind of kings", diffTripsHand.describeHandRank());

        assertTrue(diffTripsHand.compareTo(tripsHand1) < 0);

        // Same three of a kind rank and same kicker
        ArrayList<Card> sameTripsList = new ArrayList<>(5);
        sameTripsList.add(new Card(CardRank.ACE, Suit.CLUBS));
        sameTripsList.add(new Card(CardRank.ACE, Suit.HEARTS));
        sameTripsList.add(new Card(CardRank.ACE, Suit.DIAMONDS));
        sameTripsList.add(new Card(CardRank.KING, Suit.CLUBS));
        sameTripsList.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand sameTripsHand = new Hand(sameTripsList);
        assertEquals("3 of a kind of aces", sameTripsHand.describeHandRank());

        assertTrue(sameTripsHand.compareTo(tripsHand1) == 0);
    }

    @Test
    public void testCompareFourOfAKindToFourOfAKind() {
        // Same four of a kind rank, different kicker
        ArrayList<Card> quadsList1 = new ArrayList<>(5);
        quadsList1.add(new Card(CardRank.ACE, Suit.CLUBS));
        quadsList1.add(new Card(CardRank.ACE, Suit.HEARTS));
        quadsList1.add(new Card(CardRank.ACE, Suit.DIAMONDS));
        quadsList1.add(new Card(CardRank.ACE, Suit.SPADES));
        quadsList1.add(new Card(CardRank.KING, Suit.CLUBS));
        Hand quadsHand1 = new Hand(quadsList1);
        assertEquals("4 of a kind of aces", quadsHand1.describeHandRank());

        ArrayList<Card> quadsList2 = new ArrayList<>(5);
        quadsList2.add(new Card(CardRank.ACE, Suit.CLUBS));
        quadsList2.add(new Card(CardRank.ACE, Suit.HEARTS));
        quadsList2.add(new Card(CardRank.ACE, Suit.DIAMONDS));
        quadsList2.add(new Card(CardRank.ACE, Suit.SPADES));
        quadsList2.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        Hand quadsHand2 = new Hand(quadsList2);
        assertEquals("4 of a kind of aces", quadsHand2.describeHandRank());

        assertTrue(quadsHand1.compareTo(quadsHand2) > 0);

        // Different four of a kind rank
        ArrayList<Card> diffQuadsList = new ArrayList<>(5);
        diffQuadsList.add(new Card(CardRank.KING, Suit.CLUBS));
        diffQuadsList.add(new Card(CardRank.KING, Suit.HEARTS));
        diffQuadsList.add(new Card(CardRank.KING, Suit.DIAMONDS));
        diffQuadsList.add(new Card(CardRank.KING, Suit.SPADES));
        diffQuadsList.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        Hand diffQuadsHand = new Hand(diffQuadsList);
        assertEquals("4 of a kind of kings", diffQuadsHand.describeHandRank());

        assertTrue(diffQuadsHand.compareTo(quadsHand1) < 0);

        // Same four of a kind rank and same kicker
        assertTrue(quadsHand1.compareTo(quadsHand1) == 0);
    }

    @Test
    public void testCompareFullHouseToFullHouse() {
        // Same three of a kind rank, different pair rank
        ArrayList<Card> fullHouseList1 = new ArrayList<>(5);
        fullHouseList1.add(new Card(CardRank.ACE, Suit.CLUBS));
        fullHouseList1.add(new Card(CardRank.ACE, Suit.HEARTS));
        fullHouseList1.add(new Card(CardRank.ACE, Suit.DIAMONDS));
        fullHouseList1.add(new Card(CardRank.KING, Suit.CLUBS));
        fullHouseList1.add(new Card(CardRank.KING, Suit.DIAMONDS));
        Hand fullHouseHand1 = new Hand(fullHouseList1);
        assertEquals("Full house, aces over kings", fullHouseHand1.describeHandRank());

        ArrayList<Card> fullHouseList2 = new ArrayList<>(5);
        fullHouseList2.add(new Card(CardRank.ACE, Suit.CLUBS));
        fullHouseList2.add(new Card(CardRank.ACE, Suit.HEARTS));
        fullHouseList2.add(new Card(CardRank.ACE, Suit.SPADES));
        fullHouseList2.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        fullHouseList2.add(new Card(CardRank.QUEEN, Suit.DIAMONDS));
        Hand fullHouseHand2 = new Hand(fullHouseList2);
        assertEquals("Full house, aces over queens", fullHouseHand2.describeHandRank());

        assertTrue(fullHouseHand1.compareTo(fullHouseHand2) > 0);

        // Different three of a kind rank
        ArrayList<Card> fullHouseList3 = new ArrayList<>(5);
        fullHouseList3.add(new Card(CardRank.KING, Suit.CLUBS));
        fullHouseList3.add(new Card(CardRank.KING, Suit.HEARTS));
        fullHouseList3.add(new Card(CardRank.KING, Suit.DIAMONDS));
        fullHouseList3.add(new Card(CardRank.ACE, Suit.CLUBS));
        fullHouseList3.add(new Card(CardRank.ACE, Suit.DIAMONDS));
        Hand fullHouseHand3 = new Hand(fullHouseList3);
        assertEquals("Full house, kings over aces", fullHouseHand3.describeHandRank());

        assertTrue(fullHouseHand2.compareTo(fullHouseHand3) > 0);

        // Same three of a kind rank, same pair rank
        assertTrue(fullHouseHand1.compareTo(fullHouseHand1) == 0);
    }

    @Test
    public void testCompareStraightFlushToStraightFlush() {
        // Different high card
        ArrayList<Card> straightFlushList1 = new ArrayList<>(5);
        straightFlushList1.add(new Card(CardRank.NINE, Suit.CLUBS));
        straightFlushList1.add(new Card(CardRank.TEN, Suit.CLUBS));
        straightFlushList1.add(new Card(CardRank.SIX, Suit.CLUBS));
        straightFlushList1.add(new Card(CardRank.SEVEN, Suit.CLUBS));
        straightFlushList1.add(new Card(CardRank.EIGHT, Suit.CLUBS));
        Hand straightFlushHand1 = new Hand(straightFlushList1);
        assertEquals("Straight flush, ten high", straightFlushHand1.describeHandRank());

        ArrayList<Card> straightFlushList2 = new ArrayList<>(5);
        straightFlushList2.add(new Card(CardRank.SEVEN, Suit.HEARTS));
        straightFlushList2.add(new Card(CardRank.TEN, Suit.HEARTS));
        straightFlushList2.add(new Card(CardRank.JACK, Suit.HEARTS));
        straightFlushList2.add(new Card(CardRank.NINE, Suit.HEARTS));
        straightFlushList2.add(new Card(CardRank.EIGHT, Suit.HEARTS));
        Hand straightFlushHand2 = new Hand(straightFlushList2);
        assertEquals("Straight flush, jack high", straightFlushHand2.describeHandRank());

        assertTrue(straightFlushHand1.compareTo(straightFlushHand2) < 0);
        
        // Same high card
        assertTrue(straightFlushHand1.compareTo(straightFlushHand1) == 0);

    }

    @Test
    public void testCompareStraightFlushToOther() {
        ArrayList<Card> straightFlushList = new ArrayList<>(5);
        straightFlushList.add(new Card(CardRank.NINE, Suit.CLUBS));
        straightFlushList.add(new Card(CardRank.TEN, Suit.CLUBS));
        straightFlushList.add(new Card(CardRank.SIX, Suit.CLUBS));
        straightFlushList.add(new Card(CardRank.SEVEN, Suit.CLUBS));
        straightFlushList.add(new Card(CardRank.EIGHT, Suit.CLUBS));
        Hand straightFlushHand = new Hand(straightFlushList);
        assertEquals("Straight flush, ten high", straightFlushHand.describeHandRank());

        ArrayList<Card> fourOfAKindList = new ArrayList<Card>(5);
        fourOfAKindList.add(new Card(CardRank.NINE, Suit.CLUBS));
        fourOfAKindList.add(new Card(CardRank.NINE, Suit.HEARTS));
        fourOfAKindList.add(new Card(CardRank.NINE, Suit.DIAMONDS));
        fourOfAKindList.add(new Card(CardRank.NINE, Suit.SPADES));
        fourOfAKindList.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand fourOfAKindHand = new Hand(fourOfAKindList);
        assertEquals("4 of a kind of nines", fourOfAKindHand.describeHandRank());

        assertTrue(straightFlushHand.compareTo(fourOfAKindHand) > 0);

        ArrayList<Card> fullHouseList = new ArrayList<>(5);
        fullHouseList.add(new Card(CardRank.SIX, Suit.HEARTS));
        fullHouseList.add(new Card(CardRank.SIX, Suit.DIAMONDS));
        fullHouseList.add(new Card(CardRank.SIX, Suit.SPADES));
        fullHouseList.add(new Card(CardRank.TWO, Suit.CLUBS));
        fullHouseList.add(new Card(CardRank.TWO, Suit.HEARTS));
        Hand fullHouseHand = new Hand(fullHouseList);
        assertEquals("Full house, sixs over twos", fullHouseHand.describeHandRank());

        assertTrue(straightFlushHand.compareTo(fullHouseHand) > 0);

        ArrayList<Card> flushList = new ArrayList<>(5);
        flushList.add(new Card(CardRank.ACE, Suit.CLUBS));
        flushList.add(new Card(CardRank.TEN, Suit.CLUBS));
        flushList.add(new Card(CardRank.SEVEN, Suit.CLUBS));
        flushList.add(new Card(CardRank.FOUR, Suit.CLUBS));
        flushList.add(new Card(CardRank.TWO, Suit.CLUBS));
        Hand flushHand = new Hand(flushList);
        assertEquals("Flush, ace high", flushHand.describeHandRank());

        assertTrue(straightFlushHand.compareTo(flushHand) > 0);

        ArrayList<Card> straightList = new ArrayList<>(5);
        straightList.add(new Card(CardRank.TWO, Suit.DIAMONDS));
        straightList.add(new Card(CardRank.THREE, Suit.HEARTS));
        straightList.add(new Card(CardRank.FOUR, Suit.CLUBS));
        straightList.add(new Card(CardRank.FIVE, Suit.SPADES));
        straightList.add(new Card(CardRank.SIX, Suit.CLUBS));
        Hand straightHand = new Hand(straightList);
        assertEquals("Straight, six high", straightHand.describeHandRank());

        assertTrue(straightFlushHand.compareTo(straightHand) > 0);

        ArrayList<Card> threeOfAKindList = new ArrayList<>(5);
        threeOfAKindList.add(new Card(CardRank.SIX, Suit.DIAMONDS));
        threeOfAKindList.add(new Card(CardRank.SIX, Suit.HEARTS));
        threeOfAKindList.add(new Card(CardRank.SIX, Suit.SPADES));
        threeOfAKindList.add(new Card(CardRank.TWO, Suit.CLUBS));
        threeOfAKindList.add(new Card(CardRank.THREE, Suit.HEARTS));
        Hand threeOfAKindHand = new Hand(threeOfAKindList);
        assertEquals("3 of a kind of sixs", threeOfAKindHand.describeHandRank());

        assertTrue(straightFlushHand.compareTo(threeOfAKindHand) > 0);

        ArrayList<Card> twoPairList = new ArrayList<>(5);
        twoPairList.add(new Card(CardRank.TWO, Suit.DIAMONDS));
        twoPairList.add(new Card(CardRank.TWO, Suit.HEARTS));
        twoPairList.add(new Card(CardRank.FIVE, Suit.SPADES));
        twoPairList.add(new Card(CardRank.FIVE, Suit.CLUBS));
        twoPairList.add(new Card(CardRank.THREE, Suit.HEARTS));
        Hand twoPairHand = new Hand(twoPairList);
        assertEquals("Two pair, fives and twos", twoPairHand.describeHandRank());

        assertTrue(straightFlushHand.compareTo(twoPairHand) > 0);

        ArrayList<Card> onePairList = new ArrayList<>(5);
        onePairList.add(new Card(CardRank.TWO, Suit.DIAMONDS));
        onePairList.add(new Card(CardRank.TWO, Suit.HEARTS));
        onePairList.add(new Card(CardRank.FIVE, Suit.SPADES));
        onePairList.add(new Card(CardRank.SEVEN, Suit.CLUBS));
        onePairList.add(new Card(CardRank.EIGHT, Suit.HEARTS));
        Hand onePairHand = new Hand(onePairList);
        assertEquals("One pair of twos", onePairHand.describeHandRank());

        assertTrue(straightFlushHand.compareTo(onePairHand) > 0);

        ArrayList<Card> highCardList = new ArrayList<>(5);
        highCardList.add(new Card(CardRank.ACE, Suit.DIAMONDS));
        highCardList.add(new Card(CardRank.SEVEN, Suit.HEARTS));
        highCardList.add(new Card(CardRank.EIGHT, Suit.SPADES));
        highCardList.add(new Card(CardRank.TEN, Suit.CLUBS));
        highCardList.add(new Card(CardRank.JACK, Suit.HEARTS));
        Hand highCardHand = new Hand(highCardList);
        assertEquals("High card ace of diamonds", highCardHand.describeHandRank());

        assertTrue(straightFlushHand.compareTo(highCardHand) > 0);
    }

    @Test
    public void testCompareFourOfAKindToOther() {
        ArrayList<Card> fourOfAKindList = new ArrayList<Card>(5);
        fourOfAKindList.add(new Card(CardRank.NINE, Suit.CLUBS));
        fourOfAKindList.add(new Card(CardRank.NINE, Suit.HEARTS));
        fourOfAKindList.add(new Card(CardRank.NINE, Suit.DIAMONDS));
        fourOfAKindList.add(new Card(CardRank.NINE, Suit.SPADES));
        fourOfAKindList.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand fourOfAKindHand = new Hand(fourOfAKindList);
        assertEquals("4 of a kind of nines", fourOfAKindHand.describeHandRank());

        ArrayList<Card> fullHouseList = new ArrayList<>(5);
        fullHouseList.add(new Card(CardRank.SIX, Suit.HEARTS));
        fullHouseList.add(new Card(CardRank.SIX, Suit.DIAMONDS));
        fullHouseList.add(new Card(CardRank.SIX, Suit.SPADES));
        fullHouseList.add(new Card(CardRank.TWO, Suit.CLUBS));
        fullHouseList.add(new Card(CardRank.TWO, Suit.HEARTS));
        Hand fullHouseHand = new Hand(fullHouseList);
        assertEquals("Full house, sixs over twos", fullHouseHand.describeHandRank());

        assertTrue(fourOfAKindHand.compareTo(fullHouseHand) > 0);

        ArrayList<Card> flushList = new ArrayList<>(5);
        flushList.add(new Card(CardRank.ACE, Suit.CLUBS));
        flushList.add(new Card(CardRank.TEN, Suit.CLUBS));
        flushList.add(new Card(CardRank.SEVEN, Suit.CLUBS));
        flushList.add(new Card(CardRank.FOUR, Suit.CLUBS));
        flushList.add(new Card(CardRank.TWO, Suit.CLUBS));
        Hand flushHand = new Hand(flushList);
        assertEquals("Flush, ace high", flushHand.describeHandRank());

        assertTrue(fourOfAKindHand.compareTo(flushHand) > 0);

        ArrayList<Card> straightList = new ArrayList<>(5);
        straightList.add(new Card(CardRank.TWO, Suit.DIAMONDS));
        straightList.add(new Card(CardRank.THREE, Suit.HEARTS));
        straightList.add(new Card(CardRank.FOUR, Suit.CLUBS));
        straightList.add(new Card(CardRank.FIVE, Suit.SPADES));
        straightList.add(new Card(CardRank.SIX, Suit.CLUBS));
        Hand straightHand = new Hand(straightList);
        assertEquals("Straight, six high", straightHand.describeHandRank());

        assertTrue(fourOfAKindHand.compareTo(straightHand) > 0);

        ArrayList<Card> threeOfAKindList = new ArrayList<>(5);
        threeOfAKindList.add(new Card(CardRank.SIX, Suit.DIAMONDS));
        threeOfAKindList.add(new Card(CardRank.SIX, Suit.HEARTS));
        threeOfAKindList.add(new Card(CardRank.SIX, Suit.SPADES));
        threeOfAKindList.add(new Card(CardRank.TWO, Suit.CLUBS));
        threeOfAKindList.add(new Card(CardRank.THREE, Suit.HEARTS));
        Hand threeOfAKindHand = new Hand(threeOfAKindList);
        assertEquals("3 of a kind of sixs", threeOfAKindHand.describeHandRank());

        assertTrue(fourOfAKindHand.compareTo(threeOfAKindHand) > 0);

        ArrayList<Card> twoPairList = new ArrayList<>(5);
        twoPairList.add(new Card(CardRank.TWO, Suit.DIAMONDS));
        twoPairList.add(new Card(CardRank.TWO, Suit.HEARTS));
        twoPairList.add(new Card(CardRank.FIVE, Suit.SPADES));
        twoPairList.add(new Card(CardRank.FIVE, Suit.CLUBS));
        twoPairList.add(new Card(CardRank.THREE, Suit.HEARTS));
        Hand twoPairHand = new Hand(twoPairList);
        assertEquals("Two pair, fives and twos", twoPairHand.describeHandRank());

        assertTrue(fourOfAKindHand.compareTo(twoPairHand) > 0);

        ArrayList<Card> onePairList = new ArrayList<>(5);
        onePairList.add(new Card(CardRank.TWO, Suit.DIAMONDS));
        onePairList.add(new Card(CardRank.TWO, Suit.HEARTS));
        onePairList.add(new Card(CardRank.FIVE, Suit.SPADES));
        onePairList.add(new Card(CardRank.SEVEN, Suit.CLUBS));
        onePairList.add(new Card(CardRank.EIGHT, Suit.HEARTS));
        Hand onePairHand = new Hand(onePairList);
        assertEquals("One pair of twos", onePairHand.describeHandRank());

        assertTrue(fourOfAKindHand.compareTo(onePairHand) > 0);

        ArrayList<Card> highCardList = new ArrayList<>(5);
        highCardList.add(new Card(CardRank.ACE, Suit.DIAMONDS));
        highCardList.add(new Card(CardRank.SEVEN, Suit.HEARTS));
        highCardList.add(new Card(CardRank.EIGHT, Suit.SPADES));
        highCardList.add(new Card(CardRank.TEN, Suit.CLUBS));
        highCardList.add(new Card(CardRank.JACK, Suit.HEARTS));
        Hand highCardHand = new Hand(highCardList);
        assertEquals("High card ace of diamonds", highCardHand.describeHandRank());

        assertTrue(fourOfAKindHand.compareTo(highCardHand) > 0);
    }

    @Test
    public void testCompareFullHouseToOther() {
        ArrayList<Card> fullHouseList = new ArrayList<>(5);
        fullHouseList.add(new Card(CardRank.SIX, Suit.HEARTS));
        fullHouseList.add(new Card(CardRank.SIX, Suit.DIAMONDS));
        fullHouseList.add(new Card(CardRank.SIX, Suit.SPADES));
        fullHouseList.add(new Card(CardRank.TWO, Suit.CLUBS));
        fullHouseList.add(new Card(CardRank.TWO, Suit.HEARTS));
        Hand fullHouseHand = new Hand(fullHouseList);
        assertEquals("Full house, sixs over twos", fullHouseHand.describeHandRank());

        ArrayList<Card> flushList = new ArrayList<>(5);
        flushList.add(new Card(CardRank.ACE, Suit.CLUBS));
        flushList.add(new Card(CardRank.TEN, Suit.CLUBS));
        flushList.add(new Card(CardRank.SEVEN, Suit.CLUBS));
        flushList.add(new Card(CardRank.FOUR, Suit.CLUBS));
        flushList.add(new Card(CardRank.TWO, Suit.CLUBS));
        Hand flushHand = new Hand(flushList);
        assertEquals("Flush, ace high", flushHand.describeHandRank());

        assertTrue(fullHouseHand.compareTo(flushHand) > 0);

        ArrayList<Card> straightList = new ArrayList<>(5);
        straightList.add(new Card(CardRank.TWO, Suit.DIAMONDS));
        straightList.add(new Card(CardRank.THREE, Suit.HEARTS));
        straightList.add(new Card(CardRank.FOUR, Suit.CLUBS));
        straightList.add(new Card(CardRank.FIVE, Suit.SPADES));
        straightList.add(new Card(CardRank.SIX, Suit.CLUBS));
        Hand straightHand = new Hand(straightList);
        assertEquals("Straight, six high", straightHand.describeHandRank());

        assertTrue(fullHouseHand.compareTo(straightHand) > 0);

        ArrayList<Card> threeOfAKindList = new ArrayList<>(5);
        threeOfAKindList.add(new Card(CardRank.SIX, Suit.DIAMONDS));
        threeOfAKindList.add(new Card(CardRank.SIX, Suit.HEARTS));
        threeOfAKindList.add(new Card(CardRank.SIX, Suit.SPADES));
        threeOfAKindList.add(new Card(CardRank.TWO, Suit.CLUBS));
        threeOfAKindList.add(new Card(CardRank.THREE, Suit.HEARTS));
        Hand threeOfAKindHand = new Hand(threeOfAKindList);
        assertEquals("3 of a kind of sixs", threeOfAKindHand.describeHandRank());

        assertTrue(fullHouseHand.compareTo(threeOfAKindHand) > 0);

        ArrayList<Card> twoPairList = new ArrayList<>(5);
        twoPairList.add(new Card(CardRank.TWO, Suit.DIAMONDS));
        twoPairList.add(new Card(CardRank.TWO, Suit.HEARTS));
        twoPairList.add(new Card(CardRank.FIVE, Suit.SPADES));
        twoPairList.add(new Card(CardRank.FIVE, Suit.CLUBS));
        twoPairList.add(new Card(CardRank.THREE, Suit.HEARTS));
        Hand twoPairHand = new Hand(twoPairList);
        assertEquals("Two pair, fives and twos", twoPairHand.describeHandRank());

        assertTrue(fullHouseHand.compareTo(twoPairHand) > 0);

        ArrayList<Card> onePairList = new ArrayList<>(5);
        onePairList.add(new Card(CardRank.TWO, Suit.DIAMONDS));
        onePairList.add(new Card(CardRank.TWO, Suit.HEARTS));
        onePairList.add(new Card(CardRank.FIVE, Suit.SPADES));
        onePairList.add(new Card(CardRank.SEVEN, Suit.CLUBS));
        onePairList.add(new Card(CardRank.EIGHT, Suit.HEARTS));
        Hand onePairHand = new Hand(onePairList);
        assertEquals("One pair of twos", onePairHand.describeHandRank());

        assertTrue(fullHouseHand.compareTo(onePairHand) > 0);

        ArrayList<Card> highCardList = new ArrayList<>(5);
        highCardList.add(new Card(CardRank.ACE, Suit.DIAMONDS));
        highCardList.add(new Card(CardRank.SEVEN, Suit.HEARTS));
        highCardList.add(new Card(CardRank.EIGHT, Suit.SPADES));
        highCardList.add(new Card(CardRank.TEN, Suit.CLUBS));
        highCardList.add(new Card(CardRank.JACK, Suit.HEARTS));
        Hand highCardHand = new Hand(highCardList);
        assertEquals("High card ace of diamonds", highCardHand.describeHandRank());

        assertTrue(fullHouseHand.compareTo(highCardHand) > 0);
    }

    @Test
    public void testCompareFlushToOther() {
        ArrayList<Card> flushList = new ArrayList<>(5);
        flushList.add(new Card(CardRank.ACE, Suit.CLUBS));
        flushList.add(new Card(CardRank.TEN, Suit.CLUBS));
        flushList.add(new Card(CardRank.SEVEN, Suit.CLUBS));
        flushList.add(new Card(CardRank.FOUR, Suit.CLUBS));
        flushList.add(new Card(CardRank.TWO, Suit.CLUBS));
        Hand flushHand = new Hand(flushList);
        assertEquals("Flush, ace high", flushHand.describeHandRank());

        ArrayList<Card> straightList = new ArrayList<>(5);
        straightList.add(new Card(CardRank.TWO, Suit.DIAMONDS));
        straightList.add(new Card(CardRank.THREE, Suit.HEARTS));
        straightList.add(new Card(CardRank.FOUR, Suit.CLUBS));
        straightList.add(new Card(CardRank.FIVE, Suit.SPADES));
        straightList.add(new Card(CardRank.SIX, Suit.CLUBS));
        Hand straightHand = new Hand(straightList);
        assertEquals("Straight, six high", straightHand.describeHandRank());

        assertTrue(flushHand.compareTo(straightHand) > 0);

        ArrayList<Card> threeOfAKindList = new ArrayList<>(5);
        threeOfAKindList.add(new Card(CardRank.SIX, Suit.DIAMONDS));
        threeOfAKindList.add(new Card(CardRank.SIX, Suit.HEARTS));
        threeOfAKindList.add(new Card(CardRank.SIX, Suit.SPADES));
        threeOfAKindList.add(new Card(CardRank.TWO, Suit.CLUBS));
        threeOfAKindList.add(new Card(CardRank.THREE, Suit.HEARTS));
        Hand threeOfAKindHand = new Hand(threeOfAKindList);
        assertEquals("3 of a kind of sixs", threeOfAKindHand.describeHandRank());

        assertTrue(flushHand.compareTo(threeOfAKindHand) > 0);

        ArrayList<Card> twoPairList = new ArrayList<>(5);
        twoPairList.add(new Card(CardRank.TWO, Suit.DIAMONDS));
        twoPairList.add(new Card(CardRank.TWO, Suit.HEARTS));
        twoPairList.add(new Card(CardRank.FIVE, Suit.SPADES));
        twoPairList.add(new Card(CardRank.FIVE, Suit.CLUBS));
        twoPairList.add(new Card(CardRank.THREE, Suit.HEARTS));
        Hand twoPairHand = new Hand(twoPairList);
        assertEquals("Two pair, fives and twos", twoPairHand.describeHandRank());

        assertTrue(flushHand.compareTo(twoPairHand) > 0);

        ArrayList<Card> onePairList = new ArrayList<>(5);
        onePairList.add(new Card(CardRank.TWO, Suit.DIAMONDS));
        onePairList.add(new Card(CardRank.TWO, Suit.HEARTS));
        onePairList.add(new Card(CardRank.FIVE, Suit.SPADES));
        onePairList.add(new Card(CardRank.SEVEN, Suit.CLUBS));
        onePairList.add(new Card(CardRank.EIGHT, Suit.HEARTS));
        Hand onePairHand = new Hand(onePairList);
        assertEquals("One pair of twos", onePairHand.describeHandRank());

        assertTrue(flushHand.compareTo(onePairHand) > 0);

        ArrayList<Card> highCardList = new ArrayList<>(5);
        highCardList.add(new Card(CardRank.ACE, Suit.DIAMONDS));
        highCardList.add(new Card(CardRank.SEVEN, Suit.HEARTS));
        highCardList.add(new Card(CardRank.EIGHT, Suit.SPADES));
        highCardList.add(new Card(CardRank.TEN, Suit.CLUBS));
        highCardList.add(new Card(CardRank.JACK, Suit.HEARTS));
        Hand highCardHand = new Hand(highCardList);
        assertEquals("High card ace of diamonds", highCardHand.describeHandRank());

        assertTrue(flushHand.compareTo(highCardHand) > 0);
    }

    @Test
    public void testCompareStraightToOther() {
        ArrayList<Card> straightList = new ArrayList<>(5);
        straightList.add(new Card(CardRank.TWO, Suit.DIAMONDS));
        straightList.add(new Card(CardRank.THREE, Suit.HEARTS));
        straightList.add(new Card(CardRank.FOUR, Suit.CLUBS));
        straightList.add(new Card(CardRank.FIVE, Suit.SPADES));
        straightList.add(new Card(CardRank.SIX, Suit.CLUBS));
        Hand straightHand = new Hand(straightList);
        assertEquals("Straight, six high", straightHand.describeHandRank());

        ArrayList<Card> threeOfAKindList = new ArrayList<>(5);
        threeOfAKindList.add(new Card(CardRank.SIX, Suit.DIAMONDS));
        threeOfAKindList.add(new Card(CardRank.SIX, Suit.HEARTS));
        threeOfAKindList.add(new Card(CardRank.SIX, Suit.SPADES));
        threeOfAKindList.add(new Card(CardRank.TWO, Suit.CLUBS));
        threeOfAKindList.add(new Card(CardRank.THREE, Suit.HEARTS));
        Hand threeOfAKindHand = new Hand(threeOfAKindList);
        assertEquals("3 of a kind of sixs", threeOfAKindHand.describeHandRank());

        assertTrue(straightHand.compareTo(threeOfAKindHand) > 0);

        ArrayList<Card> twoPairList = new ArrayList<>(5);
        twoPairList.add(new Card(CardRank.TWO, Suit.DIAMONDS));
        twoPairList.add(new Card(CardRank.TWO, Suit.HEARTS));
        twoPairList.add(new Card(CardRank.FIVE, Suit.SPADES));
        twoPairList.add(new Card(CardRank.FIVE, Suit.CLUBS));
        twoPairList.add(new Card(CardRank.THREE, Suit.HEARTS));
        Hand twoPairHand = new Hand(twoPairList);
        assertEquals("Two pair, fives and twos", twoPairHand.describeHandRank());

        assertTrue(straightHand.compareTo(twoPairHand) > 0);

        ArrayList<Card> onePairList = new ArrayList<>(5);
        onePairList.add(new Card(CardRank.TWO, Suit.DIAMONDS));
        onePairList.add(new Card(CardRank.TWO, Suit.HEARTS));
        onePairList.add(new Card(CardRank.FIVE, Suit.SPADES));
        onePairList.add(new Card(CardRank.SEVEN, Suit.CLUBS));
        onePairList.add(new Card(CardRank.EIGHT, Suit.HEARTS));
        Hand onePairHand = new Hand(onePairList);
        assertEquals("One pair of twos", onePairHand.describeHandRank());

        assertTrue(straightHand.compareTo(onePairHand) > 0);

        ArrayList<Card> highCardList = new ArrayList<>(5);
        highCardList.add(new Card(CardRank.ACE, Suit.DIAMONDS));
        highCardList.add(new Card(CardRank.SEVEN, Suit.HEARTS));
        highCardList.add(new Card(CardRank.EIGHT, Suit.SPADES));
        highCardList.add(new Card(CardRank.TEN, Suit.CLUBS));
        highCardList.add(new Card(CardRank.JACK, Suit.HEARTS));
        Hand highCardHand = new Hand(highCardList);
        assertEquals("High card ace of diamonds", highCardHand.describeHandRank());

        assertTrue(straightHand.compareTo(highCardHand) > 0);
    }

    @Test
    public void testCompareThreeOfAKindToOther() {
        ArrayList<Card> threeOfAKindList = new ArrayList<>(5);
        threeOfAKindList.add(new Card(CardRank.SIX, Suit.DIAMONDS));
        threeOfAKindList.add(new Card(CardRank.SIX, Suit.HEARTS));
        threeOfAKindList.add(new Card(CardRank.SIX, Suit.SPADES));
        threeOfAKindList.add(new Card(CardRank.TWO, Suit.CLUBS));
        threeOfAKindList.add(new Card(CardRank.THREE, Suit.HEARTS));
        Hand threeOfAKindHand = new Hand(threeOfAKindList);
        assertEquals("3 of a kind of sixs", threeOfAKindHand.describeHandRank());

        ArrayList<Card> twoPairList = new ArrayList<>(5);
        twoPairList.add(new Card(CardRank.TWO, Suit.DIAMONDS));
        twoPairList.add(new Card(CardRank.TWO, Suit.HEARTS));
        twoPairList.add(new Card(CardRank.FIVE, Suit.SPADES));
        twoPairList.add(new Card(CardRank.FIVE, Suit.CLUBS));
        twoPairList.add(new Card(CardRank.THREE, Suit.HEARTS));
        Hand twoPairHand = new Hand(twoPairList);
        assertEquals("Two pair, fives and twos", twoPairHand.describeHandRank());

        assertTrue(threeOfAKindHand.compareTo(twoPairHand) > 0);

        ArrayList<Card> onePairList = new ArrayList<>(5);
        onePairList.add(new Card(CardRank.TWO, Suit.DIAMONDS));
        onePairList.add(new Card(CardRank.TWO, Suit.HEARTS));
        onePairList.add(new Card(CardRank.FIVE, Suit.SPADES));
        onePairList.add(new Card(CardRank.SEVEN, Suit.CLUBS));
        onePairList.add(new Card(CardRank.EIGHT, Suit.HEARTS));
        Hand onePairHand = new Hand(onePairList);
        assertEquals("One pair of twos", onePairHand.describeHandRank());

        assertTrue(threeOfAKindHand.compareTo(onePairHand) > 0);

        ArrayList<Card> highCardList = new ArrayList<>(5);
        highCardList.add(new Card(CardRank.ACE, Suit.DIAMONDS));
        highCardList.add(new Card(CardRank.SEVEN, Suit.HEARTS));
        highCardList.add(new Card(CardRank.EIGHT, Suit.SPADES));
        highCardList.add(new Card(CardRank.TEN, Suit.CLUBS));
        highCardList.add(new Card(CardRank.JACK, Suit.HEARTS));
        Hand highCardHand = new Hand(highCardList);
        assertEquals("High card ace of diamonds", highCardHand.describeHandRank());

        assertTrue(threeOfAKindHand.compareTo(highCardHand) > 0);
    }

    @Test
    public void testCompareTwoPairToOther() {
        ArrayList<Card> twoPairList = new ArrayList<>(5);
        twoPairList.add(new Card(CardRank.TWO, Suit.DIAMONDS));
        twoPairList.add(new Card(CardRank.TWO, Suit.HEARTS));
        twoPairList.add(new Card(CardRank.FIVE, Suit.SPADES));
        twoPairList.add(new Card(CardRank.FIVE, Suit.CLUBS));
        twoPairList.add(new Card(CardRank.THREE, Suit.HEARTS));
        Hand twoPairHand = new Hand(twoPairList);
        assertEquals("Two pair, fives and twos", twoPairHand.describeHandRank());

        ArrayList<Card> onePairList = new ArrayList<>(5);
        onePairList.add(new Card(CardRank.TWO, Suit.DIAMONDS));
        onePairList.add(new Card(CardRank.TWO, Suit.HEARTS));
        onePairList.add(new Card(CardRank.FIVE, Suit.SPADES));
        onePairList.add(new Card(CardRank.SEVEN, Suit.CLUBS));
        onePairList.add(new Card(CardRank.EIGHT, Suit.HEARTS));
        Hand onePairHand = new Hand(onePairList);
        assertEquals("One pair of twos", onePairHand.describeHandRank());

        assertTrue(twoPairHand.compareTo(onePairHand) > 0);

        ArrayList<Card> highCardList = new ArrayList<>(5);
        highCardList.add(new Card(CardRank.ACE, Suit.DIAMONDS));
        highCardList.add(new Card(CardRank.SEVEN, Suit.HEARTS));
        highCardList.add(new Card(CardRank.EIGHT, Suit.SPADES));
        highCardList.add(new Card(CardRank.TEN, Suit.CLUBS));
        highCardList.add(new Card(CardRank.JACK, Suit.HEARTS));
        Hand highCardHand = new Hand(highCardList);
        assertEquals("High card ace of diamonds", highCardHand.describeHandRank());

        assertTrue(twoPairHand.compareTo(highCardHand) > 0);
    }

    @Test
    public void testCompareOnePairToOther() {
        ArrayList<Card> onePairList = new ArrayList<>(5);
        onePairList.add(new Card(CardRank.TWO, Suit.DIAMONDS));
        onePairList.add(new Card(CardRank.TWO, Suit.HEARTS));
        onePairList.add(new Card(CardRank.FIVE, Suit.SPADES));
        onePairList.add(new Card(CardRank.SEVEN, Suit.CLUBS));
        onePairList.add(new Card(CardRank.EIGHT, Suit.HEARTS));
        Hand onePairHand = new Hand(onePairList);
        assertEquals("One pair of twos", onePairHand.describeHandRank());

        ArrayList<Card> highCardList = new ArrayList<>(5);
        highCardList.add(new Card(CardRank.ACE, Suit.DIAMONDS));
        highCardList.add(new Card(CardRank.SEVEN, Suit.HEARTS));
        highCardList.add(new Card(CardRank.EIGHT, Suit.SPADES));
        highCardList.add(new Card(CardRank.TEN, Suit.CLUBS));
        highCardList.add(new Card(CardRank.JACK, Suit.HEARTS));
        Hand highCardHand = new Hand(highCardList);
        assertEquals("High card ace of diamonds", highCardHand.describeHandRank());

        assertTrue(onePairHand.compareTo(highCardHand) > 0);
    }
}
    