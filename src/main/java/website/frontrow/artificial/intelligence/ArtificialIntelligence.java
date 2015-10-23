package website.frontrow.artificial.intelligence;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

import website.frontrow.level.Level;
import website.frontrow.logger.Log;
import website.frontrow.logger.Logable;
import website.frontrow.board.Enemy;
import website.frontrow.board.Mover;
import website.frontrow.board.Player;
import website.frontrow.board.Unit;
import website.frontrow.game.GameConstants;

/**
 * Class for the movement of AI units.
 */
public class ArtificialIntelligence
		implements Logable
{
	private static final float JUMP_RATE = 0.03f;
	private static final float LEFT_RATE = 0.52f;
	private static final float RIGHT_RATE = 1.00f;

	private Level level;
	private ArrayList<Player> players = new ArrayList<>();
	private ArrayList<Unit> units = new ArrayList<>();
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private Unit player;
	
	
	/**
	 * Create a new artificial intelligence.
	 * @param level input a level to derive enemies from.
	 */
	public ArtificialIntelligence(Level level)
	{
		this.level = level;
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

		// Abbreviation u here to adhere to lambda expressions.
		this.enemies.addAll(units.stream().filter(
				u -> u instanceof Enemy).map(
				u -> (Enemy) u).collect(Collectors.toList()));
		
		if(players.size() > 0)
		{
			this.player = this.players.get(0);
				
			for(int i = 0; i < enemies.size(); i++)
			{
				Enemy enemy = enemies.get(i);
				enemy.setPlayer((Player) player);
				int moveToX = PathCalculation.calculateXPath((Mover) player, enemy);
				int moveToY = PathCalculation.calculateYPath((Mover) player, enemy);
					
				randomize(enemy);
				doMoves(enemy, moveToX, moveToY);
			}
		}
	}
	
	/**
	 * Randomizes the move for an enemy every so many ticks with a percentage chance.
	 * @param enemy the enemy that is moving
	 */
	private void randomize(Enemy enemy)
	{
		enemy.setTickCounter(enemy.getTickCounter() + 1);
		
		if(enemy.getTickCounter() >= GameConstants.TICKS_PER_MOVE)
		{
			Random random = new Random();
			float chance = random.nextFloat();
			enemy.setTickCounter(0);
			enemy.setRandom(chance);
			addToLog("[AI]\tMovement random seed is now " + enemy.getRandom());
		}
	}
	
	/**
	 * This method does the actual movement of an enemy given a (possibly randomized) 
	 * x and y direction to go to.
	 * @param enemy The enemy that is moving.
	 * @param x The x direction the unit should move to.
	 * @param y The y direction the unit should move to.
	 */
	private void doMoves(Enemy enemy, int x, int y)
	{
		if (enemy.getRandom() < GameConstants.AI_RANDOMIZE)
		{	
			doRandomMove(enemy);
			return;
		}

		if(y == 1)
		{
			fallOffPlatform(enemy);
			return;
		}

		if(x == 1)
		{
			enemy.goRight();
		}
		
		if(x == -1)
		{
			enemy.goLeft();
		}
		
		if(y == -1)
		{
			enemy.jump();			
		}
		if(!enemy.getPlayer().isAlive())
		{
			nextPlayer();
		}
	}
	
	/**
	 * If an enemy is above the player we want it to fall of the platform.
	 * We do this by moving to one side of the level. 
	 * If we reach the end without falling down (sufficiently) we go in the other direction.
	 * If we are running into a wall we want to go the other way.
	 * @param enemy the enemy that is moving.
	 */
	private void fallOffPlatform(Enemy enemy)
	{
		if(enemy.getWallCollision())
		{
			enemy.setLastWall(!enemy.getLastWall());
			enemy.setWallCollision(false);
		}

		if(enemy.getLastWall())
		{
			enemy.goLeft();
		}
		else
		{
			enemy.goRight();
		}
	}
	
	/**
	 * If an enemy is eligible to make a random move it comes here.
	 * It can now randomly choose one option from three movement options 
	 * depending on it's random seed.
	 * @param enemy the enemy that is moving
	 * 
	 * It's a random, there's magic numbers.
	 */
	private void doRandomMove(Enemy enemy)
	{
		if(enemy.getRandom() < (JUMP_RATE * GameConstants.AI_RANDOMIZE))
		{
			enemy.jump();	
			addToLog("[AI]\tAn enemy just did the super rare JUMP SPAM!");
		}
		else if(enemy.getRandom() < (LEFT_RATE * GameConstants.AI_RANDOMIZE))
		{
			enemy.goLeft();
		}
		else if(enemy.getRandom() < (RIGHT_RATE * GameConstants.AI_RANDOMIZE))
		{
			enemy.goRight();
		}
	}
	
	/**
	 * Write to the log.
	 * @param action the message.
	 */
    @Override
    public void addToLog(String action)
    {
        Log.add(action);
    }
    
    /**
     * Assigns the enemies to the second player if the first dies.
     */
    public void nextPlayer()
    {
    	if(players.size() > 1)
		{
			this.player = this.players.get(1);
			
			for(int i = 0; i < enemies.size(); i++)
			{
				Enemy enemy = enemies.get(i);
				if(enemy.isAlive())
				{
					enemy.setPlayer((Player) player);
					int moveToX = PathCalculation.calculateXPath((Mover) player, enemy);
					int moveToY = PathCalculation.calculateYPath((Mover) player, enemy);
				
					randomize(enemy);
					doMoves(enemy, moveToX, moveToY);
				}
			}
		}
    }
}