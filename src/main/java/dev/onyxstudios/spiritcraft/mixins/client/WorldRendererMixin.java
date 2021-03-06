package dev.onyxstudios.spiritcraft.mixins.client;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.api.events.client.RenderInWorldEvent;
import dev.onyxstudios.spiritcraft.registry.ModRenders;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {

    @Shadow
    public ClientWorld world;

    @Shadow
    @Final
    private BufferBuilderStorage bufferBuilders;

    @Inject(method = "render", at = @At(target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", value = "INVOKE_STRING", args = "ldc=entities", shift = At.Shift.BEFORE))
    public void render(MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f matrix4f, CallbackInfo ci) {
        this.world.getProfiler().swap(SpiritCraft.MODID + ":renderWorldLast");
        RenderInWorldEvent.EVENT.invoker().renderInWorld(world, MinecraftClient.getInstance().player, matrices, bufferBuilders.getEffectVertexConsumers(), camera);
        ModRenders.renderSchedules(matrices, bufferBuilders.getEffectVertexConsumers());
    }
}
