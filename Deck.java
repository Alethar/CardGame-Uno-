import java.util.*;


/**
 * 
 * Represents a standard shuffled Uno deck, and can be refilled or drawn from.
 *
 * @author bgao718
 * @version Apr 30, 2019
 * @author Period: 3
 * @author Assignment: UnoGame
 *
 * @author Sources: none
 */
public class Deck
{
    private Queue<Card> deck;

    private Game currGame;


    /**
     * constructor for deck, creates a deck using linked lists and sets game
     * 
     * @param currGame
     *            present game
     */
    public Deck( Game currGame )
    {
        deck = new LinkedList<Card>();
        this.currGame = currGame;
    }


    /**
     * replenishes deck with all necessary cards to play
     */
    public void replenishDeck()
    {
        ArrayList<Card> shuffle = new ArrayList<Card>();
        for ( int x = 0; x < 10; x++ )
        {
            if ( x == 0 )
            {
                shuffle.add( new NumberCard( "red", x, currGame ) );
                shuffle.add( new NumberCard( "green", x, currGame ) );
                shuffle.add( new NumberCard( "yellow", x, currGame ) );
                shuffle.add( new NumberCard( "blue", x, currGame ) );
            }
            else
            {
                shuffle.add( new NumberCard( "red", x, currGame ) );
                shuffle.add( new NumberCard( "green", x, currGame ) );
                shuffle.add( new NumberCard( "yellow", x, currGame ) );
                shuffle.add( new NumberCard( "blue", x, currGame ) );
                shuffle.add( new NumberCard( "red", x, currGame ) );
                shuffle.add( new NumberCard( "green", x, currGame ) );
                shuffle.add( new NumberCard( "yellow", x, currGame ) );
                shuffle.add( new NumberCard( "blue", x, currGame ) );
            }

        }
        for ( int x = 0; x < 2; x++ )
        {
            shuffle.add( new PlusTwo( "red", currGame ) );
            shuffle.add( new PlusTwo( "green", currGame ) );
            shuffle.add( new PlusTwo( "yellow", currGame ) );
            shuffle.add( new PlusTwo( "blue", currGame ) );
        }
        for ( int x = 0; x < 2; x++ )
        {
            shuffle.add( new Skip( "red", currGame ) );
            shuffle.add( new Skip( "green", currGame ) );
            shuffle.add( new Skip( "yellow", currGame ) );
            shuffle.add( new Skip( "blue", currGame ) );
        }
        for ( int x = 0; x < 2; x++ )
        {
            shuffle.add( new Reverse( "red", currGame ) );
            shuffle.add( new Reverse( "green", currGame ) );
            shuffle.add( new Reverse( "yellow", currGame ) );
            shuffle.add( new Reverse( "blue", currGame ) );
        }
        for ( int x = 0; x < 4; x++ )
        {
            shuffle.add( new Wild( currGame ) );
            shuffle.add( new PlusFour( currGame ) );
        }
        Collections.shuffle( shuffle );
        for ( int x = 0; x < shuffle.size(); x++ )
        {
            deck.add( shuffle.get( x ) );
        }
    }


    /**
     * takes a card from the top of the deck replenishes deck if empty
     * 
     * @return Card card drawn from the deck
     */
    public Card draw()
    {

        if ( deck.size() == 0 )
        {
            replenishDeck();
        }
        Card c = deck.remove();
        return ( c );
    }


    /**
     * Accessor method for the deck
     * 
     * @return deck deck of cards
     */
    public Queue<Card> getDeck()
    {
        return deck;
    }
}
