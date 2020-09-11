package dev.onyxstudios.spiritcraft.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class InventoryUtils {

    public static int getStackCount(PlayerEntity player, ItemStack compare) {
        int count = 0;
        for (ItemStack stack : player.inventory.main) {
            if(stack.isItemEqual(compare)) {
                count+= stack.getCount();
            }
        }

        return count;
    }

    public static boolean consumeOne(PlayerEntity player, ItemStack compare) {
        if(player.isCreative()) return true;

        for (int i = 0; i < player.inventory.main.size(); i++) {
            if(player.inventory.getStack(i).isItemEqual(compare)) {
                player.inventory.removeStack(i, 1);
                return true;
            }
        }

        return false;
    }
}
