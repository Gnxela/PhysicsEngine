package me.alexng.physicsengine;

import me.alexng.physicsengine.util.Vector2f;

/**
 * Created by Alex Ng on 22/10/2016.
 */
public class ShapeCircle extends Shape {

    private float radius;

    public ShapeCircle(Vector2f position, float radius) {
        super(position.sub(radius, radius), new Vector2f(radius * 2, radius * 2));
        this.radius = radius;
        this.setMass(calculateMass());
    }

    @Override
    public float calculateMass() {
        return (float) Math.PI * radius * radius * getDensity();
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
