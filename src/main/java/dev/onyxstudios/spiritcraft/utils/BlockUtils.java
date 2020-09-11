package dev.onyxstudios.spiritcraft.utils;

import dev.onyxstudios.spiritcraft.items.tools.ElementalShovel;
import dev.onyxstudios.spiritcraft.registry.ModPackets;
import io.netty.buffer.Unpooled;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BlockUtils {

    public static int place3x3(World world, BlockPos pos, BlockState source, PlayerEntity player, Direction direction) {
        int availableBlocks = player.isCreative() ? Integer.MAX_VALUE : InventoryUtils.getStackCount(player, new ItemStack(source.getBlock()));
        int placedBlocks = 0;

        if(availableBlocks > 0) {
            String rotation = player.getMainHandStack().getOrCreateTag().getString(ElementalShovel.ROTATION);
            if(rotation.isEmpty()) rotation = ElementalShovel.ROTATION_FLOOR;
            Direction rotationDir = rotation == ElementalShovel.ROTATION_FLOOR ? direction : direction.equals(Direction.UP) || direction.equals(Direction.DOWN) ? player.getHorizontalFacing() : direction;
            List<BlockPos> placeArea = get3x3Area(rotationDir, pos.offset(direction));
            for (BlockPos placePos : placeArea) {
                if(!world.isAir(placePos) || placedBlocks >= availableBlocks) continue;
                if(InventoryUtils.consumeOne(player, new ItemStack(source.getBlock()))) {
                    world.setBlockState(placePos, source, 2);
                    placedBlocks++;

                    if(!world.isClient && player instanceof ServerPlayerEntity) {
                        PacketByteBuf buffer = new PacketByteBuf(Unpooled.buffer());
                        buffer.writeBlockPos(placePos);
                        buffer.writeInt(3);
                        buffer.writeInt(direction.getId());
                        ((ServerPlayerEntity) player).networkHandler.sendPacket(new CustomPayloadS2CPacket(ModPackets.PACKET_SPAWN_FADE, buffer));
                    }
                }
            }
        }

        return placedBlocks;
    }

    //Return the amount of blocks broken
    public static int shovel3x3(World world, BlockPos pos, BlockState source, LivingEntity user, Direction direction) {
        int brokenBlocks = 0;
        Iterable<BlockPos> shovelArea = get3x3Area(direction, pos);
        for (BlockPos shovelPos : shovelArea) {
            if(pos.equals(shovelPos) || !world.getBlockState(shovelPos).isOf(source.getBlock())) continue;
            world.breakBlock(shovelPos, true, user);
            brokenBlocks++;

            if(!world.isClient && user instanceof ServerPlayerEntity) {
                PacketByteBuf buffer = new PacketByteBuf(Unpooled.buffer());
                buffer.writeBlockPos(shovelPos);
                buffer.writeInt(3);
                buffer.writeInt(-1);
                ((ServerPlayerEntity) user).networkHandler.sendPacket(new CustomPayloadS2CPacket(ModPackets.PACKET_SPAWN_FADE, buffer));
            }
        }

        return brokenBlocks;
    }

    public static List<BlockPos> get3x3Area(Direction direction, BlockPos pos) {
        List<BlockPos> area = new ArrayList<>();
        switch (direction) {
            case UP:
            case DOWN:
                BlockPos.iterate(pos.add(1, 0, 1), pos.add(-1, 0, -1)).forEach(pos1 -> area.add(pos1.toImmutable()));
                break;
            case NORTH:
            case SOUTH:
                BlockPos.iterate(pos.add(1, 1, 0), pos.add(-1, -1, 0)).forEach(pos1 -> area.add(pos1.toImmutable()));
                break;
            case EAST:
            case WEST:
                BlockPos.iterate(pos.add(0, 1, 1), pos.add(0, -1, -1)).forEach(pos1 -> area.add(pos1.toImmutable()));
                break;
        }

        return area;
    }

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
