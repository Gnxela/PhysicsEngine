package me.alexng.physicsengine;

import me.alexng.physicsengine.util.Vector2f;

/**
 * Created by Alex Ng on 20/10/2016.
 */
public class ShapeRectangle extends Shape {

    public ShapeRectangle(Vector2f position, Vector2f size) {
        super(position, size);
        this.setMass(calculateMass());
    }

    @Override
    public float calculateMass() {
        return (getSize().getX() * getSize().getY()) * getDensity();
    }
}
