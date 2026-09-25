package collections;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/*
    test for the deck class
*/

public class DeckTest
{
    @Test
    public void testConstructorZeroDecks()
    {
        Deck deck = new Deck(0);
        assertEquals(0, deck.getDeckLength());
    }
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
    @Test
    public void testConstructorTwoDecks()
    {
        Deck deck = new Deck(2);
        assertEquals(104, deck.getDeckLength());
    }
    @Test
    public void testGetCard()
    {
        Deck deck = new Deck(0);
        deck.addCard(new Card("9", "H"));
        deck.addCard(new Card("Q", "S"));

        assertEquals("9H", deck.getCard(0).getSuitAndRank());
        assertEquals("QS", deck.getCard(1).getSuitAndRank());
    }
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
    @Test
    public void testGetDeckLength()
    {
        Deck deck = new Deck(0);
        assertEquals(0, deck.getDeckLength());
        deck.addCard(new Card("3", "C"));
        assertEquals(1, deck.getDeckLength());
    }
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
    @Test
    public void testShuffleCardSameLength()
    {
        Deck deck = new Deck(1);
        deck.shuffleCards();
        assertEquals(52, deck.getDeckLength());
    }
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
    @Test
    public void testGetDeckValueEMpty()
    {
        Deck deck = new Deck(0);
        assertEquals(0, deck.getDeckValue());
    }
    @Test
    public void testGetDeckValueNumberANdFaceCards()
    {
        Deck deck = new Deck(0);

        deck.addCard(new Card("10", "C"));
        deck.addCard(new Card("4", "D"));
        assertEquals(14, deck.getDeckValue());

    }
    @Test
    public void testGetDeckValueSoftAce()
    {
        Deck deck = new Deck(0);

        deck.addCard(new Card("A", "C"));
        deck.addCard(new Card("4", "D"));
        assertEquals(15, deck.getDeckValue());
    }
    @Test
    public void testGetDeckValueHardAce()
    {
        Deck deck = new Deck(0);

        deck.addCard(new Card("A", "C"));
        deck.addCard(new Card("4", "D"));
        deck.addCard(new Card("8", "S"));
        
        assertEquals(13, deck.getDeckValue());
    }
    @Test
    public void testGetDeckValueTwoAces()
    {
        Deck deck = new Deck(0);

        deck.addCard(new Card("A", "C"));
        deck.addCard(new Card("A", "D"));
        deck.addCard(new Card("8", "S"));

        assertEquals(20, deck.getDeckValue());
    }

} // end of deck test