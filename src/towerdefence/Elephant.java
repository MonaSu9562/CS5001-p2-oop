/**
 * @author 170026060
 */
package towerdefence;

/**
 * 
 *
 */
public class Elephant extends Enemy {

    /**
     * Initialise the elephant.
     */
    public Elephant() {
        super();
        this.health = Game.ELEPHANT_HEALTH;
        this.distance = Game.ELEPHANT_DISTANCE;
        this.step = Game.ELEPHANT_STEP;
        this.pay = Game.ELEPHANT_PAYMENT;
        this.type = "Elephant";
    }

    /**
     * Updating the position of this enemy.
     */
    public void advance() {
        if (this.step == 1) {
            this.position += this.distance;
            this.step = Game.ELEPHANT_STEP;
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
        return ("-Elephant" + super.toString());
    }
}
