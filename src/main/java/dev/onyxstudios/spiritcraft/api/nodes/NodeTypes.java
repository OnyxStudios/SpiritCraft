package dev.onyxstudios.spiritcraft.api.nodes;

public enum NodeTypes implements INodeType {
    NORMAL, PURE, HUNGRY, VILE, CORRUPT, GLITCHED;

    @Override
    public String getName() {
        return this.name();
    }
}
