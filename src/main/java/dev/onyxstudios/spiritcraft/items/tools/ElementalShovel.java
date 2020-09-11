package dev.onyxstudios.spiritcraft.items.tools;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.registry.ModItems;
import dev.onyxstudios.spiritcraft.utils.BlockUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ElementalShovel extends ShovelItem {

    public static List<Block> EFFECTIVE_BLOCKS = new ArrayList<>();
    public static final String ROTATION = "rotation";
    public static final String ROTATION_FLOOR = "floor";
    public static final String ROTATION_WALL = "wall";

    public ElementalShovel() {
        super(SCToolMaterials.ELEMENTAL, SCToolMaterials.ELEMENTAL.getAttackDamage(), SCToolMaterials.ELEMENTAL.getMiningSpeedMultiplier(), new Item.Settings().group(SpiritCraft.GROUP));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient && EFFECTIVE_BLOCKS.contains(context.getWorld().getBlockState(context.getBlockPos()).getBlock())) {
            int placedBlocks = BlockUtils.place3x3(context.getWorld(), context.getBlockPos(), context.getWorld().getBlockState(context.getBlockPos()), context.getPlayer(), context.getSide());
            context.getPlayer().getMainHandStack().damage(placedBlocks, context.getPlayer(), entity -> {});

            if(placedBlocks > 0)
                return ActionResult.success(context.getWorld().isClient);
        }

        return ActionResult.PASS;
    }

    public static void shovelDirt(World world, PlayerEntity player, BlockState state, BlockPos pos, Direction direction) {
        if(player.isSneaking()) return;
        if(!player.getMainHandStack().isEmpty() && player.getMainHandStack().isItemEqualIgnoreDamage(new ItemStack(ModItems.ELEMENTAL_SHOVEL)) && EFFECTIVE_BLOCKS.contains(state.getBlock())) {
            int brokenBlocks = BlockUtils.shovel3x3(world, pos, state, player, direction);
            player.getMainHandStack().damage(brokenBlocks++, player, entity -> {});
        }
    }

    {
        Field field = ShovelItem.class.getDeclaredFields()[0];
        field.setAccessible(true);
        try {
            Object object = field.get(this);
            if(object instanceof Set) {
                EFFECTIVE_BLOCKS.addAll((Set<Block>) object);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
