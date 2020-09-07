package dev.onyxstudios.spiritcraft.registry;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.items.BaseItem;
import dev.onyxstudios.spiritcraft.items.BlockItemCrystal;
import dev.onyxstudios.spiritcraft.items.tools.ElementalPickaxe;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import sun.security.provider.ConfigFile;

public class ModItems {

    public static Item SPIRIUM_INGOT = new BaseItem();
    public static Item NULL_INGOT = new BaseItem();
    public static Item ELEMENTAL_PICKAXE = new ElementalPickaxe();

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "spiruim_ingot"), SPIRIUM_INGOT);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "null_ingot"), NULL_INGOT);

        //Tools
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "elemental_pickaxe"), ELEMENTAL_PICKAXE);
    }
}
