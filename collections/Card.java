// Project 1
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who do.
// -- Mayank Rudraraju, Luke Hill, Abigel Daniel
// LLM Statement:
//I have not used any assistance for the assignment beyond course resources and staff.
package collections;

public class Card {
    private String suit;
    private String rank;

    public Card(String rank, String suit) {
        this.suit = suit;
        this.rank = rank;
    }


    public String getSuit() {
        return suit;
    }


    public String getRank() {
        return rank;
    }


    public String getSuitAndRank() {
        return rank + suit;
    }


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
