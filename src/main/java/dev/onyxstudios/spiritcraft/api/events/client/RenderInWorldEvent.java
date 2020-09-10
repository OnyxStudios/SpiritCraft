package dev.onyxstudios.spiritcraft.api.events.client;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public interface RenderInWorldEvent {

    public static final Event<RenderInWorldEvent> EVENT = EventFactory.createArrayBacked(RenderInWorldEvent.class, listeners -> (world, player, stack, vertexConsumer, camera) -> {
        for (RenderInWorldEvent event : listeners) {
            event.renderInWorld(world, player, stack, vertexConsumer, camera);
        }
    });

    void renderInWorld(World world, PlayerEntity player, MatrixStack stack, VertexConsumerProvider vertexConsumer, Camera camera);
}
