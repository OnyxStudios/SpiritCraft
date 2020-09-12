package dev.onyxstudios.spiritcraft.api.aspects;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public class AspectMap {

    public static Map<Block, AspectStack[]> BLOCK_ASPECTS = new HashMap<>();
    public static Map<Item, AspectStack[]> ITEM_ASPECTS = new HashMap<>();
    public static Map<Entity, AspectStack[]> ENTITY_ASPECTS = new HashMap<>();

    static {
        //Map all Block/Item/Entity -> Aspects
    }

    public static void register(Block block, AspectStack... aspects) {
        BLOCK_ASPECTS.put(block, aspects);
    }

    public static void register(Item item, AspectStack... aspects) {
        ITEM_ASPECTS.put(item, aspects);
    }

    public static void register(Entity entity, AspectStack... aspects) {
        ENTITY_ASPECTS.put(entity, aspects);
    }

    public static AspectStack[] getAspects(Block block) {
        return BLOCK_ASPECTS.getOrDefault(block, new AspectStack[]{});
    }

    public static AspectStack[] getAspects(Item item) {
        //Added check in case a BlockItem is passed in
        if(item instanceof BlockItem) {
            return BLOCK_ASPECTS.getOrDefault(((BlockItem) item).getBlock(), new AspectStack[]{});
        }

        return ITEM_ASPECTS.getOrDefault(item, new AspectStack[]{});
    }

    public static AspectStack[] getAspects(Entity entity) {
        //Check if ItemEntity and return aspects for item
        if(entity instanceof ItemEntity) {
            Item item = ((ItemEntity) entity).getStack().getItem();
            //Check if item is actually a Block and return block aspects
            if(item instanceof BlockItem) {
                return BLOCK_ASPECTS.getOrDefault(((BlockItem) item).getBlock(), new AspectStack[]{});
            }

            return ITEM_ASPECTS.getOrDefault(item, new AspectStack[]{});
        }

        return ENTITY_ASPECTS.getOrDefault(entity, new AspectStack[]{});
    }
}
