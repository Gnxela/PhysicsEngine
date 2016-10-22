package me.alexng.physicsengine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Ng on 20/10/2016.
 */
public class PhysicsEngine {

    /*

    Units:


     */

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
        List<CollisionPair> pairs = new ArrayList<>();
        for (int i = 0; i < bodies.size(); i++) {
            for (int j = i + 1; j < bodies.size(); j++) {
                CollisionPair pair = new CollisionPair(bodies.get(i), bodies.get(j));
                if (pair.solve())
                    pairs.add(pair);
            }
        }
        for (CollisionPair pair : pairs) {
            pair.resolve(timeDelta);
        }

        for (Body body : bodies) {
            body.setPosition(body.getPosition().add(body.getVelocity().mul(timeDelta, timeDelta)));
        }
    }

}
