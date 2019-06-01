/**
 * 
 * represents a skip card
 *
 * @author Benjamin
 * @version May 27, 2019
 * @author Period: 3
 * @author Assignment: UnoGame
 *
 * @author Sources: none
 */

public class Skip extends ActionCard
{

    /**
     * constructor calls action card constructor creates a card with specific
     * color and game
     * 
     * @param color
     *            specific color of card
     * @param currGame
     *            current game present
     */
    public Skip( String color, Game currGame )
    {
        super( color, currGame );
    }


    /**
     * action of the skip card
     * 
     * @param idNum
     *            current player index
     */
    public void doAction( int idNum )
    {
        if ( getCurrGame().getDirection() )
        {
            int x = ( getCurrGame().getCurrplayer() + 1 );
            if ( x > 3 )
            {
                x = 0;
            }
            getCurrGame().setCurrplayer( x );
        }
        if ( !getCurrGame().getDirection() )
        {
            int x = ( getCurrGame().getCurrplayer() - 1 );
            if ( x < 0 )
            {
                x = 3;
            }
            getCurrGame().setCurrplayer( x );
        }
        getCurrGame().getServer().broadcast( idNum + "ps" );
        getCurrGame().getServer().broadcast( "k" );
    }
}
