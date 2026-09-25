package collections;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for card class - Verifies user initialization, chip and bet management, 
 * adding/drawing cards into user hands, hand value calculations, 
 * and shuffling user cards back into the discard pile.
 * 
 * used used claude to help  finalize the list (method names) of the test cases
 *  we wanted to write in case we missed anything important.
 * 
 * @author Mayank Rudraraju, Luke Hill, Abigel Daniel
 * @version 2026.09.25
 */
public class UserTest
{
    private User user;

    /**
     * Sets up the test fixture before each test method runs.
     * Initializes a fresh user with starting chips and empty piles.
     */
    @Before
    public void setUp()
    {
        user = new User(500);
        Game.drawPile = new Deck(0);
        Game.discardPile = new Deck(0);
    }

    /**
     * Test constructor and initial state of a User, verifying starting chips, 
     * hand length, hand value, current bets, and split flags.
     */
    @Test
    public void testUser()
    {
        assertEquals(500, user.getUserChips());
        assertEquals(0, user.getUserHandLength());
        assertEquals(0, user.userHandValue());
        assertEquals(0, user.getCurrentBet());
        assertFalse(user.checkSplit());
    }

    /**
     * Test adding cards directly to a user's hand and verify 
     * hand length, individual card values, and total hand value.
     */
    @Test
    public void testAddCardAndHandValue()
    {
        user.addUserCard(new Card("K", "S"));
        user.addUserCard(new Card("A", "D"));

        assertEquals(2, user.getUserHandLength());
        assertEquals("KS", user.getUserCard(0).getSuitAndRank());
        assertEquals("AD", user.getUserCard(1).getSuitAndRank());
        assertEquals(21, user.userHandValue());
    }

    /**
     * Test drawing a specified number of cards from the draw pile 
     * into the user's hand.
     */
    @Test
    public void testDrawNumCards()
    {
        Game.drawPile.addCard(new Card("7", "C"));
        Game.drawPile.addCard(new Card("9", "D"));
        Game.drawPile.addCard(new Card("3", "H"));

        user.drawNumCards(2);

        assertEquals(2, user.getUserHandLength());
        assertEquals("7C", user.getUserCard(0).getSuitAndRank());
        assertEquals("9D", user.getUserCard(1).getSuitAndRank());
        assertEquals(1, Game.drawPile.getDeckLength());
    }

    /**
     * Test chip and bet accessor and mutator methods.
     */
    @Test
    public void testChipsAndBetAccessors()
    {
        user.setUserChips(250);
        assertEquals(250, user.getUserChips());

        user.addUserChips(50);
        assertEquals(300, user.getUserChips());

        user.setCurrentBet(25);
        assertEquals(25, user.getCurrentBet());
    }

    /**
     * Test placing bets with chip validation (handles invalid bounds, 
     * sufficient chips, and cumulative betting behavior).
     */
    @Test
    public void testBetUserChips()
    {
        assertFalse(user.betUserChips(-10));
        assertFalse(user.betUserChips(1000));
        assertEquals(500, user.getUserChips());
        assertEquals(0, user.getCurrentBet());

        assertTrue(user.betUserChips(100));
        assertEquals(400, user.getUserChips());
        assertEquals(100, user.getCurrentBet());

        assertTrue(user.betUserChips(50));
        assertEquals(350, user.getUserChips());
        assertEquals(150, user.getCurrentBet());
    }

    /**
     * Test shuffling user cards back into the discard pile, 
     * verifying the user hand is cleared and the discard pile size increases.
     */
    @Test
    public void testShuffleUserCards()
    {
        user.addUserCard(new Card("K", "S"));
        user.addUserCard(new Card("4", "H"));
        int discardSizeBefore = Game.discardPile.getDeckLength();

        user.shuffleUserCards();

        assertEquals(0, user.getUserHandLength());
        assertEquals(discardSizeBefore + 2, Game.discardPile.getDeckLength());
    }

} // end of the test case