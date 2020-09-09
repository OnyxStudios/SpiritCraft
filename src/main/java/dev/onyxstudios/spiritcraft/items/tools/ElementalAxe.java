package dev.onyxstudios.spiritcraft.items.tools;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.registry.ModItems;
import dev.onyxstudios.spiritcraft.registry.ModRenders;
import dev.onyxstudios.spiritcraft.utils.BlockUtils;
import dev.onyxstudios.spiritcraft.utils.MathUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class ElementalAxe extends AxeItem {

    public static final int PULL_RANGE = 10;
    public static final double ITEM_VELOCITY = 0.35;
    public static final int BREAK_RADIUS = 8;

    public ElementalAxe() {
        super(SCToolMaterials.ELEMENTAL, SCToolMaterials.ELEMENTAL.getAttackDamage(), SCToolMaterials.ELEMENTAL.getMiningSpeedMultiplier(), new Item.Settings().group(SpiritCraft.GROUP));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return super.use(world, user, hand);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);
        List<ItemEntity> items = world.getEntitiesByType(EntityType.ITEM, new Box(user.getBlockPos()).expand(PULL_RANGE), itemEntity -> itemEntity != null && !itemEntity.getStack().isEmpty());

        if(!items.isEmpty()) {
            for (ItemEntity entity : items) {
                if(!entity.isAlive()) continue;
                double multiplier = 0.8;
                double squaredDistance = entity.getPos().squaredDistanceTo(user.getPos().add(0, user.getHeight() / 2.0f, 0));
                Vec3d distance = MathUtils.divide(entity.getPos().subtract(user.getPos()), squaredDistance);
                entity.addVelocity(-distance.getX() * multiplier, -distance.getY() * multiplier, -distance.getZ() * multiplier);

                if (entity.getVelocity().getX() > ITEM_VELOCITY) {
                    entity.setVelocity(ITEM_VELOCITY, entity.getVelocity().getY(), entity.getVelocity().getZ());
                }

                if (entity.getVelocity().getX() < -ITEM_VELOCITY) {
                    entity.setVelocity(-ITEM_VELOCITY, entity.getVelocity().getY(), entity.getVelocity().getZ());
                }

                if (entity.getVelocity().getY() > ITEM_VELOCITY) {
                    entity.setVelocity(entity.getVelocity().getX(), ITEM_VELOCITY, entity.getVelocity().getZ());
                }

                if (entity.getVelocity().getY() < -ITEM_VELOCITY) {
                    entity.setVelocity(entity.getVelocity().getX(), -0.25, entity.getVelocity().getZ());
                }

                if (entity.getVelocity().getZ() > ITEM_VELOCITY) {
                    entity.setVelocity(entity.getVelocity().getX(), entity.getVelocity().getY(), ITEM_VELOCITY);
                }

                if (entity.getVelocity().getZ() < -ITEM_VELOCITY) {
                    entity.setVelocity(entity.getVelocity().getX(), entity.getVelocity().getY(), -ITEM_VELOCITY);
                }

                //Spawn Particles
                if(world.isClient) {
                    world.addParticle(ModRenders.MAGIC_BUBBLE_TYPE, true, entity.getPos().getX(), entity.getPos().getY() + 0.5, entity.getPos().getZ(), 0, 0, 0);
                }
            }
        }
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
        stack.damage(5, user, playerEntity -> {});
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    public static boolean breakLogs(World world, BlockPos pos, PlayerEntity player) {
        if(!player.getMainHandStack().isEmpty() && player.getMainHandStack().isItemEqualIgnoreDamage(new ItemStack(ModItems.ELEMENTAL_AXE)) && BlockTags.LOGS.contains(world.getBlockState(pos).getBlock())) {
            BlockUtils.breakHighestBlock(world, pos, player, ElementalAxe.BREAK_RADIUS);
            player.getMainHandStack().damage(1, player, playerEntity -> {});
            return true;
        }

        return false;
    }
}
