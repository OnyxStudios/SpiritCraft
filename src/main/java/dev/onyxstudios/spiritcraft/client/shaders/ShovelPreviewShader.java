package dev.onyxstudios.spiritcraft.client.shaders;

import com.mojang.blaze3d.platform.GlStateManager;
import dev.onyxstudios.spiritcraft.SpiritCraft;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.GL20;

public class ShovelPreviewShader extends ShaderProgram {

    protected int time;
    protected int texture;

    public ShovelPreviewShader() {
        super(null, new Identifier(SpiritCraft.MODID, "shaders/shovel_preview_shader.fs"));
    }

    @Override
    public void getAllUniformLocations() {
        texture = super.getUniformLocation("textureSampler");
        time = super.getUniformLocation("time");
    }

    public void loadTexture(Identifier textureLoc) {
        GlStateManager.enableTexture();
        GL20.glActiveTexture(GL20.GL_TEXTURE0);
        MinecraftClient.getInstance().getTextureManager().bindTexture(textureLoc);
        super.loadInt(texture, 0);
    }

    public void loadTime(float currTime) {
        super.loadFloat(time, currTime);
    }
}
