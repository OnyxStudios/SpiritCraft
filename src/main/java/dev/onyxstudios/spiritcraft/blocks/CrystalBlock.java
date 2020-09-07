package dev.onyxstudios.spiritcraft.blocks;

import dev.onyxstudios.spiritcraft.blockentity.BlockEntityCrystal;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class CrystalBlock extends Block implements BlockEntityProvider {

    public CrystalBlock() {
        super(FabricBlockSettings.of(Material.STONE).breakByTool(FabricToolTags.PICKAXES).lightLevel(2).strength(1.2f, 6f).nonOpaque());
        this.setDefaultState(this.getStateManager().getDefaultState().with(Properties.FACING, Direction.UP));
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        this.addCornerCrystals(world, pos, state);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        this.addCornerCrystals(world, pos, state);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if(world.getBlockState(pos.down()).isOf(this) || world.getBlockState(pos.down()).isOf(this)) {
            return false;
        }

        return true;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        BlockState neighborState = super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
        addCornerCrystals(world, pos, neighborState);
        return neighborState;
    }

    public void addCornerCrystals(WorldAccess world, BlockPos pos, BlockState state) {
        if(world.getBlockEntity(pos) instanceof BlockEntityCrystal) {
            BlockEntityCrystal crystal = (BlockEntityCrystal) world.getBlockEntity(pos);
            crystal.states.clear();

            for (Direction direction : Direction.values()) {
                if(direction.equals(state.get(Properties.FACING).getOpposite())) continue;
                BlockPos offsetPos = pos.offset(direction);

                BlockState cornerState = this.getDefaultState().with(Properties.FACING, direction.getOpposite());
                if(!world.isAir(offsetPos) && !(world.getBlockState(offsetPos).getBlock() instanceof CrystalBlock)) {
                    crystal.states.add(cornerState);
                }
            }
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(Properties.FACING, ctx.getSide());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(Properties.FACING, rotation.rotate(state.get(Properties.FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(Properties.FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.FACING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(Properties.FACING);
        VoxelShape crystalShape = getShapeFromDirection(direction);
        BlockEntity crystal = world.getBlockEntity(pos);
        if(crystal instanceof BlockEntityCrystal) {
            for (BlockState subState : ((BlockEntityCrystal) crystal).states) {
                crystalShape = VoxelShapes.union(crystalShape, getShapeFromDirection(subState.get(Properties.FACING)));
            }
        }

        return crystalShape;
    }

    public VoxelShape getShapeFromDirection(Direction direction) {
        switch (direction) {
            case NORTH:
                return Block.createCuboidShape(0, 0, 10, 16, 16, 16);
            case SOUTH:
                return Block.createCuboidShape(0, 0, 0, 16, 16, 6);
            case EAST:
                return Block.createCuboidShape(0, 0, 0, 6, 16, 16);
            case WEST:
                return Block.createCuboidShape(10, 0, 0, 16, 16, 16);
            case DOWN:
                return Block.createCuboidShape(0, 16, 0, 16, 10, 16);
            case UP:
            default:
                return Block.createCuboidShape(0, 0, 0, 16, 6, 16);
        }
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new BlockEntityCrystal();
    }
}
