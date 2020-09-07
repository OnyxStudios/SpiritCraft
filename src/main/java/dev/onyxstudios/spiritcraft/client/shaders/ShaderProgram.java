package dev.onyxstudios.spiritcraft.client.shaders;

import com.mojang.blaze3d.platform.GlStateManager;
import dev.onyxstudios.spiritcraft.SpiritCraft;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.client.util.math.Vector4f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;
import java.util.stream.Collectors;

public abstract class ShaderProgram {

    private FloatBuffer matrixBuffer;
    protected Identifier vertShader;
    protected Identifier fragShader;
    protected int shaderId;

    public ShaderProgram(Identifier vertShader, Identifier fragShader) {
        this.vertShader = vertShader;
        this.fragShader = fragShader;
    }

    public void init() {
        matrixBuffer = BufferUtils.createFloatBuffer(16);
        int program = GlStateManager.createProgram();

        if(vertShader != null) {
            int vertShaderId = createShader(vertShader, GL20.GL_VERTEX_SHADER);
            GlStateManager.attachShader(program, vertShaderId);
        }

        if(fragShader != null) {
            int fragShaderId = createShader(fragShader, GL20.GL_FRAGMENT_SHADER);
            GlStateManager.attachShader(program, fragShaderId);
        }

        bindAttributes();
        GlStateManager.linkProgram(program);

        this.shaderId = program;
        this.getAllUniformLocations();
    }

    public void start() {
        GL20.glUseProgram(shaderId);
    }

    public void stop () {
        GL20.glUseProgram(0);
    }

    public abstract void getAllUniformLocations();

    public int getUniformLocation(String uniformName) {
        return GL20.glGetUniformLocation(shaderId, uniformName);
    }

    public void bindAttributes() {
    }

    protected void bindAttribute(int attribute, String variableName) {
        GL20.glBindAttribLocation(shaderId, attribute, variableName);
    }

    protected void loadFloat(int location, float value) {
        GL20.glUniform1f(location, value);
    }

    protected void loadInt(int location, int value) {
        GL20.glUniform1i(location, value);
    }

    protected void loadVector(int location, Vec3d vector) {
        GL20.glUniform3f(location, (float) vector.x, (float) vector.y, (float) vector.z);
    }

    protected void loadVector(int location, Vector3f vector) {
        GL20.glUniform3f(location, vector.getX(), vector.getY(), vector.getZ());
    }

    protected void loadVector(int location, Vector4f vector) {
        GL20.glUniform4f(location, vector.getX(), vector.getY(), vector.getZ(), vector.getW());
    }

    protected void loadMatrix(int location, Matrix4f matrix) {
        matrix.writeToBuffer(matrixBuffer);
        ARBShaderObjects.glUniformMatrix4fvARB(location, false, matrixBuffer);
    }

    private int createShader(Identifier location, int shaderType) {
        int shader = GL20.glCreateShader(shaderType);
        if(shader == 0) return 0;
        try {
            GL20.glShaderSource(shader, readFileAsString(location));
        }catch (Exception e) {
            e.printStackTrace();
        }

        GL20.glCompileShader(shader);
        if(GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            SpiritCraft.LOGGER.fatal("Could not compile shader!");
            throw new RuntimeException("Error creating shader: " + getLogInfo(shader));
        }

        return shader;
    }

    public static String getLogInfo(int obj) {
        return ARBShaderObjects.glGetInfoLogARB(obj, ARBShaderObjects.glGetObjectParameteriARB(obj, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB));
    }

    private String readFileAsString(Identifier location) {
        InputStream shaderStream = getShaderStream(location);
        String s = "";

        if(shaderStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(shaderStream, "UTF-8"))) {
                s = reader.lines().collect(Collectors.joining("\n"));
            } catch (IOException e) {
                SpiritCraft.LOGGER.fatal("Unable to read shader file! Source: " + location.toString(), e);
            }
        }

        return s;
    }

    private InputStream getShaderStream(Identifier location) {
        if(MinecraftClient.getInstance().getResourceManager().containsResource(location)) {
            try {
                return MinecraftClient.getInstance().getResourceManager().getResource(location).getInputStream();
            }catch (IOException e) {
                SpiritCraft.LOGGER.fatal("Unable to read shader file! Source: " + location.toString(), e);
                return null;
            }
        }else {
            SpiritCraft.LOGGER.fatal("Unable to locate shader file! Source: " + location.toString());
            return null;
        }
    }
}