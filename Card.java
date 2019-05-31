/**
 * 
 * represents a card
 *
 * @author Benjamin
 * @version May 27, 2019
 * @author Period: 3
 * @author Assignment: UnoGame
 *
 * @author Sources: none
 */
public abstract class Card
{
    private String color;

    private Game currGame;


    /**
     * Constructs a Card with a specific game and no color
     * 
     * @param currGame
     */
    public Card( Game currGame )
    {
        this.currGame = currGame;
    }


    /**
     * 
     * Constructs a Card with a specific color and part of a specific game
     * 
     * @param color
     *            color of the card
     * @param currGame
     *            current game present
     */
    public Card( String color, Game currGame )
    {
        this.color = color;
        this.currGame = currGame;
    }


    /**
     * 
     * Accessor method for the card color
     * 
     * @return color the color of the card
     */
    public String getColor()
    {
        return color;
    }


    /**
     * 
     * Accessor method for the current game
     * 
     * @return currGame the current game
     */
    public Game getCurrGame()
    {
        return currGame;
    }
}
