package collections;

import java.util.*;

public class Deck {
    private ArrayList<Card> Cards = new ArrayList<>();
    private final String[] suits = { "C", "D", "H", "S" };
    private final String[] specialCards = { "J", "Q", "K", "A" };

    public Deck(int numOfDecks) {
        for (int i = 0; i < numOfDecks; i++) {
            for (int s = 0; s < suits.length; s++) {
                for (int j = 2; j < 11; j++) {
                    Cards.add(new Card(String.valueOf(j), suits[s]));
                }
                for (int j = 0; j < specialCards.length; j++) {
                    Cards.add(new Card(specialCards[j], suits[s]));
                }
            }
        }
    }


    public Card getCard(int cardNum) {
        return Cards.get(cardNum);
    }


    public Card drawCard(int cardNum) {
        return Cards.remove(cardNum);
    }


    public int getDeckLength() {
        return Cards.size();
    }


    public void addCard(Card card) {
        Cards.add(card);
    }


    public void shuffleCards() {
        Collections.shuffle(Cards);
    }


    public void shuffleInDeck(Deck deck1) {
        int len = deck1.getDeckLength();
        for (int i = 0; i < len; i++) {
            Cards.add(deck1.drawCard(0));
        }
    }
   

    public int getDeckValue() {
        int value = 0;
        int aces = 0;
        for (int i = 0; i < Cards.size(); i++) {
            if (Cards.get(i).getRank() == "A") {
                aces++;
            }
            value += Cards.get(i).getValue(value);
        }
        while (value > 21 && aces >= 1) {
            value -= 10;
            aces--;
        }
        return value;
    }
}
