package dev.onyxstudios.spiritcraft.blockentity;

import dev.onyxstudios.spiritcraft.api.aspects.Aspect;
import dev.onyxstudios.spiritcraft.api.aspects.AspectStack;
import dev.onyxstudios.spiritcraft.api.aspects.SpiritCraftAspects;
import dev.onyxstudios.spiritcraft.api.components.spirit.ISpiritComponent;
import dev.onyxstudios.spiritcraft.api.components.spirit.SpiritComponent;
import dev.onyxstudios.spiritcraft.api.nodes.*;
import dev.onyxstudios.spiritcraft.registry.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockEntityNode extends BlockEntityBase implements INode {

    private INodeType nodeType;
    private INodeProperty nodeProperty;
    public int age;

    public BlockEntityNode() {
        super(ModEntities.NODE_ENTITY);
        Random random = new Random();
        this.nodeType = NodeTypes.values()[random.nextInt(NodeTypes.values().length)];
        this.nodeProperty = NodeProperties.values()[random.nextInt(NodeProperties.values().length)];
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        if(tag.contains("nodeType"))
            nodeType = NodeTypes.valueOf(tag.getString("nodeType"));

        if(tag.contains("nodeProperty"))
            nodeProperty = NodeProperties.valueOf(tag.getString("nodeProperty"));
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        if(nodeType != null)
            tag.putString("nodeType", nodeType.getName());

        if(nodeProperty != null)
            tag.putString("nodeProperty", nodeProperty.getName());

        return tag;
    }

    @Override
    public void tick() {
        super.tick();
        age++;
    }

    @Override
    public INodeType getNodeType() {
        return this.nodeType;
    }

    @Override
    public void setNodeType(INodeType type) {
        this.nodeType = type;
    }

    @Override
    public INodeProperty getNodeProperty() {
        return this.nodeProperty;
    }

    @Override
    public void setNodeProperty(INodeProperty property) {
        this.nodeProperty = property;
    }

    @Override
    public void setLocation(World world, BlockPos pos) {
        super.setLocation(world, pos);

        if (!world.isClient) {
            ISpiritComponent component = SpiritComponent.SPIRIT.get(this);
            if (component.getAspects().isEmpty()) {
                ComponentPair pair = generateComponentPair();
                while (pair.aspects.length <= 0)
                    pair = generateComponentPair();

                component.setCapacity(pair.capacity);
                for (AspectStack stack : pair.aspects)
                    component.addAspect(stack.getAspect(), stack.getCount());

                SpiritComponent.SPIRIT.sync(this);
            }
        }
    }

    private ComponentPair generateComponentPair() {
        Random random = new Random();
        int primalAspectsCount = SpiritCraftAspects.getPrimalAspects().length;
        int aspectCount = random.nextInt(primalAspectsCount);
        AspectStack[] aspects = new AspectStack[random.nextInt(aspectCount + (aspectCount <= 0 ? 1 : 0))];

        int randomCapacity = random.nextInt(SpiritComponent.MAX_NODE_ASPECTS);
        int maxCapacity = randomCapacity + (randomCapacity < 10 ? 10 : 0);

        for (int i = 0; i < aspects.length; i++) {
            while (aspects[i] == null) {
                Aspect aspect = SpiritCraftAspects.getPrimalAspects()[random.nextInt(primalAspectsCount)];

                boolean hasAspect = false;
                for (AspectStack stack : aspects) {
                    if (stack != null && stack.getAspect() == aspect) {
                        hasAspect = true;
                    }
                }

                if (!hasAspect) {
                    int amount = random.nextInt(maxCapacity);
                    aspects[i] = new AspectStack(aspect, amount <= 6 ? amount + 4 : aspectCount);
                    break;
                }
            }
        }

        return new ComponentPair(maxCapacity, aspects);
    }

    private class ComponentPair {
        public AspectStack[] aspects;
        public int capacity;

        public ComponentPair(int capacity, AspectStack[] aspects) {
            this.capacity = capacity;
            this.aspects = aspects;
        }
    }
}
