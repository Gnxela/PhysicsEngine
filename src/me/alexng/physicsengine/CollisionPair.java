package me.alexng.physicsengine;

import me.alexng.physicsengine.util.Vector2f;

/**
 * Created by Alex Ng on 22/10/2016.
 */
public class CollisionPair {

    private Body a, b;
    private Vector2f impulse;//'A force acting briefly on a body and producing a finite change of momentum.'
    private Vector2f normal;
    private float penetration;

    public CollisionPair(Body a, Body b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Calculates the reactions for the bodies.
     * Reactions are not applies straight away as to not interfere with other collisions.
     */
    public boolean solve() {
        if (a instanceof ShapeCircle && b instanceof ShapeCircle) {
            if (!CircleVsCircle((ShapeCircle) a, (ShapeCircle) b)) {
                return false;
            }
        } else if (a instanceof ShapeRectangle && b instanceof ShapeCircle) {
            if (!RectangleVsCircle(a, b)) {
                return false;
            }
        } else if (a instanceof ShapeCircle && b instanceof ShapeRectangle) {
            if (!RectangleVsCircle(b, a)) {
                return false;
            }
        } else {
            normal = new Vector2f(0, 0);
            penetration = 1;
        }

        //https://gamedevelopment.tutsplus.com/tutorials/how-to-create-a-custom-2d-physics-engine-the-basics-and-impulse-resolution--gamedev-6331
        //Source of equations.

        Vector2f relativeVelocity = b.getVelocity().sub(a.getVelocity());

        float velocityAlongNormal = relativeVelocity.dot(normal);

        if (velocityAlongNormal > 0)
            return false;

        float e = 1;//TODO bounce/elasticity

        float j = (1 + e) * -velocityAlongNormal;
        j /= 1 / a.getMass() + 1 / b.getMass();//Create inverse mass to avoid calculating it every collision.

        // Apply impulse
        impulse = normal.mul(j, j);
        return true;
    }

    private final boolean CircleVsCircle(ShapeCircle a, ShapeCircle b) {
        Vector2f normal = b.getPosition().add(new Vector2f(b.getRadius(), b.getRadius())).sub(a.getPosition().add(new Vector2f(a.getRadius(), a.getRadius())));//getPosition() returns the position of the bounding box.

        float radius = ((ShapeCircle) a).getRadius() + ((ShapeCircle) b).getRadius();

        if (normal.length() * normal.length() > radius * radius)//Do I need to square? Check later...
            return false;

        float distance = normal.length();

        if (distance != 0) {
            this.penetration = radius - distance;
            this.normal = normal.dev(distance, distance);
        } else {
            this.penetration = ((ShapeCircle) a).getRadius();
            this.normal = new Vector2f(0, -1);
        }
        return true;
    }

    private final boolean RectangleVsCircle(Body a, Body b) {

        return true;
    }

    /**
     * Applies the reactions to the bodies.
     */
    public void resolve(float delta) {
        a.applyForce(impulse.mul(-1, -1));
        b.applyForce(impulse);
    }

    public Body getBodyA() {
        return a;
    }

    public void setBodyA(Body a) {
        this.a = a;
    }

    public Body getBodyB() {
        return b;
    }

    public void setBodyB(Body b) {
        this.b = b;
    }
}
