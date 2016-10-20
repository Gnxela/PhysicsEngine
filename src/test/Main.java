package test;

import me.alexng.physicsengine.Body;
import me.alexng.physicsengine.PhysicsEngine;
import me.alexng.physicsengine.ShapeSquare;
import me.alexng.physicsengine.util.Vector2f;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Stream;

public class Main {

    public static final int WIDTH = 1020, HEIGHT = 680;

    private final int FPS = 60, TPS = 120;
    private long lastFrame, lastTick;

    private PhysicsEngine physicsEngine;
    private JFrame frame;

    public Main() {
        physicsEngine = new PhysicsEngine();
        frame = new JFrame("PhysicsEngine");
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    public void start() {
        initFrame();
        initScene();
        lastFrame = lastTick = System.currentTimeMillis();

        while (true) {
            if (System.currentTimeMillis() > lastFrame + 1000 / FPS) {
                render();
                lastFrame = System.currentTimeMillis();
            }

            //TPS represents the frequency of updates, however the engine is updated at every possible opportunity to provide 'smooth' physics.
            //A delta is used to insure that no matter how many ticks there really are, the physics updates the same.
            if (System.currentTimeMillis() > lastFrame + 1000 / TPS) {
                lastTick = System.currentTimeMillis();
            }
            float delta = ((System.currentTimeMillis() - lastTick) / (1000 / TPS));
            physicsEngine.update(delta);
        }
    }

    private void initFrame() {
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initScene() {
        physicsEngine.addBody(new ShapeSquare(new Vector2f(100, 100), new Vector2f(50, 50)));
    }

    private void render() {
        Graphics g = frame.getGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);//What even in double buffering?

        //I could just use normal loops, but learning about Streams was useful.
        Stream<ShapeSquare> squareStream = physicsEngine.getBodies().stream().filter(body -> body instanceof ShapeSquare).map(body -> (ShapeSquare) body);
        Stream<Body> uncategorisedStream = physicsEngine.getBodies().stream().filter(body -> !(body instanceof ShapeSquare));

        squareStream.forEach(body -> g.fillRect((int) body.getPosition().getX(), (int) body.getPosition().getY(), (int) body.getSize().getX(), (int) body.getSize().getY()));
    }

}
