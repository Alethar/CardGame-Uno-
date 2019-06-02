public class Wild extends ActionCard
{

    /**
     * calls constructor of action card with current game
     * 
     * @param currGame
     *            current game present
     */
    public Wild( Game currGame )
    {
        super( currGame );
    }


    /**
     * calls constructor of action card with current game and specific color
     * 
     * @param color
     *            specific color of week
     * @param currGame
     *            current game present
     */
    public Wild( String color, Game currGame )
    {
        super( color, currGame );
    }


    /**
     * action of the wild card
     * 
     * @param idNum
     *            current player index
     * @param color
     *            color to be changed to
     */
    public void doAction( int idNum, String color )
    {
        getCurrGame().setColor( color );
        getCurrGame().getServer().broadcast( idNum + "pw" + color );
    }


    /**
     * defining the abstract method of action card
     * 
     * @param idNum
     *            current player index
     */
    public void doAction( int idNum )
    {
        doAction( idNum, "red" );
    }

}