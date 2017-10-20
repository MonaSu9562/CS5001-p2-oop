/**
 * @author 170026060
 */
package towerdefence;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 *
 */
public class Game extends Frame {
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
        System.out.println("*********************** TOWER DEFENCE ***********************");
        System.out.println("The length of this corridor is " + corridorLength);
    }

    /**
     * This is used to advance the state of the game by one step.
     */
    public void advance() {
        // generate enemy randomly.
        System.out.println("*************** generate enemies ************");
        geneEnemy();
        show();

        // place new towers
        System.out.println("***************** spending *****************");
        while (budget > 0) {
            if (budget >= Game.SLINGSHOT_COST) {
                System.out.println("BUDGET: " + this.budget);
                System.out.println("(1) Slingshot:\tCost:" + Game.SLINGSHOT_COST + "\tDamage:" + Game.SLINGSHOT_DAMAGE
                        + "\tLoad Time:" + Game.SLINGSHOT_LOADTIME);
                System.out.println("(2) Catapult:\tCost:" + Game.CATAPULT_COST + "\tDamage:" + Game.CATAPULT_DAMAGE
                        + "\tLoad Time:" + Game.CATAPULT_LOADTIME);
                System.out.println("(3) None");
                Scanner scanner = new Scanner(System.in);
                int input = scanner.nextInt();
                if (input == 1) {
                    System.out.println("Choose position in (0," + (corridorLength - 1) + "):");
                    int pos = scanner.nextInt();
                    // if this position is empty
                    while (corridor.get(pos) == true) {
                        System.out.println("This position was occupied.");
                        pos = scanner.nextInt();
                    }
                    // add new tower
                    Slingshot s = new Slingshot(pos);
                    towers.add(s);
                    corridor.add(pos, true);
                    // updating budget
                    budget -= Game.SLINGSHOT_COST;
                } else if (input == 2) {
                    System.out.println("Choose position in (0," + (corridorLength - 1) + "):");
                    int pos = scanner.nextInt();
                    // if this position is empty
                    while (corridor.get(pos) == true) {
                        System.out.println("This position was occupied.");
                        pos = scanner.nextInt();
                    }
                    // add new tower
                    Catapult c = new Catapult(pos);
                    towers.add(c);
                    corridor.add(pos, true);
                    // updating budget
                    budget -= Game.CATAPULT_COST;
                } else if (input == 3) {
                    break;
                }
            } else {
                System.out.println("BUDGET: " + this.budget + "\t Can not buy anything.");
                break;
            }
        }

        // check the status of all towers
        System.out.println("*************** killing ************");
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
                }
            }
        }
        show();

        // check the status of all enemies
        System.out.println("*************** enemyadvancing ************");
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).advance();
            if (enemies.get(i).position >= corridorLength) {
                gameOver();
            }
        }
        show();

        // timeStep + 1
        timeStep += 1;
    }

    /**
     * Call this function when game is over
     */
    public void gameOver() {
        System.out.println("Game Over");
        this.end = true;
    }

    /**
     * Let player choose which tower tobuild.
     */
    public int chooseTower() {
        System.out.println("BUDGET: " + this.budget);
        System.out.println("You can buy:");
        System.out.println("(1) Slingshot:\tCost:" + Game.SLINGSHOT_COST + "\tDamage:" + Game.SLINGSHOT_DAMAGE
                + "\tLoad Time:" + Game.SLINGSHOT_LOADTIME);
        System.out.println("(2) Catapult:\tCost:" + Game.CATAPULT_COST + "\tDamage:" + Game.CATAPULT_DAMAGE
                + "\tLoad Time:" + Game.CATAPULT_LOADTIME);
        System.out.println("(3) None");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    /**
     * generate enemy
     */
    public void geneEnemy() {
        Random random = new Random();
        int nr = random.nextInt(5);
        int ne = random.nextInt(3);
        for (int i = 0; i < nr; i++) {
            enemies.add(new Rat());
        }
        for (int i = 0; i < ne; i++) {
            enemies.add(new Elephant());
        }
    }

    public void show() {
        for (int i = 0; i < enemies.size(); i++) {
            System.out.println(enemies.get(i).toString());
        }
        for (int i = 0; i < towers.size(); i++) {
            System.out.println(towers.get(i).toString());
        }
        System.out.println("------------------------------------------");
    }

    /**
     * This is the main function.
     * 
     * @param args
     */
    public static void main(String[] args) {
        Game game = new Game(50);
        while (game.end != true) {
            game.advance();
        }
    }
}
