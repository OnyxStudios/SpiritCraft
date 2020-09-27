package dev.onyxstudios.spiritcraft.api.models;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;

public abstract class ItemModel {

    /**
     * @param stack - The current MatrixStack
     * @param provider - The current VertexConsumerProvider
     * @param mode - The current Model Transformation Mode
     * @param light - The light level
     * @param overlay - The overlay
     * @return - Whether the minecraft renderItem#renderItem should be canceled or not
     */
    public abstract boolean render(MatrixStack stack, VertexConsumerProvider provider, ModelTransformation.Mode mode, int light, int overlay);
}
