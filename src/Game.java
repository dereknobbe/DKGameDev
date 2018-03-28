import java.awt.*;
import java.awt.image.BufferStrategy;
import java.lang.reflect.GenericArrayType;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = -4774172533440031437L;
    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH / 4 * 3;

    public Game() {
        new Window(WIDTH, HEIGHT, "Let's Build a Game!", this);
    }

    private Thread thread;
    private boolean running = false;

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // Creating the "Game Loop"
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }
    private void tick() {

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        g.dispose();
        bs.show();
    }
    public static void main(String[] args) {
        new Game();
    }
}
