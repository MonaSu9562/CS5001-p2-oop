/**
 * @author 170026060
 */
package towerdefence;

/**
 * 
 *
 */
public class Tower {

    protected int damage;
    protected int position;
    protected int loadTime;
    protected int cost;

    /**
     * Initialise the tower.
     */
    public Tower() {
        this.position = 0;
    }

    /**
     * Return the damage of this tower
     * 
     * @return damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Return the position of this tower
     * 
     * @return current position
     */
    public int getPosition() {
        return position;
    }

    /**
     * This is to get the shoot status of this tower. Return true if it is ready
     * to shoot.
     * 
     * @param timeStep
     *            the current time step of the game
     * @return if this tower can shoot or not
     */
    public boolean willFire(int timeStep) {
        if (timeStep % loadTime == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Output the message.
     * 
     * @return information
     */
    @Override
    public String toString() {
        return ("\tPosition: " + this.position);
    }
}
