/**
 * 
 * represents a reverse card
 *
 * @author Benjamin
 * @version May 27, 2019
 * @author Period: 3
 * @author Assignment: UnoGame
 *
 * @author Sources: none
 */
public class Reverse extends ActionCard
{

    /**
     * constructor method that calls the constructor of action card with
     * specific color and current game
     * 
     * @param color
     *            color of the card
     * @param currGame
     *            current game present
     */
    public Reverse( String color, Game currGame )
    {
        super( color, currGame );
    }


    /**
     * action of the reverse card
     * 
     * @param idNum
     *            current player index
     */
    public void doAction( int idNum )
    {
        getCurrGame().setDirection( !getCurrGame().getDirection() );
        getCurrGame().getServer().broadcast( idNum + "pr" + getColor() );
        getCurrGame().getServer().broadcast( "r" );
    }
}