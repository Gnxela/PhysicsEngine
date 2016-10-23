package me.alexng.physicsengine.util;

/**
 * Created by Alex Ng on 23/10/2016.
 */
public class Util {

    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }

}
