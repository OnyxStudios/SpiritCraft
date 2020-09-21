package dev.onyxstudios.spiritcraft.api.events.client;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.util.math.MatrixStack;

public interface RenderHudEvent {

    public static Event<RenderHudEvent> EVENT = EventFactory.createArrayBacked(RenderHudEvent.class, listeners -> (matrix, partialTicks) -> {
        for (RenderHudEvent event : listeners) {
            event.renderHud(matrix, partialTicks);
        }
    });

    void renderHud(MatrixStack matrix, float partialTicks);
}
