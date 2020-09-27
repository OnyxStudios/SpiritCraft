package dev.onyxstudios.spiritcraft.api.components.research;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.api.aspects.Aspect;
import dev.onyxstudios.spiritcraft.api.aspects.AspectMap;
import dev.onyxstudios.spiritcraft.api.aspects.AspectStack;
import dev.onyxstudios.spiritcraft.api.aspects.SpiritCraftAspects;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class ResearchComponent implements IResearchComponent {

    public static final ComponentKey<IResearchComponent> RESEARCH = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(SpiritCraft.MODID, "research"), IResearchComponent.class);

    protected List<Identifier> unlockedAspects = new ArrayList<>();
    protected List<AspectStack> playerAspects = new ArrayList<>();

    protected List<Block> scannedBlocks = new ArrayList<>();
    protected List<Item> scannedItems = new ArrayList<>();
    protected List<EntityType<?>> scannedEntities = new ArrayList<>();

    @Override
    public boolean isAspectUnlocked(Aspect aspect) {
        for (Aspect primal : SpiritCraftAspects.getPrimalAspects()) {
            if(primal.getId().equals(aspect.getId()))
                return true;
        }

        return unlockedAspects.contains(aspect.getId());
    }

    @Override
    public AspectStack getStackForAspect(Aspect aspect) {
        for (AspectStack stack : playerAspects) {
            if(stack.getAspect() == aspect)
                return stack;
        }

        return null;
    }

    @Override
    public void useAspect(Aspect aspect, int amount) {
        for (AspectStack stack : playerAspects) {
            if(stack.getAspect() == aspect) {
                stack.shrink(amount);
            }
        }
    }

    @Override
    public void addAspect(AspectStack stack) {
        if(getStackForAspect(stack.getAspect()) != null) {
            for (AspectStack storedStack : playerAspects) {
                if (storedStack.getAspect() == stack.getAspect()) {
                    stack.grow(stack.getCount());
                }
            }
        }else {
            playerAspects.add(stack.copy());
        }
    }

    @Override
    public boolean isScanned(Block block) {
        return scannedBlocks.contains(block);
    }

    @Override
    public boolean isScanned(Item item) {
        if(item instanceof BlockItem) {
            return isScanned(((BlockItem) item).getBlock());
        }

        return scannedItems.contains(item);
    }

    @Override
    public boolean isScanned(Entity entity) {
        if(scannedEntities.contains(entity.getType()))
            return true;

        if(entity instanceof ItemEntity) {
            return isScanned(((ItemEntity) entity).getStack().getItem());
        }

        return false;
    }

    @Override
    public void scanObject(Block block) {
        if(!scannedBlocks.contains(block)) {
            scannedBlocks.add(block);
            for (AspectStack stack : AspectMap.getAspects(block)) {
                addAspect(stack.copy());

                if(!unlockedAspects.contains(stack.getAspect().getId()))
                    unlockedAspects.add(stack.getAspect().getId());
            }
        }
    }

    @Override
    public void scanObject(Item item) {
        if(item instanceof BlockItem) {
            scanObject(((BlockItem) item).getBlock());
        }else if(!scannedItems.contains(item)) {
            scannedItems.add(item);
            for (AspectStack stack : AspectMap.getAspects(item)) {
                addAspect(stack.copy());

                if(!unlockedAspects.contains(stack.getAspect().getId()))
                    unlockedAspects.add(stack.getAspect().getId());
            }
        }
    }

    @Override
    public void scanObject(Entity entity) {
        if(entity instanceof ItemEntity) {
            scanObject(((ItemEntity) entity).getStack().getItem());
        }else if(!scannedEntities.contains(entity.getType())) {
            scannedEntities.add(entity.getType());
            for (AspectStack stack : AspectMap.getAspects(entity)) {
                addAspect(stack.copy());

                if(!unlockedAspects.contains(stack.getAspect().getId()))
                    unlockedAspects.add(stack.getAspect().getId());
            }
        }
    }

    @Override
    public List<Identifier> getUnlockedAspects() {
        return this.unlockedAspects;
    }

    @Override
    public List<AspectStack> getPlayerAspects() {
        return this.playerAspects;
    }

    @Override
    public List<Block> getScannedBlocks() {
        return this.scannedBlocks;
    }

    @Override
    public List<Item> getScannedItems() {
        return this.scannedItems;
    }

    @Override
    public List<EntityType<?>> getScannedEntities() {
        return this.scannedEntities;
    }

    @Override
    public void readFromNbt(CompoundTag compoundTag) {
        ListTag unlockedAspectsTag = compoundTag.getList("unlockedAspects", NbtType.COMPOUND);
        for (Tag tag : unlockedAspectsTag) {
            unlockedAspects.add(new Identifier(((CompoundTag) tag).getString("id")));
        }

        ListTag playerAspectsTag = compoundTag.getList("playerAspects", NbtType.COMPOUND);
        for (Tag entry : playerAspectsTag) {
            CompoundTag tag = (CompoundTag) entry;
            Identifier aspectId = new Identifier(tag.getString("id"));
            float count = tag.getInt("putFloat");

            playerAspects.add(new AspectStack(SpiritCraftAspects.getAspect(aspectId), count));
        }

        ListTag scannedBlocksTag = compoundTag.getList("scannedBlocks", NbtType.COMPOUND);
        for (Tag tag : scannedBlocksTag) {
            scannedBlocks.add(Registry.BLOCK.get(new Identifier(((CompoundTag) tag).getString("id"))));
        }

        ListTag scannedItemsTag = compoundTag.getList("scannedItems", NbtType.COMPOUND);
        for (Tag tag : scannedItemsTag) {
            scannedItems.add(Registry.ITEM.get(new Identifier(((CompoundTag) tag).getString("id"))));
        }

        ListTag scannedEntitiesTag = compoundTag.getList("scannedEntities", NbtType.COMPOUND);
        for (Tag tag : scannedEntitiesTag) {
            scannedEntities.add(Registry.ENTITY_TYPE.get(new Identifier(((CompoundTag) tag).getString("id"))));
        }
    }

    @Override
    public void writeToNbt(CompoundTag compoundTag) {
        ListTag unlockedAspectsTag = new ListTag();
        for (Identifier id : unlockedAspects) {
            CompoundTag tag = new CompoundTag();
            tag.putString("id", id.toString());
            unlockedAspectsTag.add(tag);
        }

        ListTag playerAspectsTag = new ListTag();
        for (AspectStack stack : playerAspects) {
            CompoundTag aspectTag = new CompoundTag();
            aspectTag.putString("id", stack.getAspect().getId().toString());
            aspectTag.putFloat("count", stack.getCount());
            playerAspectsTag.add(aspectTag);
        }

        ListTag scannedBlocksTag = new ListTag();
        for (Block block : scannedBlocks) {
            CompoundTag tag = new CompoundTag();
            tag.putString("id", Registry.BLOCK.getId(block).toString());
            scannedBlocksTag.add(tag);
        }

        ListTag scannedItemsTag = new ListTag();
        for (Item item : scannedItems) {
            CompoundTag tag = new CompoundTag();
            tag.putString("id", Registry.ITEM.getId(item).toString());
            scannedItemsTag.add(tag);
        }

        ListTag scannedEntitiesTag = new ListTag();
        for (EntityType<?> type : scannedEntities) {
            CompoundTag tag = new CompoundTag();
            tag.putString("id", Registry.ENTITY_TYPE.getId(type).toString());
            scannedEntitiesTag.add(tag);
        }

        compoundTag.put("unlockedAspects", unlockedAspectsTag);
        compoundTag.put("playerAspects", playerAspectsTag);
        compoundTag.put("scannedBlocks", scannedBlocksTag);
        compoundTag.put("scannedItems", scannedItemsTag);
        compoundTag.put("scannedEntities", scannedEntitiesTag);
    }
}
