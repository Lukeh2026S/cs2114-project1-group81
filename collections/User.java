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
 * The user class that has methods for user functions.
 * 
 * @author Luke Hill
 * @version 2026.24.9
 */
public class User {

    private Deck userHand;
    /**
     * The list that holds the users created when hand is split.
     */
    public ArrayList<User> splitHands = new ArrayList<>();
    private int chips;
    private int currentBet;
    private int insuranceBet;
    private boolean splitOption;
    private boolean doubleOption;
    private boolean insuranceOption;
    private boolean split;

    /**
     * Constructor for user class.
     * 
     * @param chips
     */
    public User(int chips) {
        userHand = new Deck(0);
        this.chips = chips;
        currentBet = 0;
        insuranceBet = 0;
        splitOption = false;
        doubleOption = false;
        insuranceOption = false;
        split = false;
    }


    /**
     * Constructor for user class with overloaded value so split hands are
     * different.
     */
    public User() {
        userHand = new Deck(0);
        currentBet = 0;
        insuranceBet = 0;
        splitOption = false;
        doubleOption = false;
        insuranceOption = false;
        split = true;
    }


    /**
     * Draws a certain number of cards to users hand.
     * 
     * @param num
     */
    public void drawNumCards(int num) {
        for (int i = 0; i < num; i++) {
            userHand.addCard(Game.drawPile.drawCard(0));
        }
    }


    /**
     * Gets user hand length
     * 
     * @return user hand length.
     */
    public int getUserHandLength() {
        return userHand.getDeckLength();
    }


    /**
     * Gets user hand value.
     * 
     * @return value of user hand
     */
    public int userHandValue() {
        return userHand.getDeckValue();
    }


    /**
     * Gets a card at a position in user hand.
     * 
     * @param cardNum
     * @return card at position
     * 
     */
    public Card getUserCard(int cardNum) {
        return userHand.getCard(cardNum);
    }


    /**
     * Adds card to user hand.
     * 
     * @param card
     */
    public void addUserCard(Card card) {
        userHand.addCard(card);
    }


    /**
     * Gets user chips.
     * 
     * @return user chips
     */
    public int getUserChips() {
        return chips;
    }


    /**
     * Sets user chips
     * 
     * @param Chips
     */
    public void setUserChips(int Chips) {
        chips = Chips;
    }


    /**
     * Add user chips.
     * 
     * @param Chips
     */
    public void addUserChips(int Chips) {
        chips += Chips;
    }


    /**
     * Gets current bet.
     * 
     * @return current bet
     */
    public int getCurrentBet() {
        return currentBet;
    }


    /**
     * Sets current bet.
     * 
     * @param bet
     */
    public void setCurrentBet(int bet) {
        currentBet = bet;
    }


    /**
     * Gets insurance bet.
     * 
     * @return insurance bet
     */
    public int getInsuranceBet() {
        return insuranceBet;
    }


    /**
     * Sets insurance bet
     * 
     * @param chips
     */
    public void setInsuranceBet(int chips) {
        insuranceBet = chips;
    }


    /**
     * Check if the user split.
     * 
     * @return user split
     */
    public boolean checkSplit() {
        return splitHands.size() > 1;
    }


    /**
     * Bets an amount of chips and adds the to current bet while subtracting
     * from chips.
     * 
     * @param Chips
     * @return if you have enough chips or not
     */
    public boolean betUserChips(int Chips) {
        if (Chips <= 0) {
            return false;
        }
        if (Chips <= chips) {
            chips -= Chips;
            currentBet += Chips;
            return true;
        }
        return false;
    }


    /**
     * Prints user cards
     */
    public void printUserCards() {
        userHand.printDeck(false);
    }


    /**
     * Sets the values of the booleans for the user options
     * 
     * @param dealerCard
     */
    public void userOptions(Card dealerCard) {
        if (!split) {
            if (userHand.getDeckLength() == 2) {
                doubleOption = true;
                splitOption = userHand.getCard(0).getRank().equals(userHand
                    .getCard(1).getRank()) && splitHands.size() < 1;
                insuranceOption = dealerCard.getRank() == "A"
                    && insuranceBet == 0;
            }
        }
    }


    /**
     * Prints the options the user can do.
     */
    public void printUserOptions() {
        System.out.println("1. Hit");
        System.out.println("2. Stand");
        if (doubleOption) {
            System.out.println("3. Double");
            if (splitOption) {
                System.out.println("4. Split");
                if (insuranceOption) {
                    System.out.println("5. Insurance");
                }
            }
            else if (insuranceOption) {
                System.out.println("4. Insurance");
            }
        }
    }


    /**
     * Checks input and does the method they input says or prints that its not
     * an option.
     * 
     * @param input
     * @param dealer
     * @return if the loop is over
     */
    public boolean userAction(String input, Dealer dealer) {
        int inputInt;
        if (input == null) {
            System.out.println(
                "Please input an option or a number corresponding to an option.");
            return true;
        }

        input = input.trim();
        try {
            inputInt = Integer.parseInt(input);
            switch (inputInt) {
                case 1:
                    return hit();
                case 2:
                    return stand();
                case 3:
                    if (doubleOption) {
                        return doubleOption();
                    }
                case 4:
                    if (splitOption) {
                        return split();
                    }
                    else if (insuranceOption) {
                        return insurance(dealer);
                    }
                case 5:
                    if (insuranceOption) {
                        return insurance(dealer);
                    }
                default:
                    System.out.println(
                        "Please input an option or a number corresponding to an option.");
                    return true;
            }
        }
        catch (NumberFormatException e) {
            input = input.toLowerCase();
            switch (input) {
                case "hit":
                    return hit();
                case "stand":
                    return stand();
                case "double":
                    if (doubleOption) {
                        return doubleOption();
                    }
                case "split":
                    if (splitOption) {
                        return split();
                    }
                case "insurance":
                    if (insuranceOption) {
                        return insurance(dealer);
                    }
                default:
                    System.out.println(
                        "Please input an option or a number corresponding to an option.");
                    return true;
            }
        }
    }


    private boolean hit() {
        // hit
        userHand.addCard(Game.drawPile.drawCard(0));
        return true;
    }


    private boolean stand() {
        // stand
        return false;
    }


    private boolean doubleOption() {
        // double
        if (this.betUserChips(currentBet)) {
            System.out.println("Bet doubled.");
            userHand.addCard(Game.drawPile.drawCard(0));
            return false;
        }
        System.out.println("Not enough chips to double");
        return true;
    }


    private boolean split() {
        if (this.betUserChips(currentBet)) {
            int repeat = userHand.getDeckLength();
            for (int i = 0; i < repeat; i++) {
                splitHands.add(new User());
                splitHands.get(i).addUserCard(userHand.drawCard(0));
                splitHands.get(i).setCurrentBet(this.currentBet / 2);

            }
            currentBet = 0;
        }
        else {
            System.out.println("Not enough chips to split");
        }
        return true;
    }


    private boolean insurance(Dealer dealer) {
        if (chips >= currentBet / 2) {
            insuranceBet = currentBet / 2;
            chips -= insuranceBet;
        }
        else {
            System.out.println(
                "You don't have enough chips to place insurance bet. Please pick another option.");
            return true;
        }
        return true;
    }


    /**
     * Shuffles cards from user hand to discard pile.
     */
    public void shuffleUserCards() {
        Game.discardPile.shuffleInDeck(userHand);
    }

}
