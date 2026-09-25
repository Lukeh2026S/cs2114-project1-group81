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

import java.util.*;

/**
 * Class that interacts with all others and runs game loop.
 * 
 * @author Luke Hill, Mayank Rudraraju, Abigel Daniel
 * @version 2026.24.9
 */
public class Game {
    /**
     * The amount of decks that are in play.
     */
    static final int NUM_OF_DECKS = 2;
    /**
     * The draw pile that all cards are drawn from.
     */
    static Deck drawPile = new Deck(NUM_OF_DECKS);
    /**
     * The list of cards that holds cards that are discarded.
     */
    static Deck discardPile = new Deck(0);
    /**
     * The scanner that detects input.
     */
    static Scanner scanner = new Scanner(System.in);

    /**
     * The method main that runs everything.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // start game
        drawPile.shuffleCards();
        Dealer dealer1 = new Dealer();
        User user1 = new User(500);

        gameloop: while (user1.getUserChips() > 0) {

            if (discardPile.getDeckLength() > (NUM_OF_DECKS * 39)) {
                drawPile.shuffleInDeck(discardPile);
                drawPile.shuffleCards();
                System.out.println(
                    "Shuffled cards from discard back into pile");
            }

            int intInput = -1;
            String exitInput;

            // chip input
            while (!user1.betUserChips(intInput)) {
                System.out.println("Current chips: " + user1.getUserChips()
                    + "\nHow many chips do you want to bet?");
                exitInput = scanner.nextLine();
                try {
                    intInput = Integer.parseInt(exitInput);
                    if (!user1.betUserChips(intInput)) {
                        System.out.println(
                            "You do not have that many chips. Please bet less.");
                    }
                    else {
                        break;
                    }
                }
                catch (NumberFormatException e) {
                    if (exitInput.equals("EXIT")) {
                        break gameloop;
                    }
                    else if (exitInput.toLowerCase().equals("all")) {
                        user1.betUserChips(user1.getUserChips());
                        break;
                    }
                    else {
                        System.out.println("Please enter a number.");
                        intInput = -1;
                    }
                }
            }

            // Start Round
            dealer1.drawNumCards(2);
            user1.drawNumCards(2);

            // Game loop
            Game.gameWhileLoop(dealer1, user1, 0);

            dealer1.dealerAction();

            System.out.println("The dealer1 is showing: ");
            dealer1.printDealerCards(false);
            System.out.println("\nYour Cards are: ");
            user1.printUserCards();
            if (user1.splitHands.size() > 0) {
                checkSplitWin(dealer1, user1);
                user1.addUserChips(combineChips(user1));
            }
            else {
                checkWin(dealer1, user1);
            }            
            shuffleSplitCards(user1);
            dealer1.shuffleDealerCards();

        }
        scanner.close();
    }


    /**
     * Checks the dealers and users cards to see who won.
     * 
     * @param dealer
     * @param user
     */
    public static void checkWin(Dealer dealer, User user) {
        if (dealer.dealerHandValue() == 21 && dealer
            .getDealerHandLength() == 2) {
            if (user.userHandValue() == 21 && user.getUserHandLength() == 2) {
                System.out.println("\nPush.");
                user.addUserChips(user.getCurrentBet());
            }
            else {
                if (user.getInsuranceBet() == 0) {
                    System.out.println("\nDealer has blackjack. You lose.");
                }
                else {
                    System.out.println(
                        "\nDealer has blackjack. You have insurance and lost no money.");
                    user.addUserChips(user.getInsuranceBet());
                    user.addUserChips(user.getCurrentBet());
                }
            }
        }
        else if (user.userHandValue() == 21 && user.getUserHandLength() == 2) {
            System.out.println("\nYou have blackjack. You win!");
            user.addUserChips((int)(user.getCurrentBet() * 3.5));
        }
        else if (user.userHandValue() <= 21) {
            if (dealer.dealerHandValue() <= 21) {
                if (user.userHandValue() > dealer.dealerHandValue()) {
                    System.out.println("\nYou win!");
                    user.addUserChips(user.getCurrentBet() * 2);
                }
                else if (user.userHandValue() < dealer.dealerHandValue()) {
                    System.out.println("\nYou lose.");
                }
                else {
                    System.out.println("\nPush.");
                    user.addUserChips(user.getCurrentBet());
                }
            }
            else {
                System.out.println("\nThe dealer busted. You win!");
                user.addUserChips(user.getCurrentBet() * 2);
            }
        }
        else if (dealer.dealerHandValue() <= 21) {
            System.out.println("\nYou busted. You lose.");
        }
        else {
            System.out.println("\nYou and the dealer busted. Push.");
            user.addUserChips(user.getCurrentBet());
        }
        user.setCurrentBet(0);
        user.setInsuranceBet(0);
    }


    /**
     * The actual game loop for playing blackjack.
     * 
     * @param dealer
     * @param user
     * @param split
     */
    public static void gameWhileLoop(Dealer dealer, User user, int split) {
        String loopInput = null;
        while (user.userAction(loopInput, dealer)) {
            if (user.splitHands.size() > 0) {
                for (int i = 0; i < user.splitHands.size(); i++) {
                    gameWhileLoop(dealer, user.splitHands.get(i), i);
                }
                break;
            }

            if (split != 0) {
                System.out.println("This is split: " + (split + 1));
            }
            System.out.println("The dealer is showing: ");
            dealer.printDealerCards(true);
            System.out.println("\nYour Cards are: ");
            user.printUserCards();
            if (user.userHandValue() > 21) {
                if (split != 0) {
                    System.out.println("Game ended. You busted.");
                }
                break;
            }
            System.out.println("\nYour options are :");
            user.userOptions(dealer.getDealerCard(0));
            user.printUserOptions();
            System.out.print("\nWhat is your choice: ");
            loopInput = scanner.nextLine();
        }

    }


    /**
     * Combines the chips of a split hand.
     * 
     * @param user
     * @return split hand chip count
     */
    public static int combineChips(User user) {
        int finalCount = 0;
        if (user.splitHands.size() <= 1) {
            finalCount += user.getUserChips();
        }
        if (user.splitHands.size() > 0) {
            for (int i = 0; i < user.splitHands.size(); i++) {
                finalCount += combineChips(user.splitHands.get(i));
            }
        }
        return finalCount;
    }


    /**
     * Combines the win state of a split hand.
     * 
     * @param dealer
     * @param user
     */
    public static void checkSplitWin(Dealer dealer, User user) {
        if (user.splitHands.size() > 0) {
            for (int i = 0; i < user.splitHands.size(); i++) {
                checkSplitWin(dealer, user.splitHands.get(i));
            }
        }
        else {
            checkWin(dealer, user);
        }
    }


    /**
     * Shuffles the split cards back into discard pile.
     * 
     * @param user
     */
    public static void shuffleSplitCards(User user) {
        if (user.splitHands.size() > 0) {
            for (int i = 0; i < user.splitHands.size(); i++) {
                user.splitHands.get(i).shuffleUserCards();
            }
            user.splitHands.clear();
        }
        else {
            user.shuffleUserCards();
        }
    }
}
