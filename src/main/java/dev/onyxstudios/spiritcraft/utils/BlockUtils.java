package dev.onyxstudios.spiritcraft.utils;

import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;

public class BlockUtils {

    public static Box rotateBox(Box box, Direction direction) {
        Box rotatexBox;

        switch (direction) {
            case EAST:
                rotatexBox = createVoxelCoords(16 - box.minZ, box.minY, 16 - box.minX, 16 - box.maxZ, box.maxY, 16 - box.maxX);
                break;
            case SOUTH:
                rotatexBox = createVoxelCoords(16 - box.minX, box.minY, 16 - box.minZ, 16 - box.maxX, box.maxY, 16 - box.maxZ);
                break;
            case WEST:
                rotatexBox = createVoxelCoords(box.minZ, box.minY, box.minX, box.maxZ, box.maxY, box.maxX);
                break;
            case NORTH:
            default:
                rotatexBox = createVoxelCoords(box);
                break;
        }

        return rotatexBox;
    }

    public static Box createVoxelCoords(double x, double y, double z, double x1, double y1, double z1) {
        return new Box(x / 16, y / 16, z / 16, x1 / 16, y1 / 16, z1 / 16);
    }

    public static Box createVoxelCoords(Box box) {
        return new Box(box.minX / 16, box.minY / 16, box.minZ / 16, box.maxX / 16, box.maxY / 16, box.maxZ / 16);
    }
}
