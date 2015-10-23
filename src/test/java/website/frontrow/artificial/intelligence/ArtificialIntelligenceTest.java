package website.frontrow.artificial.intelligence;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import website.frontrow.board.Enemy;
import website.frontrow.board.Player;
import website.frontrow.board.Unit;
import website.frontrow.level.Cell;
import website.frontrow.level.Level;
import website.frontrow.game.GameConstants;
import website.frontrow.util.Grid;
import website.frontrow.util.Point;

/**
 * Test the Artificial Intelligence.
 */
public class ArtificialIntelligenceTest 
{
    
	private ArrayList<Player> players = new ArrayList<>();
	private Player player = new Player(new Point(0, 0), null);
	private ArrayList<Unit> units = new ArrayList<>();   
	private Grid<Cell> emptyGrid = new Grid<>(0, 0);
	private Level simpleLevel;
	private ArtificialIntelligence simpleArtificialIntelligence;
	private Point point;

    /**
     * Setup the lists with one player at a default position.
     */
    @Before 
    public void setUp()
    {
    	players.add(player);
        units.add(player);
        point = new Point(3.0, 3.0);
    }
    
    /**
     * Test the randomizer.
     */
    @Test
    public void doMovesRandomizerTest()
    {      
        Enemy enemy = new Enemy(point, null);
        units.add(enemy);
        enemy.setRandom(0);
        simpleLevel = new Level(players, units, emptyGrid);
        simpleArtificialIntelligence = new ArtificialIntelligence(simpleLevel);
        simpleArtificialIntelligence.aiMover();
	        
        for(int i = 0; i <= GameConstants.TICKS_PER_MOVE + 1; i++)
        {
            simpleLevel.tick();
        }
	        
        assertTrue(enemy.getRandom() < 1);
    }
    
    /**
     * See if the ai moves towards the player if it is to the left.
     */
    @Test
    public void doMovesLeftTest()
    {
      
        Enemy enemy = new Enemy(point, null);
        units.add(enemy);
        simpleLevel = new Level(players, units, emptyGrid);
        simpleArtificialIntelligence = new ArtificialIntelligence(simpleLevel);
        simpleArtificialIntelligence.aiMover();
        simpleLevel.tick();
        
        assertTrue(enemy.getLocation().getX() < point.getX());
    }
    
    /**
     * See if the ai moves towards the player if it is to the right.
     */
    @Test
    public void doMovesRightTest()
    {
        point = new Point(-3.0, -3.0);
    	Enemy enemy = new Enemy(point, null);
        units.add(enemy);
        simpleLevel = new Level(players, units, emptyGrid);
        simpleArtificialIntelligence = new ArtificialIntelligence(simpleLevel);
        simpleArtificialIntelligence.aiMover();
        simpleLevel.tick();
        
        assertTrue(enemy.getLocation().getX() > point.getX());
    }
    
    /**
     * See if the ai moves towards the player if it is above.
     */
    @Test
    public void doMovesDownTest()
    {           
    	Enemy enemy = new Enemy(point, null);
    	units.add(enemy);
    	simpleLevel = new Level(players, units, emptyGrid);
    	simpleArtificialIntelligence = new ArtificialIntelligence(simpleLevel);
    	simpleArtificialIntelligence.aiMover();
    	simpleLevel.tick();
        
    	assertTrue(enemy.getLocation().getY() < point.getY());
    }
}
