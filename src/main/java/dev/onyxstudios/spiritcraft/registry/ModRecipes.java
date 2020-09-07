package dev.onyxstudios.spiritcraft.registry;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.registry.recipe.ElementalDropsSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipes {

    public static RecipeType ELEMENTAL_DROPS_TYPE;
    public static ElementalDropsSerializer ELEMENTAL_DROPS_SERIALIZER = new ElementalDropsSerializer();

    public static void register() {
        ELEMENTAL_DROPS_TYPE = RecipeType.register(SpiritCraft.MODID + ":elemental_drops");
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(SpiritCraft.MODID, "elemental_drops"), ELEMENTAL_DROPS_SERIALIZER);
    }
}
