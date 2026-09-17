package collections;

public class Dealer {

    private Deck dealerHand = new Deck(0);

    public Dealer() {

    }


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
        System.out.print(dealerHand.getCard(0).getSuitAndRank() + " ");
        for (int i = 1; i < dealerHand.getDeckLength(); i++) {
            if (hideCards) {
                System.out.print("__ ");
            }
            else {
                System.out.print(dealerHand.getCard(i).getSuitAndRank() + " ");
            }
        }
    }


    public void dealerAction() {
        while (dealerHand.getDeckValue() < 17) {
            dealerHand.addCard(Game.drawPile.drawCard(11));
            if (dealerHand.getDeckValue() > 17) {
                break;
            }
        }
    }

    public void shuffledealerCards() {
        Game.discardPile.shuffleInDeck(dealerHand);
    }
}
