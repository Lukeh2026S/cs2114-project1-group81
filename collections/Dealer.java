// Project 1
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who do.
// -- Mayank Rudraraju, Luke Hill, Abigel Daniel
// LLM Statement:
//I have not used any assistance for the assignment beyond course resources and staff.
package collections;

public class Dealer {

    private Deck dealerHand = new Deck(0);

    public Dealer() {}


    public void drawNumCards(int num) {
        for (int i = 0; i < num; i++) {
            dealerHand.addCard(Game.drawPile.drawCard(0));
        }
    }


    public int getDealerHandLength() {
        return dealerHand.getDeckLength();
    }


    public int dealerHandValue() {
        return dealerHand.getDeckValue();
    }


    public Card getDealerCard(int cardNum) {
        return dealerHand.getCard(cardNum);
    }


    public void addDealerCard(Card card) {
        dealerHand.addCard(card);
    }


    public void printDealerCards(boolean hideCards) {
        dealerHand.printDeck(hideCards);
    }


    public void dealerAction() {
        while (dealerHand.getDeckValue() < 17) {
            dealerHand.addCard(Game.drawPile.drawCard(11));
            if (dealerHand.getDeckValue() > 17) {
                break;
            }
        }
    }

    public void shuffleDealerCards() {
        Game.discardPile.shuffleInDeck(dealerHand);
    }
}
