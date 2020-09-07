package dev.onyxstudios.spiritcraft.blocks.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.onyxstudios.spiritcraft.registry.ModBiomes;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ElderwoodTrunkPlacer extends TrunkPlacer {

    public static final Codec<ElderwoodTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> method_28904(instance).apply(instance, ElderwoodTrunkPlacer::new));

    public ElderwoodTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Override
    public TrunkPlacerType<?> getType() {
        return ModBiomes.ELDERWOOD_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(ModifiableTestableWorld world, Random random, int trunkHeight, BlockPos pos, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig treeFeatureConfig) {
        List<FoliagePlacer.TreeNode> foliageNodes = new ArrayList<>();

        for (int y = 0; y < trunkHeight; y++) {
            if(y >= 5) {
                //Generate Tree Branch Randomly
                if(Math.random() <= 1) {
                    boolean onX = random.nextBoolean();
                    boolean sameY = Math.random() <= 1;
                    generateBranch(world, pos.up(y), onX, random, set, blockBox, treeFeatureConfig, foliageNodes);
                    if (sameY) generateBranch(world, pos.up(y), !onX, random, set, blockBox, treeFeatureConfig, foliageNodes);
                }
            }

            //Generate 2x2 Trunk
            for (int x = 0; x < 2; x++) {
                for (int z = 0; z < 2; z++) {
                    BlockPos logPos = pos.mutableCopy().add(x, y, z);
                    if(y == 0 && world.testBlockState(logPos.down(), state -> state == Blocks.AIR.getDefaultState())) {
                        method_27404(world, logPos.down(), Blocks.DIRT.getDefaultState(), blockBox);
                        set.add(logPos.down().toImmutable());
                    }
                    method_27402(world, random, logPos, set, blockBox, treeFeatureConfig);
                }
            }
        }

        return foliageNodes;
    }

    public void generateBranch(ModifiableTestableWorld world, BlockPos pos, boolean onX, Random random, Set<BlockPos> set, BlockBox blockBox, TreeFeatureConfig treeFeatureConfig, List<FoliagePlacer.TreeNode> foliageNodes) {
        boolean invert = random.nextBoolean();
        boolean diagonal = Math.random() < 0.35;
        int randomTreeLength = random.nextInt(7);
        for (int i = 0; i < randomTreeLength; i++) {
            int offset = random.nextInt(3);
            int offsetY = random.nextInt(3);
            int x = invert ? -i : i;
            BlockPos logPos = pos.mutableCopy().add(onX ? x : offset + (diagonal ? x : 0), offsetY, onX ? offset + (diagonal ? x : 0) : x);
            method_27402(world, random, logPos, set, blockBox, treeFeatureConfig);
            foliageNodes.add(new FoliagePlacer.TreeNode(logPos, 5, false));
        }
    }
}
