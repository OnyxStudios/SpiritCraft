package dev.onyxstudios.spiritcraft.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.api.aspects.AspectStack;
import dev.onyxstudios.spiritcraft.api.components.spirit.ISpiritComponent;
import dev.onyxstudios.spiritcraft.api.components.spirit.SpiritComponent;
import dev.onyxstudios.spiritcraft.api.items.IWand;
import dev.onyxstudios.spiritcraft.utils.ColorUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

@Environment(EnvType.CLIENT)
public class WandHudRenderer {

    public static final Identifier WAND_HUD = new Identifier(SpiritCraft.MODID, "textures/gui/wand_hud.png");
    public static int textureSize = 128;
    public static int radius = 72;

    public static void renderDisplayHud(MatrixStack matrix, float partialTicks) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        ItemStack stack = player.getMainHandStack();

        if(stack.getItem() instanceof IWand) {
            matrix.push();
            ISpiritComponent component = SpiritComponent.SPIRIT.get(stack);

            matrix.push();
            matrix.scale(0.4f, 0.4f, 1);
            MinecraftClient.getInstance().getTextureManager().bindTexture(WAND_HUD);
            RenderSystem.enableBlend();
            RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.DST_ALPHA);
            DrawableHelper.drawTexture(matrix, 2, 2, 1, 1, 73, 73, textureSize, textureSize);
            RenderSystem.disableBlend();
            matrix.pop();

            for (int i = 0; i < 6; i++) {
                AspectStack aspectStack = (AspectStack) component.getAspects().toArray()[i];

                matrix.push();
                MinecraftClient.getInstance().getTextureManager().bindTexture(WAND_HUD);
                matrix.translate(12, 12, 0);
                matrix.multiply(Vector3f.NEGATIVE_Z.getDegreesQuaternion(-5 + i * 18));
                matrix.translate(0, 20, 0);
                matrix.scale(0.6f, 0.5f, 1);
                RenderSystem.enableBlend();
                RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.DST_ALPHA);
                DrawableHelper.drawTexture(matrix, 0, 0, 80, 3, 10, 43, textureSize, textureSize);
                RenderSystem.disableBlend();
                matrix.pop();

                matrix.push();
                MinecraftClient.getInstance().getTextureManager().bindTexture(WAND_HUD);
                matrix.translate(12, 12, 0);
                matrix.multiply(Vector3f.NEGATIVE_Z.getDegreesQuaternion(-5 + i * 18));
                matrix.translate(0, 20, 0);
                matrix.scale(0.6f, 0.5f, 1);
                Vec3d color = ColorUtils.hexToRgb(aspectStack.getAspect().getColor());
                RenderSystem.color4f((float) color.x, (float) color.y, (float) color.z, 0.8f);
                DrawableHelper.drawTexture(matrix, 0, 4, 96, 7, 10, (int) (aspectStack.getCount() / component.getCapacity() * 36), textureSize, textureSize);
                RenderSystem.color4f(1, 1, 1, 1);
                matrix.pop();
            }
            matrix.pop();
        }
    }
}
