package dev.onyxstudios.spiritcraft.registry;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.items.BaseItem;
import dev.onyxstudios.spiritcraft.items.tools.ElementalPickaxe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static Item SPIRIUM_INGOT = new BaseItem();
    public static Item NULL_INGOT = new BaseItem();
    public static Item ELEMENTAL_PICKAXE = new ElementalPickaxe();
    public static Item IRON_ORE_CLUSTER = new BaseItem();
    public static Item GOLD_ORE_CLUSTER = new BaseItem();

    //Shards
    public static Item AURA_CRYSTAL_SHARD = new BaseItem();

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "spirium_ingot"), SPIRIUM_INGOT);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "null_ingot"), NULL_INGOT);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "aura_crystal_shard"), AURA_CRYSTAL_SHARD);

        //Ore Clusters
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "iron_ore_cluster"), IRON_ORE_CLUSTER);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "gold_ore_cluster"), GOLD_ORE_CLUSTER);

        //Tools
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "elemental_pickaxe"), ELEMENTAL_PICKAXE);
    }

    @Environment(EnvType.CLIENT)
    public static void registerRenders() {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0xc3af49, AURA_CRYSTAL_SHARD);
    }
}
