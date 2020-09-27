package dev.onyxstudios.spiritcraft.api.items;

public interface IWandCaps {

    /**
     * Specify the efficiency of the wand caps on a wand
     * EX: 25% Efficiency will use 25% less vis
     * -25% Efficiency will use 25% more vis
     */
    float getEfficiency();
}
