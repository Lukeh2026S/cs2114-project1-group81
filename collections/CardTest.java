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
//
// During the preparation of this assignment, I, Mayank Rudraraju, Luke Hill, Abigel Daniel used Claude 
// in Test classes to debug and format.
// After using this tool, I reviewed and edited the content as needed to ensure its
// accuracy and take full responsibility for the content in relation to grading. I understand
// that I am responsible for being able to complete this work without the use of
// assistance.
package collections;

import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Card. Verifies getters, suit/rank combinations, 
 * and value calculations for numbers, face cards, and aces.
 * used claude to help me finalize the list (method names) of the 
 * test cases wanted to write in case we missed anything important.
 * @author Mayank Rudraraju, Luke Hill, Abigel Daniel
 * @version 2026.09.25
 */
public class CardTest {

    /**
     * Test initialization of a Card and ensure getRank() 
     * and getSuit() return the correct values.
     */
    @Test
    public void testCardInitializationAndGetters() {
        Card card = new Card("7", "H");
        assertEquals("7", card.getRank());
        assertEquals("H", card.getSuit());
    } 

    /**
     * Test getSuitAndRank() method to ensure it concatenates 
     * the rank and suit strings properly in the correct order.
     */
    @Test
    public void testGetSuitAndRank() {
        Card card = new Card("K", "S");
        assertEquals("KS", card.getSuitAndRank());
    } 

    /**
     * Test getValue() method with a standard number card.
     */
    @Test
    public void testGetValue() {
        Card card = new Card("7", "H");
        assertEquals(7, card.getValue(0));
    } 

    /**
     * Test getValue() with boundary number cards (low bounds 2 and high bounds 10).
     */
    @Test
    public void testGetValueNumberCardBounds() {
        Card low = new Card("2", "C");
        Card high = new Card("10", "D");
 
        assertEquals(2, low.getValue(0));
        assertEquals(10, high.getValue(0));
    }

    /**
     * Test getValue() for all face cards (Jack, Queen, King) 
     * to ensure they correctly return a value of 10.
     */
    @Test
    public void testGetFaceCardValue() {
        Card jack = new Card("J", "C");
        Card queen = new Card("Q", "D");
        Card king = new Card("K", "H");
        assertEquals(10, jack.getValue(0));
        assertEquals(10, queen.getValue(0));
        assertEquals(10, king.getValue(0));
    }

    /**
     * Test getValue() for an Ace card under different current score conditions 
     * to ensure it returns 11 as expected by the card specification.
     */
    @Test
    public void testGetAceValue() {
        Card ace = new Card("A", "S");
        assertEquals(11, ace.getValue(0));
        assertEquals(11, ace.getValue(15));
    }
} // end of class 
