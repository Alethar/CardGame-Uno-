import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.JUnit4TestAdapter;

/**
 * 
 *  JUnit testing all of the methods that are part of the game
 *  Some game classes are not included due to networking. They are
 *  tested on the outside inside the log
 *
 *  @author  Eric Zhang
 *  @version May 30, 2019
 *  @author  Period: TODO
 *  @author  Assignment: UnoGame
 *
 *  @author  Sources: TODO
 */
public class JUnit
{
    
    
    private ArrayList<Card> h; 
    private int idN;
    private Game game = new Game("KevinDeng");
    private boolean isUno;
    private Player p = new Player(2, game);
    private Player p1 = new Player(0, game);
    private Player p2 = new Player(3, game);
    /**
     * ActionCard Class JUnit completed
     */
    ActionCard unoWild = new Wild(game);
    ActionCard plus4B = new PlusFour(game);
    ActionCard unoWild1 = new Wild("Blue", game);
    ActionCard plus2 = new PlusTwo("Green", game);
    ActionCard reverse = new Reverse("Yellow", game);
    ActionCard skip = new Skip("Green", game);
    ActionCard plus4A = new PlusFour("Green", game);
   
    
    
    @Test
    public void actionCardConstructorOneParam()
    {
        assertEquals(game, unoWild.getCurrGame());
        assertEquals(game, plus4B.getCurrGame());
    }
    
    @Test
    public void actionCardConstructorTwoParam()
    {
        assertEquals("Blue", unoWild1.getColor());
        assertEquals(game, unoWild1.getCurrGame());
        assertEquals("Green", plus4A.getColor());
        assertEquals(game, plus4A.getCurrGame());
        assertEquals("Yellow", reverse.getColor());
        assertEquals(game, reverse.getCurrGame());
        assertEquals("Green", skip.getColor());
        assertEquals(game, skip.getCurrGame());
        assertEquals("Green", plus2.getColor());
        assertEquals(game, plus2.getCurrGame());
        
    }
    
    /**
     * Card Class JUnit completed
     */
    Card unoWild3 = new Wild(game);
    Card plusFourB = new PlusFour(game);
    Card unoWild2 = new Wild("Blue", game);
    Card plus2A = new PlusTwo("Green", game);
    Card reverse1 = new Reverse("Yellow", game);
    Card skip1 = new Skip("Green", game);
    Card plusFourA = new PlusFour("Green", game);
    @Test
    public void cardConstructorOneParam()
    {
        assertEquals(game, unoWild3.getCurrGame());
        assertEquals(game, plusFourB.getCurrGame());
    }
    
    @Test
    public void cardConstructorTwoParam()
    {
        assertEquals("Blue", unoWild2.getColor());
        assertEquals(game, unoWild2.getCurrGame());
        assertEquals("Green", plusFourA.getColor());
        assertEquals(game, plusFourA.getCurrGame());
        assertEquals("Yellow", reverse1.getColor());
        assertEquals(game, reverse1.getCurrGame());
        assertEquals("Green", skip1.getColor());
        assertEquals(game, skip1.getCurrGame());
        assertEquals("Green", plus2A.getColor());
        assertEquals(game, plus2A.getCurrGame());
    }
    
    @Test
    public void cardGetColor()
    {
        assertEquals("Blue", unoWild2.getColor());
        assertEquals("Green", plusFourA.getColor());
        assertEquals("Yellow", reverse1.getColor());
        assertEquals("Green", skip1.getColor());
        assertEquals("Green", plus2A.getColor());
        
    }
    
    @Test
    public void cardGetCurrGame()
    {
        assertEquals(game, unoWild2.getCurrGame());
        assertEquals(game, plusFourA.getCurrGame());
        assertEquals(game, reverse1.getCurrGame());
        assertEquals(game, skip1.getCurrGame());
        assertEquals(game, plus2A.getCurrGame());
        assertEquals(game, unoWild3.getCurrGame());
        assertEquals(game, plusFourB.getCurrGame());
    }
    
    /**
     * Deck Class JUnit completed
     */
    @Test
    public void deckConstructor()
    {
        Deck d = new Deck(game);
        assertEquals(d.getDeck().size(), 0);
    }
    
