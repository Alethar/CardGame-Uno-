
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * 
 * the logic of the game. runs a game of uno with four players.
 *
 * @author Benjamin
 * @version May 27, 2019
 * @author Period: 3
 * @author Assignment: UnoGame
 *
 * @author Sources: none
 */
public class Game
{

    private Player[] players = new Player[4];

    private String color;

    private Deck gameDeck;

    private int number;

    private boolean direction;

    private int currplayer;

    private Card pile;

    private Server server;

    private volatile boolean turnDone;


    /**
     * Game constructor, creates deck, server, establishes direction, adds
     * player to the server, and starts the game
     */
    public Game()
    {
        gameDeck = new Deck( this );
        server = new Server( this );
        direction = true;
        InetAddress IP;
        try
        {
            IP = InetAddress.getLocalHost();

            for ( int x = 0; x < 4; x++ )
            {
                System.out.println( "IP: " + IP.getHostAddress() );
                System.out.println( "Connect Players. " );
                server.addClient();
                server.broadcast( "i" + x );
                System.out.println( "Player " + ( x + 1 ) + " joined" );
            }
        }

        catch ( UnknownHostException e )
        {
            // Auto-generated catch block
            e.printStackTrace();
        }
        server.setUpListener( this );
        turnDone = false;
        System.out.println( "Game Start" );
        Card c = gameDeck.draw();
        while ( !(c instanceof NumberCard) )
        {
            c = gameDeck.draw();
        }
        pile = c;
        currplayer = -1;
        color = pile.getColor();
        number = ( (NumberCard)pile ).getNumber();
        server.broadcast( "s" + number + color );
        server.broadcast( "c" + color );
        server.broadcast( "n" + number );
        startGame();

    }


    /**
     * for testing purposes
     * 
     * @param test
     */
    public Game( String test )
    {
        gameDeck = new Deck( this );
        server = new Server( this );
        for ( int x = 0; x < 4; x++ )
        {
            players[x] = new Player( x, this );
        }

    }


    /**
     * each player begins by drawing seven cards from the top of the deck
     */
    public void startGame()
    {

        for ( int x = 0; x < 4; x++ )
        {
            players[x] = new Player( x, this );
            for ( int y = 0; y < 7; y++ )
            {
                players[x].drawCard();
            }
        }

        runGame();
    }


    /**
     * runs the game checking for turns and directions, when there is a win,
     * just ends game
     */
    public void runGame()
    {
        while ( !checkWin() )
        {
            if ( direction )
            {
                currplayer++;
            }
            else
            {
                currplayer--;
            }
            turnDone = false;
            currplayer = currplayer % 4;
            if(currplayer == -1) {
                currplayer = 3;
            }
            players[currplayer].turn();
            while ( turnDone == false )
            {
                try
                {
                    Thread.sleep( 500 );
                }
                catch ( InterruptedException e )
                {

                }
            }
            if ( players[currplayer].getHand().size() == 1 )
            {
                players[currplayer].setUnoInvul( false );
            }

        }

        for ( Player p : players )
        {
            if ( p.getHand().size() == 0 )
            {
                endGame( p.getIdNum() );
            }
        }
    }


    /**
     * checks players if there is only 1 card left in the deck
     * 
     * @param idNum
     *            current player index
     */
    public void uno( int idNum )
    {
        for ( Player p : players )
        {
            if ( p.getHand().size() == 1 )
            {
                if ( p.getIdNum() == idNum )
                {
                    p.setUnoInvul( true );
                    server.broadcast( idNum + "uno" );
                }
                else
                {
                    if ( !p.isUnoInvul() )
                    {
                        p.drawCard();
                        p.drawCard();
                        server.broadcast( idNum + "uno" );
                        p.setUnoInvul( true );
                    }
                }
            }
        }
    }


    /**
     * 
     * precondition pos of played card is not a Wild or PlusFour card
     * 
     * @param player
     *            current player index
     * @param pos
     *            current position
     */

