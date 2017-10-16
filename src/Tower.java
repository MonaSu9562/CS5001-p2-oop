/**
 * 
 */

/**
 * @author 170026060
 *
 */
public class Tower {
    int damage;
    int loadTime;
    int position;

    /**
     * Return how much damage this tower makes when it hit an enemy. 
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Return the current location of this tower.
     */
    public int getPosition() {
        return position;
    }

    /**
     * This is to get the shoot status of this tower. Return true if it is ready
     * to shoot.
     * 
     * @param timeStep
     *            the current time step of the game.
     */
    public boolean willFire(int timeStep) {
        if (timeStep % loadTime == 0) {
            return true;
        } else {
            return false;
        }
    }

}
