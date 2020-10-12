package dev.onyxstudios.spiritcraft.blocks;

import dev.onyxstudios.spiritcraft.api.aspects.AspectStack;
import dev.onyxstudios.spiritcraft.api.components.spirit.ISpiritComponent;
import dev.onyxstudios.spiritcraft.api.components.spirit.SpiritComponent;
import dev.onyxstudios.spiritcraft.api.nodes.INode;
import dev.onyxstudios.spiritcraft.blockentity.BlockEntityNode;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.Random;

public class NodeBlock extends Block implements BlockEntityProvider {

    public NodeBlock() {
        super(FabricBlockSettings.of(Material.WOOL)
                .breakByHand(true)
                .sounds(BlockSoundGroup.WOOL)
                .lightLevel(2)
                .strength(2.5f, 12f)
                .nonOpaque()
                .ticksRandomly()
                .collidable(false)
        );
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        BlockEntity nodeEntity = world.getBlockEntity(pos);

        if(nodeEntity instanceof INode && !world.isClient) {
            ISpiritComponent component = SpiritComponent.SPIRIT.get(nodeEntity);
            int aspectIndex = random.nextInt(component.getAspects().size());
            AspectStack stack = (AspectStack) component.getAspects().toArray()[aspectIndex];

            if(stack.getCount() < component.getCapacity()) {
                component.addAspect(stack.getAspect(), 1);
            }

            SpiritComponent.SPIRIT.sync(nodeEntity);
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(6.0f / 16.0f, 6.0f / 16.0f, 6.0f / 16.0f, 10.0f / 16.0f, 10.0f / 16.0f ,10.0f / 16.0f);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return type == NavigationType.AIR && !this.collidable ? true : super.canPathfindThrough(state, world, pos, type);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new BlockEntityNode();
    }
}
