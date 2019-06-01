import java.net.*;
import java.io.*;
import java.util.*;


/**
 * 
 * acts as networking, and broadcasts messages to clients
 *
 * @author Benjamin
 * @version May 27, 2019
 * @author Period: 3
 * @author Assignment: UnoGame
 *
 * @author Sources: none
 */
public class Server
{
    // initialize socket and input stream
    int port;

    private Socket initiator = null;

    private Socket s0 = null;

    private Socket s1 = null;

    private Socket s2 = null;

    private Socket s3 = null;

    private DataOutputStream initiout = null;

    private DataOutputStream out0 = null;

    private DataOutputStream out1 = null;

    private DataOutputStream out2 = null;

    private DataOutputStream out3 = null;

    private Scanner input = null;


    /**
     * constructor, initializes server on port 5000 (if taken goes up by one
     * until ok)
     * 
     * @param game
     *            the game server is sending input to (by initializing
     *            clientlistener)
     */
    public Server( Game game ) // finds an unused port and opens it
    {
        port = 5000;
        while(!portAvailable(port)) {
            port += 5;
        }
    }

    /**
     * 
     * TODO Write your method description here.
     * @param port
     * @return
     */
    public static boolean portAvailable( int port )
    {

        ServerSocket servs = null;
        DatagramSocket datas = null;
        try
        {
            servs = new ServerSocket( port );
            servs.setReuseAddress( true );
            datas = new DatagramSocket( port );
            datas.setReuseAddress( true );
            return true;
        }
        catch ( IOException e )
        {
        }
        finally
        {
            if ( datas != null )
            {
                datas.close();
            }

            if ( servs != null )
            {
                try
                {
                    servs.close();
                }
                catch ( IOException e )
                {
                    /* should not be thrown */
                }
            }
        }

        return false;
    }


    /**
     * 
     * adds a client
     * 
     * @return Socket of client
     */
    public void addClient()
    {
        port++;
        System.out.println( "Port: " + port );
        try
        {
            ServerSocket s = new ServerSocket( port );
            if ( out0 == null )
            {
                s0 = s.accept();
                out0 = new DataOutputStream( s0.getOutputStream() );
            }
            else if ( out1 == null )
            {
                s1 = s.accept();
                out1 = new DataOutputStream( s1.getOutputStream() );
            }
            else if ( out2 == null )
            {
                s2 = s.accept();
                out2 = new DataOutputStream( s2.getOutputStream() );
            }
            else if ( out3 == null )
            {
                s3 = s.accept();
                out3 = new DataOutputStream( s3.getOutputStream() );
            }

        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }

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
        ClientListener c0 = new ClientListener( s0, game );
        ClientListener c1 = new ClientListener( s1, game );
        ClientListener c2 = new ClientListener( s2, game );
        ClientListener c3 = new ClientListener( s3, game );
        c0.start();
        c1.start();
        c2.start();
        c3.start();
    }


    /**
     * 
     * broadcasts message to clients
     * 
     * @param str
     */
    public void broadcast( String str )

    {
        System.out.println( "***TESTING*** Output: " + str );
        try
        {
            if ( out0 != null )
            {
                out0.writeUTF( "4" + str );
            }
            if ( out1 != null )
            {
                out1.writeUTF( "4" + str );
            }
            if ( out2 != null )
            {
                out2.writeUTF( "4" + str );
            }
            if ( out3 != null )
            {
                out3.writeUTF( "4" + str );
            }
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