package dev.onyxstudios.spiritcraft.blocks;

import dev.onyxstudios.spiritcraft.utils.BlockFacings;
import dev.onyxstudios.spiritcraft.utils.PropertyBlockFacings;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TransparentBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

import java.util.List;

public class ConnectedBlockBase extends TransparentBlock {

    private boolean[] actualFacings;

    public ConnectedBlockBase(Settings settings) {
        super(settings);
        this.actualFacings = new boolean[Direction.values().length];
        this.setDefaultState(this.getStateManager().getDefaultState().with(PropertyBlockFacings.FACINGS, PropertyBlockFacings.None));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        return state.with(PropertyBlockFacings.FACINGS, handleBlockFacing(pos, world).toProperty());
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(PropertyBlockFacings.FACINGS, handleBlockFacing(ctx.getBlockPos(), ctx.getWorld()).toProperty());
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(PropertyBlockFacings.FACINGS);
    }

    @Environment(EnvType.CLIENT)
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.isOf(this) ? true : super.isSideInvisible(state, stateFrom, direction);
    }

    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    public BlockFacings handleBlockFacing(BlockPos pos, WorldAccess world) {
        for (int i = 0; i < actualFacings.length; i++) {
            Direction direction = Direction.values()[i];
            BlockPos facingOffset = pos.offset(direction);
            this.actualFacings[i] = world.getBlockState(facingOffset).getBlock() instanceof ConnectedBlockBase;
        }

        return BlockFacings.from(this.actualFacings);
    }


    public BlockFacings handleBlockFacingClient(BlockPos pos, List<BlockPos> neighbors) {
        facingsLoop:
        for (int i = 0; i < actualFacings.length; i++) {
            Direction direction = Direction.values()[i];
            BlockPos facingOffset = pos.offset(direction);
            for (BlockPos neighborPos : neighbors) {
                if(neighborPos.equals(facingOffset)) {
                    this.actualFacings[i] = true;
                    continue facingsLoop;
                }
            }

            this.actualFacings[i] = false;
        }

        return BlockFacings.from(this.actualFacings);
    }
}
