package dev.onyxstudios.spiritcraft.api.aspects;

import dev.onyxstudios.spiritcraft.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;

import java.util.HashMap;
import java.util.Map;

public class AspectMap {

    public static Map<Block, AspectStack[]> BLOCK_ASPECTS = new HashMap<>();
    public static Map<Item, AspectStack[]> ITEM_ASPECTS = new HashMap<>();
    public static Map<EntityType<?>, AspectStack[]> ENTITY_ASPECTS = new HashMap<>();

    static {
        //Entity Mobs (To Do: Add Charged Creeper)
        register(EntityType.SKELETON, new AspectStack(SpiritCraftAspects.IMMORTUOS_ASPECT, 4), new AspectStack(SpiritCraftAspects.IMMORTUOS_ASPECT, 3), new AspectStack(SpiritCraftAspects.VIR_ASPECT), new AspectStack(SpiritCraftAspects.TELLUS_ASPECT));
        register(EntityType.WITHER_SKELETON, new AspectStack(SpiritCraftAspects.IMMORTUOS_ASPECT, 4), new AspectStack(SpiritCraftAspects.VIR_ASPECT), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 2));
        register(EntityType.GIANT, new AspectStack(SpiritCraftAspects.IMMORTUOS_ASPECT, 4), new AspectStack(SpiritCraftAspects.VIR_ASPECT, 3), new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 3));
        register(EntityType.ZOMBIE, new AspectStack(SpiritCraftAspects.IMMORTUOS_ASPECT, 2), new AspectStack(SpiritCraftAspects.VIR_ASPECT), new AspectStack(SpiritCraftAspects.TELLUS_ASPECT));
        register(EntityType.HORSE, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 4), new AspectStack(SpiritCraftAspects.TELLUS_ASPECT));
        register(EntityType.CREEPER, new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT, 2), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 2));
        register(EntityType.SHEEP, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 2), new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 2));
        register(EntityType.PIG, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 2), new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 2));
        register(EntityType.COW, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 2), new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 2));
        register(EntityType.SNOW_GOLEM, new AspectStack(SpiritCraftAspects.FRIGAS_ASPECT, 3), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(EntityType.OCELOT, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 3), new AspectStack(SpiritCraftAspects.VALE_ASPECT, 3));
        register(EntityType.MOOSHROOM, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 3), new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT), new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 2));
        register(EntityType.FOX, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 2), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT), new AspectStack(SpiritCraftAspects.TELLUS_ASPECT));
        register(EntityType.CHICKEN, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 2), new AspectStack(SpiritCraftAspects.VOLANT_ASPECT, 2), new AspectStack(SpiritCraftAspects.AURA_ASPECT));
        register(EntityType.SQUID, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 2), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT, 2));
        register(EntityType.BEE, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT), new AspectStack(SpiritCraftAspects.AURA_ASPECT), new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT));
        register(EntityType.LLAMA, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 4), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(EntityType.PANDA, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 5), new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT));
        register(EntityType.HOGLIN, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 4), new AspectStack(SpiritCraftAspects.ITUS_ASPECT), new AspectStack(SpiritCraftAspects.FURTA_ASPECT, 2), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT));
        register(EntityType.PIGLIN, new AspectStack(SpiritCraftAspects.VIR_ASPECT, 2), new AspectStack(SpiritCraftAspects.FURTA_ASPECT, 2), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT));
        register(EntityType.ZOMBIFIED_PIGLIN, new AspectStack(SpiritCraftAspects.IMMORTUOS_ASPECT, 4), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 2));
        register(EntityType.BAT, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT), new AspectStack(SpiritCraftAspects.VOLANT_ASPECT), new AspectStack(SpiritCraftAspects.AURA_ASPECT));
        register(EntityType.WOLF, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 3), new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 3));
        register(EntityType.DONKEY, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 3), new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 2));
        register(EntityType.SPIDER, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 3), new AspectStack(SpiritCraftAspects.VALE_ASPECT, 2));
        register(EntityType.MULE, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 4), new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.STATERA_ASPECT));
        register(EntityType.SLIME, new AspectStack(SpiritCraftAspects.GELATA_ASPECT, 2), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT, 2));
        register(EntityType.GHAST, new AspectStack(SpiritCraftAspects.IMMORTUOS_ASPECT, 3), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 2));
        register(EntityType.EVOKER, new AspectStack(SpiritCraftAspects.MAGUS_ASPECT, 3), new AspectStack(SpiritCraftAspects.VIR_ASPECT, 2));
        register(EntityType.DOLPHIN, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 3), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT, 2), new AspectStack(SpiritCraftAspects.SEMITA_ASPECT));
        register(EntityType.ENDERMAN, new AspectStack(SpiritCraftAspects.NOVIS_ASPECT, 4), new AspectStack(SpiritCraftAspects.SEMITA_ASPECT, 2), new AspectStack(SpiritCraftAspects.AURA_ASPECT, 2));
        register(EntityType.CAVE_SPIDER, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 2), new AspectStack(SpiritCraftAspects.OSTIUM_ASPECT, 2), new AspectStack(SpiritCraftAspects.TELLUS_ASPECT));
        register(EntityType.PHANTOM, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 2), new AspectStack(SpiritCraftAspects.VOLANT_ASPECT, 2), new AspectStack(SpiritCraftAspects.NOVIS_ASPECT));
        register(EntityType.SILVERFISH, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT), new AspectStack(SpiritCraftAspects.TELLUS_ASPECT));
        register(EntityType.BLAZE, new AspectStack(SpiritCraftAspects.NOVIS_ASPECT, 4), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT));
        register(EntityType.RAVAGER, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 6), new AspectStack(SpiritCraftAspects.VALE_ASPECT, 4));
        register(EntityType.CAT, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT), new AspectStack(SpiritCraftAspects.ORDIN_ASPECT));
        register(EntityType.COD, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(EntityType.SALMON, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(EntityType.TROPICAL_FISH, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(EntityType.TURTLE, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(EntityType.PUFFERFISH, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT), new AspectStack(SpiritCraftAspects.OSTIUM_ASPECT));
        register(EntityType.PARROT, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT), new AspectStack(SpiritCraftAspects.VOLANT_ASPECT));
        register(EntityType.DROWNED, new AspectStack(SpiritCraftAspects.IMMORTUOS_ASPECT, 2), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(EntityType.ILLUSIONER, new AspectStack(SpiritCraftAspects.VIR_ASPECT, 2), new AspectStack(SpiritCraftAspects.MAGUS_ASPECT, 3), new AspectStack(SpiritCraftAspects.VALE_ASPECT));
        register(EntityType.MAGMA_CUBE, new AspectStack(SpiritCraftAspects.GELATA_ASPECT, 3), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 2));
        register(EntityType.STRAY, new AspectStack(SpiritCraftAspects.IMMORTUOS_ASPECT, 2), new AspectStack(SpiritCraftAspects.FRIGAS_ASPECT, 2));
        register(EntityType.HUSK, new AspectStack(SpiritCraftAspects.IMMORTUOS_ASPECT, 2), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 2));
        register(EntityType.GUARDIAN, new AspectStack(SpiritCraftAspects.NOVIS_ASPECT, 2), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT, 3));
        register(EntityType.RABBIT, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT), new AspectStack(SpiritCraftAspects.AURA_ASPECT));
        register(EntityType.ENDERMITE, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT), new AspectStack(SpiritCraftAspects.NOVIS_ASPECT));
        register(EntityType.SHULKER, new AspectStack(SpiritCraftAspects.NOVIS_ASPECT, 2), new AspectStack(SpiritCraftAspects.MAGUS_ASPECT));
        register(EntityType.STRIDER, new AspectStack(SpiritCraftAspects.SEMITA_ASPECT, 2), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 2));
        register(EntityType.PILLAGER, new AspectStack(SpiritCraftAspects.VIR_ASPECT, 3), new AspectStack(SpiritCraftAspects.VALE_ASPECT, 2));
        register(EntityType.VINDICATOR, new AspectStack(SpiritCraftAspects.VIR_ASPECT, 3), new AspectStack(SpiritCraftAspects.GLADIO_ASPECT, 2));
        register(EntityType.VEX, new AspectStack(SpiritCraftAspects.VOLANT_ASPECT), new AspectStack(SpiritCraftAspects.MAGUS_ASPECT));
        register(EntityType.ZOGLIN, new AspectStack(SpiritCraftAspects.IMMORTUOS_ASPECT, 4), new AspectStack(SpiritCraftAspects.ITUS_ASPECT), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT));
        register(EntityType.ELDER_GUARDIAN, new AspectStack(SpiritCraftAspects.NOVIS_ASPECT, 20), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT, 20));
        register(EntityType.POLAR_BEAR, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 4), new AspectStack(SpiritCraftAspects.FRIGAS_ASPECT, 2));
        register(EntityType.PIGLIN_BRUTE, new AspectStack(SpiritCraftAspects.VIR_ASPECT, 2), new AspectStack(SpiritCraftAspects.FURTA_ASPECT, 2), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 2));
        register(EntityType.ENDER_DRAGON, new AspectStack(SpiritCraftAspects.NOVIS_ASPECT, 20), new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 20), new AspectStack(SpiritCraftAspects.VALE_ASPECT, 20));
        register(EntityType.WANDERING_TRADER, new AspectStack(SpiritCraftAspects.VIR_ASPECT, 3), new AspectStack(SpiritCraftAspects.AURA_ASPECT, 2), new AspectStack(SpiritCraftAspects.STATERA_ASPECT));
        register(EntityType.VILLAGER, new AspectStack(SpiritCraftAspects.VIR_ASPECT, 3), new AspectStack(SpiritCraftAspects.AURA_ASPECT, 2));
        register(EntityType.ZOMBIE_VILLAGER, new AspectStack(SpiritCraftAspects.IMMORTUOS_ASPECT, 3), new AspectStack(SpiritCraftAspects.AURA_ASPECT, 2));
        register(EntityType.WITHER, new AspectStack(SpiritCraftAspects.IMMORTUOS_ASPECT, 20), new AspectStack(SpiritCraftAspects.VALE_ASPECT, 20), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 15));
        register(EntityType.WITCH, new AspectStack(SpiritCraftAspects.VIR_ASPECT, 3), new AspectStack(SpiritCraftAspects.MAGUS_ASPECT, 2), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT));
        register(EntityType.IRON_GOLEM, new AspectStack(SpiritCraftAspects.AES_ASPECT, 4), new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 3));

        //Tagged Blocks/Items
        register(BlockTags.BASE_STONE_OVERWORLD, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT));
        register(BlockTags.LOGS_THAT_BURN, new AspectStack(SpiritCraftAspects.SILVA_ASPECT, 4)); //Non Nether Logs
        register(BlockTags.PLANKS, new AspectStack(SpiritCraftAspects.SILVA_ASPECT));
        register(BlockTags.WOODEN_SLABS, new AspectStack(SpiritCraftAspects.SILVA_ASPECT));
        register(BlockTags.WOODEN_STAIRS, new AspectStack(SpiritCraftAspects.SILVA_ASPECT));
        register(ItemTags.SAPLINGS, new AspectStack(SpiritCraftAspects.SILVA_ASPECT), new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT, 2));
        register(ModItems.DYES, new AspectStack(SpiritCraftAspects.VISUS_ASPECT));
        register(BlockTags.LEAVES, new AspectStack(SpiritCraftAspects.SILVA_ASPECT));
    }

    public static void register(Block block, AspectStack... aspects) {
        BLOCK_ASPECTS.put(block, aspects);
    }

    public static void register(Item item, AspectStack... aspects) {
        ITEM_ASPECTS.put(item, aspects);
    }

    public static void register(EntityType<?> entity, AspectStack... aspects) {
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

        return ENTITY_ASPECTS.getOrDefault(entity.getType(), new AspectStack[]{});
    }

    public static void register(Tag<?> tag, AspectStack... aspects) {
        for (Object object : tag.values()) {
            if(object instanceof Block)
                register((Block) object, aspects);
            else if(object instanceof Block)
                register((Item) object, aspects);
        }
    }
}
