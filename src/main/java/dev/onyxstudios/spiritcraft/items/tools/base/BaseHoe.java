package dev.onyxstudios.spiritcraft.items.tools.base;

import dev.onyxstudios.spiritcraft.registry.ModItems;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class BaseHoe extends HoeItem {

    public BaseHoe(ToolMaterial material) {
        super(material, (int) material.getAttackDamage(), material.getMiningSpeedMultiplier(), new Item.Settings().group(ModItems.GROUP));
    }
}
