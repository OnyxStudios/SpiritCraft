package dev.onyxstudios.spiritcraft.client.shaders;

import com.mojang.blaze3d.platform.GlStateManager;
import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.utils.ColorUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL20;

public class CrystalShader extends ShaderProgram {

    protected int time;
    protected int crystalColor;
    protected int texture;
    public CrystalShader() {
        super(null, new Identifier(SpiritCraft.MODID, "shaders/crystals_shader.fs"));
    }

    @Override
    public void getAllUniformLocations() {
        texture = super.getUniformLocation("textureSampler");
        time = super.getUniformLocation("time");
        crystalColor = super.getUniformLocation("crystalColor");
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

    public void loadColor(Vec3d color) {
        super.loadVector(crystalColor, color);
    }

    public void loadColor(int color) {
        super.loadVector(crystalColor, ColorUtils.hexToRgb(color));
    }
}
