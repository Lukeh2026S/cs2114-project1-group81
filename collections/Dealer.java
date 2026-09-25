// Project 1
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Mayank Rudraraju, Luke Hill, Abigel Daniel
// LLM Statement:
// I have not used any assistance for the assignment beyond course resources and
// staff.
package collections;

/**
 * Dealer which holds dealer hand and methods to modify it.
 * 
 * @author Luke Hill
 * @version 2026.24.9
 */
public class Dealer {

    private Deck dealerHand = new Deck(0);

    /**
     * Constructor for dealer class.
     */
    public Dealer() {
    }


    /**
     * Draws an amount of cards to the dealers hand.
     * 
     * @param num
     */
    public void drawNumCards(int num) {
        for (int i = 0; i < num; i++) {
            dealerHand.addCard(Game.drawPile.drawCard(0));
        }
    }


    /**
     * Gets dealers hand length.
     * 
     * @return dealers hand length
     */
    public int getDealerHandLength() {
        return dealerHand.getDeckLength();
    }


    /**
     * Gets the value of a dealers hand.
     * 
     * @return dealer hand value
     */
    public int dealerHandValue() {
        return dealerHand.getDeckValue();
    }


    /**
     * Gets a card a position in dealers hand.
     * 
     * @param cardNum
     * @return card at position
     */
    public Card getDealerCard(int cardNum) {
        return dealerHand.getCard(cardNum);
    }


    /**
     * Adds card to dealer hand.
     * 
     * @param card
     */
    public void addDealerCard(Card card) {
        dealerHand.addCard(card);
    }


    /**
     * Prints dealer hand.
     * 
     * @param hideCards
     */
    public void printDealerCards(boolean hideCards) {
        dealerHand.printDeck(hideCards);
    }


    /**
     * Does the action for the dealer to hit and stand.
     */
    public void dealerAction() {
        while (dealerHand.getDeckValue() < 17) {
            dealerHand.addCard(Game.drawPile.drawCard(11));
            if (dealerHand.getDeckValue() > 17) {
                break;
            }
        }
    }


    /**
     * Shuffles dealers hand.
     */
    public void shuffleDealerCards() {
        Game.discardPile.shuffleInDeck(dealerHand);
    }
}
