package dev.onyxstudios.spiritcraft.items.tools;

import dev.onyxstudios.spiritcraft.registry.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class ElementalHoe extends HoeItem {

    public ElementalHoe() {
        super(SCToolMaterials.ELEMENTAL, (int) SCToolMaterials.ELEMENTAL.getAttackDamage(), SCToolMaterials.ELEMENTAL.getMiningSpeedMultiplier(), new Item.Settings().group(ModItems.GROUP));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState state = world.getBlockState(blockPos);
        if(TILLED_BLOCKS.containsKey(state.getBlock())) {
            if (context.getPlayer().isSneaking()) {
                return super.useOnBlock(context);
            }

            if (context.getSide() != Direction.DOWN && world.getBlockState(blockPos.up()).isAir()) {
                Iterable<BlockPos> positions = BlockPos.iterate(blockPos.add(1, 0, 1), blockPos.add(-1, 0, -1));
                for (BlockPos areaPos : positions) {
                    super.useOnBlock(new ItemUsageContext(context.getPlayer(), context.getHand(), new BlockHitResult(context.getHitPos(), context.getSide(), areaPos, context.hitsInsideBlock())));
                }
            }
        }

        if(BoneMealItem.useOnFertilizable(ItemStack.EMPTY, world, blockPos)) {
            if (!world.isClient) {
                world.syncWorldEvent(2005, blockPos, 0);
                context.getPlayer().getStackInHand(context.getHand()).damage(5, context.getPlayer(), playerEntity -> {});
            }
        }

        return ActionResult.success(world.isClient);
    }
}
