import java.net.*;
import java.io.*;


/**
 * 
 * seperate thread that listens for client input
 *
 * @author Benjamin
 * @version May 27, 2019
 * @author Period: 3
 * @author Assignment: UnoGame
 *
 * @author Sources: none
 */
public class ClientListener extends Thread
{
    private DataInputStream in;

    private volatile boolean stopRun;

    private Game game;

    private String prevString = null;


    /**
     * Constructor that takes a Socket and feeds client inputs from the Socket
     * to game class
     * 
     * @param socket
     *            a socket to take inputs from
     * @param game
     *            the game to send inputs to
     */
    public ClientListener( Socket socket, Game game )
    {
        try
        {
            in = new DataInputStream( socket.getInputStream() );
        }

        catch ( IOException e )
        {
            e.printStackTrace();
        }
        this.game = game;
    }


    /**
     * This class extends Thread, and run is called when the thread is started.
     * It constantly listens for inputs, and when inputs are heard, it calls the
     * relevant methods in Game.
     */
    public void run()
    {
        while ( !stopRun )
        {
            try
            {
                String clientInput = in.readUTF();
                if ( !clientInput.equals( prevString )
                    || clientInput.substring( 1, 2 ).equals( "u" ) )
                {
                    int clientID = Integer.parseInt( clientInput.substring( 0, 1 ) );
                    // client number 0 - 3
                    System.out
                        .println( "Client " + clientID + " Inputs " + clientInput.substring( 1 ) );
                    if ( clientID < 4 )
                    {
                        if ( clientInput.substring( 1, 2 ).equals( "u" ) )
                        {
                            game.uno( clientID );
                        }
                        else
                        {
                            game.setTurnDone( true );
                            if ( clientInput.substring( 1, 2 ).equals( "p" ) )
                            {
                                if ( !( game.getPlayers()[clientID].getHand()
                                    .get( Integer.parseInt(
                                        clientInput.substring( 2, 3 ) ) ) instanceof Wild ) )
                                {

                                    game.cardPlay( clientID,

                                        Integer.parseInt( clientInput.substring( 2, 3 ) ) );
                                }
                                else
                                {
                                    game.cardPlay( clientID,

                                        Integer.parseInt( clientInput.substring( 2, 3 ) ),
                                        clientInput.substring( 3 ) );
                                }
                            }
                            else
                            {
                                if ( !( game.getPlayers()[clientID].getHand()
                                    .get( Integer.parseInt(
                                        clientInput.substring( 1, 2 ) ) ) instanceof Wild ) )
                                {

                                    game.cardPlay( clientID,

                                        Integer.parseInt( clientInput.substring( 1, 2 ) ) );
                                }
                                else
                                {
                                    game.cardPlay( clientID,

                                        Integer.parseInt( clientInput.substring( 1, 2 ) ),
                                        clientInput.substring( 2 ) );
                                }
                            }

                        }

                    }
                    else// input from server instead of clients
                    {
                        System.out.println( "Connection Lost" );
                        System.exit( 0 );
                    }
                }
                else
                {
                }
            }
            catch ( IOException e )
            {
                System.out.println( "Connection Lost" );
                System.exit( 0 );
            }
        }

    }


    /**
     * ends the thread when called
     */
    public void end()
    {
        stopRun = true;
    }
}