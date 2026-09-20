package collections;

import static org.junit.Assert.*;
/*
    test for the game class
*/

public class GameTest extends student.TestCase
{
    private Dealer dealer;
    private User user;

    public void setUp()
    {
        dealer = new Dealer();
        user = new User(500);
        Game.drawPile = new Deck(0);
        Game.discardPile = new Deck(0);
    }

    public void testCheckWinDealerCodejack()
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

    public void testCheckWInBoth()
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