package me.alexng.physicsengine.util;

/**
 * Created by Alex Ng on 20/10/2016.
 */
public class Vector2f {

    private float x, y;

    /**
     * Creates a new {@link Vector2f} with the same values as v.
     *
     * @param v The {@link Vector2f} to copy.
     */
    public Vector2f(Vector2f v) {
        this.x = v.getX();
        this.y = v.getY();
    }

    /**
     * Creates a new {@link Vector2f} with the values x and y.
     *
     * @param x The vectors x value.
     * @param y The vectors y value.
     */
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the dot product of v1 and v2.
     *
     * @param v1 Vector 1.
     * @param v2 Vector 2.
     * @return The dot product.
     */
    public static float dot(Vector2f v1, Vector2f v2) {
        return v2.getX() * v1.getX() + v2.getY() * v1.getY();
    }

    /**
     * Clones the vector.
     *
     * @return A new identical vector.
     */
    public Vector2f clone() {
        return new Vector2f(this);
    }

    /**
     * Sets the x and y values of the vector.
     *
     * @param v The vector to get the new x and y values.
     * @return The changed vector.
     */
    public Vector2f set(Vector2f v) {
        float xx = v.getX();
        float yy = v.getY();
        return new Vector2f(xx, yy);
    }

    /**
     * Sets the x and y values of the vector.
     *
     * @param x The new x value.
     * @param y The new y value.
     * @return The changed vector.
     */
    public Vector2f set(float x, float y) {
        float xx = x;
        float yy = y;
        return new Vector2f(xx, yy);
    }

    /**
     * Adds the x and y values from v.
     *
     * @param v The vector to add.
     * @return The changes vector.
     */
    public Vector2f add(Vector2f v) {
        float xx = this.x + v.getX();
        float yy = this.y + v.getY();
        return new Vector2f(xx, yy);
    }

    /**
     * Adds the x and y values to the vectors.
     *
     * @param x The x value.
     * @param y The y value.
     * @return The changed vector.
     */
    public Vector2f add(float x, float y) {
        float xx = this.getX() + x;
        float yy = this.getY() + y;
        return new Vector2f(xx, yy);
    }

    /**
     * Subtract the x and y values from v.
     *
     * @param v The vector to subtract.
     * @return The changed vector.
     */
    public Vector2f sub(Vector2f v) {
        float xx = this.x - v.getX();
        float yy = this.y - v.getY();
        return new Vector2f(xx, yy);
    }

    /**
     * Subtracts the x and y values.
     *
     * @param x The x value.
     * @param y The y value.
     * @return The changed vector.
     */
    public Vector2f sub(float x, float y) {
        float xx = this.getX() - x;
        float yy = this.getY() - y;
        return new Vector2f(xx, yy);
    }

    /**
     * Multiplies the vector by v.
     *
     * @param v The vector to multiply by.
     * @return The changed vector.
     */
    public Vector2f mul(Vector2f v) {
        float xx = this.x * v.getX();
        float yy = this.y * v.getY();
        return new Vector2f(xx, yy);
    }

    /**
     * Multiplies the vector by the x and y values.
     *
     * @param x The x value.
     * @param y The y value.
     * @return The changed vector.
     */
    public Vector2f mul(float x, float y) {
        float xx = this.getX() * x;
        float yy = this.getY() * y;
        return new Vector2f(xx, yy);
    }

    /**
     * Divides the vector by v.
     *
     * @param v The vector to divide by.
     * @return The changed vector.
     */
    public Vector2f dev(Vector2f v) {
        float xx = this.x / v.getX();
        float yy = this.y / v.getY();
        return new Vector2f(xx, yy);
    }

    /**
     * Divides the vector by x and y.
     *
     * @param x The x value.
     * @param y the y value.
     * @return The changed vector.
     */
    public Vector2f dev(float x, float y) {
        float xx = this.getX() / x;
        float yy = this.getY() / y;
        return new Vector2f(xx, yy);
    }

    /**
     * Gets the dot product of this vector and v.
     *
     * @param v The other vector.
     * @return The dot product.
     */
    public float dot(Vector2f v) {
        return new Vector2f(x, y).getX() * v.getX() + this.getY() * v.getY();
    }

    /**
     * Returns the length of the vector from (0, 0).
     *
     * @return The length of the vector.
     */
    public float length() {
        return (float) Math.sqrt(getX() * getX() + getY() * getY());
    }

    /**
     * Normalizes the vector.
     *
     * @return The normalized vector.
     */
    public Vector2f normalize() {
        float length = length();
        return dev(length, length);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Vector2f[" + getX() + ", " + getY() + "]";
    }

}