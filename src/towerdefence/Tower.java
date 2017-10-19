package towerdefence;
/**
 * 
 */

/**
 * @author 170026060
 *
 */
public class Tower {

    protected int damage;
    protected int position;
    protected int loadTime;

    public Tower(int damage, int loadTime) {
        this.damage = damage;
        this.loadTime = loadTime;
        this.position = 0;
    }

    /**
     * Return the damage of this tower
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Return the position of this tower
     */
    public int getPosition() {
        return position;
    }

    /**
     * This is to get the shoot status of this tower. Return true if it is ready to
     * shoot.
     * 
     * @param timeStep
     *            the current time step of the game
     */
    public boolean willFire(int timeStep) {
        if (timeStep % loadTime == 0) {
            return true;
        } else {
            return false;
        }
    }

}
