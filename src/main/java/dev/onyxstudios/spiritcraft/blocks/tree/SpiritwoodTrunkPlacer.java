package dev.onyxstudios.spiritcraft.blocks.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.onyxstudios.spiritcraft.registry.ModBiomes;
import dev.onyxstudios.spiritcraft.registry.ModBlocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SpiritwoodTrunkPlacer extends TrunkPlacer {

    public static final Codec<SpiritwoodTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> method_28904(instance).apply(instance, SpiritwoodTrunkPlacer::new));

    public SpiritwoodTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return ModBiomes.SPIRITWOOD_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(ModifiableTestableWorld world, Random random, int trunkHeight, BlockPos pos, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig treeFeatureConfig) {
        List<FoliagePlacer.TreeNode> foliageNodes = new ArrayList<>();

        for (int i = 0; i < trunkHeight; i++) {
            method_27402(world, random, pos.mutableCopy().up(i), set, blockBox, treeFeatureConfig);
            for (Direction direction : Direction.values()) {
                if(direction == Direction.DOWN || direction == Direction.UP) continue;
                method_27402(world, random, pos.mutableCopy().offset(direction).up(i), set, blockBox, treeFeatureConfig);
                if(i >= 4) {
                    foliageNodes.add(new FoliagePlacer.TreeNode(pos.mutableCopy().offset(direction).up(i), 0, false));
                }
            }

        }

        for (Direction direction : Direction.values()) {
            if(direction == Direction.DOWN || direction == Direction.UP) continue;
            BlockPos edgePos = pos.offset(direction, 2);
            method_27404(world, edgePos, ModBlocks.SPIRITWOOD_LOG.getDefaultState().with(Properties.AXIS, direction.getAxis()), blockBox);

            BlockPos cornerPos = pos.offset(direction).offset(direction.rotateYClockwise());
            method_27404(world, cornerPos, ModBlocks.SPIRITWOOD_LOG.getDefaultState().with(Properties.AXIS, direction.getAxis()), blockBox);
        }

        return foliageNodes;
    }
}
