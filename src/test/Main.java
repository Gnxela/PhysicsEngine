package test;

import me.alexng.physicsengine.Body;
import me.alexng.physicsengine.PhysicsEngine;
import me.alexng.physicsengine.ShapeCircle;
import me.alexng.physicsengine.ShapeRectangle;
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
                lastFrame = System.currentTimeMillis();
                frames++;
                render();
            }

            //A delta is used to insure that no matter how many ticks there really are, the physics updates the same.
            if (System.currentTimeMillis() > lastTick + 1000 / TPS) {
                float delta = ((float) (System.currentTimeMillis() - lastTick) / (float) (1000 / TPS));//Cast to float so allow for decimals.
                lastTick = System.currentTimeMillis();
                ticks++;
                physicsEngine.update(delta);

                for (Body body : physicsEngine.getBodies()) {//Gravity.
                    body.applyForce(new Vector2f(0, body.getMass() / (9.8f * 9.8f)).mul(delta, delta));
                }
            }

            if (System.currentTimeMillis() > lastTitleUpdate + 1000) {
                lastTitleUpdate = System.currentTimeMillis();
                frame.setTitle("PhysicsEngine | " + frames + " | " + ticks);
                frames = ticks = 0;
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
        ShapeRectangle platform = new ShapeRectangle(new Vector2f(100, 600), new Vector2f(WIDTH - 200, 50));
        platform.setMass(0);
        //physicsEngine.addBody(platform);

        ShapeCircle test = new ShapeCircle(new Vector2f(345, 400), 25);
        test.applyForce(new Vector2f(0, -4000));
        physicsEngine.addBody(test);

        physicsEngine.addBody(new ShapeCircle(new Vector2f(300, 100), 25));
        physicsEngine.addBody(new ShapeCircle(new Vector2f(310, 30), 25));
    }

    private void render() {
        Image buffer = frame.createImage(WIDTH, HEIGHT);
        Graphics bufferGraphics = buffer.getGraphics();

        bufferGraphics.clearRect(0, 0, WIDTH, HEIGHT);

        Stream<ShapeRectangle> rectangleStream = physicsEngine.getBodies().stream().filter(body -> body instanceof ShapeRectangle).map(body -> (ShapeRectangle) body);
        Stream<ShapeCircle> circleStream = physicsEngine.getBodies().stream().filter(body -> body instanceof ShapeCircle).map(body -> (ShapeCircle) body);
        Stream<Body> uncategorisedStream = physicsEngine.getBodies().stream().filter(body -> !(body instanceof ShapeRectangle) && !(body instanceof ShapeCircle));

        rectangleStream.forEach(body -> {
            bufferGraphics.fillRect((int) body.getPosition().getX(), (int) body.getPosition().getY(), (int) body.getSize().getX(), (int) body.getSize().getY());
        });

        circleStream.forEach(body -> {
            int x = (int) body.getPosition().getX(), y = (int) body.getPosition().getY();
            bufferGraphics.fillOval(x, y, (int) body.getSize().getX(), (int) body.getSize().getY());
//            bufferGraphics.drawLine(x, y, WIDTH / 2, HEIGHT / 2);
        });

        int i = 0;
        bufferGraphics.drawString("Type | Mass", 4, 37 + i * 12);
        for (Body body : physicsEngine.getBodies()) {
            i++;
            bufferGraphics.drawString(body.getClass().getSimpleName() + " | " + body.getMass(), 4, 37 + i * 12);
        }

        Graphics g = frame.getGraphics();
        g.drawImage(buffer, 0, 0, frame);
    }

}
