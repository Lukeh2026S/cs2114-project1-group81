/**
 * Unit tests for card class
 * I used claude to help me finalize the list (method names) of the test cases I wanted to write in case I missed anything important.
 */
// @author Mayank Rudraraju, Luke Hill, Abigel Daniel
// @version 2026.09.25
package collections;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CardTest
{

    @Before
    public void setUp()
    {
        //not a setup function
        Card card = new Card("7", "H");
        assertEquals("7", card.getRank());
        assertEquals("H", card.getSuit());

    } 
    @Test
    public void testGetSuitAndRank()   // TRY THIS AGAIN
    {
        Card card = new Card("K", "S");
        assertEquals("KS", card.getSuitAndRank());
        
    } // end of suit and rank test
    @Test
    public void testGetValue()
    {
        Card card = new Card("7", "H");
        assertEquals(7, card.getValue(0));

    } // end of get value test
    @Test
    public void testGetValueNumberCardBounds()
    {
        Card low = new Card("2", "C");
        Card high = new Card("10", "D");
 
        assertEquals(2, low.getValue(0));
        assertEquals(10, high.getValue(0));
    }
    @Test
    public void testGetFaceCardValue()
    {
        Card jack = new Card("J", "C");
        Card queen = new Card("Q", "D");
        Card king = new Card("K", "H");

        assertEquals(10, jack.getValue(0));
        assertEquals(10, queen.getValue(0));
        assertEquals(10, king.getValue(0));
    }
    @Test
    public void testGetAceValue()
    {
        Card ace = new Card("A", "S");
        assertEquals(11, ace.getValue(0));
        assertEquals(11, ace.getValue(15));
    }

} // end of class

