package dev.onyxstudios.spiritcraft.registry;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.api.components.research.IResearchComponent;
import dev.onyxstudios.spiritcraft.api.components.research.ResearchComponent;
import dev.onyxstudios.spiritcraft.api.nodes.INode;
import dev.onyxstudios.spiritcraft.items.tools.ElementalShovel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
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
    public static Identifier PACKET_SCAN_BLOCK = new Identifier(SpiritCraft.MODID, "scan_block");
    public static Identifier PACKET_SCAN_ENTITY = new Identifier(SpiritCraft.MODID, "scan_entity");

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

        ServerSidePacketRegistry.INSTANCE.register(PACKET_SCAN_BLOCK, (context, buf) -> {
            PlayerEntity player = context.getPlayer();
            BlockPos pos = buf.readBlockPos();

            if(player != null) {
                context.getTaskQueue().execute(() -> {
                    IResearchComponent component = ResearchComponent.RESEARCH.get(player);
                    BlockState state = player.world.getBlockState(pos);
                    BlockEntity blockEntity = player.world.getBlockEntity(pos);
                    if(blockEntity instanceof INode) {

                        component.scanObject(player.world, pos);
                    }else {
                        component.scanObject(state.getBlock());
                    }
                    ResearchComponent.RESEARCH.sync(player);
                });
            }
        });

        ServerSidePacketRegistry.INSTANCE.register(PACKET_SCAN_ENTITY, (context, buf) -> {
            PlayerEntity player = context.getPlayer();

            if(player != null) {
                IResearchComponent component = ResearchComponent.RESEARCH.get(player);
                Entity entity = player.world.getEntityById(buf.readInt());
                component.scanObject(entity);
                ResearchComponent.RESEARCH.sync(player);
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
