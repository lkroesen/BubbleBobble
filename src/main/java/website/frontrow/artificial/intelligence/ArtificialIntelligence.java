package website.frontrow.artificial.intelligence;

import java.util.ArrayList;
import java.util.Random;

import website.frontrow.level.Level;
import website.frontrow.board.Enemy;
import website.frontrow.board.Mover;
import website.frontrow.board.Player;
import website.frontrow.board.Unit;
import website.frontrow.game.GameConstants;

/**
 * Class for the movement of AI units.
 */
public class ArtificialIntelligence 
{
	
	private Level level;
	private ArrayList<Player> players;
	private ArrayList<Unit> units;
	private ArrayList<Enemy> enemies;
	private ArrayList<Integer> movementsX;
	private ArrayList<Integer> movementsY;
	private Unit player;
	
	
	/**
	 * Create a new artificial intelligence.
	 * @param level - input a level to derive enemies from.
	 */
	public ArtificialIntelligence(Level level)
	{
		this.level = level;
		this.enemies = new ArrayList<Enemy>();
		this.units = new ArrayList<Unit>();
		this.movementsX = new ArrayList<Integer>();
		this.movementsY = new ArrayList<Integer>();
		
	}
	
	/**
	 * Method prepares a list of preferred movements for all enemy units.
	 * Then for each enemy, a random check is done to see whether the move is randomized or not.
	 * After that the move is actually executed.
	 */
	public void aiMover()
	{
		this.players = this.level.getPlayers();
		this.units = this.level.getUnits();
		
		for(Unit u:units)
		{
			if (u instanceof Enemy)
			{
					this.enemies.add((Enemy) u);
			}		
		}
		
		if(players.size() > 0)
		{
			this.player = this.players.get(0);	
			movementsX = PathCalculation.calculateXPath((Mover) player, enemies, this.level);
			movementsY = PathCalculation.calculateYPath((Mover) player, enemies, this.level);
				
			for(int i = 0; i < enemies.size(); i++)
			{
				Enemy enemy = enemies.get(i);
				int moveToX = movementsX.get(i);
				int moveToY = movementsY.get(i);
					
				randomizer(enemy);
				doMoves(enemy, moveToX, moveToY);
			}
		}	
	}
	
	/**
	 * Randomizes the move for an enemy every so many ticks with a percentage chance.
	 * @param enemy, the enemy that is moving
	 */
	private void randomizer(Enemy enemy)
	{
		Random r = new Random();
		enemy.setTickCounter(enemy.getTickCounter() + 1);
		
		if(enemy.getTickCounter() >= GameConstants.TICKS_PER_MOVE)
		{
			float chance = r.nextFloat();
			enemy.setTickCounter(0);
			
			if (chance <= GameConstants.AI_RANDOMIZER)
			{
				enemy.setRandom(true);
			}
			else
			{
				enemy.setRandom(false);
			}
		}
	}
	
	/**
	 * This method does the actual movement of an enemy given a (possibly randomized) 
	 * x and y direction to go to.
	 * @param enemy, the enemy that is moving.
	 * @param x, the x direction the unit should move to.
	 * @param y, the y direction the unit should move to.
	 */
	private void doMoves(Enemy enemy, int x, int y)
	{
		if (enemy.getRandom())
		{
		    //TODO: Improve the way things are randomized
			x = 0;
			y = 0;
		}
		
		if(x == 1)
		{
			enemy.goRight();
		}
		else if(x == -1)
		{
			enemy.goLeft();
		}
		
		if(y == -1)
		{
			enemy.jump();
		}
	}
}
