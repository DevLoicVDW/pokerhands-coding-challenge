package com.sprinthive.pokerhands;

import com.sprinthive.pokerhands.exception.NotEnoughCardsInDeckException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> cards = new ArrayList<Card>(52);

    public Deck() {
        for (Suit suit : Suit.values()) {
            for (CardRank rank : CardRank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(cards);
    }

    public synchronized int getNumberOfCards() {
        return cards.size();
    }

    public synchronized Card[] pick(int numberOfCards) throws NotEnoughCardsInDeckException {
        if(numberOfCards > cards.size()){
            throw new NotEnoughCardsInDeckException("Not enough cards in the deck.");
        }

        Card[] pickedCards = new Card[numberOfCards];
        
        for(int i = 0; i < numberOfCards; i++){
            pickedCards[i] = cards.remove(0); 
        }

        return pickedCards;
    }
}
