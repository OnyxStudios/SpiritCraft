package dev.onyxstudios.spiritcraft.mixins;

import dev.onyxstudios.spiritcraft.registry.ModRecipes;
import dev.onyxstudios.spiritcraft.utils.BasicInvWrapper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(Block.class)
public class BlockMixin {

    @Inject(method = "afterBreak", at = @At("HEAD"), cancellable = true)
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack, CallbackInfo ci) {
        Optional<Recipe<Inventory>> recipe = world.getRecipeManager().getFirstMatch(ModRecipes.ELEMENTAL_DROPS_TYPE, new BasicInvWrapper(new ItemStack(state.getBlock())), world);
        if(recipe.isPresent() && Math.random() <= 0.3) {
            if(!world.isClient) {
                world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), recipe.get().getOutput().copy()));
            }

            player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0F);
            ci.cancel();
        }
    }
}
