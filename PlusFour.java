
/**
 * 
 * represents a plus four card
 *
 * @author Benjamin
 * @version May 27, 2019
 * @author Period: 3
 * @author Assignment: UnoGame
 *
 * @author Sources: none
 */
public class PlusFour extends Wild
{

    /**
     * constructor for plus four calls wild's constructor for a specific game
     * 
     * @param currGame
     *            current game present
     */
    public PlusFour( Game currGame )
    {
        super( currGame );
    }


    /**
     * constructor for plus four calls wild's constructor for specific game and
     * contains a specific color. Color is not used by PlusFour, and this
     * constructor is only here for structure.
     * 
     * @param color
     *            specific color of wild
     * @param currGame
     *            current game present
     */
    public PlusFour( String color, Game currGame )
    {
        super( color, currGame );
    }


    
    /**
     * action of the plus four card
     * 
     * @param idNum
     *            current index of player
     * @param color
     *            color it is going to be changed to
     */
    @Override
    public void doAction( int idNum, String color )
    {
        if ( getCurrGame().getDirection() )
        {
            idNum++;
            if ( idNum == 4 )
            {
                idNum = 0;
            }
            for ( int x = 0; x < 4; x++ )
            {

                getCurrGame().getPlayers()[idNum].drawCard();
                getCurrGame().setColor( color );
                getCurrGame().getServer().broadcast( "c" + color );
            }
            int x = ( getCurrGame().getCurrplayer() + 1 );
            if ( x > 3 )
            {
                x = 0;
            }
            getCurrGame().setCurrplayer( x );
        }
        if ( !getCurrGame().getDirection() )
        {
            idNum--;
            if ( idNum == -1 )
            {
                idNum = 3;
            }
            for ( int x = 0; x < 4; x++ )
            {

                getCurrGame().getPlayers()[idNum].drawCard();
                getCurrGame().setColor( color );
                getCurrGame().getServer().broadcast( "c" + color );
            }
            int x = ( getCurrGame().getCurrplayer() - 1 );
            if ( x < 0 )
            {
                x = 3;
            }
            getCurrGame().setCurrplayer( x );

        }
        getCurrGame().getServer().broadcast( idNum + "pp4" );
        getCurrGame().getServer().broadcast( "k" );
    }
}