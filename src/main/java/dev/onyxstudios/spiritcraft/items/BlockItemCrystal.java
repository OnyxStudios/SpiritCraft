package dev.onyxstudios.spiritcraft.items;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.blocks.CrystalBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;

public class BlockItemCrystal extends BlockItem {

    public BlockItemCrystal(Block block) {
        super(block, new Item.Settings().group(SpiritCraft.GROUP));
    }

    @Override
    protected boolean canPlace(ItemPlacementContext context, BlockState state) {
        if(context.getWorld().getBlockState(context.getBlockPos().offset(context.getSide().getOpposite())).getBlock() instanceof CrystalBlock) {
            return false;
        }

        return super.canPlace(context, state);
    }
}
