import java.util.ArrayList;

/**
 * @author 170026060
 */

/**
 *
 */
public class Game {
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    ArrayList<Tower> towers = new ArrayList<Tower>();
    public int timeStep;

    /**
     * 
     * 
     */
    public Game(int corridorLength) {
        timeStep = 0;
    }

    /**
     * This is used to advance the state of the game by one step.
     * 
     * @param
     * 
     */
    public void advance() {
        // check the status of all towers
        for (int i = 0; i < towers.size(); i++) {
            // check if this tower is ready to hit
            if (towers.get(i).willFire(timeStep)) {
                // check the enemies can be hit, tower can only hit the enemy
                // whose position is less than or equal to the position of the
                // tower.
                for (int j = 0; j < enemies.size(); j++) {
                    if (enemies.get(j).getPosition() <= towers.get(i).getPosition()) {
                        // hit enemy
                        enemies.get(j).hit(towers.get(i));
                    }
                }
            }
        }

        // move all the enemies
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).advance();
        }

    }
}
