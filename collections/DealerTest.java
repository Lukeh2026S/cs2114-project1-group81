// @author Mayank Rudraraju, Luke Hill, Abigel Daniel
// @version 2026.09.25
package collections;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/*
    test for the dealer class
*/
public class DealerTest 
{
    private Dealer dealer;
    @Before
    public void setUp()
    {
        dealer = new Dealer();
        Game.drawPile = new Deck(0);
        Game.discardPile = new Deck(0);
    }
    @Test
    public void testConstructor()
    {
        assertEquals(0, dealer.getDealerHandLength());
        assertEquals(0, dealer.dealerHandValue());
    }
    @Test
    public void testAddDealerCard()
    {
        dealer.addDealerCard(new Card("5", "H"));
        assertEquals(1, dealer.getDealerHandLength());
        assertEquals(5, dealer.dealerHandValue());
        assertEquals("5H", dealer.getDealerCard(0).getSuitAndRank());
    }
    @Test
    public void testGetDealerCard()
    {
        dealer.addDealerCard(new Card("K", "S"));
        dealer.addDealerCard(new Card("A", "D"));
        assertEquals("KS", dealer.getDealerCard(0).getSuitAndRank());
        assertEquals("AD", dealer.getDealerCard(1).getSuitAndRank());
    }
    @Test
    public void testDrawNumCards() 
    {
     // The second value needs to be one of the four suits
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
    @Test
    public void testDrawNumCardsRemovesFromPile()
    {
        Game.drawPile.addCard(new Card("2", "C"));
        Game.drawPile.addCard(new Card("4", "D"));
        int originalSize = Game.drawPile.getDeckLength();

        dealer.drawNumCards(2);
        assertEquals(originalSize - 2, Game.drawPile.getDeckLength());
    }
    @Test
    public void testDrawNumCardsZero()
    {
        Game.drawPile.addCard(new Card("2", "C"));
        dealer.drawNumCards(0);

        assertEquals(0, dealer.getDealerHandLength());
        assertEquals(1, Game.drawPile.getDeckLength());
    }
    @Test
    public void  testDealerHandValue()
    {
        dealer.addDealerCard(new Card("10", "C"));
        dealer.addDealerCard(new Card("9", "D"));

        assertEquals(19, dealer.dealerHandValue());
    }
    @Test
    public void testDealerHandValueSoftAce()
    {
        
        dealer.addDealerCard(new Card("A", "C"));
        dealer.addDealerCard(new Card("9", "D"));

        assertEquals(20, dealer.dealerHandValue());
    }
    @Test
    public void testDealerHandValueHardAce()
    {
        // The second value needs to be one of the four suits
        dealer.addDealerCard(new Card("A", "C"));
        dealer.addDealerCard(new Card("8", "J"));
        dealer.addDealerCard(new Card("10", "P"));

        assertEquals(19, dealer.dealerHandValue());
    }
    //@Test
    public void tesPrintDealerCardsNotHidden()
    {
        dealer.addDealerCard(new Card("K", "S"));
        dealer.addDealerCard(new Card("4", "H"));

        dealer.printDealerCards(false);
        //systemOut().getHistory() doesn't work with junit 5
        assertEquals("KS 4H", systemOut().getHistory());
    }
    //@Test
    public void testPrintDealerCardsHidden()
    {
        dealer.addDealerCard(new Card("K", "S"));
        dealer.addDealerCard(new Card("4", "H"));
        dealer.addDealerCard(new Card("9", "D"));
      //systemOut().getHistory() doesn't work with junit 5
        dealer.printDealerCards(true);
        assertEquals("KS__ __", System.out().getHistory());

    }
    @Test
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
    @Test
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
    @Test
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
    @Test
    public void testShuffleDealerCards()
    {
        dealer.addDealerCard(new Card("K", "S"));
        dealer.addDealerCard(new Card("4", "H"));
        int discardSizeBefore = Game.discardPile.getDeckLength();
 
        dealer.shuffleDealerCards();
 
        assertEquals(0, dealer.getDealerHandLength());
        assertEquals(discardSizeBefore + 2, Game.discardPile.getDeckLength());
    }

}

