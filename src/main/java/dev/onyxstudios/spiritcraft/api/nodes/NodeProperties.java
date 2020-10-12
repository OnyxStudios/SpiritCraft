package dev.onyxstudios.spiritcraft.api.nodes;

public enum NodeProperties implements INodeProperty {
    NORMAL(100), VIVID(125), DULL(65), DYING(0);

    private int rechargeRate;

    NodeProperties(int rechargeRate) {
        this.rechargeRate = rechargeRate;
    }

    @Override
    public int getRechargeRate() {
        return this.rechargeRate;
    }

    @Override
    public String getName() {
        return this.name();
    }
}
