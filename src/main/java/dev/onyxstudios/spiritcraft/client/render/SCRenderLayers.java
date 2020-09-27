package dev.onyxstudios.spiritcraft.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.onyxstudios.spiritcraft.SpiritCraft;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.GL11;

public class SCRenderLayers {

    public static Identifier ELEMENTAL_SCAN_ICON = new Identifier(SpiritCraft.MODID, "textures/particle/scan_icon.png");

    public static RenderLayer getElementalInstance() {
        return RenderLayer.of(
                "elemental_scan_layer",
                VertexFormats.POSITION_TEXTURE,
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
                        .texture(new RenderPhase.Texture(ELEMENTAL_SCAN_ICON, false, false))
                        .transparency(new RenderPhase.Transparency("elemental_transparency", () -> {
                            GL11.glEnable(GL11.GL_BLEND);
                            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
                            RenderSystem.color3f(0.9f, 0.05f, 0.08f);
                        }, () -> GL11.glDisable(GL11.GL_BLEND))).build(false));
    }
}
