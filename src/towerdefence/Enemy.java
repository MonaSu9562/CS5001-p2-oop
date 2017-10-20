/**
 * @author 170026060
 */
package towerdefence;

/**
 * 
 *
 */
public class Enemy {
    protected int health;
    protected int distance;
    protected int step;
    protected int position;
    protected int pay;

    /**
     * Initialise the enemy.
     */
    public Enemy() {
        this.position = 0;
    }

    /**
     * Return the current health level of this enemy.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Return the current position of this enemy.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Return the payment of killing this enemy.
     */
    public int getPay() {
        return pay;
    }

    /**
     * This will be called when this enemy is hit. Updating the health level of
     * this enemy.
     * 
     * @param t
     *            the tower who hit this enemy.
     */
    public void hit(Tower t) {
        if (getPosition() <= t.getPosition()) {
            this.health -= t.getDamage();
        }
    }

    /**
     * Updating the position of this enemy.
     */
    public void advance() {
    }

    @Override
    public String toString() {
        return ("\tPostion: " + position + "\tHP: " + health);
    }
}