    @Test
    public void deckReplenish()
    {
        Deck d = new Deck(game);
        if(d.getDeck().size() == 0)
        {
            d.replenishDeck();
        }
        assertEquals(d.getDeck().size() > 0, true);
    }
    
    @Test
    public void deckDraw()
    {
        Deck d = new Deck(game);
        if(d.getDeck().size() == 0)
        {
            d.replenishDeck();
        }
        assertEquals(d.draw() instanceof Card, true);
    }
    
    @Test
    public void deckGetDeck()
    {
        Deck d = new Deck(game);
        assertEquals(d.getDeck().size(), 0);
    }
    
    /**
     * Game Class JUnit - networking, parts of networking tested in spreadsheets
     */
    
    /**
     * Game Class JUnit
     */
    
    @Test
    public void gameCheckWin()
    {
        assertEquals(true, game.checkWin());
    }
    
    @Test
    public void gameSetPile()
    {
        game.setPile( unoCard );
        assertEquals(game.getPile(), unoCard);
    }
    
    @Test
    public void gameGetPile()
    {
        game.setPile(unoCard);
        assertEquals(game.getPile(), unoCard);
    }


    @Test
    public void gameGetGameDeck()
    {
        assertEquals(game.getDeck() instanceof Deck, true);
    }


    @Test
    public void gameGetPlayers()
    {
        assertEquals(game.getPlayers().length > 0, true);
    }

    @Test
    public void gameChangeCurrPlayer()
    {
        game.changeCurrplayer( 1 );
        assertEquals(game.getCurrplayer(), 1);
    }
    
    /**
     * PlusFour Class JUnit completed
     */
    PlusFour uno4 = new PlusFour(game);
    PlusFour uno5 = new PlusFour("Blue", game);
    // completed
    @Test
    public void plusFourConstructorOneParam()
    {
        assertEquals(game, uno4.getCurrGame());
    }
    
    // completed
    @Test
    public void plusFourConstructorTwoParam()
    {
        assertEquals(game, uno5.getCurrGame());
        assertEquals("Blue", uno5.getColor());
    }
    
    @Test
    public void plusFourDoActionFirstCase()
    {
        Player p5 = new Player(2, game);
        game.setDirection( true );
        int x = game.getCurrplayer();
        int handnum = game.getPlayers()[p5.getIdNum() + 1].getHand().size();
        uno5.doAction( p5.getIdNum(), "Yellow" );
        
        
        if(game.getDirection())
        {
            assertEquals(game.getPlayers()[p5.getIdNum() + 1].getHand().size(), handnum + 4);
            assertEquals(game.getColor(), "Yellow");
            x++;
            if(x == 4)
            {
                x = 0;
                assertEquals(p.getIdNum(), 0);
            }
            assertEquals(game.getCurrplayer(), x);
        }
        
    }
    
    @Test
    public void plusFourDoActionSecondCase()
    {
        int x = game.getCurrplayer();
        uno5.doAction( p.getIdNum(), "Yellow" );
        game.setDirection( false );
        if(game.getDirection() == false)
        {
            assertEquals(game.getPlayers()[p.getIdNum() - 1].getHand().size(), game.getPlayers()[p.getIdNum() + 1].getHand().size() + 4);
            assertEquals(game.getColor(), "Yellow");
            x--;
            if(x == -1)
            {
                x = 3;
            }
            assertEquals(game.getCurrplayer(), x);
        }
    }
    
    /**
     * PlusTwo Class JUnit
     */
    PlusTwo uno1 = new PlusTwo("Blue", game);
    // completed
    @Test 
    public void plusTwoConstructor()
    {
        assertEquals(game, uno1.getCurrGame());
        assertEquals("Blue", uno1.getColor());
    }
    
    @Test 
    public void plusTwoDoActionFirstCase()
    {
        Player p7 = new Player(1, game );
        int x = game.getCurrplayer();
        int handsize = game.getPlayers()[p7.getIdNum() + 1].getHand().size();
        game.setDirection( true );
        uno1.doAction( p7.getIdNum() );
        assertEquals( game.getPlayers()[p7.getIdNum() + 1].getHand().size() - handsize, 2 );
        
        if ( game.getDirection() )
        {
            x++;
            if ( x > 3 )
            {
                x = 0;
            }
            assertEquals(game.getCurrplayer(), x);
        }
    }
    
