package dev.onyxstudios.spiritcraft.blocks.tree;

import dev.onyxstudios.spiritcraft.registry.ModBiomes;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;

public class SpiritwoodSaplingGenerator extends SaplingGenerator {

    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random random, boolean bl) {
        return ModBiomes.SPIRITWOOD_FEATURE;
    }
}
