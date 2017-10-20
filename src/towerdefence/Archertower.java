/**
 * 
 */
package towerdefence;

/**
 * @author 170026060
 *
 */
public class Archertower extends Tower {
    /**
     * Initialise the tower.
     * 
     * @param position
     *            the position which will be placed at.
     */
    public Archertower(int position) {
        this.position = position;
        this.damage = Game.ARCHER_DAMAGE;
        this.loadTime = Game.ARCHER_LOADTIME;
        this.cost = Game.ARCHER_COST;
        this.type = "Archertower";
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
