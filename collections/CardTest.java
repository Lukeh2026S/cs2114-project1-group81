package collections;

import static org.junit.Assert.*;

public class CardTest extends student.TestCase
{

    
    public void setUp()
    {
        Card card = new Card("7", "H");
        assertEquals("7", card.getRank());
        assertEquals("H", card.getSuit());

    } 

    public void testGetSuitAndRank()   // TRY THIS AGAIN
    {
        Card card = new Card("K", "S");
        assertEquals("KS", card.getSuitAndRank());
        
    } // end of suit and rank test

    public void testGetValue()
    {
        Card card = new Card("7", "H");
        assertEquals(7, card.getValue(0));

    } // end of get value test

    public void testGetValueNumberCardBounds()
    {
        Card low = new Card("2", "C");
        Card high = new Card("10", "D");
 
        assertEquals(2, low.getValue(0));
        assertEquals(10, high.getValue(0));
    }

    public void testGetFaceCardValue()
    {
        Card jack = new Card("J", "C");
        Card queen = new Card("Q", "D");
        Card king = new Card("K", "H");

        assertEquals(10, jack.getValue(0));
        assertEquals(10, queen.getValue(0));
        assertEquals(10, king.getValue(0));
    }

    public void testGetAceValue()
    {
        Card ace = new Card("A", "S");
        assertEquals(11, ace.getvalue(0));
        assertEquals(11, ace.getValue(15));
    }

} // end of class

