/**
 * 
 */
package towerdefence;

/**
 * @author 170026060
 *
 */
public class Dog extends Enemy {
    /**
     * Initialize the dog.
     */
    public Dog() {
        super();
        this.health = Game.DOG_HEALTH;
        this.distance = Game.DOG_DIATANCE;
        this.step = Game.DOG_STEP;
        this.pay = Game.DOG_PAYMENT;
        this.type = "Dog";
    }

    /**
     * Updating the position of this enemy.
     */
    public void advance() {
        if (this.step == 1) {
            this.position += this.distance;
            this.step = Game.DOG_STEP;
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
