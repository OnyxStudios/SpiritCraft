package dev.onyxstudios.spiritcraft.registry.recipe;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ElementalDrop implements Recipe<Inventory> {

    protected RecipeType<?> recipeType;
    protected Identifier id;
    protected Ingredient source;
    protected ItemStack result;

    public ElementalDrop(Identifier id, Ingredient source, ItemStack result) {
        this.id = id;
        this. source = source;
        this. result  = result;
    }

    @Override
    public boolean matches(Inventory inv, World world) {
        if(source.test(inv.getStack(0))) {
            return true;
        }
        return false;
    }

    @Override
    public ItemStack craft(Inventory inv) {
        return this.result.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return this.result;
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return this.recipeType;
    }
}
