import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.JUnit4TestAdapter;


public class JUnit
{

    private ArrayList<Card> h;

    private int idN;

    private Game game = new Game( "KevinDeng" );

    private boolean isUno;

    private Player p = new Player( 2, game );

    /**
     * ActionCard Class JUnit completed
     */
    Wild unoWild = new Wild( game );

    Wild unoWild1 = new Wild( "Blue", game );


    @Test
    public void actionCardConstructorOneParam()
    {
        assertEquals( game, unoWild.getCurrGame() );
    }


    @Test
    public void actionCardConstructorTwoParam()
    {
        assertEquals( "Blue", unoWild1.getColor() );
        assertEquals( game, unoWild1.getCurrGame() );

    }

    /**
     * Card Class JUnit completed
     */
    Card unoWild2 = new Wild( game );

    Card unoWild3 = new Wild( "Green", game );


    @Test
    public void cardConstructorOneParam()
    {
        assertEquals( game, unoWild.getCurrGame() );
    }


    @Test
    public void cardConstructorTwoParam()
    {
        assertEquals( game, unoWild3.getCurrGame() );
        assertEquals( "Green", unoWild3.getColor() );
    }


    @Test
    public void cardGetColor()
    {
        assertEquals( "Green", unoWild3.getColor() );
    }


    @Test
    public void cardGetCurrGame()
    {
        assertEquals( game, unoWild3.getCurrGame() );
    }


    /**
     * Deck Class JUnit completed
     */
    @Test
    public void deckConstructor()
    {
        Deck d = new Deck( game );
        assertEquals( d.getDeck().size(), 0 );
    }


    @Test
    public void deckReplenish()
    {
        Deck d = new Deck( game );
        for ( int x = 0; x < d.getDeck().size(); x++ )
        {
            d.draw();
        }
        d.draw();
        assertEquals( d.getDeck().size() > 0, true );
    }


    @Test
    public void deckDraw()
    {
        Deck d = new Deck( game );
        assertEquals( d.draw() instanceof Card, true );
    }


    @Test
    public void deckGetDeck()
    {
        Deck d = new Deck( game );
        assertEquals( d.getDeck().size(), 0 );
    }

    /**
     * Game Class JUnit
     */

    Game p2;


    @Test
    public void gameConstructor()
    {
        p2 = new Game( "testing constructor" );

    }


    @Test
    public void gameRunGame()
    {

    }


    @Test
    public void gameUno()
    {

    }


    @Test
    public void gameCardPlay()
    {

    }


    @Test
    public void gameCheckWin()
    {
        Deck d = new Deck( game );

    }


    @Test
    public void gameEndGame()
    {

    }


    @Test
    public void gameGetPile()
    {

    }


    @Test
    public void gameGetGameDeck()
    {

    }


    @Test
    public void gameGetPlayers()
    {

    }


    @Test
    public void gameSetColor()
    {

    }


    @Test
    public void gameGetColor()
    {

    }


    @Test
    public void gameSetNumber()
    {

    }


    @Test
    public void gameGetNumber()
    {

    }


    @Test
    public void gameGetDirection()
    {

    }


    @Test
    public void gameSetDirection()
    {

    }


    @Test
    public void gameGetCurrPlayer()
    {

    }


    @Test
    public void gameSetCurrPlayer()
    {

    }

    /**
     * PlusFour Class JUnit completed
     */

    PlusFour uno5 = new PlusFour( "Blue", game );


    // completed
    @Test
    public void plusFourConstructorOneParam()
    {
        assertEquals( game, uno5.getCurrGame() );
    }


    // completed
    @Test
    public void plusFourConstructorTwoParam()
    {
        assertEquals( game, uno5.getCurrGame() );
        assertEquals( "Blue", uno5.getColor() );
    }


    @Test
    public void plusFourDoAction()
    {
        game.setCurrplayer( 0 );
        int x = game.getCurrplayer();
        int handsize = game.getPlayers()[1].getHand().size();
        uno5.doAction( game.getPlayers()[x].getIdNum(), "Yellow" );
        assertEquals( game.getPlayers()[game.getPlayers()[game.getCurrplayer()].getIdNum()].getHand().size() - handsize, 1);
        assertEquals( game.getColor(), "Yellow" );
        assertEquals( game.getCurrplayer(), x + 1 );

    }

    /**
     * PlusTwo Class JUnit
     */
    PlusTwo uno1 = new PlusTwo( "Blue", game );


    // completed
    @Test
    public void plusTwoConstructor()
    {
        assertEquals( game, uno1.getCurrGame() );
        assertEquals( "Blue", uno1.getColor() );

    }


