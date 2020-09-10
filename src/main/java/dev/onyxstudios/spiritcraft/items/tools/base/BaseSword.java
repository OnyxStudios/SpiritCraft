package dev.onyxstudios.spiritcraft.items.tools.base;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class BaseSword extends SwordItem {

    public BaseSword(ToolMaterial material) {
        super(material, (int) material.getAttackDamage(), material.getMiningSpeedMultiplier(), new Item.Settings().group(SpiritCraft.GROUP));
    }
}
