/**
 * 
 * represents an action card
 *
 * @author Benjamin
 * @version May 27, 2019
 * @author Period: 3
 * @author Assignment: UnoGame
 *
 * @author Sources: none
 */
public abstract class ActionCard extends Card
{

    /**
     * Calls constructor of Card creates a card with specific type action card
     * part of a game that does not have color
     * 
     * @param currGame
     *            current game present
     */
    public ActionCard( Game currGame )
    {
        super( currGame );
    }


    /**
     * Calls constructor of Card creates a care with a specific type action card
     * part of a game that has a specific color
     * 
     * @param color
     *            specific color for card
     * @param currGame
     *            current game present
     */
    public ActionCard( String color, Game currGame )
    {
        super( color, currGame );
    }


    /**
     * 
     * method invoked in subclasses by player on specific card
     * 
     * @param idNum
     *            current player index in array
     */
    public abstract void doAction( int idNum );

}
