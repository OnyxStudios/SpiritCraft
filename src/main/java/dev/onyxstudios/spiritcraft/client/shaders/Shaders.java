package dev.onyxstudios.spiritcraft.client.shaders;

public class Shaders {

    public static CrystalShader CRYSTAL_SHADER = new CrystalShader();
    public static ShovelPreviewShader SHOVEL_SHADER = new ShovelPreviewShader();

    public static void init() {
        CRYSTAL_SHADER.init();
        SHOVEL_SHADER.init();
    }
}
