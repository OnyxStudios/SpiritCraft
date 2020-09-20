package dev.onyxstudios.spiritcraft.mixins.client;

import dev.onyxstudios.spiritcraft.api.aspects.AspectMap;
import dev.onyxstudios.spiritcraft.api.aspects.AspectRenderHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.OrderedText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Screen.class)
public abstract class ScreenMixin {

    private ItemStack hoveredStack = ItemStack.EMPTY;

    @Inject(method = "renderTooltip(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/item/ItemStack;II)V", at = @At("HEAD"))
    public void renderTooltip(MatrixStack matrices, ItemStack stack, int x, int y, CallbackInfo ci) {
        this.hoveredStack = stack;
    }

    @Inject(method = "renderOrderedTooltip", at = @At("HEAD"), cancellable = true)
    public void renderOrderedTooltip(MatrixStack matrices, List<? extends OrderedText> lines, int x, int y, CallbackInfo ci) {
        if(!hoveredStack.isEmpty() && Screen.hasShiftDown() && AspectMap.getAspects(hoveredStack.getItem()).length > 0) {
            AspectRenderHelper.renderAspectsInTooltip(matrices, hoveredStack, lines, x, y);
            ci.cancel();
        }
    }
}
