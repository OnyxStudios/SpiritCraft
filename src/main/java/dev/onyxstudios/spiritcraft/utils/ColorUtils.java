package dev.onyxstudios.spiritcraft.utils;

import net.minecraft.util.math.Vec3d;

public class ColorUtils {

    public static Vec3d hexToRgb(int hex) {
        double r = (hex & 0xFF0000) >> 16;
        double g = (hex & 0xFF00) >> 8;
        double b = (hex & 0xFF);

        return new Vec3d(r / 255.0, g / 255.0, b / 255.0);
    }
}
