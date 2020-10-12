package dev.onyxstudios.spiritcraft.client.render;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.api.aspects.AspectStack;
import dev.onyxstudios.spiritcraft.api.components.spirit.ISpiritComponent;
import dev.onyxstudios.spiritcraft.api.components.spirit.SpiritComponent;
import dev.onyxstudios.spiritcraft.blockentity.BlockEntityNode;
import dev.onyxstudios.spiritcraft.utils.ColorUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class NodeRenderer extends BlockEntityRenderer<BlockEntityNode> {

    public static Identifier NODES = new Identifier(SpiritCraft.MODID, "textures/block/node/nodes.png");
    public static float IMAGE_SIZE = 2048.0f;
    public static int SPRITE_SIZE = 64;

    public NodeRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(BlockEntityNode entity, float tickDelta, MatrixStack matrix, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ISpiritComponent component = SpiritComponent.SPIRIT.getNullable(entity);

        float frame = (1.0f / IMAGE_SIZE) * (SPRITE_SIZE * entity.age);
        int skyLight = light >>> 8 & 0xF;
        float brightness = 1.0f - (skyLight / 15.0f);
        if(component != null) {
            float scale;
            int aspectCount = 0;
            for (AspectStack stack : component.getAspects()) {
                scale = MathHelper.sin(MinecraftClient.getInstance().player.age / (16.0f - aspectCount)) * 0.25f + 0.25f * 2;
                scale = 0.25f + scale * stack.getCount() / component.getCapacity();
                this.renderNodeLayer(matrix, vertexConsumers, stack.getAspect().getColor(), scale, frame, brightness, 0);
                aspectCount++;
            }

            if(component.getAspects().size() <= 1) {
                this.renderNodeLayer(matrix, vertexConsumers, 0x6e6e6e, 0.25f, frame, brightness, 1);
            }
        }
    }

    private void renderNodeLayer(MatrixStack matrix, VertexConsumerProvider vertexConsumers, int color, float scale, float frame, float brightness, int row) {
        Vec3d col = ColorUtils.hexToRgb(color).multiply(brightness);
        float minU = frame - (1.0f / IMAGE_SIZE) * SPRITE_SIZE;
        float minV = ((1.0f / IMAGE_SIZE) * SPRITE_SIZE) * row;
        float maxV = minV + (1.0f / IMAGE_SIZE) * SPRITE_SIZE;
        matrix.push();
        matrix.translate(0.5f, 0.5f, 0.5f);
        matrix.multiply(this.dispatcher.camera.getRotation());
        matrix.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180));
        matrix.scale(scale, scale, 1);
        VertexConsumer buffer = vertexConsumers.getBuffer(getInstance(NODES));
        buffer.vertex(matrix.peek().getModel(), -0.5f, -0.5f, 0).color((float) col.getX(), (float) col.getY(), (float) col.getZ(), brightness).texture(minU, minV).next();
        buffer.vertex(matrix.peek().getModel(), 0.5f, -0.5f, 0).color((float) col.getX(), (float) col.getY(), (float) col.getZ(), brightness).texture(minU, maxV).next();
        buffer.vertex(matrix.peek().getModel(), 0.5f, 0.5f, 0).color((float) col.getX(), (float) col.getY(), (float) col.getZ(), brightness).texture(frame, maxV).next();
        buffer.vertex(matrix.peek().getModel(), -0.5f, 0.5f, 0).color((float) col.getX(), (float) col.getY(), (float) col.getZ(), brightness).texture(frame, minV).next();
        matrix.pop();
    }

    public static RenderLayer getInstance(Identifier texture) {
        return RenderLayer.of(
                "node_render_layer" + texture.toString(),
                VertexFormats.POSITION_COLOR_TEXTURE,
                GL11.GL_QUADS,
                256,
                true,
                true,
                RenderLayer.MultiPhaseParameters
                        .builder()
                        .cull(new RenderPhase.Cull(false))
                        .depthTest(new RenderPhase.DepthTest("always", 519))
                        .alpha(new RenderPhase.Alpha(0.003921569F))
                        .shadeModel(new RenderPhase.ShadeModel(false))
                        .texture(new RenderPhase.Texture(texture, false, false))
                        .transparency(new RenderPhase.Transparency("node_transparency", () -> {
                            GL11.glEnable(GL11.GL_BLEND);
                            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
                        }, () -> GL11.glDisable(GL11.GL_BLEND))).build(false));
    }
}
