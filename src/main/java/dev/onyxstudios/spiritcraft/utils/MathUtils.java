package dev.onyxstudios.spiritcraft.utils;

import net.minecraft.util.math.Vec3d;

public class MathUtils {

    public static Vec3d divide(Vec3d vec3d, double amount) {
        return divide(vec3d, amount, amount, amount);
    }

    public static Vec3d divide(Vec3d vec3d, double x, double y, double z) {
        return new Vec3d(vec3d.getX() / x, vec3d.getY() / y, vec3d.getZ() / z);
    }
}
