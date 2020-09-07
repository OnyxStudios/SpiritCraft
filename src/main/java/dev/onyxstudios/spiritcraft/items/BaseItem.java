package dev.onyxstudios.spiritcraft.items;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import net.minecraft.item.Item;

public class BaseItem extends Item {

    public BaseItem() {
        super(new Item.Settings().group(SpiritCraft.GROUP));
    }
}
