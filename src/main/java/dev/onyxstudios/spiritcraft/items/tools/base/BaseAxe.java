package dev.onyxstudios.spiritcraft.items.tools.base;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class BaseAxe extends AxeItem {

    public BaseAxe(ToolMaterial material) {
        super(material, material.getAttackDamage(), material.getMiningSpeedMultiplier(), new Item.Settings().group(SpiritCraft.GROUP));
    }
}
