package website.frontrow.artificial.intelligence;

import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.ArrayList;

import org.junit.Test;
import org.mockito.Mock;

import website.frontrow.board.Bubble;
import website.frontrow.board.Enemy;
import website.frontrow.board.Player;
import website.frontrow.board.Unit;
import website.frontrow.level.Cell;
import website.frontrow.level.Level;
import website.frontrow.artificial.intelligence.ArtificialIntelligence;
import website.frontrow.util.Grid;

public class ArtificialIntelligenceTest {

    @Mock Player player;
    @Mock Enemy enemy;
	
	ArrayList<Player> emptyPlayer = new ArrayList<>();
    ArrayList<Unit> emptyUnit = new ArrayList<>();
    Grid<Cell> emptyGrid = new Grid<>(0, 0);
    Level simpleLevel = new Level(emptyPlayer, emptyUnit, emptyGrid);
    ArtificialIntelligence simpleArtificialIntelligence = new ArtificialIntelligence(simpleLevel);
    
    /**
     * AI with empty test.
     */
    @Test
    public void emptyMoving()
    {
        simpleArtificialIntelligence.aiMover();
    }
    
    /**
     * AI with units test.
     */
    @Test
    public void basicMoving()
    {
    	ArrayList<Player> players = new ArrayList<>();
    	players.add(player);
        ArrayList<Unit> units = new ArrayList<>();
        units.add(player);
        units.add(enemy);
        Grid<Cell> grid = new Grid<>(0, 0);
        Level level = new Level(players, units, grid);
        ArtificialIntelligence artificialIntelligence = new ArtificialIntelligence(level);
        
    	//artificialIntelligence.aiMover();
    }
    
    /**
     * 
     */
    @Test
    public void randomizerTest()
    {
        //artificialIntelligence.randomizer(enemy);
    }
}
