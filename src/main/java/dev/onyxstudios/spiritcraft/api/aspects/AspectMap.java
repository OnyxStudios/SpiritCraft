package dev.onyxstudios.spiritcraft.api.aspects;

import dev.onyxstudios.spiritcraft.registry.ModBlocks;
import dev.onyxstudios.spiritcraft.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
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

        //Entities
        register(EntityType.MINECART, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT, 3), new AspectStack(SpiritCraftAspects.AURA_ASPECT, 2));
        register(EntityType.ITEM_FRAME, new AspectStack(SpiritCraftAspects.VISUS_ASPECT, 3), new AspectStack(SpiritCraftAspects.LINTEUM_ASPECT));
        register(EntityType.FURNACE_MINECART, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT, 3), new AspectStack(SpiritCraftAspects.AURA_ASPECT), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT));
        register(EntityType.TNT_MINECART, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT, 3), new AspectStack(SpiritCraftAspects.AURA_ASPECT), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT));
        register(EntityType.PAINTING, new AspectStack(SpiritCraftAspects.VISUS_ASPECT, 5), new AspectStack(SpiritCraftAspects.LINTEUM_ASPECT, 3));
        register(EntityType.CHEST_MINECART, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT, 3), new AspectStack(SpiritCraftAspects.AURA_ASPECT), new AspectStack(SpiritCraftAspects.INANIS_ASPECT));
        register(EntityType.EXPERIENCE_ORB, new AspectStack(SpiritCraftAspects.ANIMO_ASPECT, 5));
        register(EntityType.HOPPER_MINECART, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT, 3), new AspectStack(SpiritCraftAspects.AURA_ASPECT), new AspectStack(SpiritCraftAspects.STATERA_ASPECT));
        register(EntityType.SPAWNER_MINECART, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT, 3), new AspectStack(SpiritCraftAspects.AURA_ASPECT), new AspectStack(SpiritCraftAspects.MAGUS_ASPECT));
        register(EntityType.END_CRYSTAL, new AspectStack(SpiritCraftAspects.NOVIS_ASPECT, 3), new AspectStack(SpiritCraftAspects.REMEDIUM_ASPECT, 3));

        //Tagged Blocks/Items
        register(BlockTags.BASE_STONE_OVERWORLD, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT));
        register(BlockTags.LOGS_THAT_BURN, new AspectStack(SpiritCraftAspects.SILVA_ASPECT, 4)); //Non Nether Logs
        register(BlockTags.PLANKS, new AspectStack(SpiritCraftAspects.SILVA_ASPECT));
        register(BlockTags.SLABS, new AspectStack(SpiritCraftAspects.CREO_ASPECT));
        register(BlockTags.STAIRS, new AspectStack(SpiritCraftAspects.CREO_ASPECT, 2));
        register(BlockTags.SAPLINGS, new AspectStack(SpiritCraftAspects.SILVA_ASPECT), new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT, 2));
        register(ModItems.DYES, new AspectStack(SpiritCraftAspects.VISUS_ASPECT));
        register(BlockTags.LEAVES, new AspectStack(SpiritCraftAspects.SILVA_ASPECT));

        //Redstone/Transportation
        register(Blocks.RAIL, new AspectStack(SpiritCraftAspects.AES_ASPECT), new AspectStack(SpiritCraftAspects.SEMITA_ASPECT));
        register(Blocks.POWERED_RAIL, new AspectStack(SpiritCraftAspects.AES_ASPECT), new AspectStack(SpiritCraftAspects.INDUSTRIA_ASPECT));
        register(Blocks.DETECTOR_RAIL, new AspectStack(SpiritCraftAspects.AES_ASPECT), new AspectStack(SpiritCraftAspects.VISUS_ASPECT));
        register(Blocks.ACTIVATOR_RAIL, new AspectStack(SpiritCraftAspects.AES_ASPECT, 2));
        register(Items.MINECART, new AspectStack(SpiritCraftAspects.AES_ASPECT, 2), new AspectStack(SpiritCraftAspects.ITUS_ASPECT, 4));
        register(Items.SADDLE, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 2), new AspectStack(SpiritCraftAspects.LINTEUM_ASPECT, 3), new AspectStack(SpiritCraftAspects.ITUS_ASPECT, 3));
        register(ItemTags.BOATS, new AspectStack(SpiritCraftAspects.HYDRA_ASPECT, 4), new AspectStack(SpiritCraftAspects.ITUS_ASPECT, 4));
        register(Items.CARROT_ON_A_STICK, new AspectStack(SpiritCraftAspects.ITUS_ASPECT, 4));
        register(Items.WARPED_FUNGUS_ON_A_STICK, new AspectStack(SpiritCraftAspects.ITUS_ASPECT, 4));
        register(Items.ELYTRA, new AspectStack(SpiritCraftAspects.ITUS_ASPECT, 6), new AspectStack(SpiritCraftAspects.LINTEUM_ASPECT, 2));
        register(Blocks.DISPENSER, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT), new AspectStack(SpiritCraftAspects.STATERA_ASPECT), new AspectStack(SpiritCraftAspects.INANIS_ASPECT));
        register(Blocks.NOTE_BLOCK, new AspectStack(SpiritCraftAspects.VISUS_ASPECT, 4), new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT), new AspectStack(SpiritCraftAspects.AURA_ASPECT, 4));
        register(Blocks.PISTON, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT, 2), new AspectStack(SpiritCraftAspects.ITUS_ASPECT, 4));
        register(Blocks.STICKY_PISTON, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT, 2), new AspectStack(SpiritCraftAspects.ITUS_ASPECT, 4));
        register(Blocks.LEVER, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT));
        register(BlockTags.PRESSURE_PLATES, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT), new AspectStack(SpiritCraftAspects.VISUS_ASPECT));
        register(Blocks.REDSTONE_TORCH, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT, 2));
        register(BlockTags.FENCE_GATES, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT), new AspectStack(SpiritCraftAspects.SEMITA_ASPECT));
        register(Blocks.REDSTONE_LAMP, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT), new AspectStack(SpiritCraftAspects.RADIANT_ASPECT));
        register(Blocks.TRIPWIRE_HOOK, new AspectStack(SpiritCraftAspects.VISUS_ASPECT), new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT), new AspectStack(SpiritCraftAspects.DECIPULA_ASPECT));
        register(BlockTags.BUTTONS, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT));
        register(Blocks.CHEST, new AspectStack(SpiritCraftAspects.INANIS_ASPECT, 4));
        register(Blocks.DAYLIGHT_DETECTOR, new AspectStack(SpiritCraftAspects.VISUS_ASPECT, 2), new AspectStack(SpiritCraftAspects.RADIANT_ASPECT, 3), new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT, 3));
        register(Blocks.REDSTONE_BLOCK, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.INDUSTRIA_ASPECT, 3), new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT, 2));
        register(Blocks.HOPPER, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT), new AspectStack(SpiritCraftAspects.STATERA_ASPECT), new AspectStack(SpiritCraftAspects.ITUS_ASPECT));
        register(Blocks.DROPPER, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT), new AspectStack(SpiritCraftAspects.STATERA_ASPECT), new AspectStack(SpiritCraftAspects.INANIS_ASPECT));
        register(Blocks.OBSERVER, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT), new AspectStack(SpiritCraftAspects.STATERA_ASPECT), new AspectStack(SpiritCraftAspects.INANIS_ASPECT));
        register(BlockTags.DOORS, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT, 2), new AspectStack(SpiritCraftAspects.ITUS_ASPECT));
        register(Blocks.REPEATER, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT), new AspectStack(SpiritCraftAspects.ITUS_ASPECT), new AspectStack(SpiritCraftAspects.INDUSTRIA_ASPECT));
        register(Blocks.COMPARATOR, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT), new AspectStack(SpiritCraftAspects.ITUS_ASPECT), new AspectStack(SpiritCraftAspects.INDUSTRIA_ASPECT));
        register(Blocks.REDSTONE_WIRE, new AspectStack(SpiritCraftAspects.INDUSTRIA_ASPECT, 2), new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT));
        register(Blocks.LECTERN, new AspectStack(SpiritCraftAspects.SILVA_ASPECT, 2), new AspectStack(SpiritCraftAspects.ANIMO_ASPECT));
        register(Blocks.TARGET, new AspectStack(SpiritCraftAspects.ITUS_ASPECT), new AspectStack(SpiritCraftAspects.VASA_ASPECT), new AspectStack(SpiritCraftAspects.VISUS_ASPECT));

        //Food
        register(Items.APPLE, new AspectStack(SpiritCraftAspects.SEGES_ASPECT, 2), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT));
        register(Items.MUSHROOM_STEW, new AspectStack(SpiritCraftAspects.ESURIES_ASPECT, 4));
        register(Items.BREAD, new AspectStack(SpiritCraftAspects.SEGES_ASPECT), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT, 2));
        register(Items.PORKCHOP, new AspectStack(SpiritCraftAspects.CARNES_ASPECT, 3), new AspectStack(SpiritCraftAspects.VITA_ASPECT), new AspectStack(SpiritCraftAspects.CREATURA_ASPECT));
        register(Items.COOKED_PORKCHOP, new AspectStack(SpiritCraftAspects.CREO_ASPECT), new AspectStack(SpiritCraftAspects.CARNES_ASPECT, 3), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT, 3));
        register(Items.GOLDEN_APPLE, new AspectStack(SpiritCraftAspects.MAGUS_ASPECT, 2), new AspectStack(SpiritCraftAspects.REMEDIUM_ASPECT, 4));
        register(Items.ENCHANTED_GOLDEN_APPLE, new AspectStack(SpiritCraftAspects.MAGUS_ASPECT, 4), new AspectStack(SpiritCraftAspects.REMEDIUM_ASPECT, 8));
        register(Items.COD, new AspectStack(SpiritCraftAspects.CARNES_ASPECT, 3), new AspectStack(SpiritCraftAspects.VITA_ASPECT), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(Items.SALMON, new AspectStack(SpiritCraftAspects.CARNES_ASPECT, 3), new AspectStack(SpiritCraftAspects.VITA_ASPECT), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(Items.TROPICAL_FISH, new AspectStack(SpiritCraftAspects.CARNES_ASPECT, 3), new AspectStack(SpiritCraftAspects.VITA_ASPECT), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(Items.PUFFERFISH, new AspectStack(SpiritCraftAspects.CARNES_ASPECT, 3), new AspectStack(SpiritCraftAspects.VITA_ASPECT), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(Items.COOKED_COD, new AspectStack(SpiritCraftAspects.CREO_ASPECT), new AspectStack(SpiritCraftAspects.CARNES_ASPECT, 4), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(Items.COOKED_SALMON, new AspectStack(SpiritCraftAspects.CREO_ASPECT), new AspectStack(SpiritCraftAspects.CARNES_ASPECT, 4), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(Blocks.CAKE, new AspectStack(SpiritCraftAspects.HYDRA_ASPECT, 4), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT, 4), new AspectStack(SpiritCraftAspects.VITA_ASPECT, 4));
        register(Items.COOKIE, new AspectStack(SpiritCraftAspects.ESURIES_ASPECT));
        register(Items.MELON_SLICE, new AspectStack(SpiritCraftAspects.ESURIES_ASPECT));
        register(Items.DRIED_KELP, new AspectStack(SpiritCraftAspects.CREO_ASPECT), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(Items.BEEF, new AspectStack(SpiritCraftAspects.CARNES_ASPECT, 4), new AspectStack(SpiritCraftAspects.VITA_ASPECT, 2), new AspectStack(SpiritCraftAspects.CREATURA_ASPECT));
        register(Items.COOKED_BEEF, new AspectStack(SpiritCraftAspects.CREO_ASPECT), new AspectStack(SpiritCraftAspects.CARNES_ASPECT, 4), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT, 4));
        register(Items.CHICKEN, new AspectStack(SpiritCraftAspects.CARNES_ASPECT, 3), new AspectStack(SpiritCraftAspects.VITA_ASPECT, 2), new AspectStack(SpiritCraftAspects.CREATURA_ASPECT));
        register(Items.COOKED_CHICKEN, new AspectStack(SpiritCraftAspects.CREO_ASPECT), new AspectStack(SpiritCraftAspects.CARNES_ASPECT, 4), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT, 3));
        register(Items.ROTTEN_FLESH, new AspectStack(SpiritCraftAspects.VIR_ASPECT), new AspectStack(SpiritCraftAspects.CARNES_ASPECT, 2));
        register(Items.SPIDER_EYE, new AspectStack(SpiritCraftAspects.VISUS_ASPECT, 2), new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 2), new AspectStack(SpiritCraftAspects.OSTIUM_ASPECT, 2));
        register(Blocks.CARROTS, new AspectStack(SpiritCraftAspects.SEGES_ASPECT), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT), new AspectStack(SpiritCraftAspects.VISUS_ASPECT));
        register(Blocks.POTATOES, new AspectStack(SpiritCraftAspects.SEGES_ASPECT), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT), new AspectStack(SpiritCraftAspects.TELLUS_ASPECT));
        register(Items.BAKED_POTATO, new AspectStack(SpiritCraftAspects.SEGES_ASPECT), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT, 2));
        register(Items.POISONOUS_POTATO, new AspectStack(SpiritCraftAspects.SEGES_ASPECT), new AspectStack(SpiritCraftAspects.OSTIUM_ASPECT, 2));
        register(Items.PUMPKIN_PIE, new AspectStack(SpiritCraftAspects.ESURIES_ASPECT, 3), new AspectStack(SpiritCraftAspects.VITA_ASPECT, 2));
        register(Items.RABBIT, new AspectStack(SpiritCraftAspects.CARNES_ASPECT, 2), new AspectStack(SpiritCraftAspects.VITA_ASPECT), new AspectStack(SpiritCraftAspects.CREATURA_ASPECT));
        register(Items.COOKED_RABBIT, new AspectStack(SpiritCraftAspects.CREO_ASPECT), new AspectStack(SpiritCraftAspects.CARNES_ASPECT, 2), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT, 2));
        register(Items.RABBIT_STEW, new AspectStack(SpiritCraftAspects.CREO_ASPECT), new AspectStack(SpiritCraftAspects.CARNES_ASPECT, 2), new AspectStack(SpiritCraftAspects.SEGES_ASPECT), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT, 4));
        register(Items.MUTTON, new AspectStack(SpiritCraftAspects.CARNES_ASPECT, 3), new AspectStack(SpiritCraftAspects.VITA_ASPECT, 2), new AspectStack(SpiritCraftAspects.CREATURA_ASPECT));
        register(Items.COOKED_MUTTON, new AspectStack(SpiritCraftAspects.CREO_ASPECT), new AspectStack(SpiritCraftAspects.CARNES_ASPECT, 4), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT, 3));
        register(Items.BEETROOT, new AspectStack(SpiritCraftAspects.VISUS_ASPECT), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT));
        register(Items.BEETROOT_SOUP, new AspectStack(SpiritCraftAspects.ESURIES_ASPECT, 2));
        register(Blocks.SWEET_BERRY_BUSH, new AspectStack(SpiritCraftAspects.ESURIES_ASPECT), new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT));
        register(Items.HONEY_BOTTLE, new AspectStack(SpiritCraftAspects.ESURIES_ASPECT,3), new AspectStack(SpiritCraftAspects.VITA_ASPECT, 2), new AspectStack(SpiritCraftAspects.GELATA_ASPECT));

        //Combat
        register(Items.TURTLE_HELMET, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 8));
        register(Items.LEATHER_HELMET, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 8));
        register(Items.LEATHER_CHESTPLATE, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 12));
        register(Items.LEATHER_LEGGINGS, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 11));
        register(Items.LEATHER_BOOTS, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 7));

        register(Items.IRON_HELMET, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 8));
        register(Items.IRON_CHESTPLATE, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 12));
        register(Items.IRON_LEGGINGS, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 11));
        register(Items.IRON_BOOTS, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 7));

        register(Items.CHAINMAIL_HELMET, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 8));
        register(Items.CHAINMAIL_CHESTPLATE, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 12));
        register(Items.CHAINMAIL_LEGGINGS, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 11));
        register(Items.CHAINMAIL_BOOTS, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 7));

        register(Items.DIAMOND_HELMET, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 8));
        register(Items.DIAMOND_CHESTPLATE, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 12));
        register(Items.DIAMOND_LEGGINGS, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 11));
        register(Items.DIAMOND_BOOTS, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 7));

        register(Items.GOLDEN_HELMET, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 8));
        register(Items.GOLDEN_CHESTPLATE, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 12));
        register(Items.GOLDEN_LEGGINGS, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 11));
        register(Items.GOLDEN_BOOTS, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 7));

        register(Items.NETHERITE_HELMET, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 8));
        register(Items.NETHERITE_CHESTPLATE, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 12));
        register(Items.NETHERITE_LEGGINGS, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 11));
        register(Items.NETHERITE_BOOTS, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 7));

        register(ItemTags.ARROWS, new AspectStack(SpiritCraftAspects.GLADIO_ASPECT));
        register(Items.BOW, new AspectStack(SpiritCraftAspects.GLADIO_ASPECT), new AspectStack(SpiritCraftAspects.INANIS_ASPECT));
        register(Items.CROSSBOW, new AspectStack(SpiritCraftAspects.GLADIO_ASPECT, 2), new AspectStack(SpiritCraftAspects.INANIS_ASPECT));
        register(Items.WOODEN_SWORD, new AspectStack(SpiritCraftAspects.GLADIO_ASPECT, 2));
        register(Items.STONE_SWORD, new AspectStack(SpiritCraftAspects.GLADIO_ASPECT, 2));
        register(Items.IRON_SWORD, new AspectStack(SpiritCraftAspects.GLADIO_ASPECT, 2));
        register(Items.GOLDEN_SWORD, new AspectStack(SpiritCraftAspects.GLADIO_ASPECT, 2));
        register(Items.DIAMOND_SWORD, new AspectStack(SpiritCraftAspects.GLADIO_ASPECT, 2));
        register(Items.NETHERITE_SWORD, new AspectStack(SpiritCraftAspects.GLADIO_ASPECT, 2));
        register(Items.ENCHANTED_BOOK, new AspectStack(SpiritCraftAspects.ANIMO_ASPECT, 3), new AspectStack(SpiritCraftAspects.MAGUS_ASPECT));
        register(Items.SHIELD, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT));
        register(Items.TOTEM_OF_UNDYING, new AspectStack(SpiritCraftAspects.MAGUS_ASPECT, 2), new AspectStack(SpiritCraftAspects.VITA_ASPECT, 2), new AspectStack(SpiritCraftAspects.REMEDIUM_ASPECT, 2));
        register(Items.TRIDENT, new AspectStack(SpiritCraftAspects.ITUS_ASPECT), new AspectStack(SpiritCraftAspects.GLADIO_ASPECT));

        register(Items.FLINT_AND_STEEL, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 4));
        register(Items.WOODEN_AXE, new AspectStack(SpiritCraftAspects.VASA_ASPECT));
        register(Items.WOODEN_SHOVEL, new AspectStack(SpiritCraftAspects.VASA_ASPECT));
        register(Items.WOODEN_PICKAXE, new AspectStack(SpiritCraftAspects.VASA_ASPECT));
        register(Items.WOODEN_HOE, new AspectStack(SpiritCraftAspects.VASA_ASPECT));
        register(Items.STONE_AXE, new AspectStack(SpiritCraftAspects.VASA_ASPECT, 2));
        register(Items.STONE_SHOVEL, new AspectStack(SpiritCraftAspects.VASA_ASPECT, 2));
        register(Items.STONE_PICKAXE, new AspectStack(SpiritCraftAspects.VASA_ASPECT, 2));
        register(Items.STONE_HOE, new AspectStack(SpiritCraftAspects.VASA_ASPECT, 2));
        register(Items.GOLDEN_AXE, new AspectStack(SpiritCraftAspects.VASA_ASPECT, 2), new AspectStack(SpiritCraftAspects.AES_ASPECT, 2), new AspectStack(SpiritCraftAspects.FURTA_ASPECT, 2));
        register(Items.GOLDEN_SHOVEL, new AspectStack(SpiritCraftAspects.VASA_ASPECT, 2), new AspectStack(SpiritCraftAspects.AES_ASPECT, 2), new AspectStack(SpiritCraftAspects.FURTA_ASPECT, 2));
        register(Items.GOLDEN_PICKAXE, new AspectStack(SpiritCraftAspects.VASA_ASPECT, 2), new AspectStack(SpiritCraftAspects.AES_ASPECT, 2), new AspectStack(SpiritCraftAspects.FURTA_ASPECT, 2));
        register(Items.GOLDEN_HOE, new AspectStack(SpiritCraftAspects.VASA_ASPECT, 2), new AspectStack(SpiritCraftAspects.AES_ASPECT, 2), new AspectStack(SpiritCraftAspects.FURTA_ASPECT, 2));
        register(Items.IRON_AXE, new AspectStack(SpiritCraftAspects.VASA_ASPECT, 2), new AspectStack(SpiritCraftAspects.AES_ASPECT, 4));
        register(Items.IRON_SHOVEL, new AspectStack(SpiritCraftAspects.VASA_ASPECT, 2), new AspectStack(SpiritCraftAspects.AES_ASPECT, 4));
        register(Items.IRON_PICKAXE, new AspectStack(SpiritCraftAspects.VASA_ASPECT, 2), new AspectStack(SpiritCraftAspects.AES_ASPECT, 4));
        register(Items.IRON_HOE, new AspectStack(SpiritCraftAspects.VASA_ASPECT, 2), new AspectStack(SpiritCraftAspects.AES_ASPECT, 4));
        register(Items.DIAMOND_AXE, new AspectStack(SpiritCraftAspects.VASA_ASPECT, 4), new AspectStack(SpiritCraftAspects.AES_ASPECT, 2), new AspectStack(SpiritCraftAspects.CRYSTALLO_ASPECT, 4));
        register(Items.DIAMOND_SHOVEL, new AspectStack(SpiritCraftAspects.VASA_ASPECT, 4), new AspectStack(SpiritCraftAspects.AES_ASPECT, 2), new AspectStack(SpiritCraftAspects.CRYSTALLO_ASPECT, 4));
        register(Items.DIAMOND_PICKAXE, new AspectStack(SpiritCraftAspects.VASA_ASPECT, 4), new AspectStack(SpiritCraftAspects.AES_ASPECT, 2), new AspectStack(SpiritCraftAspects.CRYSTALLO_ASPECT, 4));
        register(Items.DIAMOND_HOE, new AspectStack(SpiritCraftAspects.VASA_ASPECT, 4), new AspectStack(SpiritCraftAspects.AES_ASPECT, 2), new AspectStack(SpiritCraftAspects.CRYSTALLO_ASPECT, 4));
        register(Items.NETHERITE_AXE, new AspectStack(SpiritCraftAspects.VASA_ASPECT, 4), new AspectStack(SpiritCraftAspects.AES_ASPECT, 4));
        register(Items.NETHERITE_SHOVEL, new AspectStack(SpiritCraftAspects.VASA_ASPECT, 4), new AspectStack(SpiritCraftAspects.AES_ASPECT, 4));
        register(Items.NETHERITE_PICKAXE, new AspectStack(SpiritCraftAspects.VASA_ASPECT, 4), new AspectStack(SpiritCraftAspects.AES_ASPECT, 4));
        register(Items.NETHERITE_HOE, new AspectStack(SpiritCraftAspects.VASA_ASPECT, 4), new AspectStack(SpiritCraftAspects.AES_ASPECT, 4));
        register(Items.COMPASS, new AspectStack(SpiritCraftAspects.AES_ASPECT), new AspectStack(SpiritCraftAspects.SEMITA_ASPECT));
        register(Items.CLOCK, new AspectStack(SpiritCraftAspects.AES_ASPECT), new AspectStack(SpiritCraftAspects.VASA_ASPECT));
        register(Items.FISHING_ROD, new AspectStack(SpiritCraftAspects.HYDRA_ASPECT), new AspectStack(SpiritCraftAspects.VASA_ASPECT));
        register(Items.SHEARS, new AspectStack(SpiritCraftAspects.VASA_ASPECT), new AspectStack(SpiritCraftAspects.TONDEO_ASPECT));
        register(Items.LEAD, new AspectStack(SpiritCraftAspects.ITUS_ASPECT), new AspectStack(SpiritCraftAspects.DECIPULA_ASPECT));
        register(Items.NAME_TAG, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 2), new AspectStack(SpiritCraftAspects.ANIMO_ASPECT, 2));

        register(Blocks.BEACON, new AspectStack(SpiritCraftAspects.SPHAERAM_ASPECT, 2), new AspectStack(SpiritCraftAspects.MAGUS_ASPECT, 2), new AspectStack(SpiritCraftAspects.STATERA_ASPECT, 2));
        register(Blocks.TURTLE_EGG, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 2), new AspectStack(SpiritCraftAspects.CREATURA_ASPECT), new AspectStack(SpiritCraftAspects.DECIPULA_ASPECT));
        register(Blocks.CONDUIT, new AspectStack(SpiritCraftAspects.ITUS_ASPECT, 2), new AspectStack(SpiritCraftAspects.ANIMO_ASPECT));
        register(Items.SCUTE, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT), new AspectStack(SpiritCraftAspects.CREATURA_ASPECT));
        register(ItemTags.COALS, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.INDUSTRIA_ASPECT, 2), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT));
        register(Items.DIAMOND, new AspectStack(SpiritCraftAspects.CRYSTALLO_ASPECT, 3), new AspectStack(SpiritCraftAspects.FURTA_ASPECT, 2));
        register(Items.GOLD_INGOT, new AspectStack(SpiritCraftAspects.AES_ASPECT, 3), new AspectStack(SpiritCraftAspects.FURTA_ASPECT, 3));
        register(Items.IRON_INGOT, new AspectStack(SpiritCraftAspects.AES_ASPECT, 4), new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 2));
        register(Items.NETHERITE_INGOT, new AspectStack(SpiritCraftAspects.AES_ASPECT, 4), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT), new AspectStack(SpiritCraftAspects.MAGUS_ASPECT));
        register(Items.NETHERITE_SCRAP, new AspectStack(SpiritCraftAspects.AES_ASPECT), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT), new AspectStack(SpiritCraftAspects.FURTA_ASPECT));
        register(Items.STICK, new AspectStack(SpiritCraftAspects.SILVA_ASPECT));
        register(Items.BOWL, new AspectStack(SpiritCraftAspects.SILVA_ASPECT), new AspectStack(SpiritCraftAspects.INANIS_ASPECT));
        register(Blocks.TRIPWIRE, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT), new AspectStack(SpiritCraftAspects.LINTEUM_ASPECT));
        register(Items.FEATHER, new AspectStack(SpiritCraftAspects.VOLANT_ASPECT, 2));
        register(Items.GUNPOWDER, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 4), new AspectStack(SpiritCraftAspects.VALE_ASPECT, 4));
        register(Blocks.WHEAT, new AspectStack(SpiritCraftAspects.SEGES_ASPECT), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT));
        register(Items.WHEAT, new AspectStack(SpiritCraftAspects.SEGES_ASPECT));
        register(Items.FLINT, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.VASA_ASPECT));
        register(Items.BUCKET, new AspectStack(SpiritCraftAspects.AES_ASPECT), new AspectStack(SpiritCraftAspects.INANIS_ASPECT, 2));
        register(Items.WATER_BUCKET, new AspectStack(SpiritCraftAspects.AES_ASPECT), new AspectStack(SpiritCraftAspects.INANIS_ASPECT), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(Items.LAVA_BUCKET, new AspectStack(SpiritCraftAspects.AES_ASPECT), new AspectStack(SpiritCraftAspects.INANIS_ASPECT), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT));
        register(Items.SNOWBALL, new AspectStack(SpiritCraftAspects.FRIGAS_ASPECT, 2));
        register(Items.MILK_BUCKET, new AspectStack(SpiritCraftAspects.AES_ASPECT), new AspectStack(SpiritCraftAspects.INANIS_ASPECT), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT));
        register(Items.PUFFERFISH_BUCKET, new AspectStack(SpiritCraftAspects.AES_ASPECT), new AspectStack(SpiritCraftAspects.INANIS_ASPECT), new AspectStack(SpiritCraftAspects.CREATURA_ASPECT));
        register(Items.SALMON_BUCKET, new AspectStack(SpiritCraftAspects.AES_ASPECT), new AspectStack(SpiritCraftAspects.INANIS_ASPECT), new AspectStack(SpiritCraftAspects.CREATURA_ASPECT));
        register(Items.COD_BUCKET, new AspectStack(SpiritCraftAspects.AES_ASPECT), new AspectStack(SpiritCraftAspects.INANIS_ASPECT), new AspectStack(SpiritCraftAspects.CREATURA_ASPECT));
        register(Items.TROPICAL_FISH, new AspectStack(SpiritCraftAspects.AES_ASPECT), new AspectStack(SpiritCraftAspects.INANIS_ASPECT), new AspectStack(SpiritCraftAspects.CREATURA_ASPECT));
        register(Items.BRICK, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT));
        register(Items.CLAY_BALL, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(Items.PAPER, new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT), new AspectStack(SpiritCraftAspects.ANIMO_ASPECT));
        register(Items.BOOK, new AspectStack(SpiritCraftAspects.ANIMO_ASPECT, 3));
        register(Items.SLIME_BALL, new AspectStack(SpiritCraftAspects.GELATA_ASPECT, 3));
        register(Items.EGG, new AspectStack(SpiritCraftAspects.VITA_ASPECT), new AspectStack(SpiritCraftAspects.GELATA_ASPECT), new AspectStack(SpiritCraftAspects.CREATURA_ASPECT));
        register(Items.GLOWSTONE_DUST, new AspectStack(SpiritCraftAspects.VISUS_ASPECT), new AspectStack(SpiritCraftAspects.RADIANT_ASPECT));
        register(Items.INK_SAC, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT), new AspectStack(SpiritCraftAspects.VASA_ASPECT));
        register(Items.COCOA_BEANS, new AspectStack(SpiritCraftAspects.SEGES_ASPECT), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT));
        register(Items.LAPIS_LAZULI, new AspectStack(SpiritCraftAspects.INFODIO_ASPECT), new AspectStack(SpiritCraftAspects.AES_ASPECT));
        register(Items.BONE_MEAL, new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT), new AspectStack(SpiritCraftAspects.MAGUS_ASPECT));
        register(Items.BONE, new AspectStack(SpiritCraftAspects.MORS_ASPECT), new AspectStack(SpiritCraftAspects.CARNES_ASPECT));
        register(Items.SUGAR, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT));
        register(Blocks.MELON_STEM, new AspectStack(SpiritCraftAspects.SEGES_ASPECT));
        register(Blocks.PUMPKIN_STEM, new AspectStack(SpiritCraftAspects.SEGES_ASPECT));
        register(Items.ENDER_PEARL, new AspectStack(SpiritCraftAspects.NOVIS_ASPECT, 4), new AspectStack(SpiritCraftAspects.MAGUS_ASPECT, 2), new AspectStack(SpiritCraftAspects.SEMITA_ASPECT));
        register(Items.BLAZE_ROD, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 2), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 4), new AspectStack(SpiritCraftAspects.MAGUS_ASPECT));
        register(Items.GOLD_NUGGET, new AspectStack(SpiritCraftAspects.AES_ASPECT), new AspectStack(SpiritCraftAspects.FURTA_ASPECT));
        register(Blocks.NETHER_WART, new AspectStack(SpiritCraftAspects.SEGES_ASPECT), new AspectStack(SpiritCraftAspects.MAGUS_ASPECT));
        register(Items.ENDER_EYE, new AspectStack(SpiritCraftAspects.ANIMO_ASPECT, 4));
        register(Items.FIRE_CHARGE, new AspectStack(SpiritCraftAspects.ITUS_ASPECT), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 2));
        register(Items.KNOWLEDGE_BOOK, new AspectStack(SpiritCraftAspects.ANIMO_ASPECT, 2));
        register(Items.WRITABLE_BOOK, new AspectStack(SpiritCraftAspects.ANIMO_ASPECT, 2));
        register(Items.WRITTEN_BOOK, new AspectStack(SpiritCraftAspects.ANIMO_ASPECT, 2));
        register(Items.EMERALD, new AspectStack(SpiritCraftAspects.CRYSTALLO_ASPECT, 4), new AspectStack(SpiritCraftAspects.FURTA_ASPECT, 2));
        register(Items.NETHER_STAR, new AspectStack(SpiritCraftAspects.NOVIS_ASPECT, 8), new AspectStack(SpiritCraftAspects.MAGUS_ASPECT, 8), new AspectStack(SpiritCraftAspects.ORDIN_ASPECT, 8), new AspectStack(SpiritCraftAspects.RADIANT_ASPECT, 8));
        register(Items.FIREWORK_ROCKET, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 2), new AspectStack(SpiritCraftAspects.VALE_ASPECT));
        register(Items.FIREWORK_STAR, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 2), new AspectStack(SpiritCraftAspects.VALE_ASPECT));
        register(Items.NETHER_BRICK, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT));
        register(Items.QUARTZ, new AspectStack(SpiritCraftAspects.CRYSTALLO_ASPECT), new AspectStack(SpiritCraftAspects.INDUSTRIA_ASPECT));
        register(Items.PRISMARINE_SHARD, new AspectStack(SpiritCraftAspects.CRYSTALLO_ASPECT), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(Items.PRISMARINE_CRYSTALS, new AspectStack(SpiritCraftAspects.CRYSTALLO_ASPECT), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(Items.RABBIT_HIDE, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT), new AspectStack(SpiritCraftAspects.CARNES_ASPECT));
        register(Items.LEATHER_HORSE_ARMOR, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 2));
        register(Items.IRON_HORSE_ARMOR, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 2));
        register(Items.GOLDEN_HORSE_ARMOR, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 2), new AspectStack(SpiritCraftAspects.FURTA_ASPECT));
        register(Items.DIAMOND_HORSE_ARMOR, new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT, 2));
        register(Items.CHORUS_FRUIT, new AspectStack(SpiritCraftAspects.NOVIS_ASPECT, 2), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT));
        register(Items.POPPED_CHORUS_FRUIT, new AspectStack(SpiritCraftAspects.NOVIS_ASPECT, 2));
        register(Blocks.BEETROOTS, new AspectStack(SpiritCraftAspects.SEGES_ASPECT, 2));
        register(Items.SHULKER_SHELL, new AspectStack(SpiritCraftAspects.NOVIS_ASPECT), new AspectStack(SpiritCraftAspects.CREATURA_ASPECT));
        register(Items.IRON_NUGGET, new AspectStack(SpiritCraftAspects.AES_ASPECT));
        register(ItemTags.MUSIC_DISCS, new AspectStack(SpiritCraftAspects.AURA_ASPECT), new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT));
        register(Items.NAUTILUS_SHELL, new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(Items.HEART_OF_THE_SEA, new AspectStack(SpiritCraftAspects.HYDRA_ASPECT, 10));
        register(Items.PIGLIN_BANNER_PATTERN, new AspectStack(SpiritCraftAspects.LINTEUM_ASPECT));
        register(Items.HONEYCOMB, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT), new AspectStack(SpiritCraftAspects.GELATA_ASPECT));

        register(Blocks.COBWEB, new AspectStack(SpiritCraftAspects.DECIPULA_ASPECT, 2), new AspectStack(SpiritCraftAspects.LINTEUM_ASPECT));
        register(Blocks.TALL_GRASS, new AspectStack(SpiritCraftAspects.SEGES_ASPECT), new AspectStack(SpiritCraftAspects.AURA_ASPECT));
        register(Blocks.FERN, new AspectStack(SpiritCraftAspects.SEGES_ASPECT), new AspectStack(SpiritCraftAspects.AURA_ASPECT));
        register(Blocks.DEAD_BUSH, new AspectStack(SpiritCraftAspects.SEGES_ASPECT), new AspectStack(SpiritCraftAspects.AURA_ASPECT));
        register(Blocks.SEAGRASS, new AspectStack(SpiritCraftAspects.SEGES_ASPECT), new AspectStack(SpiritCraftAspects.AURA_ASPECT), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(ItemTags.FLOWERS, new AspectStack(SpiritCraftAspects.SEGES_ASPECT), new AspectStack(SpiritCraftAspects.AURA_ASPECT));
        register(ItemTags.SMALL_FLOWERS, new AspectStack(SpiritCraftAspects.SEGES_ASPECT), new AspectStack(SpiritCraftAspects.AURA_ASPECT));
        register(ItemTags.TALL_FLOWERS, new AspectStack(SpiritCraftAspects.SEGES_ASPECT), new AspectStack(SpiritCraftAspects.AURA_ASPECT));
        register(Blocks.SUGAR_CANE, new AspectStack(SpiritCraftAspects.SEGES_ASPECT), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT), new AspectStack(SpiritCraftAspects.AURA_ASPECT));
        register(Blocks.BAMBOO, new AspectStack(SpiritCraftAspects.SEGES_ASPECT), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT), new AspectStack(SpiritCraftAspects.AURA_ASPECT));
        register(Blocks.TORCH, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT), new AspectStack(SpiritCraftAspects.INDUSTRIA_ASPECT), new AspectStack(SpiritCraftAspects.RADIANT_ASPECT, 2));
        register(Blocks.END_ROD, new AspectStack(SpiritCraftAspects.NOVIS_ASPECT));
        register(Blocks.CHORUS_PLANT, new AspectStack(SpiritCraftAspects.SEGES_ASPECT), new AspectStack(SpiritCraftAspects.NOVIS_ASPECT));
        register(Blocks.CHORUS_FLOWER, new AspectStack(SpiritCraftAspects.SEGES_ASPECT), new AspectStack(SpiritCraftAspects.NOVIS_ASPECT));
        register(Blocks.CRAFTING_TABLE, new AspectStack(SpiritCraftAspects.CREO_ASPECT, 4), new AspectStack(SpiritCraftAspects.SILVA_ASPECT));
        register(Blocks.FARMLAND, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(Blocks.FURNACE, new AspectStack(SpiritCraftAspects.CREO_ASPECT, 2), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT));
        register(Blocks.LADDER, new AspectStack(SpiritCraftAspects.SILVA_ASPECT));
        register(Blocks.SNOW, new AspectStack(SpiritCraftAspects.FRIGAS_ASPECT, 2));
        register(Blocks.SNOW_BLOCK, new AspectStack(SpiritCraftAspects.FRIGAS_ASPECT, 4));
        register(Blocks.CACTUS, new AspectStack(SpiritCraftAspects.SEGES_ASPECT, 2), new AspectStack(SpiritCraftAspects.DECIPULA_ASPECT));
        register(Blocks.JUKEBOX, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT), new AspectStack(SpiritCraftAspects.ANIMO_ASPECT), new AspectStack(SpiritCraftAspects.AURA_ASPECT));
        register(BlockTags.FENCES, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT), new AspectStack(SpiritCraftAspects.SEMITA_ASPECT));
        register(Blocks.SOUL_TORCH, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT), new AspectStack(SpiritCraftAspects.SOL_ASPECT, 4));
        register(Blocks.BROWN_MUSHROOM_BLOCK, new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT, 2));
        register(Blocks.RED_MUSHROOM, new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT, 2));
        register(Blocks.MUSHROOM_STEM, new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT, 2));
        register(Blocks.IRON_BARS, new AspectStack(SpiritCraftAspects.AES_ASPECT, 4), new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT));
        register(Blocks.CHAIN, new AspectStack(SpiritCraftAspects.AES_ASPECT, 4), new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT));
        register(Blocks.VINE, new AspectStack(SpiritCraftAspects.SEGES_ASPECT, 2), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(Blocks.LILY_PAD, new AspectStack(SpiritCraftAspects.SEGES_ASPECT, 2), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(Blocks.TWISTING_VINES, new AspectStack(SpiritCraftAspects.SEGES_ASPECT, 2), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(Blocks.TWISTING_VINES_PLANT, new AspectStack(SpiritCraftAspects.SEGES_ASPECT, 2), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(Blocks.WEEPING_VINES_PLANT, new AspectStack(SpiritCraftAspects.SEGES_ASPECT, 2), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(Blocks.WEEPING_VINES, new AspectStack(SpiritCraftAspects.SEGES_ASPECT, 2), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(Blocks.ENCHANTING_TABLE, new AspectStack(SpiritCraftAspects.MAGUS_ASPECT, 8), new AspectStack(SpiritCraftAspects.CREO_ASPECT, 4));
        register(Blocks.END_PORTAL_FRAME, new AspectStack(SpiritCraftAspects.NOVIS_ASPECT, 8), new AspectStack(SpiritCraftAspects.INANIS_ASPECT));
        register(Blocks.END_PORTAL, new AspectStack(SpiritCraftAspects.NOVIS_ASPECT, 8), new AspectStack(SpiritCraftAspects.INANIS_ASPECT));
        register(Blocks.ENDER_CHEST, new AspectStack(SpiritCraftAspects.STATERA_ASPECT), new AspectStack(SpiritCraftAspects.NOVIS_ASPECT), new AspectStack(SpiritCraftAspects.INANIS_ASPECT));
        register(BlockTags.WALLS, new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT), new AspectStack(SpiritCraftAspects.TELLUS_ASPECT));
        register(Blocks.ANVIL, new AspectStack(SpiritCraftAspects.AES_ASPECT, 6), new AspectStack(SpiritCraftAspects.CREO_ASPECT, 2), new AspectStack(SpiritCraftAspects.ORDIN_ASPECT, 2));
        register(Blocks.CHIPPED_ANVIL, new AspectStack(SpiritCraftAspects.AES_ASPECT, 6), new AspectStack(SpiritCraftAspects.CREO_ASPECT, 2), new AspectStack(SpiritCraftAspects.ORDIN_ASPECT, 2));
        register(Blocks.DAMAGED_ANVIL, new AspectStack(SpiritCraftAspects.AES_ASPECT, 6), new AspectStack(SpiritCraftAspects.CREO_ASPECT, 2), new AspectStack(SpiritCraftAspects.ORDIN_ASPECT, 2));
        register(BlockTags.CARPETS, new AspectStack(SpiritCraftAspects.LINTEUM_ASPECT, 2));
        register(Blocks.SLIME_BLOCK, new AspectStack(SpiritCraftAspects.GELATA_ASPECT, 4));
        register(BlockTags.SHULKER_BOXES, new AspectStack(SpiritCraftAspects.NOVIS_ASPECT, 2), new AspectStack(SpiritCraftAspects.INANIS_ASPECT), new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT));
        register(BlockTags.CORAL_PLANTS, new AspectStack(SpiritCraftAspects.STATERA_ASPECT, 2), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT, 2));
        register(Blocks.SCAFFOLDING, new AspectStack(SpiritCraftAspects.ORDIN_ASPECT), new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT));
        register(BlockTags.SIGNS, new AspectStack(SpiritCraftAspects.ANIMO_ASPECT), new AspectStack(SpiritCraftAspects.SILVA_ASPECT));
        register(BlockTags.BEDS, new AspectStack(SpiritCraftAspects.ANIMO_ASPECT), new AspectStack(SpiritCraftAspects.LINTEUM_ASPECT), new AspectStack(SpiritCraftAspects.ORDIN_ASPECT));
        register(Blocks.FLOWER_POT, new AspectStack(SpiritCraftAspects.INANIS_ASPECT), new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT));
        register(Items.ARMOR_STAND, new AspectStack(SpiritCraftAspects.ANIMO_ASPECT), new AspectStack(SpiritCraftAspects.ARMATURA_ASPECT));
        register(BlockTags.BANNERS, new AspectStack(SpiritCraftAspects.LINTEUM_ASPECT, 2), new AspectStack(SpiritCraftAspects.ANIMO_ASPECT));
        register(Items.END_CRYSTAL, new AspectStack(SpiritCraftAspects.NOVIS_ASPECT, 2), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT), new AspectStack(SpiritCraftAspects.INANIS_ASPECT));
        register(Blocks.LOOM, new AspectStack(SpiritCraftAspects.LINTEUM_ASPECT), new AspectStack(SpiritCraftAspects.ANIMO_ASPECT));
        register(Blocks.FLETCHING_TABLE, new AspectStack(SpiritCraftAspects.LINTEUM_ASPECT), new AspectStack(SpiritCraftAspects.ANIMO_ASPECT));
        register(Blocks.CARTOGRAPHY_TABLE, new AspectStack(SpiritCraftAspects.LINTEUM_ASPECT), new AspectStack(SpiritCraftAspects.ANIMO_ASPECT));
        register(Blocks.COMPOSTER, new AspectStack(SpiritCraftAspects.ORDIN_ASPECT), new AspectStack(SpiritCraftAspects.INANIS_ASPECT), new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT));
        register(Blocks.BARREL, new AspectStack(SpiritCraftAspects.INANIS_ASPECT), new AspectStack(SpiritCraftAspects.STATERA_ASPECT), new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT));
        register(Blocks.SMOKER, new AspectStack(SpiritCraftAspects.INANIS_ASPECT), new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT), new AspectStack(SpiritCraftAspects.CREO_ASPECT), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT));
        register(Blocks.BLAST_FURNACE, new AspectStack(SpiritCraftAspects.INANIS_ASPECT), new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT), new AspectStack(SpiritCraftAspects.CREO_ASPECT), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT));
        register(Blocks.SMITHING_TABLE, new AspectStack(SpiritCraftAspects.INANIS_ASPECT), new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT), new AspectStack(SpiritCraftAspects.CREO_ASPECT));
        register(Blocks.STONECUTTER, new AspectStack(SpiritCraftAspects.INANIS_ASPECT), new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT), new AspectStack(SpiritCraftAspects.CREO_ASPECT));
        register(Blocks.GRINDSTONE, new AspectStack(SpiritCraftAspects.INANIS_ASPECT), new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT), new AspectStack(SpiritCraftAspects.CREO_ASPECT));
        register(Blocks.BELL, new AspectStack(SpiritCraftAspects.AES_ASPECT), new AspectStack(SpiritCraftAspects.ITUS_ASPECT));
        register(Blocks.LANTERN, new AspectStack(SpiritCraftAspects.RADIANT_ASPECT, 2));
        register(Blocks.SEA_LANTERN, new AspectStack(SpiritCraftAspects.RADIANT_ASPECT, 2));
        register(Blocks.SOUL_LANTERN, new AspectStack(SpiritCraftAspects.RADIANT_ASPECT, 2), new AspectStack(SpiritCraftAspects.SOL_ASPECT));
        register(Blocks.CAMPFIRE, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT), new AspectStack(SpiritCraftAspects.RADIANT_ASPECT), new AspectStack(SpiritCraftAspects.INDUSTRIA_ASPECT));
        register(Blocks.SOUL_CAMPFIRE, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT), new AspectStack(SpiritCraftAspects.RADIANT_ASPECT), new AspectStack(SpiritCraftAspects.INDUSTRIA_ASPECT));
        register(Blocks.SHROOMLIGHT, new AspectStack(SpiritCraftAspects.RADIANT_ASPECT), new AspectStack(SpiritCraftAspects.CREATURA_ASPECT));
        register(Blocks.BEE_NEST, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 2));
        register(Blocks.BEEHIVE, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT, 2));
        register(Blocks.HONEY_BLOCK, new AspectStack(SpiritCraftAspects.GELATA_ASPECT, 2));
        register(Blocks.HONEYCOMB_BLOCK, new AspectStack(SpiritCraftAspects.GELATA_ASPECT, 2));
        register(Blocks.GRASS_PATH, new AspectStack(SpiritCraftAspects.ORDIN_ASPECT), new AspectStack(SpiritCraftAspects.TELLUS_ASPECT));
        register(Blocks.LODESTONE, new AspectStack(SpiritCraftAspects.MAGUS_ASPECT, 2), new AspectStack(SpiritCraftAspects.SEMITA_ASPECT));
        register(Blocks.RESPAWN_ANCHOR, new AspectStack(SpiritCraftAspects.MAGUS_ASPECT, 2), new AspectStack(SpiritCraftAspects.NOVIS_ASPECT, 2));
        register(ModBlocks.GLASS_PANES, new AspectStack(SpiritCraftAspects.VISUS_ASPECT), new AspectStack(SpiritCraftAspects.CRYSTALLO_ASPECT));
        register(ModBlocks.GLAZED_TERRACOTTA, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.ORDIN_ASPECT), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT));
        register(ModBlocks.SKULLS, new AspectStack(SpiritCraftAspects.VALE_ASPECT), new AspectStack(SpiritCraftAspects.MORS_ASPECT), new AspectStack(SpiritCraftAspects.UMBRA_ASPECT), new AspectStack(SpiritCraftAspects.NOVIS_ASPECT), new AspectStack(SpiritCraftAspects.ANIMO_ASPECT));
        register(Items.POTION, new AspectStack(SpiritCraftAspects.MAGUS_ASPECT, 2), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT, 2));
        register(Items.SPLASH_POTION, new AspectStack(SpiritCraftAspects.MAGUS_ASPECT, 2), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT, 2));
        register(Items.LINGERING_POTION, new AspectStack(SpiritCraftAspects.MAGUS_ASPECT, 2), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT, 2));
        register(Items.GHAST_TEAR, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT), new AspectStack(SpiritCraftAspects.NOVIS_ASPECT));
        register(Items.FERMENTED_SPIDER_EYE, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT), new AspectStack(SpiritCraftAspects.CARNES_ASPECT));
        register(Items.BLAZE_POWDER, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 2));
        register(Items.MAGMA_CREAM, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT), new AspectStack(SpiritCraftAspects.GELATA_ASPECT));
        register(Blocks.BREWING_STAND, new AspectStack(SpiritCraftAspects.CREO_ASPECT, 2), new AspectStack(SpiritCraftAspects.MAGUS_ASPECT, 2));
        register(Blocks.CAULDRON, new AspectStack(SpiritCraftAspects.AES_ASPECT, 2), new AspectStack(SpiritCraftAspects.INANIS_ASPECT), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(Items.GLISTERING_MELON_SLICE, new AspectStack(SpiritCraftAspects.ESURIES_ASPECT), new AspectStack(SpiritCraftAspects.FURTA_ASPECT));
        register(Items.GOLDEN_CARROT, new AspectStack(SpiritCraftAspects.FURTA_ASPECT), new AspectStack(SpiritCraftAspects.VISUS_ASPECT));
        register(Items.RABBIT_FOOT, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT), new AspectStack(SpiritCraftAspects.MAGUS_ASPECT));
        register(Items.DRAGON_BREATH, new AspectStack(SpiritCraftAspects.MAGUS_ASPECT, 8), new AspectStack(SpiritCraftAspects.NOVIS_ASPECT, 6));
        register(Items.PHANTOM_MEMBRANE, new AspectStack(SpiritCraftAspects.CREATURA_ASPECT), new AspectStack(SpiritCraftAspects.VOLANT_ASPECT), new AspectStack(SpiritCraftAspects.NOVIS_ASPECT));
        register(Items.HONEY_BOTTLE, new AspectStack(SpiritCraftAspects.ESURIES_ASPECT, 3), new AspectStack(SpiritCraftAspects.VITA_ASPECT, 2), new AspectStack(SpiritCraftAspects.GELATA_ASPECT));

        //Building Blocks
        register(Blocks.POLISHED_GRANITE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.CREO_ASPECT));
        register(Blocks.POLISHED_DIORITE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.CREO_ASPECT));
        register(Blocks.POLISHED_ANDESITE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.CREO_ASPECT));
        register(Blocks.GRASS_BLOCK, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT));
        register(Blocks.DIRT, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 2));
        register(Blocks.COARSE_DIRT, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 1));
        register(Blocks.PODZOL, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT));
        register(Blocks.CRIMSON_NYLIUM, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 2), new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT));
        register(Blocks.WARPED_NYLIUM, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 2), new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT));
        register(Blocks.COBBLESTONE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.VALE_ASPECT));
        register(Blocks.BEDROCK, new AspectStack(SpiritCraftAspects.INANIS_ASPECT, 16), new AspectStack(SpiritCraftAspects.VALE_ASPECT, 16), new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 16), new AspectStack(SpiritCraftAspects.UMBRA_ASPECT, 16));
        register(Blocks.SAND, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.VALE_ASPECT));
        register(Blocks.RED_SAND, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.VALE_ASPECT));
        register(Blocks.GRAVEL, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 2));
        register(Blocks.GOLD_ORE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.AES_ASPECT, 2), new AspectStack(SpiritCraftAspects.FURTA_ASPECT));
        register(Blocks.IRON_ORE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.AES_ASPECT, 3));
        register(Blocks.COAL_ORE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.INDUSTRIA_ASPECT, 2), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT));
        register(Blocks.NETHER_GOLD_ORE, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT), new AspectStack(SpiritCraftAspects.AES_ASPECT), new AspectStack(SpiritCraftAspects.FURTA_ASPECT));
        register(Blocks.CRIMSON_STEM, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT), new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT, 2));
        register(Blocks.WARPED_STEM, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT), new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT, 2));
        register(Blocks.CRIMSON_HYPHAE, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT), new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT, 2));
        register(Blocks.WARPED_HYPHAE, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT), new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT, 2));
        register(ModBlocks.GLASS, new AspectStack(SpiritCraftAspects.CRYSTALLO_ASPECT), new AspectStack(SpiritCraftAspects.VISUS_ASPECT));
        register(Blocks.LAPIS_ORE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.VISUS_ASPECT, 3));
        register(Blocks.LAPIS_BLOCK, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 3), new AspectStack(SpiritCraftAspects.VISUS_ASPECT, 4));
        register(Blocks.SANDSTONE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT));
        register(Blocks.CHISELED_SANDSTONE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT));
        register(Blocks.CUT_SANDSTONE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT));
        register(BlockTags.WOOL, new AspectStack(SpiritCraftAspects.LINTEUM_ASPECT, 4), new AspectStack(SpiritCraftAspects.CREO_ASPECT));
        register(Blocks.GOLD_BLOCK, new AspectStack(SpiritCraftAspects.AES_ASPECT, 5), new AspectStack(SpiritCraftAspects.FURTA_ASPECT, 4));
        register(Blocks.IRON_BLOCK, new AspectStack(SpiritCraftAspects.AES_ASPECT, 6));
        register(Blocks.SMOOTH_QUARTZ, new AspectStack(SpiritCraftAspects.CRYSTALLO_ASPECT, 4), new AspectStack(SpiritCraftAspects.INDUSTRIA_ASPECT, 4));
        register(Blocks.SMOOTH_SANDSTONE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.CREO_ASPECT));
        register(Blocks.SMOOTH_RED_SANDSTONE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.CREO_ASPECT));
        register(Blocks.SMOOTH_STONE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.CREO_ASPECT), new AspectStack(SpiritCraftAspects.ORDIN_ASPECT));
        register(Blocks.BRICKS, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT));
        register(Blocks.BOOKSHELF, new AspectStack(SpiritCraftAspects.ANIMO_ASPECT, 8));
        register(Blocks.MOSSY_COBBLESTONE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT), new AspectStack(SpiritCraftAspects.MAGUS_ASPECT));
        register(Blocks.OBSIDIAN, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 2), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 2), new AspectStack(SpiritCraftAspects.UMBRA_ASPECT));
        register(Blocks.PURPUR_BLOCK, new AspectStack(SpiritCraftAspects.NOVIS_ASPECT, 4), new AspectStack(SpiritCraftAspects.CREO_ASPECT, 2));
        register(Blocks.PURPUR_PILLAR, new AspectStack(SpiritCraftAspects.NOVIS_ASPECT, 4), new AspectStack(SpiritCraftAspects.CREO_ASPECT, 2));
        register(Blocks.DIAMOND_ORE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.FURTA_ASPECT, 3), new AspectStack(SpiritCraftAspects.CRYSTALLO_ASPECT, 3));
        register(Blocks.DIAMOND_BLOCK, new AspectStack(SpiritCraftAspects.CRYSTALLO_ASPECT, 6), new AspectStack(SpiritCraftAspects.FURTA_ASPECT, 6));
        register(Blocks.REDSTONE_ORE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.INDUSTRIA_ASPECT, 2), new AspectStack(SpiritCraftAspects.OBFICINA_ASPECT, 2));
        register(Blocks.ICE, new AspectStack(SpiritCraftAspects.FRIGAS_ASPECT, 4), new AspectStack(SpiritCraftAspects.ORDIN_ASPECT));
        register(Blocks.PACKED_ICE, new AspectStack(SpiritCraftAspects.FRIGAS_ASPECT, 6), new AspectStack(SpiritCraftAspects.ORDIN_ASPECT));
        register(Blocks.BLUE_ICE, new AspectStack(SpiritCraftAspects.FRIGAS_ASPECT, 6), new AspectStack(SpiritCraftAspects.ORDIN_ASPECT,2));
        register(Blocks.SNOW_BLOCK, new AspectStack(SpiritCraftAspects.FRIGAS_ASPECT, 3));
        register(Blocks.CLAY, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.STATERA_ASPECT, 2));
        register(Blocks.PUMPKIN, new AspectStack(SpiritCraftAspects.SEGES_ASPECT, 2));
        register(Blocks.CARVED_PUMPKIN, new AspectStack(SpiritCraftAspects.SEGES_ASPECT, 2), new AspectStack(SpiritCraftAspects.CREO_ASPECT));
        register(Blocks.NETHERRACK, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT), new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 2));
        register(Blocks.SOUL_SAND, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.DECIPULA_ASPECT), new AspectStack(SpiritCraftAspects.SOL_ASPECT));
        register(Blocks.SOUL_SOIL, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.DECIPULA_ASPECT), new AspectStack(SpiritCraftAspects.SOL_ASPECT));
        register(Blocks.BASALT, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.UMBRA_ASPECT));
        register(Blocks.POLISHED_BASALT, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.UMBRA_ASPECT), new AspectStack(SpiritCraftAspects.CREO_ASPECT));
        register(Blocks.GLOWSTONE, new AspectStack(SpiritCraftAspects.VISUS_ASPECT, 3), new AspectStack(SpiritCraftAspects.RADIANT_ASPECT, 10));
        register(Blocks.JACK_O_LANTERN, new AspectStack(SpiritCraftAspects.SEGES_ASPECT), new AspectStack(SpiritCraftAspects.RADIANT_ASPECT, 4));
        register(Blocks.STONE_BRICKS, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 2));
        register(Blocks.MOSSY_STONE_BRICKS, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 2), new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT));
        register(Blocks.CRACKED_STONE_BRICKS, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 2), new AspectStack(SpiritCraftAspects.VALE_ASPECT));
        register(Blocks.CHISELED_STONE_BRICKS, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 2), new AspectStack(SpiritCraftAspects.ORDIN_ASPECT));
        register(Blocks.MELON, new AspectStack(SpiritCraftAspects.SEGES_ASPECT, 4));
        register(Blocks.MYCELIUM, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 2), new AspectStack(SpiritCraftAspects.CORRUPTIO_ASPECT, 2));
        register(Blocks.NETHER_BRICKS, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT));
        register(Blocks.CRACKED_NETHER_BRICKS, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT), new AspectStack(SpiritCraftAspects.VALE_ASPECT));
        register(Blocks.CHISELED_NETHER_BRICKS, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT), new AspectStack(SpiritCraftAspects.ORDIN_ASPECT));
        register(Blocks.END_STONE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.UMBRA_ASPECT));
        register(Blocks.END_STONE_BRICKS, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.UMBRA_ASPECT), new AspectStack(SpiritCraftAspects.CREO_ASPECT));
        register(Blocks.EMERALD_ORE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.FURTA_ASPECT, 4), new AspectStack(SpiritCraftAspects.CRYSTALLO_ASPECT, 3));
        register(Blocks.EMERALD_BLOCK, new AspectStack(SpiritCraftAspects.FURTA_ASPECT, 5), new AspectStack(SpiritCraftAspects.CRYSTALLO_ASPECT, 6));
        register(Blocks.NETHER_QUARTZ_ORE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.CRYSTALLO_ASPECT, 3));
        register(Blocks.CHISELED_QUARTZ_BLOCK,new AspectStack(SpiritCraftAspects.CRYSTALLO_ASPECT, 4), new AspectStack(SpiritCraftAspects.ORDIN_ASPECT));
        register(Blocks.QUARTZ_BLOCK, new AspectStack(SpiritCraftAspects.CRYSTALLO_ASPECT, 6));
        register(ModBlocks.TERRACOTTA, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT,2), new AspectStack(SpiritCraftAspects.ORDIN_ASPECT));
        register(Blocks.HAY_BLOCK, new AspectStack(SpiritCraftAspects.SEGES_ASPECT, 6));
        register(Blocks.COAL_BLOCK, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 4), new AspectStack(SpiritCraftAspects.INDUSTRIA_ASPECT,4));
        register(Blocks.PRISMARINE, new AspectStack(SpiritCraftAspects.HYDRA_ASPECT,2), new AspectStack(SpiritCraftAspects.CRYSTALLO_ASPECT,2));
        register(Blocks.SEA_LANTERN, new AspectStack(SpiritCraftAspects.HYDRA_ASPECT, 2), new AspectStack(SpiritCraftAspects.RADIANT_ASPECT, 4));
        register(Blocks.RED_SANDSTONE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.CREO_ASPECT));
        register(Blocks.CHISELED_RED_SANDSTONE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.ORDIN_ASPECT));
        register(Blocks.CUT_RED_SANDSTONE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.VALE_ASPECT));
        register(Blocks.MAGMA_BLOCK, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT,3), new AspectStack(SpiritCraftAspects.DECIPULA_ASPECT));
        register(Blocks.NETHER_WART_BLOCK, new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT,4), new AspectStack(SpiritCraftAspects.MAGUS_ASPECT,2));
        register(Blocks.WARPED_WART_BLOCK, new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT,4), new AspectStack(SpiritCraftAspects.MAGUS_ASPECT,2));
        register(Blocks.RED_NETHER_BRICKS, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 4), new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT,2));
        register(Blocks.BONE_BLOCK, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT,2));
        register(ModBlocks.CONCRETE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 2), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(ModBlocks.CONCRETE_POWDER, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 2));
        register(BlockTags.CORAL_BLOCKS, new AspectStack(SpiritCraftAspects.SEGES_ASPECT,2), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT, 2));
        register(Blocks.DRIED_KELP_BLOCK, new AspectStack(SpiritCraftAspects.SEGES_ASPECT, 4), new AspectStack(SpiritCraftAspects.HYDRA_ASPECT));
        register(Blocks.NETHERITE_BLOCK, new AspectStack(SpiritCraftAspects.AES_ASPECT, 16), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 4), new AspectStack(SpiritCraftAspects.MAGUS_ASPECT, 16));
        register(Blocks.ANCIENT_DEBRIS, new AspectStack(SpiritCraftAspects.AES_ASPECT, 2), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT), new AspectStack(SpiritCraftAspects.MAGUS_ASPECT));
        register(Blocks.CRYING_OBSIDIAN, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 4), new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 4), new AspectStack(SpiritCraftAspects.UMBRA_ASPECT,2));
        register(Blocks.BLACKSTONE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT));
        register(Blocks.POLISHED_BLACKSTONE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.UMBRA_ASPECT));
        register(Blocks.GILDED_BLACKSTONE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.UMBRA_ASPECT), new AspectStack(SpiritCraftAspects.FURTA_ASPECT));
        register(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.UMBRA_ASPECT), new AspectStack(SpiritCraftAspects.VALE_ASPECT));
        register(Blocks.CHISELED_POLISHED_BLACKSTONE, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.UMBRA_ASPECT), new AspectStack(SpiritCraftAspects.ORDIN_ASPECT));
        register(Blocks.POLISHED_BLACKSTONE_BRICKS, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT), new AspectStack(SpiritCraftAspects.UMBRA_ASPECT), new AspectStack(SpiritCraftAspects.CREO_ASPECT));
    }

    public static void register(Block block, AspectStack... aspects) {
        BLOCK_ASPECTS.put(block, aspects);
    }

    public static void register(Item item, AspectStack... aspects) {
        if(item instanceof BlockItem) {
            BLOCK_ASPECTS.put(((BlockItem) item).getBlock(), aspects);
            return;
        }

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
            else if(object instanceof Item)
                register((Item) object, aspects);
        }
    }
}
