package dev.onyxstudios.spiritcraft.registry;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.blocks.CrystalBlock;
import dev.onyxstudios.spiritcraft.blocks.ElderwoodSaplingGenerator;
import dev.onyxstudios.spiritcraft.blocks.SaplingBlockBase;
import dev.onyxstudios.spiritcraft.items.BlockItemCrystal;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    public static int GREATWOOD_LEAVES_COLOR = 0x1aad77;
    public static Block ELDERWOOD = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F).sounds(BlockSoundGroup.WOOD));
    public static Block ELDERWOOD_LOG = new PillarBlock(Block.Settings.of(Material.WOOD).strength(2.0F).sounds(BlockSoundGroup.WOOD));
    public static Block ELDERWOOD_LEAVES = new LeavesBlock(Block.Settings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().suffocates((state, world, pos) -> false).blockVision((state, world, pos) -> false));
    public static Block ELDERWOOD_SAPLING = new SaplingBlockBase(new ElderwoodSaplingGenerator());

    //Crystals
    public static Block AURA_CRYSTAL_BLOCK = new CrystalBlock();
    public static Block SOLARIS_CRYSTAL_BLOCK = new CrystalBlock();
    public static Block HYDRA_CRYSTAL_BLOCK = new CrystalBlock();
    public static Block TELLUS_CRYSTAL_BLOCK = new CrystalBlock();
    public static Block ORDIN_CRYSTAL_BLOCK = new CrystalBlock();
    public static Block VALE_CRYSTAL_BLOCK = new CrystalBlock();

    public static void register() {
        registerBlock(new Identifier(SpiritCraft.MODID, "elderwood"), ELDERWOOD);
        registerBlock(new Identifier(SpiritCraft.MODID, "elderwood_log"), ELDERWOOD_LOG);
        registerBlock(new Identifier(SpiritCraft.MODID, "elderwood_leaves"), ELDERWOOD_LEAVES);
        registerBlock(new Identifier(SpiritCraft.MODID, "elderwood_sapling"), ELDERWOOD_SAPLING);

        //Crystals
        registerBlock(new Identifier(SpiritCraft.MODID, "aura_crystal_block"), AURA_CRYSTAL_BLOCK, new BlockItemCrystal(AURA_CRYSTAL_BLOCK));
        registerBlock(new Identifier(SpiritCraft.MODID, "solaris_crystal_block"), SOLARIS_CRYSTAL_BLOCK, new BlockItemCrystal(SOLARIS_CRYSTAL_BLOCK));
        registerBlock(new Identifier(SpiritCraft.MODID, "hydra_crystal_block"), HYDRA_CRYSTAL_BLOCK, new BlockItemCrystal(HYDRA_CRYSTAL_BLOCK));
        registerBlock(new Identifier(SpiritCraft.MODID, "tellus_crystal_block"), TELLUS_CRYSTAL_BLOCK, new BlockItemCrystal(TELLUS_CRYSTAL_BLOCK));
        registerBlock(new Identifier(SpiritCraft.MODID, "ordin_crystal_block"), ORDIN_CRYSTAL_BLOCK, new BlockItemCrystal(ORDIN_CRYSTAL_BLOCK));
        registerBlock(new Identifier(SpiritCraft.MODID, "vale_crystal_block"), VALE_CRYSTAL_BLOCK, new BlockItemCrystal(VALE_CRYSTAL_BLOCK));

        FlammableBlockRegistry.getDefaultInstance().add(ELDERWOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ELDERWOOD_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ELDERWOOD_LEAVES, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(ELDERWOOD_SAPLING, 5, 5);
    }

    public static void registerRenders() {
        BlockRenderLayerMap.INSTANCE.putBlock(AURA_CRYSTAL_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SOLARIS_CRYSTAL_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HYDRA_CRYSTAL_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TELLUS_CRYSTAL_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ORDIN_CRYSTAL_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VALE_CRYSTAL_BLOCK, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ELDERWOOD_SAPLING, RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> GREATWOOD_LEAVES_COLOR, ELDERWOOD_LEAVES);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GREATWOOD_LEAVES_COLOR, ELDERWOOD_LEAVES);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0xc3af49, AURA_CRYSTAL_BLOCK);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0xf4503a, SOLARIS_CRYSTAL_BLOCK);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x3accf4, HYDRA_CRYSTAL_BLOCK);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x07c700, TELLUS_CRYSTAL_BLOCK);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0xfffafc, ORDIN_CRYSTAL_BLOCK);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x242424, VALE_CRYSTAL_BLOCK);
    }

    public static void registerBlock(Identifier identifier, Block block) {
        Registry.register(Registry.BLOCK, identifier, block);
        Registry.register(Registry.ITEM, identifier, new BlockItem(block, new Item.Settings().group(SpiritCraft.GROUP)));
    }

    public static void registerBlock(Identifier identifier, Block block, BlockItem blockItem) {
        Registry.register(Registry.BLOCK, identifier, block);
        Registry.register(Registry.ITEM, identifier, blockItem);
    }
}
