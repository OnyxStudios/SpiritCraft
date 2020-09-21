package dev.onyxstudios.spiritcraft.mixins.client;

import dev.onyxstudios.spiritcraft.client.RenderHelper;
import dev.onyxstudios.spiritcraft.registry.ModItems;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {

    @Inject(method = "renderFirstPersonItem", at = @At(target = "Lnet/minecraft/client/util/math/MatrixStack;push()V", shift = At.Shift.AFTER, value = "INVOKE"), cancellable = true)
    public void renderFirstPersonItem(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (!item.isEmpty() && item.getItem() == ModItems.SCANNER && hand == Hand.MAIN_HAND && player.getOffHandStack().isEmpty()) {
            RenderHelper.renderScannerInHands(matrices, vertexConsumers, light, pitch, equipProgress, swingProgress, item);
            matrices.pop();
            ci.cancel();
        }
    }
}
