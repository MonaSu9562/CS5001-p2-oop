/**
 * @author 170026060
 */
package towerdefence;

/**
 * 
 *
 */
public class Rat extends Enemy {

    /**
     * Initialise the rat.
     */
    public Rat() {
        super();
        this.health = Game.RAT_HEALTH;
        this.distance = Game.RAT_DIATANCE;
        this.step = Game.RAT_STEP;
        this.pay = Game.RAT_PAYMENT;
        this.type = "Rat";
    }

    /**
     * Updating the position of this enemy.
     */
    public void advance() {
        if (this.step == 1) {
            this.position += this.distance;
            this.step = Game.RAT_STEP;
        } else {
            this.step--;
        }
    }

    /**
     * Output the message.
     * 
     * @return information
     */
    @Override
    public String toString() {
        return ("-Rat\t" + super.toString());
    }
}
