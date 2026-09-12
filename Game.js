import java.util.*;

public class Game {
    public static void main(String[] args) {
        final int NUM_OF_DECKS = 1;
        ArrayList<String> Cards = new ArrayList<>();
        String[] deck = {
        "2C","3C","4C","5C","6C","7C","8C","9C","10C","JC","QC","KC","AC",
        "2D","3D","4D","5D","6D","7D","8D","9D","10D","JD","QD","KD","AD",
        "2H","3H","4H","5H","6H","7H","8H","9H","10H","JH","QH","KH","AH",
        "2S","3S","4S","5S","6S","7S","8S","9S","10S","JS","QS","KS","AS"
        };
        for(int i = 0; i < NUM_OF_DECKS; i++){
            Collections.addAll(Cards, deck);
        }
        for(int i = 0; i < 10; i++){
            System.out.println(Cards.get((int)(Math.random()*52)));
        }
    }
}
