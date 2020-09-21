package dev.onyxstudios.spiritcraft.items.tools;

import dev.onyxstudios.spiritcraft.registry.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class ElementalSword extends SwordItem {

    public ElementalSword() {
        super(SCToolMaterials.ELEMENTAL, (int) SCToolMaterials.ELEMENTAL.getAttackDamage(), SCToolMaterials.ELEMENTAL.getMiningSpeedMultiplier(), new Item.Settings().group(ModItems.GROUP));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        Vec3d rotation = player.getRotationVector();
        player.setVelocity(rotation.x * 0.45, rotation.getY() <= 0 ? 0.25 : Math.max(rotation.getY() * 0.6, 0.4), rotation.getZ() * 0.45);
        player.getStackInHand(hand).damage(10, player, playerEntity -> {});
        return super.use(world, player, hand);
    }

    public static ActionResult damageAOE(World world, PlayerEntity player, Hand hand, Entity entity) {
        if(!world.isClient && !player.getStackInHand(hand).isEmpty() && player.getStackInHand(hand).isItemEqualIgnoreDamage(new ItemStack(ModItems.ELEMENTAL_PICKAXE))) {
            List<LivingEntity> neighbors = world.getEntitiesByClass(LivingEntity.class, new Box(entity.getBlockPos().add(4, 4, 4), entity.getBlockPos().add(-4, -4, -4)), livingEntity -> livingEntity != null && !(livingEntity instanceof PlayerEntity));

            for (LivingEntity neighbor : neighbors) {
                neighbor.damage(DamageSource.player(player), ((ElementalSword) ModItems.ELEMENTAL_SWORD).getAttackDamage() * 0.2f);
                neighbor.animateDamage();
            }

            player.getStackInHand(hand).damage(neighbors.size(), player, playerEntity -> {});
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}
