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

    private final int FPS = 60, TPS = 128;
    private int frames, ticks;
    private long lastFrame, lastTick, lastTitleUpdate;

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
                frames++;
                render();
                lastFrame = System.currentTimeMillis();
            }

            //A delta is used to insure that no matter how many ticks there really are, the physics updates the same.
            if(System.currentTimeMillis() > lastTick + 1000 / TPS) {
                ticks++;
                float delta = ((float)(System.currentTimeMillis() - lastTick) / (float)(1000 / TPS));//Cast to float so allow for decimals.
                physicsEngine.update(delta);
                lastTick = System.currentTimeMillis();
            }

            if(System.currentTimeMillis() > lastTitleUpdate + 1000) {
                frame.setTitle("PhysicsEngine | " + frames + " | " + ticks);
                frames = ticks = 0;
                lastTitleUpdate = System.currentTimeMillis();
            }
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
        Image buffer = frame.createImage(WIDTH, HEIGHT);
        Graphics bufferGraphics = buffer.getGraphics();

        bufferGraphics.clearRect(0, 0, WIDTH, HEIGHT);

        Stream<ShapeSquare> squareStream = physicsEngine.getBodies().stream().filter(body -> body instanceof ShapeSquare).map(body -> (ShapeSquare) body);
        Stream<Body> uncategorisedStream = physicsEngine.getBodies().stream().filter(body -> !(body instanceof ShapeSquare));

        squareStream.forEach(body -> bufferGraphics.fillRect((int) body.getPosition().getX(), (int) body.getPosition().getY(), (int) body.getSize().getX(), (int) body.getSize().getY()));


        Graphics g = frame.getGraphics();
        g.drawImage(buffer, 0, 0, frame);
    }

}