    @Test
    public void plusTwoDoAction()
    {
        Player p1 = new Player( 1, game );
        int x = game.getCurrplayer();
        int handsize = game.getPlayers()[p1.getIdNum() + 1].getHand().size();
        uno1.doAction( p1.getIdNum() );
        assertEquals( game.getPlayers()[p1.getIdNum() + 1].getHand().size() - handsize, 2 );
        if ( game.getDirection() )
        {
            x++;
            if ( x == 4 )
            {
                x = 0;
            }
            assertEquals( game.getCurrplayer(), x );
        }
        else
        {
            x--;
            if ( x == -1 )
            {
                x = 3;
            }
            assertEquals( game.getCurrplayer(), x );
        }
    }

    /**
     * Reverse Class JUnit completed
     */
    Reverse uno = new Reverse( "Blue", game );


    // completed
    @Test
    public void reverseConstructor()
    {
        assertEquals( game, uno.getCurrGame() );
        assertEquals( "Blue", uno.getColor() );
    }


    @Test
    public void reverseDoAction()
    {
        unoCard.doAction( p.getIdNum() );
        assertEquals( game.getDirection(), false );
    }

    /**
     * Skip Class JUnit completed
     */
    Skip unoCard = new Skip( "Blue", game );


    @Test
    // completed
    public void skipConstructor()
    {
        assertEquals( game, unoCard.getCurrGame() );
        assertEquals( "Blue", unoCard.getColor() );
    }


    @Test
    public void skipDoAction()
    {
        if ( game.getDirection() )
        {
            int x = game.getCurrplayer();
            unoCard.doAction( p.getIdNum() );
            int nextplayer = x + 1;
            if ( nextplayer == 4 )
            {
                nextplayer = 0;
            }
            assertEquals( game.getCurrplayer(), nextplayer );
        }
        else
        {
            int x = game.getCurrplayer();
            unoCard.doAction( p.getIdNum() );
            int nextplayer = x - 1;
            if ( nextplayer == -1 )
            {
                nextplayer = 3;
            }
            assertEquals( game.getCurrplayer(), nextplayer );

        }

    }

    /**
     * Wild Class JUnit completed
     */
    Wild unoCard1 = new Wild( "Blue", game );

    Wild unoCard2 = new Wild( game );


    // completed
    @Test
    public void wildConstructorOneParam()
    {
        assertEquals( game, unoCard2.getCurrGame() );
    }


    // completed
    @Test
    public void wildConstructorTwoParam()
    {
        assertEquals( game, unoCard1.getCurrGame() );
        assertEquals( "Blue", unoCard1.getColor() );
    }


    @Test
    public void wildDoActionOneParam()
    {
        // need to be completed
        unoCard1.doAction( p.getIdNum() );
        assertEquals( game.getColor(), "red" );
    }


    @Test
    public void wildDoActionTwoParam()
    {
        // need to be completed
        unoCard1.doAction( p.getIdNum(), "Green" );
        assertEquals( game.getColor(), "Green" );
    }


    /**
     * Player Class JUnit missing turn
     */

    @Test
    public void playerConstructor()
    {

        assertEquals( 2, p.getIdNum() );
        assertEquals( p.getGame(), game );

    }


    @Test
    public void playerDrawCard()
    {
        assertEquals( p.drawCard() instanceof Card, true );
    }


    @Test
    public void playerGetHand()
    {
        assertEquals( p.getHand().size(), 0 );
    }


    @Test
    public void playerGetIdNum()
    {
        assertEquals( p.getIdNum(), 2 );
    }


    @Test
    public void playerTurn()
    {
        // need to comeplete
        Card pile = new NumberCard( "Blue", 2, game );

    }


    @Test
    public void playerIsUnoInvul()
    {
        assertEquals( p.isUnoInvul(), false );
    }


    @Test
    public void playerSetUnoInvul()
    {
        p.setUnoInvul( true );
        assertEquals( p.isUnoInvul(), true );
    }

    /**
     * NumberCard Class JUnit completed
     */

    NumberCard n = new NumberCard( "Blue", 3, game );


    @Test
    public void numberCardConstructor()
    {
        assertEquals( "Blue", n.getColor() );
        assertEquals( 3, n.getNumber() );
        assertEquals( game, n.getCurrGame() );
    }


    @Test
    public void numberCardGetNumber()
    {
        assertEquals( 3, n.getNumber() );
    }


    @Test
    public void numberCardSetColor()
    {
        n.setColor();
        assertEquals( game.getColor(), n.getColor() );
        assertEquals( game.getNumber(), n.getNumber() );
    }

}