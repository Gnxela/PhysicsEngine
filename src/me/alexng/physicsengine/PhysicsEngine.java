package me.alexng.physicsengine;

import java.util.ArrayList;

/**
 * Created by Alex Ng on 20/10/2016.
 */
public class PhysicsEngine {

    private ArrayList<Body> bodies = new ArrayList<>();

    public PhysicsEngine() {

    }

    public void addBody(Body body) {
        bodies.add(body);
    }

    public void removeBody(Body body) {
        bodies.remove(body);
    }

    public ArrayList<Body> getBodies() {
        return bodies;
    }

    public void update(float timeDelta) {

    }

}
