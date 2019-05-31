import java.net.*;
import java.io.*;
import java.util.*;


/**
 * 
 * acts as networking, and broadcasts messages to clients
 *
 * @author Eric
 * @version May 27, 2019
 * @author Period: 3
 * @author Assignment: UnoGame
 *
 * @author Sources: none
 */
public class OldServer
{
    // initialize socket and input stream
    private Socket socket = null;

    private ServerSocket server = null;

    private DataOutputStream out = null;

    private Scanner input = null;


    /**
     * constructor, initializes server on port 5000 (if taken goes up by one
     * until ok)
     * 
     * @param game
     *            the game server is sending input to (by initializing
     *            clientlistener)
     */
    public OldServer( Game game ) // finds an unused port and opens it
    {
        int port = 5000; // rand num, set later
        while ( true )
        {

            try (Socket ignored = new Socket( "localhost", port ))
            {
                port++;
            }
            catch ( IOException ignored )
            {
                try
                {
                    server = new ServerSocket( port );
                    System.out.println("Port: " + port);
                }
                catch ( IOException e )
                {
                    e.printStackTrace();
                }
                break;
            }
        }
    }


    /**
     * 
     * adds a client
     * 
     * @return Socket of client
     */
    public Socket addClient()
    {

        try
        {
            out = new DataOutputStream( socket.getOutputStream() );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }

        return socket;

    }


    /**
     * 
     * initializes the clientlistener
     * 
     * @param game
     *            game to put input to
     */
    public void setUpListener( Game game )
    {
        ClientListener c = new ClientListener( socket, game );
        c.start();
    }


    /**
     * 
     * broadcasts message to clients
     * 
     * @param str
     */
    public void broadcast( String str )

    {
        try
        {
            socket = server.accept();
            out.writeUTF( "4" + str );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }

}

/*
 * public static void main( String args[] ) throws UnknownHostException {
 * InetAddress inetAddress = InetAddress.getLocalHost(); System.out.println(
 * "IP Address:- " + inetAddress.getHostAddress() ); System.out.println(
 * "Host Name:- " + inetAddress.getHostName() ); Server server = new Server(
 * 5000 ); }
 */