package collections;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Dealer. Verifies the constructor, adding cards, 
 * drawing from the deck, hand value calculations (including Aces), 
 * dealer action rules (hit/stand/bust), and shuffling cards back into the discard pile.
 * 
 * @author Mayank Rudraraju, Luke Hill, Abigel Daniel
 * @version 2026.09.25
 */
public class DealerTest 
{
    private Dealer dealer;

    /**
     * Sets up the test fixture before each test method runs.
     * Initializes a fresh dealer and empty draw/discard piles.
     */
    @Before
    public void setUp()
    {
        dealer = new Dealer();
        Game.drawPile = new Deck(0);
        Game.discardPile = new Deck(0);
    }

    /**
     * Test the constructor to ensure a newly created dealer 
     * starts with an empty hand length and a hand value of 0.
     */
    @Test
    public void testConstructor()
    {
        assertEquals(0, dealer.getDealerHandLength());
        assertEquals(0, dealer.dealerHandValue());
    }

    /**
     * Test adding a card directly to the dealer's hand 
     * and verify hand length, hand value, and suit/rank string.
     */
    @Test
    public void testAddDealerCard()
    {
        dealer.addDealerCard(new Card("5", "H"));
        assertEquals(1, dealer.getDealerHandLength());
        assertEquals(5, dealer.dealerHandValue());
        assertEquals("5H", dealer.getDealerCard(0).getSuitAndRank());
    }

    /**
     * Test retrieving specific cards from the dealer's hand by index 
     * using the getDealerCard method.
     */
    @Test
    public void testGetDealerCard()
    {
        dealer.addDealerCard(new Card("K", "S"));
        dealer.addDealerCard(new Card("A", "D"));
        assertEquals("KS", dealer.getDealerCard(0).getSuitAndRank());
        assertEquals("AD", dealer.getDealerCard(1).getSuitAndRank());
    }

    /**
     * Test drawing a specified number of cards from the draw pile 
     * into the dealer's hand and verifying correct distribution.
     */
    @Test
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

    /**
     * Test that drawing cards properly removes the correct 
     * number of cards from the draw pile.
     */
    @Test
    public void testDrawNumCardsRemovesFromPile()
    {
        Game.drawPile.addCard(new Card("2", "C"));
        Game.drawPile.addCard(new Card("4", "D"));
        int originalSize = Game.drawPile.getDeckLength();

        dealer.drawNumCards(2);
        assertEquals(originalSize - 2, Game.drawPile.getDeckLength());
    }

    /**
     * Test drawing zero cards results in no changes to the dealer's hand 
     * and leaves the draw pile intact.
     */
    @Test
    public void testDrawNumCardsZero()
    {
        Game.drawPile.addCard(new Card("2", "C"));
        dealer.drawNumCards(0);

        assertEquals(0, dealer.getDealerHandLength());
        assertEquals(1, Game.drawPile.getDeckLength());
    }

    /**
     * Test calculating the dealer hand value with standard number cards.
     */
    @Test
    public void testDealerHandValue()
    {
        dealer.addDealerCard(new Card("10", "C"));
        dealer.addDealerCard(new Card("9", "D"));

        assertEquals(19, dealer.dealerHandValue());
    }

    /**
     * Test hand value calculation when an Ace acts as a soft high value (11).
     */
    @Test
    public void testDealerHandValueSoftAce()
    {
        dealer.addDealerCard(new Card("A", "C"));
        dealer.addDealerCard(new Card("9", "D"));

        assertEquals(20, dealer.dealerHandValue());
    }

    /**
     * Test hand value calculation when an Ace counts appropriately 
     * alongside multiple cards.
     */
    @Test
    public void testDealerHandValueHardAce()
    {
        dealer.addDealerCard(new Card("A", "C"));
        dealer.addDealerCard(new Card("8", "J"));
        dealer.addDealerCard(new Card("10", "P"));

        assertEquals(19, dealer.dealerHandValue());
    }

    /**
     * Test dealer action when the hand value is already 17 or higher 
     * (the dealer should stand and not draw any new cards).
     */
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

    /**
     * Test dealer action when the hand value is below 17 
     * (the dealer must draw cards until reaching at least 17).
     */
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

    /**
     * Test dealer action stops drawing immediately once a bust condition occurs (>21).
     */
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

    /**
     * Test shuffling dealer cards back into the discard pile, 
     * verifying the hand is cleared and the discard pile size increases.
     */
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
