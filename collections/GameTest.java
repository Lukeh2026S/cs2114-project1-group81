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
// staff, and AI tools utilized for formatting and reviewing tester methods.
package collections;

import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Game. Verifies various win, loss, and push conditions 
 * handled by the checkWin method, including natural blackjacks, busts, 
 * pushes, and standard card value comparisons.
 * 
 * @author Mayank Rudraraju, Luke Hill, Abigel Daniel
 * @version 2026.03.25
 */
public class GameTest
{
    private Dealer dealer;
    private User user;

    /**
     * Sets up the test fixture before each test method runs.
     * Initializes a fresh dealer, user with starting chips, and empty piles.
     */
    @BeforeEach
    public void setUp()
    {
        dealer = new Dealer();
        user = new User(500);
        Game.drawPile = new Deck(0);
        Game.discardPile = new Deck(0);
    }

    /**
     * Test checkWin when the dealer has a natural blackjack 
     * and the user loses their bet.
     */
    @Test
    public void testCheckWinDealerBlackjack()
    {
        dealer.addDealerCard(new Card("A", "S"));
        dealer.addDealerCard(new Card("K", "H"));

        user.addUserCard(new Card("10", "C"));
        user.addUserCard(new Card("5", "D"));

        user.setUserChips(400);
        user.setCurrentBet(50);

        Game.checkWin(dealer, user);

        assertEquals(400, user.getUserChips());
        assertEquals(0, user.getCurrentBet());
    }

    /**
     * Test checkWin when both the dealer and user have natural blackjacks (push).
     */
    @Test
    public void testCheckWinBoth()
    {
        dealer.addDealerCard(new Card("A", "S"));
        dealer.addDealerCard(new Card("K", "H"));

        user.addUserCard(new Card("A", "C"));
        user.addUserCard(new Card("Q", "D"));

        user.setUserChips(400);
        user.setCurrentBet(50);

        Game.checkWin(dealer, user);

        assertEquals(450, user.getUserChips());
        assertEquals(0, user.getCurrentBet());
    }

    /**
     * Test checkWin when only the user has a natural blackjack 
     * and wins with a blackjack payout.
     */
    @Test
    public void testCheckWinUser()
    {
        dealer.addDealerCard(new Card("10", "S"));
        dealer.addDealerCard(new Card("6", "H"));

        user.addUserCard(new Card("A", "C"));
        user.addUserCard(new Card("Q", "D"));

        user.setUserChips(400);
        user.setCurrentBet(10);

        Game.checkWin(dealer, user);

        assertEquals(435, user.getUserChips());
        assertEquals(0, user.getCurrentBet());
    }

    /**
     * Test checkWin when the user has a higher hand value than the dealer without busting.
     */
    @Test
    public void testCheckWinUserHigherValue()
    {
        dealer.addDealerCard(new Card("10", "S"));
        dealer.addDealerCard(new Card("6", "H"));
        dealer.addDealerCard(new Card("2", "C"));

        user.addUserCard(new Card("10", "C"));
        user.addUserCard(new Card("9", "D"));

        user.setUserChips(400);
        user.setCurrentBet(50);
        Game.checkWin(dealer, user);

        assertEquals(500, user.getUserChips());
        assertEquals(0, user.getCurrentBet());
    }

    /**
     * Test checkWin when the dealer has a higher hand value than the user.
     */
    @Test
    public void testCheckWinDealerHigherValue()
    {
        dealer.addDealerCard(new Card("10", "S"));
        dealer.addDealerCard(new Card("9", "H"));

        user.addUserCard(new Card("10", "C"));
        user.addUserCard(new Card("6", "D"));

        user.setUserChips(400);
        user.setCurrentBet(50);

        Game.checkWin(dealer, user);

        assertEquals(400, user.getUserChips());
        assertEquals(0, user.getCurrentBet()); 
    }

    /**
     * Test checkWin when both the dealer and user end up with equal hand values (push).
     */
    @Test
    public void testCheckWinPushOnEqualVal()
    {
        dealer.addDealerCard(new Card("10", "S"));
        dealer.addDealerCard(new Card("5", "H"));
        dealer.addDealerCard(new Card("5", "C"));

        user.addUserCard(new Card("10", "C"));
        user.addUserCard(new Card("10", "D"));

        user.setUserChips(400);
        user.setCurrentBet(50);

        Game.checkWin(dealer, user);

        assertEquals(450, user.getUserChips());
        assertEquals(0, user.getCurrentBet()); 
    }

    /**
     * Test checkWin when the dealer busts, resulting in a win for the user.
     */
    @Test
    public void testCheckWinDealerBust()
    {
        dealer.addDealerCard(new Card("10", "S"));
        dealer.addDealerCard(new Card("9", "H"));
        dealer.addDealerCard(new Card("5", "C"));

        user.addUserCard(new Card("10", "C"));
        user.addUserCard(new Card("6", "D"));

        user.setUserChips(400);
        user.setCurrentBet(50);

        Game.checkWin(dealer, user);

        assertEquals(500, user.getUserChips());
        assertEquals(0, user.getCurrentBet());
    }

    /**
     * Test checkWin when the user busts before the dealer.
     */
    @Test
    public void testCheckWinUserBusts()
    {
        dealer.addDealerCard(new Card("10", "S"));
        dealer.addDealerCard(new Card("6", "H"));

        user.addUserCard(new Card("10", "C"));
        user.addUserCard(new Card("9", "D"));
        user.addUserCard(new Card("5", "H"));

        user.setUserChips(400);
        user.setCurrentBet(50);

        Game.checkWin(dealer, user);

        assertEquals(400, user.getUserChips());
        assertEquals(0, user.getCurrentBet());
    }

    /**
     * Test checkWin when both the dealer and user bust.
     */
    @Test
    public void testCheckWinBothBust()
    {
        dealer.addDealerCard(new Card("10", "S"));
        dealer.addDealerCard(new Card("9", "H"));
        dealer.addDealerCard(new Card("5", "C"));

        user.addUserCard(new Card("10", "C"));
        user.addUserCard(new Card("9", "D"));
        user.addUserCard(new Card("5", "H"));

        user.setUserChips(400);
        user.setCurrentBet(50);

        Game.checkWin(dealer, user);

        assertEquals(450, user.getUserChips());
        assertEquals(0, user.getCurrentBet());
    }

} // end of game test case
