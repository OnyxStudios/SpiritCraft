package dev.onyxstudios.spiritcraft.api.nodes;

public interface INode {

    /**
     * Get the current node type
     */
    INodeType getNodeType();

    /**
     * Set the node type
     */
    void setNodeType(INodeType type);

    /**
     * Get the current node property
     */
    INodeProperty getNodeProperty();

    /**
     * Set the node property
     */
    void setNodeProperty(INodeProperty property);
}
