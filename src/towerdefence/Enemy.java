package towerdefence;
/**
 * 
 */

/**
 * @author 170026060
 *
 */
public class Enemy {
    protected int health;
    protected int position;
    protected double speed;

    public Enemy(int health, double speed) {
        this.health = health;
        this.speed = speed;
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
     * This will be called when this enemy is hit. Updating the health level of this
     * enemy.
     * 
     * @param t
     *            the tower who hit this enemy.
     */
    public void hit(Tower t) {
        this.health -= t.getDamage();
    }

    /**
     * Updating the position of this enemy.
     */
    public void advance() {
        this.position += speed;
    }
}