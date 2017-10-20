/**
 * @author 170026060
 */
package towerdefence;

/**
 * 
 *
 */
public class Slingshot extends Tower {

    /**
     * Initialise the tower.
     * 
     * @param position
     *            the position which will be placed at.
     */
    public Slingshot(int position) {
        this.position = position;
        this.damage = Game.SLINGSHOT_DAMAGE;
        this.loadTime = Game.SLINGSHOT_LOADTIME;
        this.cost = Game.SLINGSHOT_COST;
    }

    /**
     * Output the message.
     * 
     * @return information
     */
    @Override
    public String toString() {
        return ("+Slingshot" + super.toString());
    }

}
