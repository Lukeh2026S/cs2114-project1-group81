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

public class User {

    private Deck userHand;
    public ArrayList<User> splitHands = new ArrayList<>();
    private int chips;
    private int currentBet;
    private int insuranceBet;
    private boolean splitOption;
    private boolean doubleOption;
    private boolean insuranceOption;

    public User(int chips) {
        userHand = new Deck(0);
        this.chips = chips;
        currentBet = 0;
        insuranceBet = 0;
        splitOption = false;
        doubleOption = false;
        insuranceOption = false;
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
    
    
    public void addUserChips(int Chips) {
        chips += Chips;
    }


    public int getCurrentBet() {
        return currentBet;
    }


    public void setCurrentBet(int bet) {
        currentBet = bet;
    }

    
    public int getInsuranceBet() {
        return insuranceBet;
    }
    
    
    public void setInsuranceBet(int chips) {
        insuranceBet = chips;
    }
    

    public boolean checkSplit() {
        return splitHands.size() > 1;
    }


    public boolean betUserChips(int Chips) {
        if(Chips <= 0) {
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
        userHand.printDeck(false);
    }

    public void userOptions(Card dealerCard) {
        if (userHand.getDeckLength() == 2) {
            doubleOption = true;
            splitOption = userHand.getCard(0).getRank().equals(userHand.getCard(1).getRank())
                && splitHands.size()<1;
            insuranceOption = dealerCard.getRank() == "A" && insuranceBet == 0;
        }
    }
    
    
     public void printUserOptions() {
        System.out.println("1. Hit");
        System.out.println("2. Stand");
        if (doubleOption) {
            System.out.println("3. Double");
            if(splitOption) {
                System.out.println("4. Split");
                if(insuranceOption) {
                    System.out.println("5. Insurance");
                }
            }else if(insuranceOption) {
                System.out.println("4. Insurance");
            }
         }
     }


    public boolean userAction(String input, Dealer dealer) {
        int inputInt;
        if (input == null) {
            System.out.println("Please input an option or a number corresponding to an option.");
            return true;
        }
        
        input = input.trim();
        try {
            inputInt = Integer.parseInt(input);
            switch(inputInt) {
                case 1: return hit();
                case 2: return stand();
                case 3: if(doubleOption) {return doubleOption();}
                case 4:
                    if(splitOption) {
                        return split();
                    } else if (insuranceOption) {
                        return insurance(dealer);
                    }
                case 5: if(insuranceOption) {return insurance(dealer);}
                default:
                    System.out.println("Please input an option or a number corresponding to an option.");
                    return true;
            } 
        }
        catch (NumberFormatException e) {
            input = input.toLowerCase();
            switch (input) {
                case "hit": return hit();
                case "stand": return stand();
                case "double": if(doubleOption) {return doubleOption();}
                case "split": if(splitOption) {return split();}
                case "insurance": if(insuranceOption) {return insurance(dealer);}
                default:
                    System.out.println("Please input an option or a number corresponding to an option.");
                    return true;
            }
        }
    }


    
    private boolean hit() {
        //hit
        userHand.addCard(Game.drawPile.drawCard(0));
        return true;
    }
    
    private boolean stand() {
        //stand
        return false;
    }
    
    private boolean doubleOption() {
      //double
        if(this.betUserChips(currentBet)) {
            System.out.println("Bet doubled.");
            userHand.addCard(Game.drawPile.drawCard(0));
            return false;
        } 
        System.out.println("Not enough chips to double");
        return true;
    }
    
    private boolean split() {
        if(this.betUserChips(currentBet)) {
            int repeat = userHand.getDeckLength();
            for(int i = 0; i < repeat; i++) {
                splitHands.add(new User(currentBet/2));
                splitHands.get(i).addUserCard(userHand.drawCard(0));
                splitHands.get(i).betUserChips(currentBet/2);
                
            }
            currentBet = 0;
        } else {
            System.out.println("Not enough chips to split");
        }
        return true;
    }
    
    private boolean insurance(Dealer dealer) {
        if(chips>=currentBet/2) {
            insuranceBet = currentBet/2;
            chips -= insuranceBet;
        } else {
            System.out.println("You don't have enough chips to place insurance bet. Please pick another option.");
            return true;
    }
        return true;
    }
    
    public void shuffleUserCards() {
        Game.discardPile.shuffleInDeck(userHand);
    }
    
    
}
