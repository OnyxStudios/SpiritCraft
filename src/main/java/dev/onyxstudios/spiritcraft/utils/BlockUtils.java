package dev.onyxstudios.spiritcraft.utils;

import dev.onyxstudios.spiritcraft.registry.ModPackets;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class BlockUtils {

    public static void breakHighestBlock(World world, BlockPos pos, PlayerEntity player, int range) {
        BlockPos currentLog = pos;
        for (int y = 0; y < 255; y++) {
            for (int x = -range / 2; x < range / 2; x++) {
                for (int z = -range / 2; z < range / 2; z++) {
                    BlockPos checkPos = pos.mutableCopy().add(x, y, z);
                    if(pos.equals(checkPos)) continue;

                    if(BlockTags.LOGS.contains(world.getBlockState(checkPos).getBlock())) {
                        currentLog = checkPos;
                    }
                }
            }
        }

        world.breakBlock(currentLog, true, player);
        if(!world.isClient && !currentLog.equals(pos)) {
            PacketByteBuf buffer = new PacketByteBuf(Unpooled.buffer());
            buffer.writeBlockPos(currentLog);
            buffer.writeInt(5);
            ((ServerPlayerEntity) player).networkHandler.sendPacket(new CustomPayloadS2CPacket(ModPackets.PACKET_SPAWN_BUBBLE, buffer));
        }
    }

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
