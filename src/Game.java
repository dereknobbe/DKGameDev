import java.awt.*;
import java.lang.reflect.GenericArrayType;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = -4774172533440031437L;
    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH / 12 * 9;

    public Game() {
        new Window(WIDTH, HEIGHT, "Let's Build a Game!", this);
    }
    public static synchronized void start() {

    }

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        new Game();
    }
}
