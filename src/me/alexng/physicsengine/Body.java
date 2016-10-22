package me.alexng.physicsengine;

import me.alexng.physicsengine.util.Vector2f;

/**
 * Created by Alex Ng on 20/10/2016.
 */
public class Body {

    private Vector2f position, size;// AABB data used in broad phase.
    private Vector2f velocity = new Vector2f();
    private float mass = 1;
    private float density = 1;//Defaults to 1.

    public Body(Vector2f position, Vector2f size) {
        this.position = position;
        this.size = size;
    }

    public void applyForce(Vector2f force) {
        //F = ma
        //a = F / m
        if (mass <= 0)
            return;
        velocity = velocity.add(force.dev(mass, mass));
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

    public Vector2f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }
}
