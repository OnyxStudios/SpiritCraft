package dev.onyxstudios.spiritcraft.registry;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {

    public static SoundEvent ELEMENTAL_PICKAXE_SCAN = new SoundEvent(new Identifier(SpiritCraft.MODID, "elemental_pickaxe_scan"));

    public static void register() {
        Registry.register(Registry.SOUND_EVENT, ELEMENTAL_PICKAXE_SCAN.getId(), ELEMENTAL_PICKAXE_SCAN);
    }
}
