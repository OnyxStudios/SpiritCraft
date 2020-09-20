package dev.onyxstudios.spiritcraft.items;

import dev.onyxstudios.spiritcraft.registry.ModItems;
import net.minecraft.item.Item;

public class BaseItem extends Item {

    public BaseItem() {
        this(new Item.Settings().group(ModItems.GROUP));
    }

    public BaseItem(Item.Settings settings) {
        super(settings);
    }
}
