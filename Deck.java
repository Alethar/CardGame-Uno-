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
                shuffle.add( new NumberCard( "Red", x, currGame ) );
                shuffle.add( new NumberCard( "Green", x, currGame ) );
                shuffle.add( new NumberCard( "Yellow", x, currGame ) );
                shuffle.add( new NumberCard( "Blue", x, currGame ) );
            }
            else
            {
                shuffle.add( new NumberCard( "Red", x, currGame ) );
                shuffle.add( new NumberCard( "Green", x, currGame ) );
                shuffle.add( new NumberCard( "Yellow", x, currGame ) );
                shuffle.add( new NumberCard( "Blue", x, currGame ) );
                shuffle.add( new NumberCard( "Red", x, currGame ) );
                shuffle.add( new NumberCard( "Green", x, currGame ) );
                shuffle.add( new NumberCard( "Yellow", x, currGame ) );
                shuffle.add( new NumberCard( "Blue", x, currGame ) );
            }

        }
        for ( int x = 0; x < 2; x++ )
        {
            shuffle.add( new PlusTwo( "Red", currGame ) );
            shuffle.add( new PlusTwo( "Green", currGame ) );
            shuffle.add( new PlusTwo( "Yellow", currGame ) );
            shuffle.add( new PlusTwo( "Blue", currGame ) );
        }
        for ( int x = 0; x < 2; x++ )
        {
            shuffle.add( new Skip( "Red", currGame ) );
            shuffle.add( new Skip( "Green", currGame ) );
            shuffle.add( new Skip( "Yellow", currGame ) );
            shuffle.add( new Skip( "Blue", currGame ) );
        }
        for ( int x = 0; x < 2; x++ )
        {
            shuffle.add( new Reverse( "Red", currGame ) );
            shuffle.add( new Reverse( "Green", currGame ) );
            shuffle.add( new Reverse( "Yellow", currGame ) );
            shuffle.add( new Reverse( "Blue", currGame ) );
        }
        for ( int x = 0; x < 4; x++ )
        {
            shuffle.add( new Wild( currGame ) );
            shuffle.add( new PlusFour( currGame ) );
        }
        for ( int x = 0; x < shuffle.size(); x++ )
        {
            deck.add( shuffle.get( (int)( Math.random() * ( shuffle.size() ) ) ) );
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