    @Test 
    public void plusTwoDoActionSecondCase()
    {
        Player p1 = new Player( 1, game );
        int x = game.getCurrplayer();
        int handsize = game.getPlayers()[p1.getIdNum() + 1].getHand().size();
        uno1.doAction( p1.getIdNum() );
        assertEquals( game.getPlayers()[p1.getIdNum() + 1].getHand().size() - handsize, 2 );
        game.setDirection( false );
        if(game.getDirection() == false)
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
    Reverse uno = new Reverse("Blue", game);
    // completed
    @Test 
    public void reverseConstructor()
    {
        assertEquals(game, uno.getCurrGame());
        assertEquals("Blue", uno.getColor());
    }
    
    @Test
    public void reverseDoAction()
    {
        unoCard.doAction( p.getIdNum() );
        assertEquals(game.getDirection(), false);
    }
    
    /**
     * Skip Class JUnit  completed
     */
    Skip unoCard = new Skip("Blue", game);
    @Test 
    // completed 
    public void skipConstructor()
    {
        assertEquals(game, unoCard.getCurrGame());
        assertEquals("Blue", unoCard.getColor());
    }
    @Test
    public void skipDoActionFirstCase()
    {
        Player p6 = new Player(3, game);
        game.setDirection( true );
        game.setCurrplayer( 1 );
        if ( game.getDirection() )
        {
            int x = game.getCurrplayer();
            unoCard.doAction( p6.getIdNum() );
            int nextplayer = x + 1;
            if ( nextplayer == 4 )
            {
                nextplayer = 0;
            }
            assertEquals( game.getCurrplayer(), nextplayer );
        }
        
    }
    @Test
    public void skipDoActionSecondCase()
    {
        game.setDirection( false );
        if(game.getDirection() == false)
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
    Wild unoCard1 = new Wild("Blue", game);
    Wild unoCard2 = new Wild(game);
    // completed
    @Test
    public void wildConstructorOneParam()
    {
        assertEquals(game, unoCard2.getCurrGame());
    }
    // completed
    @Test
    public void wildConstructorTwoParam()
    {
        assertEquals(game, unoCard1.getCurrGame());
        assertEquals("Blue", unoCard1.getColor());
    }
    
    @Test
    public void wildDoActionOneParam()
    {
        unoCard1.doAction( p.getIdNum());
        assertEquals(game.getColor(), "red");
    }
    
    @Test
    public void wildDoActionTwoParam()
    {
        unoCard1.doAction( p.getIdNum(), "Green" );
        assertEquals(game.getColor(), "Green");
    }
    
    /**
     * Player Class JUnit missing turn
     */

    @Test 
    public void playerConstructor()
    {
        assertEquals(2, p.getIdNum());
        assertEquals(p.getGame(), game);
                       
    }
    
    @Test
    public void playerDrawCard()
    {
        assertEquals(p.drawCard() instanceof Card, true);
    }
    
    @Test
    public void playerGetHand()
    {
        assertEquals(p.getHand().size(), 0);
    }
    
    @Test
    public void playerGetIdNum()
    {
        assertEquals(p.getIdNum(), 2);
    }
    
    @Test
    public void playerTurn()
    {
        // need to complete
        Card pile = new NumberCard("Blue", 2, game);
        
                
        
    }
    
    @Test
    public void playerIsUnoInvul()
    {
        assertEquals(p.isUnoInvul(), false);
    }
    
    @Test
    public void playerSetUnoInvul()
    {
        p.setUnoInvul( true );
        assertEquals(p.isUnoInvul(), true);
    }
    
    @Test
    public void playerIsPlayable()
    {
        
    }
    
    /**
     * NumberCard Class JUnit completed
     */
    
    NumberCard n = new NumberCard("Blue", 3, game);
    @Test 
    public void numberCardConstructor()
    {
        assertEquals("Blue", n.getColor());
        assertEquals(3, n.getNumber());
        assertEquals(game, n.getCurrGame());
    }
    
    @Test 
    public void numberCardGetNumber()
    {
        assertEquals(3, n.getNumber());
    }
    
    @Test
    public void numberCardSetColor()
    {
        n.setColor();
        assertEquals(game.getColor(), n.getColor());
        assertEquals(game.getNumber(), n.getNumber());
    }

}