package dev.onyxstudios.spiritcraft.blocks;

import dev.onyxstudios.spiritcraft.registry.ModBiomes;
import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import java.util.Random;

public class ElderwoodSaplingGenerator extends LargeTreeSaplingGenerator {

    @Override
    public ConfiguredFeature<TreeFeatureConfig, ?> createLargeTreeFeature(Random random) {
        return null;
    }

    @Override
    public ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random random, boolean bl) {
        return ModBiomes.ELDERWOOD_FEATURE;
    }
}
