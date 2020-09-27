package dev.onyxstudios.spiritcraft.items.wands;

import dev.onyxstudios.spiritcraft.api.items.IWandCaps;
import dev.onyxstudios.spiritcraft.items.BaseItem;

public class BaseCap extends BaseItem implements IWandCaps {

    private float efficiency;

    public BaseCap(float efficiency) {
        super();
        this.efficiency = efficiency;
    }

    @Override
    public float getEfficiency() {
        return efficiency / 100.0f;
    }
}
