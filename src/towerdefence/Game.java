package towerdefence;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author 170026060
 *
 */
public class Game extends Frame {
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    ArrayList<Tower> towers = new ArrayList<Tower>();
    public int timeStep;

    public Game(int corridorLength) {
        timeStep = 0;
    }

    /**
     * This is used to advance the state of the game by one step.
     *
     */
    public void advance() {
        // check the status of all towers
        for (int i = 0; i < towers.size(); i++) {
            // check if this tower is ready to hit
            if (towers.get(i).willFire(timeStep)) {
                // search the enemies can be hit, tower can only hit the enemy
                // whose position is less than or equal to the position of the
                // tower.
                for (int j = 0; j < enemies.size(); j++) {
                    if (enemies.get(j).getPosition() <= towers.get(i).getPosition()) {
                        // hit enemy
                        enemies.get(j).hit(towers.get(i));
                    }
                }
            }
        }

        // move all the enemies
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).advance();
        }
    }

    // **************UI************************************

    /**
     * Default serial version ID
     */
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        int corridorLength = new Integer(args[0]);
        new Game(corridorLength).launchFrame();
    }

    // 坦克所在的位置的坐标
    private int x = 50;
    private int y = 50;

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, 30, 30);
        g.setColor(c);

    }

    public void launchFrame() {

        this.setTitle("TowerDefence");
        this.setLocation(300, 400);
        this.setSize(600, 400);
        // this.setBackground(Color.GRAY);
        // 为关闭窗口添加响应
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });
        // 设置是否允许用户改变窗口的大小，这里限制下，不允许
        this.setResizable(false);
        this.setVisible(true);

        new Thread(new MyRepaint()).start();
        // this.addKeyListener(new KeyMonitor());

    }

    private class MyRepaint implements Runnable {

        @Override
        public void run() {
            while (true) {
                // 每50ms重画一次
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    // private class KeyMonitor extends KeyAdapter {
    //
    // @Override
    // public void keyPressed(KeyEvent e) {
    // int key = e.getKeyCode();
    // switch (key) {
    // case KeyEvent.VK_LEFT:
    // x -= 5;
    // break;
    // case KeyEvent.VK_UP:
    // y -= 5;
    // break;
    // case KeyEvent.VK_RIGHT:
    // x += 5;
    // break;
    // case KeyEvent.VK_DOWN:
    // y += 5;
    // break;
    // }
    // }
    //
    // }
}
