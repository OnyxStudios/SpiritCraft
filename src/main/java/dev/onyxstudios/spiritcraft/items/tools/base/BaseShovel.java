package dev.onyxstudios.spiritcraft.items.tools.base;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;

public class BaseShovel extends ShovelItem {

    public BaseShovel(ToolMaterial material) {
        super(material, material.getAttackDamage(), material.getMiningSpeedMultiplier(), new Item.Settings().group(SpiritCraft.GROUP));
    }
}
