package collections;

import static org.junit.Assert.*;
/*
    test for the dealer class
*/
public class DealerTest extends student.TestCase
{
    private Dealer dealer;

    public void setUp()
    {
        dealer = new Dealer();
        Game.drawPile = new Deck(0);
        Game.discardPile = new Deck(0);
    }

    public void testConstructor()
    {
        assertEquals(0, dealer.getDealerHandLength());
        assertEquals(0, dealer.dealerHandValue());
    }

    public void testAddDealerCard()
    {
        dealer.addDealerCard(new Card("5", "H"));
        assertEquals(1, dealer.getDealerHandLength());
        assertEquals(5, dealer.dealerHandValue());
        assertEquals("5H", dealer.getDealerCard(0), getSuitAndRank());
    }

    public void testGetDealerCard()
    {
        dealer.addDealerCard(new Card("K", "S"));
        dealer.addDealerCard(new Card("A", "d"));
        assertEquals("KS", dealer.getDealerCard(0).getSuitAndRank());
        assertEquals("AD", dealer.getDealerCard(1).getSuitAndRank());
    }
    public void testDrawNumCards()
    {
        Game.drawPile.addCard(new Card("7", "J"));
        Game.drawPile.addCard(new Card("8", "H"));
        Game.drawPile.addCard(new Card("9", "P"));

        dealer.drawNumCards(2);

        assertEquals(2, dealer.getDealerHandLength());
        assertEquals("7J", dealer.getDealerCard(0).getSuitAndRank());
        assertEquals("8H", dealer.getDealerCard(1).getSuitAndRank());
        assertEquals(1, Game.drawPile.getDeckLength());
        assertEquals("9P", Game.drawPile.getCard(0).getSuitAndRank());
    }

    public void testDrawNumCardsRemovesFromPile()
    {
        Game.drawPile.addCard(new Card("2", "C"));
        Game.drawPile.addCard(new Card("4", "D"));
        int originalSize = Game.drawPile.getDeckLength();

        dealer.drawNumCards(2);
        assertEquals(originalsize-2, Game.drawPile.getDeckLength());
    }

    public void testDrawNumCardsZero()
    {
        Game.drawPile.addCard(new Card("2", "C"));
        dealer.drawNumCards(0);

        assertEquals(0, dealer.getDealerHandLength());
        assertEquals(1, Game.drawPile.getDeckLength());
    }

    public void  testDealerHandValue()
    {
        dealer.addDealerCard(new Card("10", "C"));
        dealer.addDealerCard(new Card("9", "D"));

        assertEquals(19, dealer.dealerHandValue());
    }

    public void testDealerHandValueSoftAce()
    {
        dealer.addDealerCard(new Card("A", "C"));
        dealer.addDealerCard(new Card("9", "D"));

        assertEquals(20, dealer.dealerHandValue());
    }

    public void testDealerHandValueHardAce()
    {
        dealer.addDealerCard(new card("A", "C"));
        dealer.addDealerCard(new card("8", "J"));
        dealer.addDealerCard(new card("10", "P"));

        assertEquals(29, dealer.dealerHandValue());
    }

    public void tesPrintDealerCardsNotHidden()
    {
        dealer.addDealerCard(new Card("K", "S"));
        dealer.addDealerCard(new Card("4", "H"));

        dealer.printDealerCards(false);

        assertEquals("KS 4H", systemOut().getHistory());
    }

    public void testPrintDealerCardsHidden()
    {
        dealer.addDealerCard(new Card("K", "S"));
        dealer.addDealerCard(new Card("4", "H"));
        dealer.addDealerCard(new Card("9", "D"));

        dealer.printDealerCards(true);
        assertEquals("KS__ __", systemOut().getHistory());

    }

    public void testDealerActionAtSeventeen()
    {
        dealer.addDealerCard(new Card("10", "C"));
        dealer.addDealerCard(new Card("7", "D"));
 
        for (int i = 0; i < 12; i++)
        {
            Game.drawPile.addCard(new Card("5", "H"));
        }
        int drawPileSizeBefore = Game.drawPile.getDeckLength();
        dealer.dealerAction();
 
        assertEquals(17, dealer.dealerHandValue());
        assertEquals(2, dealer.getDealerHandLength());
        assertEquals(drawPileSizeBefore, Game.drawPile.getDeckLength());
    }

    public void testDealerActionDrawsToSeventeen()
    {
        dealer.addDealerCard(new Card("2", "C"));
        dealer.addDealerCard(new Card("2", "D"));
 
        for (int i = 0; i < 20; i++) 
        {
            Game.drawPile.addCard(new Card("5", "H"));
        }
 
        dealer.dealerAction();
        assertTrue(dealer.dealerHandValue() >= 17);
    }

    public void testDealerActionStopsAfterBust()
    {
        dealer.addDealerCard(new Card("10", "C"));
        dealer.addDealerCard(new Card("2", "D"));
 
        for (int i = 0; i < 12; i++) 
        {
            Game.drawPile.addCard(new Card("K", "S"));
        }
        int drawPileSizeBefore = Game.drawPile.getDeckLength();
        dealer.dealerAction();
 
        assertEquals(22, dealer.dealerHandValue());
        assertEquals(3, dealer.getDealerHandLength());
        assertEquals(drawPileSizeBefore - 1, Game.drawPile.getDeckLength());
    }

    public void testShuffleDealerCards()
    {
        dealer.addDealerCard(new Card("K", "S"));
        dealer.addDealerCard(new Card("4", "H"));
        int discardSizeBefore = Game.discardPile.getDeckLength();
 
        dealer.shuffledealerCards();
 
        assertEquals(0, dealer.getDealerHandLength());
        assertEquals(discardSizeBefore + 2, Game.discardPile.getDeckLength());
    }

}

