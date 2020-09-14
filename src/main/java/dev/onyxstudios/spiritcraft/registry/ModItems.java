package dev.onyxstudios.spiritcraft.registry;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.items.BaseItem;
import dev.onyxstudios.spiritcraft.items.tools.*;
import dev.onyxstudios.spiritcraft.items.tools.base.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier(SpiritCraft.MODID, "itemgroup"), () -> new ItemStack(ModBlocks.ELDERWOOD_LOG));
    public static final Tag<Item> DYES = TagRegistry.item(new Identifier("c", "dyes"));

    public static Item SPIRIUM_INGOT = new BaseItem();
    public static Item NULL_INGOT = new BaseItem();
    public static Item IRON_ORE_CLUSTER = new BaseItem();
    public static Item GOLD_ORE_CLUSTER = new BaseItem();

    //Tools
    public static Item ELEMENTAL_SWORD = new ElementalSword();
    public static Item ELEMENTAL_PICKAXE = new ElementalPickaxe();
    public static Item ELEMENTAL_AXE = new ElementalAxe();
    public static Item ELEMENTAL_SHOVEL = new ElementalShovel();
    public static Item ELEMENTAL_HOE = new ElementalHoe();
    public static Item SPIRIUM_PICKAXE = new BasePickaxe(SCToolMaterials.SPIRIUM);
    public static Item SPIRIUM_AXE = new BaseAxe(SCToolMaterials.SPIRIUM);
    public static Item SPIRIUM_HOE = new BaseHoe(SCToolMaterials.SPIRIUM);
    public static Item SPIRIUM_SHOVEL = new BaseShovel(SCToolMaterials.SPIRIUM);
    public static Item SPIRIUM_SWORD = new BaseSword(SCToolMaterials.SPIRIUM);
    public static Item NULL_PICKAXE = new BasePickaxe(SCToolMaterials.NULL);
    public static Item NULL_AXE = new BaseAxe(SCToolMaterials.NULL);
    public static Item NULL_HOE = new BaseHoe(SCToolMaterials.NULL);
    public static Item NULL_SHOVEL = new BaseShovel(SCToolMaterials.NULL);
    public static Item NULL_SWORD = new BaseSword(SCToolMaterials.NULL);

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
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "elemental_sword"), ELEMENTAL_SWORD);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "elemental_pickaxe"), ELEMENTAL_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "elemental_axe"), ELEMENTAL_AXE);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "elemental_shovel"), ELEMENTAL_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "elemental_hoe"), ELEMENTAL_HOE);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "spirium_pickaxe"), SPIRIUM_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "spirium_axe"), SPIRIUM_AXE);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "spirium_hoe"), SPIRIUM_HOE);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "spirium_shovel"), SPIRIUM_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "spirium_sword"), SPIRIUM_SWORD);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "null_pickaxe"), NULL_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "null_axe"), NULL_AXE);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "null_hoe"), NULL_HOE);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "null_shovel"), NULL_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier(SpiritCraft.MODID, "null_sword"), NULL_SWORD);
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
