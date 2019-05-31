import java.util.*;


/**
 * 
 * represents a player, and hold all info related to said player
 *
 * @author Benjamin
 * @version May 27, 2019
 * @author Period: 3
 * @author Assignment: UnoGame
 *
 * @author Sources: none
 */
public class Player
{
    private ArrayList<Card> hand = new ArrayList<Card>();

    private int idNum;

    private Game game;

    private boolean unoInvul;


    /**
     * constructor, sets id num and game
     * 
     * @param idNum
     *            id of player
     * @param game
     *            game player is in
     */
    public Player( int idNum, Game game )
    {
        this.idNum = idNum;
        this.game = game;
    }


    /**
     * 
     * player draws a card and broadcasts to server
     * 
     * @return card drawn
     */
    public Card drawCard()
    {
        Card c = game.getGameDeck().draw();
        hand.add( c );
        if ( c instanceof NumberCard )
        {
            game.getServer()
                .broadcast( idNum + "d" + ( (NumberCard)c ).getNumber() + c.getColor() );
        }
        else if ( c instanceof PlusFour )
        {
            game.getServer().broadcast( idNum + "dp4" );
        }
        else if ( c instanceof PlusTwo )
        {
            game.getServer().broadcast( idNum + "dp2" + c.getColor() );
        }
        else if ( c instanceof Reverse )
        {
            game.getServer().broadcast( idNum + "dr" );
        }
        else if ( c instanceof Wild )
        {
            game.getServer().broadcast( idNum + "dw" );
        }
        else if ( c instanceof Skip )
        {
            game.getServer().broadcast( idNum + "ds" );
        }

        unoInvul = false;
        return c;
    }


    /**
     * returns hand
     * 
     * @return hand
     */

    public ArrayList<Card> getHand()
    {
        return hand;
    }


    /**
     * 
     * returns id num
     * 
     * @return idNum
     */

    public int getIdNum()
    {
        return idNum;
    }


    /**
     * 
     * completes the player's turn
     */
    public void turn()
    {
        // this loop checks if current hand has card to play, otherwise
        // draws until has a card that can play and plays it
        Card pile = game.getPile();
        boolean draw = true;
        for ( Card c : hand )
        {
            for ( int x = 0; x < hand.size(); x++ )
            {
                if(isPlayable(x)) {
                    draw = false;
                }
            }
        }

        Card drew = null;
        if ( draw == true )
        {
            game.getServer().broadcast( idNum + "a" );
        }
        while ( draw == true )
        {
            drew = drawCard();
            if ( drew instanceof ActionCard )
            {
                if ( !( drew instanceof PlusTwo ) || pile.getColor().equals( drew.getColor() ) )
                {
                    draw = false;
                }
            }
            else if ( drew instanceof NumberCard )
            {
                if ( game.getPile() instanceof ActionCard )
                {
                    if ( drew.getColor().equals( pile.getColor() ) )
                    {
                        draw = false;
                    }
                }
                else
                {
                    if ( drew.getColor().equals( pile.getColor() )
                        || ( (NumberCard)drew ).getNumber() == ( (NumberCard)pile ).getNumber() )
                    {
                        draw = false;
                    }

                }
            }
        }
        if ( drew != null )
        {
            if ( drew instanceof NumberCard )
            {
                game.cardPlay( idNum, hand.indexOf( drew ) );
                game.getServer().broadcast( idNum + "p" );
            }
            else
            {

                game.cardPlay( idNum, hand.indexOf( drew ) );

            }
        }
        else
        {
            game.getServer().broadcast( idNum + "t" );
        }
    }


    /**
     * 
     * returns if player has said uno
     * 
     * @return unoInvul
     */
    public boolean isUnoInvul()
    {
        return unoInvul;
    }


    public boolean isPlayable( int index )
    {
        if ( hand.get( index ) instanceof NumberCard )
        {
            if ( ((NumberCard)hand.get( index )).getNumber() == game.getNumber()
                || ((NumberCard)hand.get( index )).getColor().equals( game.getColor() ) )// if
                                                                     // number
                                                                     // matches
                                                                     // current
                                                                     // number
            {
                return true;
            }
        }
        else//is action card
        {
            if ( hand.get( index ) instanceof PlusTwo )
            {
                if ( ((PlusTwo)hand.get( index )).getColor().equals( game.getColor() ) ) 
                {
                    return true;
                }
            }
            else if ( hand.get( index ) instanceof PlusFour )
            {
                if ( isP4Playable() )
                {
                    return true;
                }
            }
            else
            {
                return true;
            }

        }
        return false;
    }


    public boolean isP4Playable()
    {
        for ( int x = 0; x < hand.size(); x++ )
        {
            if ( !( hand.get( x ) instanceof PlusFour ) && isPlayable( x ) )
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * accesses unoInvul
     * 
     * @param unoInvul
     *            to set
     */
    public void setUnoInvul( boolean unoInvul )
    {
        this.unoInvul = unoInvul;
    }


    /**
     * 
     * returns game
     * 
     * @return game
     */
    public Game getGame()
    {
        return game;
    }

}