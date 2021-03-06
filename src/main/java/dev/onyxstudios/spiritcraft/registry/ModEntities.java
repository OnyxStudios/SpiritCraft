package dev.onyxstudios.spiritcraft.registry;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.blockentity.BlockEntityCrystal;
import dev.onyxstudios.spiritcraft.client.render.CrystalRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {

    public static BlockEntityType<BlockEntityCrystal> CRYSTAL_ENTITY;

    public static void register() {
        CRYSTAL_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(SpiritCraft.MODID), BlockEntityType.Builder.create(
                () -> new BlockEntityCrystal(),
                ModBlocks.AURA_CRYSTAL_BLOCK,
                ModBlocks.SOLARIS_CRYSTAL_BLOCK,
                ModBlocks.HYDRA_CRYSTAL_BLOCK,
                ModBlocks.TELLUS_CRYSTAL_BLOCK,
                ModBlocks.ORDIN_CRYSTAL_BLOCK,
                ModBlocks.VALE_CRYSTAL_BLOCK
        ).build(null));
    }

    @Environment(EnvType.CLIENT)
    public static void registerRenders() {
        BlockEntityRendererRegistry.INSTANCE.register(CRYSTAL_ENTITY, CrystalRenderer::new);
    }
}
