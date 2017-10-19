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
    protected String type;

    public Tower(int damage, int loadTime, String type) {
        this.damage = damage;
        this.loadTime = loadTime;
        this.type = type;
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

    @Override
    public String toString() {
        return ("Type: " + this.type + " Damage: " + this.damage + " Position: " + this.position);
    }
}
