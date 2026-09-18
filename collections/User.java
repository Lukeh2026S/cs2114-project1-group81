package collections;

import java.util.*;

public class User {

    private Deck userHand = new Deck(0);
    private ArrayList<User> splitHands = new ArrayList<>();
    private int chips;
    private int currentBet = 0;

    public User(int chips) {
        this.chips = chips;
    }


    public void drawNumCards(int num) {
        for (int i = 0; i < num; i++) {
            userHand.addCard(Game.drawPile.drawCard(0));
        }
    }


    public int getUserHandLength() {
        return userHand.getDeckLength();
    }


    public int userHandValue() {
        return userHand.getDeckValue();
    }


    public Card getUserCard(int cardNum) {
        return userHand.getCard(cardNum);
    }


    public void addUserCard(Card card) {
        userHand.addCard(card);
    }


    public int getUserChips() {
        return chips;
    }


    public void setUserChips(int Chips) {
        chips = Chips;
    }


    public int getCurrentBet() {
        return currentBet;
    }


    public void setCurrentBet(int bet) {
        currentBet = bet;
    }


    public void addUserChips(int Chips) {
        chips += Chips;
    }


    public boolean checkSplit() {
        return splitHands.size() > 1;
    }


    public boolean betUserChips(int Chips) {
        if(Chips < 0) {
            return false;
        }
        if (Chips <= chips) {
            chips -= Chips;
            currentBet += Chips;
            return true;
        }
        return false;
    }

    public void printUserCards() {

        for (int i = 0; i < userHand.getDeckLength(); i++) {
            System.out.print("┌──────────┐  ");
        }
        System.out.println();

        for (int i = 0; i < userHand.getDeckLength(); i++) {
            Card card = userHand.getCard(i);
            System.out.printf("│ %-9s│  ", card.getRank());
        }
        System.out.println();

        for (int i = 0; i < userHand.getDeckLength(); i++) {
            System.out.print("│          │  ");
        }
        System.out.println();

        for (int i = 0; i < userHand.getDeckLength(); i++) {
            Card card = userHand.getCard(i);
            System.out.printf("│     %s    │  ", card.getSuit());
        }
        System.out.println();

        for (int i = 0; i < userHand.getDeckLength(); i++) {
            System.out.print("│          │  ");
        }
        System.out.println();

        for (int i = 0; i < userHand.getDeckLength(); i++) {
            Card card = userHand.getCard(i);
            System.out.printf("│        %-2s│  ", card.getRank());
        }
        System.out.println();

        for (int i = 0; i < userHand.getDeckLength(); i++) {
            System.out.print("└──────────┘  ");
        }
        System.out.println();
    }


    public void userOptions(Card dealerCard) {
        System.out.print("1. Hit\n2. Stand");
        if (userHand.getDeckLength() <= 2) {
            System.out.println("\n3. Double");
        }
        if (userHand.getCard(0).getRank().equals(userHand.getCard(1).getRank())
            && userHand.getDeckLength() == 2) {
            System.out.println("4. Split");
            if (dealerCard.getRank() == "A") {
                System.out.println("5. Insurance");
            }
        }
        else {
            if (dealerCard.getRank() == "A" && userHand.getDeckLength() == 2) {
                System.out.println("4. Insurance");
            }
        }
    }


    public boolean userAction(String input, Card dealerCard) {
        int inputInt;
        if (input == null) {
            System.out.println(
                "Please input an option or a number corresponding to an option.");
            return true;
        }
        input = input.trim();
        try {
            inputInt = Integer.parseInt(input);
            if (inputInt == 1) {
                userHand.addCard(Game.drawPile.drawCard(0));
                //hit
                return true;
            }
            else if (inputInt == 2) {
                //stand
                return false;
            }
            else if (inputInt == 3 && userHand.getDeckLength() <= 2) {
                userHand.addCard(Game.drawPile.drawCard(0));
                //double
                return false;
            }
            else if (inputInt == 4) {
                if (userHand.getCard(0).getRank().equals(userHand.getCard(1)
                    .getRank())) {
                //split
                    return true;
                } else if (dealerCard.getRank() == "A") {
                    return true;
                    //insurance
                } else {
                    System.out.println(
                        "Please input an option or a number corresponding to an option.");
                    return true;
                }
                    
            }
            else if (inputInt == 5 && dealerCard.getRank() == "A" && userHand
                .getCard(0).getRank().equals(userHand.getCard(1).getRank())) {
                    //insurance
                return true;
            }
            else {
                System.out.println(
                    "Please input an option or a number corresponding to an option.");
                return true;
            }

        }
        catch (NumberFormatException e) {
            if (input.toLowerCase().equals("hit")) {
                //hit
                userHand.addCard(Game.drawPile.drawCard(0));
                return true;
            }
            else if (input.toLowerCase().equals("stand")) {
                //stand
                return false;
            }
            else if (input.toLowerCase().equals("double") && userHand.getDeckLength() <= 2 && chips >= currentBet) {
                //double
                if(this.betUserChips(currentBet)) {
                    System.out.println("Bet doubled.");
                    userHand.addCard(Game.drawPile.drawCard(0));
                    return false;
                } else {
                    System.out.println("Not enough chips to double");
                    return true;
                }
                
                
            }
            else if (input.toLowerCase() == "split" && userHand.getCard(0)
                .getRank().equals(userHand.getCard(1).getRank())) {
                //split
                return true;
            }
            else if (input.toLowerCase() == "insurance" && dealerCard
                .getRank() == "A") {
                //insurance
                return true;
            }
            else {
                System.out.println(
                    "Please input an option or a number corresponding to an option.");
                return true;
            }
        }
    }


    public void shuffleUserCards() {
        Game.discardPile.shuffleInDeck(userHand);
    }
}
