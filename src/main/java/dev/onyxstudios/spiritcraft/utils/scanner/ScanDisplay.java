package dev.onyxstudios.spiritcraft.utils.scanner;

import dev.onyxstudios.spiritcraft.api.aspects.Aspect;
import net.minecraft.client.MinecraftClient;

public class ScanDisplay {

    public Aspect aspect;
    public float x, y;
    public long created;
    public long expired;

    public ScanDisplay(Aspect aspect, float x, float y) {
        this.aspect = aspect;
        this.x = x + MinecraftClient.getInstance().world.random.nextFloat() * 0.2f;
        this.y = y + MinecraftClient.getInstance().world.random.nextFloat() * 0.2f;
        this.created = System.currentTimeMillis() + MinecraftClient.getInstance().world.random.nextInt(1200);
        this.expired = created + 1000;
    }
}
