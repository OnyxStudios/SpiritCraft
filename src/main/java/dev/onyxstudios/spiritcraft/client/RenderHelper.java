package dev.onyxstudios.spiritcraft.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.MathHelper;

public class RenderHelper {

    public static void renderScannerInHands(MatrixStack matrix, VertexConsumerProvider vertexConsumers, int light, float pitch, float equipProgress, float swingProgress, ItemStack stack) {
        float swing = MathHelper.sqrt(swingProgress);
        float g = -0.2F * MathHelper.sin(swingProgress * 3.1415927F);
        float h = -0.4F * MathHelper.sin(swing * 3.1415927F);
        matrix.translate(0.0D, -g / 2.0F, h);
        if (!MinecraftClient.getInstance().player.isInvisible()) {
            matrix.push();
            matrix.translate(0.0D, 0.04F + equipProgress * -1.2F, -0.7200000286102295D);
            matrix.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(90.0F));
            renderArm(matrix, vertexConsumers, light, Arm.RIGHT);
            renderArm(matrix, vertexConsumers, light, Arm.LEFT);
            matrix.pop();
        }

        //matrix.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(90));
        matrix.scale(1.2f, 1.2f, 1.2f);
        matrix.translate(-0.115, -0.08, -0.35);
        matrix.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(90));
        matrix.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-90));
        MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.FIXED, light, OverlayTexture.DEFAULT_UV, matrix, vertexConsumers);
    }

    //Copied from HeldItemRenderer
    private static void renderArm(MatrixStack matrix, VertexConsumerProvider vertexConsumers, int light, Arm arm) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.getTextureManager().bindTexture(client.player.getSkinTexture());
        PlayerEntityRenderer playerEntityRenderer = (PlayerEntityRenderer)client.getEntityRenderDispatcher().getRenderer(client.player);
        matrix.push();
        float f = arm == Arm.RIGHT ? 1.0F : -1.0F;
        matrix.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(92.0F));
        matrix.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(45.0F));
        matrix.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(f * -41.0F));
        matrix.translate(f * 0.3F, -1.100000023841858D, 0.44999998807907104D);
        if (arm == Arm.RIGHT) {
            playerEntityRenderer.renderRightArm(matrix, vertexConsumers, light, client.player);
        } else {
            playerEntityRenderer.renderLeftArm(matrix, vertexConsumers, light, client.player);
        }

        matrix.pop();
    }
}
