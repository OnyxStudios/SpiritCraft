package dev.onyxstudios.spiritcraft.api.aspects;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Matrix4f;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Environment(EnvType.CLIENT)
public class AspectRenderHelper {

    public static void renderAspectTooltip(MatrixStack matrix, Aspect aspect, int x, int y) {
        MinecraftClient client = MinecraftClient.getInstance();
        TranslatableText name = new TranslatableText(Util.createTranslationKey("aspect", aspect.getId()));
        int i = client.textRenderer.getWidth(name.asOrderedText());

        int k = x + 12;
        int l = y - 12;
        int n = 8;

        if (k + i > client.currentScreen.width) {
            k -= 22 + i;
        }

        if (l + n + 6 > client.currentScreen.height) {
            l = client.currentScreen.height - n - 6;
        }

        matrix.push();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(7, VertexFormats.POSITION_COLOR);
        Matrix4f matrix4f = matrix.peek().getModel();
        fillGradient(matrix4f, bufferBuilder, k - 3, l - 4, k + i + 3, l - 3, 400, -267386864, -267386864);
        fillGradient(matrix4f, bufferBuilder, k - 3, l + n + 3, k + i + 3, l + n + 4, 400, -267386864, -267386864);
        fillGradient(matrix4f, bufferBuilder, k - 3, l - 3, k + i + 3, l + n + 3, 400, -267386864, -267386864);
        fillGradient(matrix4f, bufferBuilder, k - 4, l - 3, k - 3, l + n + 3, 400, -267386864, -267386864);
        fillGradient(matrix4f, bufferBuilder, k + i + 3, l - 3, k + i + 4, l + n + 3, 400, -267386864, -267386864);
        fillGradient(matrix4f, bufferBuilder, k - 3, l - 3 + 1, k - 3 + 1, l + n + 3 - 1, 400, 1347420415, 1344798847);
        fillGradient(matrix4f, bufferBuilder, k + i + 2, l - 3 + 1, k + i + 3, l + n + 3 - 1, 400, 1347420415, 1344798847);
        fillGradient(matrix4f, bufferBuilder, k - 3, l - 3, k + i + 3, l - 3 + 1, 400, 1347420415, 1347420415);
        fillGradient(matrix4f, bufferBuilder, k - 3, l + n + 2, k + i + 3, l + n + 3, 400, 1344798847, 1344798847);
        RenderSystem.enableDepthTest();
        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.shadeModel(7425);
        bufferBuilder.end();
        BufferRenderer.draw(bufferBuilder);
        RenderSystem.shadeModel(7424);
        RenderSystem.disableBlend();
        RenderSystem.enableTexture();
        VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate(Tessellator.getInstance().getBuffer());
        matrix.translate(0.0D, 0.0D, 400.0D);
        client.textRenderer.draw(name.asOrderedText(), k, l, -1, true, matrix4f, immediate, false, 0, 15728880);


        immediate.draw();
        matrix.pop();
    }

    //Copied from DrawableHelper bcs PROTECTED
    public static void fillGradient(Matrix4f matrix, BufferBuilder bufferBuilder, int xStart, int yStart, int xEnd, int yEnd, int z, int colorStart, int colorEnd) {
        float f = (float)(colorStart >> 24 & 255) / 255.0F;
        float g = (float)(colorStart >> 16 & 255) / 255.0F;
        float h = (float)(colorStart >> 8 & 255) / 255.0F;
        float i = (float)(colorStart & 255) / 255.0F;
        float j = (float)(colorEnd >> 24 & 255) / 255.0F;
        float k = (float)(colorEnd >> 16 & 255) / 255.0F;
        float l = (float)(colorEnd >> 8 & 255) / 255.0F;
        float m = (float)(colorEnd & 255) / 255.0F;
        bufferBuilder.vertex(matrix, (float)xEnd, (float)yStart, (float)z).color(g, h, i, f).next();
        bufferBuilder.vertex(matrix, (float)xStart, (float)yStart, (float)z).color(g, h, i, f).next();
        bufferBuilder.vertex(matrix, (float)xStart, (float)yEnd, (float)z).color(k, l, m, j).next();
        bufferBuilder.vertex(matrix, (float)xEnd, (float)yEnd, (float)z).color(k, l, m, j).next();
    }

    public static void renderGuiAspectModel(Aspect aspect, int x, int y) {
        RenderSystem.pushMatrix();
        MinecraftClient.getInstance().getTextureManager().bindTexture(SpriteAtlasTexture.BLOCK_ATLAS_TEX);
        MinecraftClient.getInstance().getTextureManager().getTexture(SpriteAtlasTexture.BLOCK_ATLAS_TEX).setFilter(false, false);
        RenderSystem.enableRescaleNormal();
        RenderSystem.enableAlphaTest();
        RenderSystem.defaultAlphaFunc();
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.translatef(x, y, 100.0F);
        RenderSystem.translatef(8.0F, 8.0F, 0.0F);
        RenderSystem.scalef(1.0F, -1.0F, 1.0F);
        RenderSystem.scalef(16.0F, 16.0F, 16.0F);
        MatrixStack matrixStack = new MatrixStack();
        VertexConsumerProvider.Immediate immediate = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();

        BakedModel itemModel = MinecraftClient.getInstance().getItemRenderer().getModels().getModelManager().getModel(new ModelIdentifier(new Identifier(aspect.getId().getNamespace(), "aspect/" + aspect.getId().getPath()), ""));
        DiffuseLighting.disableGuiDepthLighting();
        renderAspect(aspect, ModelTransformation.Mode.GUI, false, matrixStack, immediate, 15728880, OverlayTexture.DEFAULT_UV, itemModel);

        immediate.draw();
        RenderSystem.enableDepthTest();
        DiffuseLighting.enableGuiDepthLighting();
        RenderSystem.disableAlphaTest();
        RenderSystem.disableRescaleNormal();
        RenderSystem.popMatrix();
    }

    public static void renderAspect(Aspect aspect, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model) {
        matrices.push();
        model.getTransformation().getTransformation(renderMode).apply(leftHanded, matrices);
        matrices.translate(-0.5D, -0.5D, -0.5D);
        RenderLayer renderLayer = TexturedRenderLayers.getEntityTranslucentCull();
        VertexConsumer vertexConsumer4 = ItemRenderer.getDirectGlintVertexConsumer(vertexConsumers, renderLayer, true, false);
        Random random = new Random();
        Direction[] var10 = Direction.values();
        int var11 = var10.length;

        for(int var12 = 0; var12 < var11; ++var12) {
            Direction direction = var10[var12];
            random.setSeed(42L);
            renderAspectQuads(aspect, matrices, vertexConsumer4, model.getQuads((BlockState)null, direction, random), light, overlay);
        }

        random.setSeed(42L);
        renderAspectQuads(aspect, matrices, vertexConsumer4, model.getQuads((BlockState)null, (Direction)null, random), light, overlay);

        matrices.pop();
    }

    private static void renderAspectQuads(Aspect aspect, MatrixStack matrices, VertexConsumer vertices, List<BakedQuad> quads, int light, int overlay) {
        MatrixStack.Entry entry = matrices.peek();
        Iterator var9 = quads.iterator();

        while(var9.hasNext()) {
            BakedQuad bakedQuad = (BakedQuad)var9.next();
            int i = aspect.getColor();
            float f = (float)(i >> 16 & 255) / 255.0F;
            float g = (float)(i >> 8 & 255) / 255.0F;
            float h = (float)(i & 255) / 255.0F;
            vertices.quad(entry, bakedQuad, f, g, h, light, overlay);
        }
    }
}
