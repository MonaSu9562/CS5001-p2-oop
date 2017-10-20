/**
 * @author 170026060
 */
package towerdefence;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 *
 */
public class Game {
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Tower> towers = new ArrayList<Tower>();
    public int timeStep;
    public int budget;
    public int corridorLength;
    public ArrayList<Boolean> corridor = new ArrayList<Boolean>();
    public boolean end = false;

    /**
     * the health of rat.
     */
    public static final int RAT_HEALTH = 1;
    /**
     * the payment player get if one rat was killed.
     */
    public static final int RAT_PAYMENT = 1;
    /**
     * rat move "distance" position every "step" game steps.
     */
    public static final int RAT_STEP = 1;
    /**
     * rat move "distance" position every "step" game steps.
     */
    public static final int RAT_DIATANCE = 2;

    /**
     * the health of elephant.
     */
    public static final int ELEPHANT_HEALTH = 10;
    /**
     * the payment player get if one elephant was killed.
     */
    public static final int ELEPHANT_PAYMENT = 5;
    /**
     * elephant move "distance" position every "step" game steps.
     */
    public static final int ELEPHANT_STEP = 2;
    /**
     * elephant move "distance" position every "step" game steps.
     */
    public static final int ELEPHANT_DISTANCE = 1;

    /**
     * the health of dog.
     */
    public static final int DOG_HEALTH = 3;
    /**
     * the payment player get if one dog was killed.
     */
    public static final int DOG_PAYMENT = 3;
    /**
     * dog move "distance" position every "step" game steps.
     */
    public static final int DOG_STEP = 1;
    /**
     * dog move "distance" position every "step" game steps.
     */
    public static final int DOG_DIATANCE = 1;

    /**
     * the damage slingshot could make in one hit.
     */
    public static final int SLINGSHOT_DAMAGE = 1;
    /**
     * the load time of slingshot.
     */
    public static final int SLINGSHOT_LOADTIME = 1;
    /**
     * the price of slingshot.
     */
    public static final int SLINGSHOT_COST = 5;

    /**
     * the damage catapult could make in one hit.
     */
    public static final int CATAPULT_DAMAGE = 5;
    /**
     * the load time of catapult.
     */
    public static final int CATAPULT_LOADTIME = 3;
    /**
     * the price of catapult.
     */
    public static final int CATAPULT_COST = 15;

    /**
     * the damage archertower could make in one hit.
     */
    public static final int ARCHER_DAMAGE = 2;
    /**
     * the load time of archertower.
     */
    public static final int ARCHER_LOADTIME = 2;
    /**
     * the price of archertower.
     */
    public static final int ARCHER_COST = 8;

    /**
     * This is the constructor of class Game.
     * 
     * @param corridorLength
     *            the length of the map.
     */
    public Game(int corridorLength) {
        this.corridorLength = corridorLength;
        this.budget = 10;
        this.timeStep = 0;
        for (int i = 0; i < corridorLength; i++) {
            corridor.add(false);
        }
        System.out.println("############################ TOWER DEFENCE ############################");
        System.out.println("The length of this corridor is " + corridorLength);
    }

    /**
     * This is used to advance the state of the game by one step.
     */
    public void advance() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        System.out.println("Press Enter to continue");
        scanner.nextLine();

        System.out.println("*************************** TimeStep: " + timeStep + "***************************");
        // generate enemy randomly.
        geneEnemy();

        // place new towers
        buildTower();

        // towers shooting
        // check the status of all towers
        for (int i = 0; i < towers.size(); i++) {
            // check if this tower is ready to hit
            if (towers.get(i).willFire(timeStep)) {
                // search the nearest enemy.
                int pos = 0;
                int index = enemies.size();
                for (int j = 0; j < enemies.size(); j++) {
                    if (enemies.get(j).getPosition() <= towers.get(i).getPosition()
                            && enemies.get(j).getPosition() >= pos) {
                        pos = enemies.get(j).getPosition();
                        index = j;
                    }
                }
                if (index < enemies.size()) {
                    enemies.get(index).hit(towers.get(i));
                }
                // delete the died enemy
                if (enemies.get(index).getHealth() <= 0) {
                    budget += enemies.get(index).getPay();
                    enemies.remove(index);
                }
                if (enemies.size() == 0) {
                    System.out.println("YOU WIN!!");
                    System.exit(0);
                }
            }
        }

