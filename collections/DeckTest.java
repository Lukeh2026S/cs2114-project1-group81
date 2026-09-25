
package collections;

import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Deck, where it verifies constructors for zero, single, 
 * and multiple decks, card retrieval, drawing, shuffling, deck length, 
 * and hand value calculations (including face cards and soft/hard aces).
 * 
 * @author Mayank Rudraraju, Luke Hill, Abigel Daniel
 * @version 2026.09.25
 */
public class DeckTest
{
    /**
     * Test constructor with 0 decks to ensure it initializes an empty deck.
     */
    @Test
    public void testConstructorZeroDecks()
    {
        Deck deck = new Deck(0);
        assertEquals(0, deck.getDeckLength());
    }

    /**
     * Test constructor with 1 standard deck (52 cards) and verify 
     * correct initial card order and suit/rank creation.
     */
    @Test
    public void testConstructorOneDeck()
    {
        Deck deck = new Deck(1);
        assertEquals(52, deck.getDeckLength());

        assertEquals("2C", deck.getCard(0).getSuitAndRank());
        assertEquals("10C", deck.getCard(8).getSuitAndRank());
        assertEquals("JC", deck.getCard(9).getSuitAndRank());
        assertEquals("AC", deck.getCard(12).getSuitAndRank());

        assertEquals("2D", deck.getCard(13).getSuitAndRank());
    }

    /**
     * Test constructor with 2 decks to ensure it correctly 
     * combines multiple decks (104 cards).
     */
    @Test
    public void testConstructorTwoDecks()
    {
        Deck deck = new Deck(2);
        assertEquals(104, deck.getDeckLength());
    }

    /**
     * Test getting a specific card from the deck by index.
     */
    @Test
    public void testGetCard()
    {
        Deck deck = new Deck(0);
        deck.addCard(new Card("9", "H"));
        deck.addCard(new Card("Q", "S"));

        assertEquals("9H", deck.getCard(0).getSuitAndRank());
        assertEquals("QS", deck.getCard(1).getSuitAndRank());
    }

    /**
     * Test drawing a card from a specific index, ensuring it returns 
     * the correct card and reduces the deck length.
     */
    @Test
    public void testDrawCard()
    {
        Deck deck = new Deck(0);
        deck.addCard(new Card("9", "H"));
        deck.addCard(new Card("Q", "S"));

        Card drawn = deck.drawCard(0);

        assertEquals("9H", drawn.getSuitAndRank());
        assertEquals(1, deck.getDeckLength());
        assertEquals("QS", deck.getCard(0).getSuitAndRank());
    }

    /**
     * Test getting the length of the deck dynamically as cards are added.
     */
    @Test
    public void testGetDeckLength()
    {
        Deck deck = new Deck(0);
        assertEquals(0, deck.getDeckLength());
        deck.addCard(new Card("3", "C"));
        assertEquals(1, deck.getDeckLength());
    }

    /**
     * Test adding cards individually to the deck.
     */
    @Test
    public void testAddCard()
    {
        Deck deck = new Deck(0);
        deck.addCard(new Card("3", "C"));
        deck.addCard(new Card("4", "D"));

        assertEquals(2, deck.getDeckLength());
        assertEquals("3C", deck.getCard(0).getSuitAndRank());
        assertEquals("4D", deck.getCard(1).getSuitAndRank());
    }

    /**
     * Test shuffling a full deck maintains the expected total card count.
     */
    @Test
    public void testShuffleCardSameLength()
    {
        Deck deck = new Deck(1);
        deck.shuffleCards();
        assertEquals(52, deck.getDeckLength());
    }

    /**
     * Test shuffling another deck into the main deck, ensuring cards 
     * are transferred and the other deck is emptied.
     */
    @Test
    public void testShuffleInDeck()
    {
        Deck main = new Deck(0);
        main.addCard(new Card("2", "C"));

        Deck other = new Deck(0);
        other.addCard(new Card("5", "H"));
        other.addCard(new Card("9", "S"));

        main.shuffleInDeck(other);

        assertEquals(3, main.getDeckLength());
        assertEquals(0, other.getDeckLength());
        assertEquals("5H", main.getCard(1).getSuitAndRank());
        assertEquals("9S", main.getCard(2).getSuitAndRank());
    }

    /**
     * Test that an empty deck evaluates to a value of 0.
     */
    @Test
    public void testGetDeckValueEmpty()
    {
        Deck deck = new Deck(0);
        assertEquals(0, deck.getDeckValue());
    }

    /**
     * Test deck value calculation with a mix of number and face cards.
     */
    @Test
    public void testGetDeckValueNumberAndFaceCards()
    {
        Deck deck = new Deck(0);

        deck.addCard(new Card("10", "C"));
        deck.addCard(new Card("4", "D"));
        assertEquals(14, deck.getDeckValue());
    }

    /**
     * Test deck value calculation when an Ace counts high (soft ace).
     */
    @Test
    public void testGetDeckValueSoftAce()
    {
        Deck deck = new Deck(0);

        deck.addCard(new Card("A", "C"));
        deck.addCard(new Card("4", "D"));
        assertEquals(15, deck.getDeckValue());
    }

    /**
     * Test deck value calculation when an Ace counts low to prevent a bust (hard ace).
     */
    @Test
    public void testGetDeckValueHardAce()
    {
        Deck deck = new Deck(0);

        deck.addCard(new Card("A", "C"));
        deck.addCard(new Card("4", "D"));
        deck.addCard(new Card("8", "S"));
        
        assertEquals(13, deck.getDeckValue());
    }

    /**
     * Test deck value calculation with multiple aces in the hand.
     */
    @Test
    public void testGetDeckValueTwoAces()
    {
        Deck deck = new Deck(0);

        deck.addCard(new Card("A", "C"));
        deck.addCard(new Card("A", "D"));
        deck.addCard(new Card("8", "S"));

        assertEquals(20, deck.getDeckValue());
    }
}