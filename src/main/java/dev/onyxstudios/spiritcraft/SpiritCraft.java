package dev.onyxstudios.spiritcraft;

import dev.onyxstudios.spiritcraft.registry.ModBiomes;
import dev.onyxstudios.spiritcraft.registry.ModBlocks;
import dev.onyxstudios.spiritcraft.registry.ModEntities;
import dev.onyxstudios.spiritcraft.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpiritCraft implements ModInitializer {

    public static String MODID = "spiritcraft";
    public static Logger LOGGER = LogManager.getLogger("SpiritCraft");
    public static ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier(MODID, "itemgroup"), () -> new ItemStack(ModBlocks.ELDERWOOD_LOG));


    @Override
    public void onInitialize() {
        ModBlocks.register();
        ModItems.register();
        ModBiomes.register();
        ModEntities.register();
    }
}
