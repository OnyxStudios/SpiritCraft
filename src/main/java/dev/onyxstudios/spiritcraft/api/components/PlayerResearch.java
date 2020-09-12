package dev.onyxstudios.spiritcraft.api.components;

import dev.onyxstudios.spiritcraft.api.aspects.AspectStack;
import dev.onyxstudios.spiritcraft.api.aspects.SpiritCraftAspects;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class PlayerResearch {

    protected List<Identifier> unlockedAspects = new ArrayList<>();
    protected List<AspectStack> playerAspects = new ArrayList<>();

    protected List<Block> scannedBlocks = new ArrayList<>();
    protected List<Item> scannedItems = new ArrayList<>();
    protected List<EntityType<?>> scannedEntities = new ArrayList<>();

    public CompoundTag toTag() {
        CompoundTag compoundTag = new CompoundTag();

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
        return compoundTag;
    }

    public void fromTag(CompoundTag compoundTag) {
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
}
