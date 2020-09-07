package dev.onyxstudios.spiritcraft.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class ClientTickHandler {

    public static int ticksInGame;

    public static void registerClientTick() {
        ClientTickEvents.START_CLIENT_TICK.register(minecraftClient -> ticksInGame++);
    }
}
