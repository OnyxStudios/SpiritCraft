package dev.onyxstudios.spiritcraft.registry;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class ModPackets {

    public static Identifier PACKET_SPAWN_BUBBLE = new Identifier(SpiritCraft.MODID, "packet_spawn_bubble");

    public static void registerServer() {
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
    }
}
