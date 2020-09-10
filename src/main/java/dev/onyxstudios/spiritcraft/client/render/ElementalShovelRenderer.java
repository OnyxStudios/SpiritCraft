package dev.onyxstudios.spiritcraft.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.client.ClientTickHandler;
import dev.onyxstudios.spiritcraft.client.shaders.Shaders;
import dev.onyxstudios.spiritcraft.items.tools.ElementalShovel;
import dev.onyxstudios.spiritcraft.registry.ModBlocks;
import dev.onyxstudios.spiritcraft.registry.ModItems;
import dev.onyxstudios.spiritcraft.utils.BlockFacings;
import dev.onyxstudios.spiritcraft.utils.BlockUtils;
import dev.onyxstudios.spiritcraft.utils.PropertyBlockFacings;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.BlockModels;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ElementalShovelRenderer {

    public static final Random random = new Random();

    public static void render(World world, PlayerEntity player, MatrixStack stack, VertexConsumerProvider vertexConsumer, Camera camera) {
        if(player.getMainHandStack().isItemEqual(new ItemStack(ModItems.ELEMENTAL_SHOVEL)) && player.isSneaking()) {
            if(MinecraftClient.getInstance().crosshairTarget instanceof BlockHitResult) {
                BlockHitResult target = (BlockHitResult) MinecraftClient.getInstance().crosshairTarget;
                BlockState source = world.getBlockState(target.getBlockPos());
                if(!ElementalShovel.EFFECTIVE_BLOCKS.contains(source.getBlock())) return;
                List<BlockPos> placePos = new ArrayList<>();
                VertexConsumer buffer = vertexConsumer.getBuffer(getInstance());
                for (BlockPos pos : BlockUtils.get3x3Area(target.getSide(), target.getBlockPos().offset(target.getSide()))) {
                    if(world.isAir(pos)) placePos.add(pos);
                }

                for (BlockPos pos : placePos) {
                    BlockFacings facings = ModBlocks.WARDED_GLASS.handleBlockFacingClient(pos, placePos);
                    BlockState state = ModBlocks.WARDED_GLASS.getDefaultState().with(PropertyBlockFacings.FACINGS, facings.toProperty());
                    ModelIdentifier modelId = new ModelIdentifier(new Identifier(SpiritCraft.MODID, "warded_glass"), BlockModels.getModelId(state).getVariant());
                    BakedModel model = MinecraftClient.getInstance().getBakedModelManager().getBlockModels().getModelManager().getModel(modelId);
                    renderGhostBlock(world, stack, buffer, source, model, pos, camera);
                }
            }
        }
    }

    public static void renderGhostBlock(World world, MatrixStack stack, VertexConsumer buffer, BlockState state, BakedModel model, BlockPos pos, Camera camera) {
        stack.push();
        stack.translate(pos.getX() - camera.getPos().x, pos.getY() - camera.getPos().y, pos.getZ() - camera.getPos().z);
        MinecraftClient.getInstance().getBlockRenderManager().getModelRenderer().renderSmooth(world, model, state, pos, stack, buffer, true, random, 42L, 0);
        stack.pop();
    }

    public static RenderLayer getInstance() {
        return RenderLayer.of(
                "elemental_shovel_render_layer",
                VertexFormats.POSITION_TEXTURE,
                GL11.GL_QUADS,
                256,
                true,
                true,
                RenderLayer.MultiPhaseParameters.builder().shadeModel(new RenderPhase.ShadeModel(false)).texture(new RenderPhase.Texture(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, false, false)).transparency(new RenderPhase.Transparency("shovel_transparency", () -> {
                    RenderSystem.enableBlend();
                    RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.DST_ALPHA);
                    RenderSystem.enableDepthTest();
                    Shaders.SHOVEL_SHADER.start();
                    Shaders.SHOVEL_SHADER.loadTexture(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE);
                    Shaders.SHOVEL_SHADER.loadTime(ClientTickHandler.ticksInGame);
                }, () -> {
                    Shaders.SHOVEL_SHADER.stop();
                    RenderSystem.disableDepthTest();
                    RenderSystem.disableBlend();
                })).build(false)
        );
    }
}
