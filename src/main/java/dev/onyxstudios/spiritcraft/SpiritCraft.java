package dev.onyxstudios.spiritcraft;

import dev.onyxstudios.spiritcraft.api.events.BlockBreakEvent;
import dev.onyxstudios.spiritcraft.items.tools.ElementalAxe;
import dev.onyxstudios.spiritcraft.items.tools.ElementalShovel;
import dev.onyxstudios.spiritcraft.items.tools.ElementalSword;
import dev.onyxstudios.spiritcraft.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpiritCraft implements ModInitializer {

    public static String MODID = "spiritcraft";
    public static Logger LOGGER = LogManager.getLogger("SpiritCraft");

    @Override
    public void onInitialize() {
        ModBlocks.register();
        ModItems.register();
        ModBiomes.register();
        ModEntities.register();
        ModRecipes.register();
        ModSounds.register();
        ModPackets.registerServer();

        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> !ElementalAxe.breakLogs(world, pos, player));
        BlockBreakEvent.EVENT.register((world, player, state, pos, direction) -> ElementalShovel.shovelDirt(world, player, state, pos, direction));
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> ElementalSword.damageAOE(world, player, hand, entity));
    }
}
