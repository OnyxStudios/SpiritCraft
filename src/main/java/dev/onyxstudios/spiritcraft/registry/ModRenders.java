package dev.onyxstudios.spiritcraft.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

@Environment(EnvType.CLIENT)
public class ModRenders {

    //For one time renders
    public static Map<BiConsumer<MatrixStack, VertexConsumerProvider>, Map.Entry<Long, Integer>> scheduledRenders = new HashMap<>();

    public static void registerRenders() {
        //Any RenderWorldLast renders
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
}
