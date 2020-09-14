package dev.onyxstudios.spiritcraft.api.aspects;

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
        register(BlockTags.WOODEN_SLABS, new AspectStack(SpiritCraftAspects.SILVA_ASPECT));
        register(BlockTags.WOODEN_STAIRS, new AspectStack(SpiritCraftAspects.SILVA_ASPECT));
        register(BlockTags.SAPLINGS, new AspectStack(SpiritCraftAspects.SILVA_ASPECT), new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT, 2));
        register(ModItems.DYES, new AspectStack(SpiritCraftAspects.VISUS_ASPECT));
        register(BlockTags.LEAVES, new AspectStack(SpiritCraftAspects.SILVA_ASPECT));
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
        register(Items.COOKED_RABBIT, new AspectStack(SpiritCraftAspects.CREO_ASPECT), new AspectStack(SpiritCraftAspects.CARNES_ASPECT, 2), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT,2));
        register(Items.RABBIT_STEW, new AspectStack(SpiritCraftAspects.CREO_ASPECT), new AspectStack(SpiritCraftAspects.CARNES_ASPECT, 2), new AspectStack(SpiritCraftAspects.SEGES_ASPECT), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT,4));
        register(Items.MUTTON, new AspectStack(SpiritCraftAspects.CARNES_ASPECT,3), new AspectStack(SpiritCraftAspects.VITA_ASPECT, 2), new AspectStack(SpiritCraftAspects.CREATURA_ASPECT));
        register(Items.COOKED_MUTTON, new AspectStack(SpiritCraftAspects.CREO_ASPECT), new AspectStack(SpiritCraftAspects.CARNES_ASPECT, 4), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT,3));
        register(Items.BEETROOT, new AspectStack(SpiritCraftAspects.VISUS_ASPECT), new AspectStack(SpiritCraftAspects.ESURIES_ASPECT));
        register(Items.BEETROOT_SOUP, new AspectStack(SpiritCraftAspects.ESURIES_ASPECT, 2));
        register(Blocks.SWEET_BERRY_BUSH, new AspectStack(SpiritCraftAspects.ESURIES_ASPECT), new AspectStack(SpiritCraftAspects.FOLIUM_ASPECT));
        register(Items.HONEY_BOTTLE, new AspectStack(SpiritCraftAspects.ESURIES_ASPECT,3), new AspectStack(SpiritCraftAspects.VITA_ASPECT, 2), new AspectStack(SpiritCraftAspects.GELATA_ASPECT));
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
