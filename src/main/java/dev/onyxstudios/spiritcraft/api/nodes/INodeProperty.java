package dev.onyxstudios.spiritcraft.api.nodes;

public interface INodeProperty {

    /**
     * Get the recharge rate for a node property
     * @return - Returns a percentage of how fast recharge should work, (0 if no recharge)
     */
    int getRechargeRate();

    String getName();
}
