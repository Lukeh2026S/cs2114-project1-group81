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
