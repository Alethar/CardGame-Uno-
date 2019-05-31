/**
 * 
 * represents a number card
 *
 * @author Benjamin
 * @version May 27, 2019
 * @author Period: 3
 * @author Assignment: UnoGame
 *
 * @author Sources: none
 */
public class NumberCard extends Card
{
    private int number;


    /**
     * constructor
     * 
     * @param color
     *            color of card
     * @param number
     *            number of card
     * @param currGame
     *            current game
     */
    public NumberCard( String color, int number, Game currGame )
    {
        super( color, currGame );
        this.number = number;
    }


    /**
     * returns number of card
     * 
     * @return number
     */
    public int getNumber()
    {
        return number;
    }


    /**
     * sets color of card
     */
    public void setColor()
    {
        getCurrGame().setColor( getColor() );
        getCurrGame().setNumber( getNumber() );
    }
}
