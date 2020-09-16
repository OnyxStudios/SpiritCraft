package dev.onyxstudios.spiritcraft.api.components.research;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.api.aspects.Aspect;
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
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class ResearchComponent implements IResearchComponent {

    public static final ComponentKey<ResearchComponent> RESEARCH = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(SpiritCraft.MODID, "research"), ResearchComponent.class);

    protected List<Identifier> unlockedAspects = new ArrayList<>();
    protected List<AspectStack> playerAspects = new ArrayList<>();

    protected List<Block> scannedBlocks = new ArrayList<>();
    protected List<Item> scannedItems = new ArrayList<>();
    protected List<EntityType<?>> scannedEntities = new ArrayList<>();

    @Override
    public boolean isAspectUnlocked(Aspect aspect) {
        for (Identifier identifier : unlockedAspects) {
            if(aspect.getId().equals(identifier))
                return true;
        }

        return false;
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
            playerAspects.add(stack);
        }
    }

    @Override
    public boolean isScanned(Block block) {
        for (Block scannedBlock : scannedBlocks) {
            if(block.is(scannedBlock))
                return true;
        }

        return false;
    }

    @Override
    public boolean isScanned(Item item) {
        for (Item scannedItem : scannedItems) {
            if(item == scannedItem)
                return true;
        }

        if(item instanceof BlockItem) {
            return isScanned(((BlockItem) item).getBlock());
        }

        return false;
    }

    @Override
    public boolean isScanned(Entity entity) {
        for (EntityType<?> scannedEntity : scannedEntities) {
            if(entity.getType() == scannedEntity)
                return true;
        }

        if(entity instanceof ItemEntity) {
            return isScanned(((ItemEntity) entity).getStack().getItem());
        }

        return false;
    }

    @Override
    public void scanObject(Block block) {
        if(!scannedBlocks.contains(block))
            scannedBlocks.add(block);
    }

    @Override
    public void scanObject(Item item) {
        if(item instanceof BlockItem) {
            scannedBlocks.add(((BlockItem) item).getBlock());
        }else {
            if(!scannedItems.contains(item))
                scannedItems.add(item);
        }
    }

    @Override
    public void scanObject(Entity entity) {
        if(!scannedEntities.contains(entity.getType()))
            scannedEntities.add(entity.getType());
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
            unlockedAspects.add(new Identifier(tag.asString()));
        }

        ListTag playerAspectsTag = compoundTag.getList("playerAspects", NbtType.COMPOUND);
        for (Tag entry : playerAspectsTag) {
            CompoundTag tag = (CompoundTag) entry;
            Identifier aspectId = new Identifier(tag.getString("id"));
            int count = tag.getInt("count");

            playerAspects.add(new AspectStack(SpiritCraftAspects.getAspect(aspectId), count));
        }

        ListTag scannedBlocksTag = compoundTag.getList("scannedBlocks", NbtType.COMPOUND);
        for (Tag tag : scannedBlocksTag) {
            scannedBlocks.add(Registry.BLOCK.get(new Identifier(tag.asString())));
        }

        ListTag scannedItemsTag = compoundTag.getList("scannedItems", NbtType.COMPOUND);
        for (Tag tag : scannedItemsTag) {
            scannedItems.add(Registry.ITEM.get(new Identifier(tag.asString())));
        }

        ListTag scannedEntitiesTag = compoundTag.getList("scannedEntities", NbtType.COMPOUND);
        for (Tag tag : scannedEntitiesTag) {
            scannedEntities.add(Registry.ENTITY_TYPE.get(new Identifier(tag.asString())));
        }
    }

    @Override
    public void writeToNbt(CompoundTag compoundTag) {
        ListTag unlockedAspectsTag = new ListTag();
        for (Identifier id : unlockedAspects) {
            unlockedAspectsTag.add(StringTag.of(id.toString()));
        }

        ListTag playerAspectsTag = new ListTag();
        for (AspectStack stack : playerAspects) {
            CompoundTag aspectTag = new CompoundTag();
            aspectTag.putString("id", stack.getAspect().getId().toString());
            aspectTag.putInt("count", stack.getCount());
            playerAspectsTag.add(aspectTag);
        }

        ListTag scannedBlocksTag = new ListTag();
        for (Block block : scannedBlocks) {
            scannedBlocksTag.add(StringTag.of(Registry.BLOCK.getId(block).toString()));
        }

        ListTag scannedItemsTag = new ListTag();
        for (Item item : scannedItems) {
            scannedItemsTag.add(StringTag.of(Registry.ITEM.getId(item).toString()));
        }

        ListTag scannedEntitiesTag = new ListTag();
        for (EntityType<?> type : scannedEntities) {
            scannedEntitiesTag.add(StringTag.of(Registry.ENTITY_TYPE.getId(type).toString()));
        }

        compoundTag.put("unlockedAspects", unlockedAspectsTag);
        compoundTag.put("playerAspects", playerAspectsTag);
        compoundTag.put("scannedBlocks", scannedBlocksTag);
        compoundTag.put("scannedItems", scannedItemsTag);
        compoundTag.put("scannedEntities", scannedEntitiesTag);
    }
}
