package website.frontrow.artificial.intelligence;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import org.mockito.Mock;

import website.frontrow.board.Enemy;
import website.frontrow.board.Player;
import website.frontrow.board.Unit;
import website.frontrow.level.Cell;
import website.frontrow.level.Level;
import website.frontrow.util.Grid;
import website.frontrow.util.Point;

/**
 * Tecking the pathcalculation
 * 
 * I want to Mock, checksstyle mocks me because of that.
 */
@SuppressWarnings("checkstyle:visibilitymodifier")
public class PathCalculationTest 
{
	
    @Mock Player player;
    @Mock Enemy enemy;
	
    private ArrayList<Player> emptyPlayer = new ArrayList<>();
    private ArrayList<Unit> emptyUnit = new ArrayList<>();
    private Grid<Cell> emptyGrid = new Grid<>(0, 0);
    private Level simpleLevel = new Level(emptyPlayer, emptyUnit, emptyGrid);
    
    /**
     * Test the enemy movement to the right.
     */
    @Test
    public void moveOnXaxisRightTest()
    {
    	int move = PathCalculation.moveOnXAxis(new Point(1, 0), new Point(2, 0));
    	assertTrue(move < 0);
    }
    
    /**
     * Test the enemy movement to the left.
     */
    @Test
    public void moveOnXaxisLeftTest()
    {
    	int move = PathCalculation.moveOnXAxis(new Point(2, 0), new Point(1, 0));
    	
    	assertTrue(move > 0);
    }
    
    /**
     * Test the enemy movement.
     */
    @Test
    public void moveOnXaxisNoneTest()
    {
    	int move = PathCalculation.moveOnXAxis(new Point(1, 0), new Point(1, 0));
    	assertTrue(move == 0);
    }
    
    /**
     * Test the enemy movement up.
     */
    @Test
    public void moveOnYaxisUpTest()
    {
    	int move = PathCalculation.moveOnYAxis(new Point(0, 1), new Point(0, 2));
    	assertTrue(move < 0);
    }
    
    /**
     * Test the enemy movement to the bottom.
     */
    @Test
    public void moveOnYaxisDownTest()
    {
    	int move = PathCalculation.moveOnYAxis(new Point(0, 2), new Point(0, 1));
    	assertTrue(move > 0);
    }
    
    /**
     * Test the enemy movement.
     */
    @Test
    public void moveOnYaxisNoneTest()
    {
    	int move = PathCalculation.moveOnYAxis(new Point(0, 1), new Point(0, 1));
    	assertTrue(move == 0);
    }
    
    /**
     * Test basic functionality of calculatePathX moving Left.
     */
    @Test
    public void calculateXPathLeftTest()
    {
    	Enemy enemy = new Enemy(new Point(2, 2), null);
    	Player player = new Player(new Point(1, 1), null);
    	
    	int move = PathCalculation.calculateXPath(player, enemy, simpleLevel);
    	assertTrue(move == -1);
    }
    
    /**
     * Test basic functionality of calculatePathX moving Right.
     */
    @Test
    public void calculateXPathRightTest()
    {
    	Enemy enemy = new Enemy(new Point(0, 0), null);
    	Player player = new Player(new Point(1, 1), null);
    	
    	int move = PathCalculation.calculateXPath(player, enemy, simpleLevel);
    	assertTrue(move == 1);
    }
    
    /**
     * Test basic functionality of calculatePathX not moving at all.
     */
    @Test
    public void calculateXPathStandingStillTest()
    {
    	Enemy enemy = new Enemy(new Point(1, 1), null);
    	Player player = new Player(new Point(1, 1), null);
    	
    	int move = PathCalculation.calculateXPath(player, enemy, simpleLevel);
    	assertTrue(move == 0);
    }
    
    /**
     * Test basic functionality of calculatePathY moving Up.
     */
    @Test
    public void calculateYPathUpTest()
    {
    	Enemy enemy = new Enemy(new Point(2, 2), null);
    	Player player = new Player(new Point(1, 1), null);
    	
    	int move = PathCalculation.calculateYPath(player, enemy, simpleLevel);
    	assertTrue(move == -1);
    }
    
    /**
     * Test basic functionality of calculatePathY moving Down.
     */
    @Test
    public void calculateYPathDownTest()
    {
    	Enemy enemy = new Enemy(new Point(0, 0), null);
    	Player player = new Player(new Point(1, 1), null);
    	
    	int move = PathCalculation.calculateYPath(player, enemy, simpleLevel);
    	assertTrue(move == 1);
    }
    
    /**
     * Test basic functionality of calculatePathY not moving at all.
     */
    @Test
    public void calculateYPathStandingStillTest()
    {
    	Enemy enemy = new Enemy(new Point(1, 1), null);
    	Player player = new Player(new Point(1, 1), null);
    	
    	int move = PathCalculation.calculateYPath(player, enemy, simpleLevel);
    	assertTrue(move == 0);
    }
    
}
