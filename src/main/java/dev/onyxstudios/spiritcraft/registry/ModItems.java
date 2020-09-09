package dev.onyxstudios.spiritcraft.registry;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.items.BaseItem;
import dev.onyxstudios.spiritcraft.items.tools.ElementalAxe;
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
    public static Item IRON_ORE_CLUSTER = new BaseItem();
    public static Item GOLD_ORE_CLUSTER = new BaseItem();

    //Tools
    public static Item ELEMENTAL_PICKAXE = new ElementalPickaxe();
    public static Item ELEMENTAL_AXE = new ElementalAxe();

    //Shards
    public static Item AURA_CRYSTAL_SHARD = new BaseItem();
    public static Item SOLARIS_CRYSTAL_SHARD = new BaseItem();
    public static Item HYDRA_CRYSTAL_SHARD = new BaseItem();
    public static Item TELLUS_CRYSTAL_SHARD = new BaseItem();
    public static Item ORDIN_CRYSTAL_SHARD = new BaseItem();
    public static Item VALE_CRYSTAL_SHARD = new BaseItem();

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "spirium_ingot"), SPIRIUM_INGOT);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "null_ingot"), NULL_INGOT);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "aura_crystal_shard"), AURA_CRYSTAL_SHARD);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "solaris_crystal_shard"), SOLARIS_CRYSTAL_SHARD);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "hydra_crystal_shard"), HYDRA_CRYSTAL_SHARD);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "tellus_crystal_shard"), TELLUS_CRYSTAL_SHARD);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "ordin_crystal_shard"), ORDIN_CRYSTAL_SHARD);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "vale_crystal_shard"), VALE_CRYSTAL_SHARD);

        //Ore Clusters
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "iron_ore_cluster"), IRON_ORE_CLUSTER);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "gold_ore_cluster"), GOLD_ORE_CLUSTER);

        //Tools
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "elemental_pickaxe"), ELEMENTAL_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "elemental_axe"), ELEMENTAL_AXE);
    }

    @Environment(EnvType.CLIENT)
    public static void registerRenders() {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0xc3af49, AURA_CRYSTAL_SHARD);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0xf4503a, SOLARIS_CRYSTAL_SHARD);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x3accf4, HYDRA_CRYSTAL_SHARD);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x07c700, TELLUS_CRYSTAL_SHARD);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0xfffafc, ORDIN_CRYSTAL_SHARD);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x242424, VALE_CRYSTAL_SHARD);
    }
}
