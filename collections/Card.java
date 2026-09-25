// Project 1
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Mayank Rudraraju, Luke Hill, Abigel Daniel
// @author Mayank Rudraraju, Luke Hill, Abigel Daniel
// @version 2026.09.25
// LLM Statement:
// I have not used any assistance for the assignment beyond course resources and
// staff.
package collections;

/**
 * Card class which is an object that imitates a card.
 * 
 * @author Luke Hill
 * @version 2026.24.9
 */
public class Card {
    private String suit;
    private String rank;

    /**
     * Constructor of card class.
     * 
     * @param rank
     * @param suit
     */
    public Card(String rank, String suit) {
        this.suit = suit;
        this.rank = rank;
    }


    /**
     * Returns rank of a card.
     * 
     * @return suit
     */
    public String getSuit() {
        return suit;
    }


    /**
     * Returns rank of a card.
     * 
     * @return rank
     */
    public String getRank() {
        return rank;
    }


    /**
     * Returns suit and rank of a card.
     * 
     * @return suit and rank together
     */
    public String getSuitAndRank() {
        return rank + suit;
    }


    /**
     * Gets value of a card.
     * 
     * @param currentScore
     * @return value of card
     */
    public int getValue(int currentScore) {
        try {
            return Integer.parseInt(rank);
        }
        catch (NumberFormatException e) {
            if (rank == "J" || rank == "Q" || rank == "K") {
                return 10;
            }
            else if (rank == "A") {
                return 11;
            }
        }
        return 0;
    }
}
