// Project 1
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who do.
// -- Mayank Rudraraju, Luke Hill, Abigel Daniel
// LLM Statement:
//I have not used any assistance for the assignment beyond course resources and staff.
package collections;

import java.util.*;

public class Game {
    static final int NUM_OF_DECKS = 2;
    
    static Deck drawPile = new Deck(NUM_OF_DECKS);
    static Deck discardPile = new Deck(0);

    public static void main(String[] args) {
        //start game
        drawPile.shuffleCards();
        Dealer dealer1 = new Dealer();
        User user1 = new User(500);
        Scanner scanner = new Scanner(System.in);
        gameloop:
        while(user1.getUserChips()>0){
            
            if(discardPile.getDeckLength() > (NUM_OF_DECKS*39)) {
                drawPile.shuffleInDeck(discardPile);
                drawPile.shuffleCards();
                System.out.println("Shuffled cards from discard back into pile");
            }
            
            int intInput = -1;
            String exitInput;
            while(!user1.betUserChips(intInput)) {
                System.out.println("Current chips: " + user1.getUserChips() + "\nHow many chips do you want to bet?");
                exitInput = scanner.nextLine();
                try {
                    intInput = Integer.parseInt(exitInput);
                    if(!user1.betUserChips(intInput)) {
                        System.out.println("You do not have that many chips. Please bet less.");
                    } else {
                        break;
                    }
                } catch(NumberFormatException e) {
                    if(exitInput.equals("EXIT")) {
                        break gameloop;
                    }
                    System.out.println("Please enter a number.");
                    intInput = -1;
                }
                
                    
                
            }

            // Start Round
            dealer1.addDealerCard(new Card("A", "S"));
            dealer1.addDealerCard(new Card("3", "S"));
            //user1.addUserCard(new Card("10", "S"));
            //user1.addUserCard(new Card("3", "H"));
            //dealer1.drawNumCards(2);
            user1.drawNumCards(2);
            String input = null;

            while (user1.userAction(input, dealer1) && user1.userHandValue() <= 21) {
                System.out.println("The dealer is showing: ");
                dealer1.printDealerCards(true);
                System.out.println("\nYour Cards are: ");
                user1.printUserCards();
                System.out.println("\nYour options are :");
                user1.userOptions(dealer1.getDealerCard(0));
                System.out.print("\nWhat is your choice: ");
                input = scanner.nextLine();
            }
            //scanner.close();


            
            dealer1.dealerAction();

            System.out.println("The dealer is showing: ");
            dealer1.printDealerCards(false);
            System.out.println("\nYour Cards are: ");
            user1.printUserCards();
            
            if (user1.getCurrentBet() != 0){
                checkWin(dealer1, user1);
            } else {
                System.out.println("Dealer didn't have blackjack. You Lost.");
                user1.setCurrentBet(0);
            }
            
            System.out.println(user1.getUserChips());
            System.out.println(user1.getCurrentBet());
            user1.shuffleUserCards();
            dealer1.shuffleDealerCards();

            



        }
        scanner.close();
    }

    public static void checkWin(Dealer dealer, User user) {
        if (dealer.dealerHandValue() == 21 && dealer.getDealerHandLength() == 2) {
            if (user.userHandValue() == 21 && user.getUserHandLength() == 2) {
                System.out.println("\nPush.");
                user.addUserChips(user.getCurrentBet());
            } else {
                if(user.getCurrentBet() != 0) {
                    System.out.println("\nDealer has blackjack. You have insurance and lost no money.");
                } else {
                    System.out.println("\nDealer has blackjack. You lose.");
                }
            }
        } else if (user.userHandValue() == 21 && user.getUserHandLength() == 2) {
            System.out.println("\nYou have blackjack. You win!");
            user.addUserChips((int)(user.getCurrentBet()*3.5));
        } else if (user.userHandValue() <= 21) {
            if (dealer.dealerHandValue() <= 21) {
                if (user.userHandValue() > dealer.dealerHandValue()) {
                    System.out.println("\nYou win!");
                    user.addUserChips(user.getCurrentBet()*2);
                }
                else if (user.userHandValue() < dealer.dealerHandValue()) {
                    System.out.println("\nYou lose.");
                }
                else {
                    System.out.println("\nPush.");
                    user.addUserChips(user.getCurrentBet());
                }
            } else {
                System.out.println("\nThe dealer busted. You win!");
                user.addUserChips(user.getCurrentBet()*2);
            }
        } else if (dealer.dealerHandValue() <= 21) {
            System.out.println("\nYou busted. You lose.");
        } else {
            System.out.println("\nYou and the dealer busted. Push.");
            user.addUserChips(user.getCurrentBet());
        }
        user.setCurrentBet(0);
    }
}
