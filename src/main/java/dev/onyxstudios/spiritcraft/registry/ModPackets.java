package dev.onyxstudios.spiritcraft.registry;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.items.tools.ElementalShovel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.Random;

public class ModPackets {

    public static Identifier PACKET_SPAWN_BUBBLE = new Identifier(SpiritCraft.MODID, "packet_spawn_bubble");
    public static Identifier PACKET_SPAWN_FADE = new Identifier(SpiritCraft.MODID, "packet_spawn_fade");
    public static Identifier PACKET_ROTATE_SHOVEL = new Identifier(SpiritCraft.MODID, "packet_rotate_shovel");

    public static void registerServer() {
        ServerSidePacketRegistry.INSTANCE.register(PACKET_ROTATE_SHOVEL,  (context, buf) -> {
            if(context.getPlayer() != null) {
                ItemStack stack = context.getPlayer().getMainHandStack();
                CompoundTag tag = stack.getOrCreateTag();

                if(tag.contains(ElementalShovel.ROTATION)) {
                    tag.putString(ElementalShovel.ROTATION, tag.getString(ElementalShovel.ROTATION).equals(ElementalShovel.ROTATION_FLOOR) ? ElementalShovel.ROTATION_WALL : ElementalShovel.ROTATION_FLOOR);
                }else {
                    tag.putString(ElementalShovel.ROTATION, ElementalShovel.ROTATION_WALL);
                }
            }
        });
    }

    @Environment(EnvType.CLIENT)
    public static void registerClient() {
        ClientSidePacketRegistry.INSTANCE.register(PACKET_SPAWN_BUBBLE, (context, buf) -> {
            Random random = new Random();
            BlockPos particlePos = buf.readBlockPos();
            int particleAmount = buf.readInt();

            if(context.getPlayer() != null && context.getPlayer().world != null) {
                for (int i = 0; i < particleAmount; i++)
                    context.getPlayer().world.addParticle(ModRenders.MAGIC_BUBBLE_TYPE, false, particlePos.getX() + random.nextDouble(), particlePos.getY() + random.nextDouble(), particlePos.getZ() + random.nextDouble(), 0, 0, 0);
            }
        });

        ClientSidePacketRegistry.INSTANCE.register(PACKET_SPAWN_FADE, (context, buf) -> {
            Random random = new Random();
            BlockPos particlePos = buf.readBlockPos();
            int particleAmount = buf.readInt();
            int dirIndex = buf.readInt();
            if(dirIndex >= 0) particlePos = particlePos.offset(Direction.values()[dirIndex]);

            if(context.getPlayer() != null && context.getPlayer().world != null) {
                for (int i = 0; i < particleAmount; i++)
                    context.getPlayer().world.addParticle(ModRenders.MAGIC_FADE_TYPE, false, particlePos.getX() + random.nextDouble(), particlePos.getY() + random.nextDouble(), particlePos.getZ() + random.nextDouble(), 0, 0, 0);
            }
        });
    }
}
