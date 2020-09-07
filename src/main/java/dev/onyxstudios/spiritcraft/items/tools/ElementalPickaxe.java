package dev.onyxstudios.spiritcraft.items.tools;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.registry.ModRecipes;
import dev.onyxstudios.spiritcraft.utils.BasicInvWrapper;
import net.fabricmc.fabric.api.tag.FabricItemTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.recipe.Recipe;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.Random;

public class ElementalPickaxe extends PickaxeItem {

    public ElementalPickaxe() {
        super(SCToolMaterials.ELEMENTAL, (int) SCToolMaterials.ELEMENTAL.getAttackDamage(), SCToolMaterials.ELEMENTAL.getMiningSpeedMultiplier(), new Item.Settings().group(SpiritCraft.GROUP));
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        super.postMine(stack, world, state, pos, miner);
        if(!world.isClient) {
            Optional<Recipe<Inventory>> recipe = world.getRecipeManager().getFirstMatch(ModRecipes.ELEMENTAL_DROPS_TYPE, new BasicInvWrapper(new ItemStack(state.getBlock())), world);
            if(recipe.isPresent() && Math.random() <= 0.3) {
                PlayerEntity player = (PlayerEntity) miner;
                world.playSound(player, pos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), recipe.get().getOutput().copy()));
            }
        }
        return true;
    }
}