    public void cardPlay( int player, int pos )
    {
        System.out.println("***TESTING*** Recieved: " + player + pos);
        Card c = players[player].getHand().get( pos );
        if ( c instanceof NumberCard )
        {
            pile = players[player].getHand().get( pos ) ;
            setNumber( ( (NumberCard)c ).getNumber() );
            setColor( c.getColor() );
            server.broadcast( player + "p" + ((NumberCard)c ).getNumber() + c.getColor() );
            server.broadcast( "c" + c.getColor() );
            server.broadcast( "n" + ( (NumberCard)c ).getNumber() );
            players[player].getHand().remove( pos );
            System.out.println("Player " + (player + 1) + " p " + ((NumberCard)c ).getNumber() + c.getColor());
            System.out.println("Color changed to: " + c.getColor());
            System.out.println("Number changed to: " + ( (NumberCard)c ).getNumber());

        }
        else
        {
            System.out.println("***TESTING*** Recieved: ACTION CARD PLAYED" );
            if ( !( pile instanceof Wild ) )
            {
                System.out.println("***TESTING*** Recieved: NON WILD ACTION CARD PLAYED" );
                pile = players[player].getHand().get( pos ) ;
                ( (ActionCard)( pile ) ).doAction( player );
                players[player].getHand().remove( pos );
            }
        }
    }


    /**
     * precondition pos of played card is a Wild or PlusFour card
     * 
     * @param player
     *            current player index
     * @param pos
     *            current position
     * @param color
     *            current color of the card
     */
    public void cardPlay( int player, int pos, String color )
    {
        setPile( players[player].getHand().get( pos ) );
        ( (Wild)( pile ) ).doAction( player, color );
        players[player].getHand().remove( pos );
        server.broadcast( "c" + color );
        server.broadcast( "n" + number );
        System.out.println("Player " + (player + 1) + " played " + color);
    }


    /**
     * checks if player has won the game
     * 
     * @return true/false depending on whethor player has won the game
     */
    public boolean checkWin()
    {
        for ( Player p : players )
        {
            if ( p.getHand().size() == 0 )
            {
                return true;
            }
        }
        return false;
    }


    /**
     * server broadcasts game has ended
     * 
     * @param winnerID
     *            index of winner
     */
    public void endGame( int winnerID )
    {
        server.broadcast( "4end" );
        System.out.println( "Game Ended. Winner: " + ( winnerID + 1 ) );
        System.exit( 0 );
    }


    /**
     * gets the card on to of the last played pile
     * 
     * @return Card first card on pile
     */
    public Card getPile()
    {
        return pile;
    }


    /**
     * gets the deck used
     * 
     * @return Deck deck used
     */

    public Deck getGameDeck()
    {
        return gameDeck;
    }


    /**
     * gets the array that holds the players
     * 
     * @return players array of players
     */
    public Player[] getPlayers()
    {
        return players;
    }


    /**
     * sets the color to a specific color
     * 
     * @param color
     *            to be set to
     */
    public void setColor( String color )
    {
        this.color = color;
    }


    /**
     * gets the color of the current card
     * 
     * @return color color of current card
     */
    public String getColor()
    {
        return color;
    }


    /**
     * sets the current number to a separate number
     * 
     * @param number
     *            specific number to be changed to
     */
    public void setNumber( int number )
    {
        this.number = number;
    }


    /**
     * gets the current number of the card on the pile
     * 
     * @return number of card on the pile
     */
    public int getNumber()
    {
        return number;
    }


    /**
     * gets the current direction of turns
     * 
     * @return direction of turns
     */
    public boolean getDirection()
    {
        return direction;
    }


    /**
     * 
     * changes current player
     * 
     * @param c
     *            new current player
     */
    public void changeCurrplayer( int c )
    {
        currplayer = c;
    }


    /**
     * 
     * Sets the direction to a different direction
     * 
     * @param direction
     *            to be set to
     */
    public void setDirection( boolean direction )
    {
        this.direction = direction;
    }


    /**
     * 
     * Gets the current player index
     * 
     * @return currplayer index
     */
    public int getCurrplayer()
    {
        return currplayer;
    }


    /**
     * Gets the server
     * 
     * @return server of the game
     */
    public Server getServer()
    {
        return server;
    }


    /**
     * 
     * gets the current game deck
     * 
     * @return gameDeck current game deck
     */
    public Deck getDeck()
    {
        return gameDeck;
    }


    /**
     * 
     * sets the top card of the pile to a different given card
     * 
     * @param c
     *            card to be set to
     */
    public void setPile( Card c )
    {
        this.pile = c;
    }


    /**
     * 
     * sets the current status of the players turn
     * 
     * @param change
     *            in status
     */
    public void setTurnDone( boolean change )
    {
        turnDone = change;
    }


    /**
     * 
     * sets current player to given player
     * 
     * @param currplayer
     *            player to be set to
     */

    public void setCurrplayer( int currplayer )
    {
        this.currplayer = currplayer;
    }
    /**
     * 
     * main method. Starts a new Game by creating a game object.
     * @param args no arguments needed for main method
     */
    public static void main( String args[] )
    {
        
        Game g = new Game();
        
    }
}