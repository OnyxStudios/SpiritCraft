package dev.onyxstudios.spiritcraft.registry;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.items.BaseItem;
import dev.onyxstudios.spiritcraft.items.tools.ElementalPickaxe;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static Item SPIRIUM_INGOT = new BaseItem();
    public static Item NULL_INGOT = new BaseItem();
    public static Item ELEMENTAL_PICKAXE = new ElementalPickaxe();
    public static Item IRON_ORE_CLUSTER = new BaseItem();
    public static Item GOLD_ORE_CLUSTER = new BaseItem();


    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "spirium_ingot"), SPIRIUM_INGOT);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "null_ingot"), NULL_INGOT);

        //Ore Clusters
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "iron_ore_cluster"), IRON_ORE_CLUSTER);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "gold_ore_cluster"), GOLD_ORE_CLUSTER);

        //Tools
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "elemental_pickaxe"), ELEMENTAL_PICKAXE);
    }
}
