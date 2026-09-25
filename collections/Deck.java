// Project 1
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Mayank Rudraraju, Luke Hill, Abigel Daniel
// LLM Statement:
// I have not used any assistance for the assignment beyond course resources and
// staff.
package collections;

import java.util.*;

/**
 * The deck class that has methods decks like user and dealer hands and draw and
 * discard piles.
 * 
 * @author Luke Hill
 * @version 2026.24.9
 */
public class Deck {
    private ArrayList<Card> Cards = new ArrayList<>();
    private final String[] suits = { "C", "D", "H", "S" };
    private final String[] specialCards = { "J", "Q", "K", "A" };

    /**
     * Constructor for deck class.
     * 
     * @param numOfDecks
     */
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


    /**
     * Gets card from deck at int position.
     * 
     * @param cardNum
     * @return card at position
     */
    public Card getCard(int cardNum) {
        return Cards.get(cardNum);
    }


    /**
     * Removes card from position of deck and returns it.
     * 
     * @param cardNum
     * @return card drawn
     */
    public Card drawCard(int cardNum) {
        return Cards.remove(cardNum);
    }


    /**
     * Gets deck length.
     * 
     * @return deck length
     */
    public int getDeckLength() {
        return Cards.size();
    }


    /**
     * Adds card to deck.
     * 
     * @param card
     */
    public void addCard(Card card) {
        Cards.add(card);
    }


    /**
     * Shuffles cards
     */
    public void shuffleCards() {
        Collections.shuffle(Cards);
    }


    /**
     * Shuffles two decks into one.
     * 
     * @param deck1
     */
    public void shuffleInDeck(Deck deck1) {
        int len = deck1.getDeckLength();
        for (int i = 0; i < len; i++) {
            Cards.add(deck1.drawCard(0));
        }
    }


    /**
     * Returns the value of a deck based on rules.
     * 
     * @return value of deck
     */
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


    /**
     * Prints out each card next to each other.
     * 
     * @param hideCards
     */
    public void printDeck(boolean hideCards) {
        System.out.print("┌───────────┐  ".repeat(Cards.size()) + "\n");
        if (hideCards) {
            System.out.printf("│%-11s│  ", Cards.get(0).getRank());
            System.out.print("│           │  ".repeat(Cards.size() - 1) + "\n");
        }
        else {
            for (int i = 0; i < Cards.size(); i++) {
                System.out.printf("│%-11s│  ", Cards.get(i).getRank());
            }
            System.out.println();
        }
        for (int j = 0; j < 2; j++) {
            System.out.print("│           │  ".repeat(Cards.size()) + "\n");
        }
        if (hideCards) {
            System.out.printf("│     %s     │  ", Cards.get(0).getSuit());
            System.out.print("│     ?     │  ".repeat(Cards.size() - 1) + "\n");
        }
        else {
            for (int i = 0; i < Cards.size(); i++) {
                System.out.printf("│     %s     │  ", Cards.get(i).getSuit());
            }
            System.out.println();
        }
        for (int j = 0; j < 2; j++) {
            System.out.print("│           │  ".repeat(Cards.size()) + "\n");
        }
        if (hideCards) {
            System.out.printf("│%11s│  ", Cards.get(0).getRank());
            System.out.print("│           │  ".repeat(Cards.size() - 1) + "\n");
        }
        else {
            for (int i = 0; i < Cards.size(); i++) {
                System.out.printf("│%11s│  ", Cards.get(i).getRank());
            }
            System.out.println();
        }
        System.out.print("└───────────┘  ".repeat(Cards.size()) + "\n");

    }
}
