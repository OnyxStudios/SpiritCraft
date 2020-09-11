package dev.onyxstudios.spiritcraft.registry.recipe;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

public class ElementalDropsSerializer implements RecipeSerializer<ElementalDrop> {

    @Override
    public ElementalDrop read(Identifier id, JsonObject json) {
        Ingredient ingredient = Ingredient.fromJson(json.get("ore"));
        ItemStack result = ShapedRecipe.getItemStack(JsonHelper.getObject(json, "result"));

        return new ElementalDrop(id, ingredient, result);
    }

    @Override
    public ElementalDrop read(Identifier id, PacketByteBuf buf) {
        return new ElementalDrop(id, Ingredient.fromPacket(buf), buf.readItemStack());
    }

    @Override
    public void write(PacketByteBuf buf, ElementalDrop recipe) {
        recipe.source.write(buf);
        buf.writeItemStack(recipe.result);
    }
}
