/**
 * @author 170026060
 */
package towerdefence;

/**
 * 
 *
 */
public class Catapult extends Tower {

    /**
     * Initialise the tower.
     * 
     * @param position
     *            the position which will be placed at.
     */
    public Catapult(int position) {
        this.position = position;
        this.damage = Game.CATAPULT_DAMAGE;
        this.loadTime = Game.CATAPULT_LOADTIME;
        this.cost = Game.CATAPULT_COST;
        this.type = "Catapult";
    }

    /**
     * Output the message.
     * 
     * @return information
     */
    @Override
    public String toString() {
        return ("+Catapult" + super.toString());
    }
}
