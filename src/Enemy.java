/**
 * 
 */

/**
 * @author 170026060
 *
 */
public class Enemy {
    int health;
    int speed;
    int position;

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
     * Updating the health level of this enemy.
     */
    public void hit(Tower t) {
        health -= t.damage;
        // if health level less or equal to 0, this enemy died.
        if (health <= 0){
            
        }
    }

    /**
     * Updating the position of this enemy.
     */
    public void advance() {
        position += speed;
    }
    
}
