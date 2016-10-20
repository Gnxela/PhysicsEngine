package me.alexng.physicsengine;

import me.alexng.physicsengine.util.Vector2f;

/**
 * Created by Alex Ng on 20/10/2016.
 */
public class Body {

    public Vector2f position, size;// AABB data used in broad phase.

    public Body(Vector2f position, Vector2f size) {
        this.position = position;
        this.size = size;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public Vector2f getSize() {
        return size;
    }

    public void setSize(Vector2f size) {
        this.size = size;
    }
}
