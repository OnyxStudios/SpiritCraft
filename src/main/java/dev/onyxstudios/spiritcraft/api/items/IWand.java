package dev.onyxstudios.spiritcraft.api.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IWand {

    /**
     * Get the default wand cap for the wand
     * @return - The default cap item
     */
    Item getDefaultCap();

    /**
     * Specify the capacity of Spirit a wand can hold
     */
    int getCapacity();

    /**
     * Get the current wand cap attached
     * @param stack - The wand ItemStack
     * @return - The item form of the Wand Cap
     */
    Item getWandCap(ItemStack stack);

    /**
     * Get the current foci attached
     * @param stack - The wand ItemStack
     * @return - The foci currently equiped, Nullable
     */
    Item getWandFoci(ItemStack stack);
}
