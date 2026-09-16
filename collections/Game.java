package collections;

import java.util.*;

public class Game {
    static final int NUM_OF_DECKS = 1;
    static Deck drawPile = new Deck(NUM_OF_DECKS);
    static Deck discardPile = new Deck(0);

    public static void main(String[] args) {
        //start game
        drawPile.shuffleCards();
        Dealer dealer1 = new Dealer();
        User user1 = new User(500);
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Current chips: " + user1.getUserChips() + "\nHow many chips do you want to bet?");
        int intInput = scanner.nextInt();
        while(!user1.betUserChips(intInput)) {
            System.out.println("You do not have that many chips. Please bet less.");
            intInput = scanner.nextInt();
        }

        // Start Round
        dealer1.drawNumCards(2);
        user1.drawNumCards(2);
        String input = null;

        while (user1.userAction(input, dealer1.getDealerCard(0)) && user1.userHandValue() <= 21) {
            System.out.print("The dealer is showing: ");
            dealer1.printDealerCards(true);
            System.out.print("\nYour Cards are: ");
            user1.printUserCards();
            System.out.println("\nYour options are :");
            user1.userOptions(dealer1.getDealerCard(0));
            input = scanner.nextLine();
        }
        scanner.close();


        
        dealer1.dealerAction();
        System.out.print("The dealer is showing: ");
        dealer1.printDealerCards(false);
        System.out.print("\nYour Cards are: ");
        user1.printUserCards();
        if (dealer1.dealerHandValue() == 21 && dealer1.getDealerHandLength() == 2) {
            if (user1.userHandValue() == 21 && user1.getUserHandLength() == 2) {
                System.out.print("\nPush.");
            } else {
                System.out.print("\nDealer has blcakjack. You lose.");
            }
        } else if (user1.userHandValue() == 21 && user1.getUserHandLength() == 2) {
            System.out.print("\nYou have blcakjack. You win!");
        } else if (user1.userHandValue() <= 21) {
            if (dealer1.dealerHandValue() <= 21) {
                if (user1.userHandValue() > dealer1.dealerHandValue()) {
                    System.out.print("\nYou win!");
                }
                else if (user1.userHandValue() < dealer1.dealerHandValue()) {
                    System.out.print("\nYou lose.");
                }
                else {
                    System.out.print("\nPush.");
                }
            } else {
                System.out.print("\nThe dealer busted. You win!");
            }
        } else if (dealer1.dealerHandValue() <= 21) {
            System.out.print("\nYou busted. You lose.");
        } else {
            System.out.print("\nYou and the dealer busted. Push.");
        }
        
        
        
        
        
        
        
        // dealer1.addDealerCard(new Card("A", "S"));
        // dealer1.addDealerCard(new Card("3", "S"));
        // dealer1.addDealerCard(new Card("K", "S"));

        // dealer1.dealerAction();

        /**
         * dealer1.addDealerCard(new Card("K", "S"));
         * System.out.println(drawPile.getDeckLength());
         * System.out.println(dealer1.getHandDealerLength());
         * System.out.println(dealer1.dealerHandValue());
         * for(int i = 0; i < dealer1.getHandDealerLength(); i++){
         * System.out.println(dealer1.getDealerCard(i).getSuitAndRank() + " " +
         * dealer1.getDealerCard(i).getValue(0));
         * }
         * 
         * /**
         * drawPile.shuffleCards();
         * for(int i = 0; i < 52; i++){
         * System.out.println((drawPile.getCard(0)).getSuitAndRank());
         * discardPile.addCard(drawPile.drawCard(0));
         * 
         * 
         * 
         * 
         * 
         * System.out.println((discardPile.getCard(discardPile.getDeckLength()-1)).getSuitAndRank());
         * 
         * }
         * System.out.println(drawPile.getDeckLength());
         * System.out.println(discardPile.getDeckLength());
         * drawPile.shuffleInDeck(discardPile);
         * System.out.println(drawPile.getDeckLength());
         * System.out.println(discardPile.getDeckLength());
         * 
         */
    }
}