        // enemies moving
        // check the status of all enemies
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).advance();
            if (enemies.get(i).position >= corridorLength) {
                gameOver();
            }
        }

        // show statues and ask if go ahead.
        show();
        System.out.println("*************************** Step: " + timeStep + " is over.  ***************************");

        // timeStep + 1
        this.timeStep += 1;
    }

    /**
     * Call this function when game is over
     */
    public void gameOver() {
        System.out.println("Game Over");
        this.end = true;
        System.exit(0);
    }

    /**
     * Generate unless 1 enemy randomly.
     */
    public void geneEnemy() {
        Random random = new Random();
        int nr = random.nextInt(2);
        int ne = random.nextInt(1);
        while (ne + nr == 0) {
            nr = random.nextInt(5);
            ne = random.nextInt(3);
        }
        for (int i = 0; i < nr; i++) {
            enemies.add(new Rat());
        }
        for (int i = 0; i < ne; i++) {
            enemies.add(new Elephant());
        }
    }

    /**
     * 
     */
    public void buildTower() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        while (budget > 0) {
            if (budget >= Game.SLINGSHOT_COST) {
                System.out.println("BUDGET: " + this.budget);
                System.out.println("(1) Slingshot:\tCost:" + Game.SLINGSHOT_COST + "\tDamage:" + Game.SLINGSHOT_DAMAGE
                        + "\tLoad Time:" + Game.SLINGSHOT_LOADTIME);
                System.out.println("(2) Catapult:\tCost:" + Game.CATAPULT_COST + "\tDamage:" + Game.CATAPULT_DAMAGE
                        + "\tLoad Time:" + Game.CATAPULT_LOADTIME);
                System.out.println("(3) None");
                int input = scanner.nextInt();
                if (input == 1) {
                    System.out.println("Choose position in (0," + (corridorLength - 1) + "):");
                    int pos = scanner.nextInt();
                    while (pos >= corridorLength || pos < 0 || corridor.get(pos) == true) {
                        // Check if the input position is in the correct range.
                        if (pos >= corridorLength || pos < 0) {
                            System.out.println("The position should between 0 and " + (corridorLength - 1) + ".");
                            pos = scanner.nextInt();
                        }
                        // Check input position, until that position is empty.
                        if (corridor.get(pos) == true) {
                            System.out.println("This position was occupied.");
                            pos = scanner.nextInt();
                        }
                    }
                    // add new tower
                    Slingshot s = new Slingshot(pos);
                    towers.add(s);
                    corridor.add(pos, true);
                    // updating budget
                    budget -= Game.SLINGSHOT_COST;
                } else if (input == 2) {
                    if (budget >= Game.CATAPULT_COST) {
                        System.out.println("Choose position in (0," + (corridorLength - 1) + "):");
                        int pos = scanner.nextInt();
                        while (pos >= corridorLength || pos < 0 || corridor.get(pos) == true) {
                            // Check if the input position is in the correct
                            // range.
                            if (pos >= corridorLength || pos < 0) {
                                System.out.println("The position should between 0 and " + (corridorLength - 1) + ".");
                                pos = scanner.nextInt();
                            }
                            // Check input position, until that position is
                            // empty.
                            if (corridor.get(pos) == true) {
                                System.out.println("This position was occupied.");
                                pos = scanner.nextInt();
                            }
                        }
                        // add new tower
                        Catapult c = new Catapult(pos);
                        towers.add(c);
                        corridor.add(pos, true);
                        // updating budget
                        budget -= Game.CATAPULT_COST;
                    } else {
                        System.out.println("Don't have enough budget. Please choose again.\n");
                    }

                } else if (input == 3) {
                    break;
                }
            } else {
                System.out.println("BUDGET: " + this.budget + "\nCan not buy anything.\n");
                break;
            }
        }
    }

    /**
     * 
     */
    public void show() {
        System.out.println("************************ Results ************************");
        StringBuffer str1 = new StringBuffer(corridorLength);
        StringBuffer str2 = new StringBuffer(corridorLength);

        for (int i = 0; i < corridorLength; i++) {
            str1.append('_');
            str2.append('_');
        }

        for (int i = 0; i < enemies.size(); i++) {
            System.out.println(enemies.get(i).toString());
        }
        for (int i = 0; i < towers.size(); i++) {
            System.out.println(towers.get(i).toString());
        }

        for (int i = 0; i < towers.size(); i++) {
            int pos = towers.get(i).getPosition();
            if (towers.get(i).getType().equals("Slingshot")) {
                str1.replace(pos, pos + 1, "^");
            } else if (towers.get(i).getType().equals("Catapult")) {
                str1.replace(pos, pos + 1, "A");
            }
        }
        System.out.println(str1);

        for (int i = 0; i < enemies.size(); i++) {
            int pos = enemies.get(i).getPosition();
            if (enemies.get(i).getType().equals("Rat")) {
                str2.replace(pos, pos + 1, ".");
            } else if (enemies.get(i).getType().equals("Elephant")) {
                str2.replace(pos, pos + 1, "O");
            }
        }
        System.out.println(str2);
    }

}
