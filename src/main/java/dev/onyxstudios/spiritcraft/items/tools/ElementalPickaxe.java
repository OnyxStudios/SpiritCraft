package dev.onyxstudios.spiritcraft.items.tools;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;

public class ElementalPickaxe extends PickaxeItem {

    public ElementalPickaxe() {
        super(SCToolMaterials.ELEMENTAL, (int) SCToolMaterials.ELEMENTAL.getAttackDamage(), SCToolMaterials.ELEMENTAL.getMiningSpeedMultiplier(), new Item.Settings().group(SpiritCraft.GROUP));
    }
}
