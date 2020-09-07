package dev.onyxstudios.spiritcraft.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.blockentity.BlockEntityCrystal;
import dev.onyxstudios.spiritcraft.client.ClientTickHandler;
import dev.onyxstudios.spiritcraft.client.shaders.Shaders;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class CrystalRenderer extends BlockEntityRenderer<BlockEntityCrystal> {

    public static final ModelIdentifier CRYSTAL_MODEL = new ModelIdentifier(new Identifier(SpiritCraft.MODID, "base_crystal"), "");
    public static final Random random = new Random();

    public CrystalRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(BlockEntityCrystal entity, float tickDelta, MatrixStack stack, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        Direction facing = entity.getCachedState().get(Properties.FACING);
        BakedModel model = MinecraftClient.getInstance().getBakedModelManager().getBlockModels().getModelManager().getModel(CRYSTAL_MODEL);
        int color = ColorProviderRegistry.BLOCK.get(entity.getCachedState().getBlock()).getColor(entity.getCachedState(), entity.getWorld(), entity.getPos(), 0);
        VertexConsumer buffer = vertexConsumers.getBuffer(getInstance(color));

        stack.push();
        renderFacing(stack, facing);
        MinecraftClient.getInstance().getBlockRenderManager().getModelRenderer().renderSmooth(entity.getWorld(), model, entity.getCachedState(), entity.getPos(), stack, buffer, true, random, 42L, 0);
        stack.pop();

        for (BlockState state : entity.states) {
            stack.push();
            renderFacing(stack, state.get(Properties.FACING));
            MinecraftClient.getInstance().getBlockRenderManager().getModelRenderer().renderSmooth(entity.getWorld(), model, state, entity.getPos(), stack, buffer, true, random, 42L, 0);
            stack.pop();
        }
    }

    public void renderFacing(MatrixStack stack, Direction direction) {
        switch (direction) {
            case DOWN:
                stack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(180));
                stack.translate(0, -1, -1);
                break;
            case NORTH:
                stack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(-90));
                stack.translate(0, -1, 0);
                break;
            case EAST:
                stack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(-90));
                stack.translate(-1, 0, 0);
                break;
            case SOUTH:
                stack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(90));
                stack.translate(0, 0, -1);
                break;
            case WEST:
                stack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(90));
                stack.translate(0, -1, 0);
                break;
            default:
                break;
        }
    }

    public static RenderLayer getInstance(int color) {
        return RenderLayer.of(
                "crystal_render_layer" + color,
                VertexFormats.POSITION_TEXTURE,
                GL11.GL_QUADS,
                256,
                true,
                true,
                RenderLayer.MultiPhaseParameters.builder().shadeModel(new RenderPhase.ShadeModel(false)).texture(new RenderPhase.Texture(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, false, false)).transparency(new RenderPhase.Transparency("crystal_transparency", () -> {
                    RenderSystem.enableBlend();
                    RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.DST_ALPHA);
                    RenderSystem.enableDepthTest();
                    Shaders.CRYSTAL_SHADER.start();
                    Shaders.CRYSTAL_SHADER.loadTexture(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE);
                    Shaders.CRYSTAL_SHADER.loadTime(ClientTickHandler.ticksInGame);
                    Shaders.CRYSTAL_SHADER.loadColor(color);
                }, () -> {
                    Shaders.CRYSTAL_SHADER.stop();
                    RenderSystem.disableDepthTest();
                    RenderSystem.disableBlend();
                })).build(false));
    }
}
