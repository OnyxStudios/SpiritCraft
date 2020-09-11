package dev.onyxstudios.spiritcraft.registry;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.api.events.client.RenderInWorldEvent;
import dev.onyxstudios.spiritcraft.client.particles.MagicBubbleParticle;
import dev.onyxstudios.spiritcraft.client.particles.MagicFadeParticle;
import dev.onyxstudios.spiritcraft.client.render.ElementalShovelRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

@Environment(EnvType.CLIENT)
public class ModRenders {

    //For one time renders
    public static Map<BiConsumer<MatrixStack, VertexConsumerProvider>, Map.Entry<Long, Integer>> scheduledRenders = new HashMap<>();

    public static DefaultParticleType MAGIC_BUBBLE_TYPE = FabricParticleTypes.simple();
    public static DefaultParticleType MAGIC_FADE_TYPE = FabricParticleTypes.simple();

    public static void register() {
        registerParticle(new Identifier(SpiritCraft.MODID, "magic_bubble"), MAGIC_BUBBLE_TYPE, MagicBubbleParticle.Factory::new);
        registerParticle(new Identifier(SpiritCraft.MODID, "magic_fade"), MAGIC_FADE_TYPE, MagicFadeParticle.Factory::new);

        RenderInWorldEvent.EVENT.register((world, player, stack, vertexConsumer, camera) -> ElementalShovelRenderer.render(world, player, stack, vertexConsumer, camera));
    }

    public static void renderSchedules(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider) {
        for (BiConsumer<MatrixStack, VertexConsumerProvider> consumer : scheduledRenders.keySet()) {
            consumer.accept(matrixStack, vertexConsumerProvider);
        }

        scheduledRenders.entrySet().removeIf(entry -> TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - TimeUnit.MILLISECONDS.toSeconds(entry.getValue().getKey()) > entry.getValue().getValue());
    }

    public static void scheduleRender(BiConsumer<MatrixStack, VertexConsumerProvider> consumer, int duration) {
        scheduledRenders.put(consumer, new AbstractMap.SimpleEntry<>(System.currentTimeMillis(), duration));
    }

    private static void registerParticle(Identifier id, ParticleType particleType, ParticleFactoryRegistry.PendingParticleFactory factory) {
        Registry.register(Registry.PARTICLE_TYPE, id, particleType);
        ParticleFactoryRegistry.getInstance().register(particleType, factory);
    }
}
