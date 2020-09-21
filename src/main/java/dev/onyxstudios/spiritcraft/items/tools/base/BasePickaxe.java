package dev.onyxstudios.spiritcraft.items.tools.base;

import dev.onyxstudios.spiritcraft.registry.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class BasePickaxe extends PickaxeItem {

    public BasePickaxe(ToolMaterial material) {
        super(material, (int) material.getAttackDamage(), material.getMiningSpeedMultiplier(), new Item.Settings().group(ModItems.GROUP));
    }
}
