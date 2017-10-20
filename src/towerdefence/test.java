package towerdefence;

public class test {
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
