/**
 * 
 * represents a plus two card
 *
 * @author Benjamin
 * @version May 27, 2019
 * @author Period: 3
 * @author Assignment: UnoGame
 *
 * @author Sources: none
 */
public class PlusTwo extends ActionCard
{

    /**
     * constructor method for plus two, calls action card constructor with
     * specific color and current game
     * 
     * @param color
     *            color of the card
     * @param currGame
     *            current game present
     */
    public PlusTwo( String color, Game currGame )
    {
        super( color, currGame );
    }


    /**
     * action of the plus two card
     * 
     * @param idNum
     *            current player index
     */
    public void doAction( int idNum )
    {
        for ( int x = 0; x < 2; x++ )
        {
            if ( getCurrGame().getDirection() )
            {
                idNum = ( idNum + 1 ) % 4;
            }
            else
            {
                idNum--;
                if ( idNum == -1 )
                {
                    idNum = 3;
                }
            }
            getCurrGame().getPlayers()[idNum].drawCard();
            getCurrGame().setColor( getColor() );

        }
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
        getCurrGame().getServer().broadcast( idNum + "pp2" + getColor() );
        getCurrGame().getServer().broadcast( "k" );
    }
}