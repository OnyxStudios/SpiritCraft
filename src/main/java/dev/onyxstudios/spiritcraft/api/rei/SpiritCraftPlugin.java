package dev.onyxstudios.spiritcraft.api.rei;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.api.aspects.Aspect;
import dev.onyxstudios.spiritcraft.api.aspects.SpiritCraftAspects;
import me.shedaniel.rei.api.EntryRegistry;
import me.shedaniel.rei.api.plugins.REIPluginV0;
import net.minecraft.util.Identifier;

public class SpiritCraftPlugin implements REIPluginV0 {

    @Override
    public void registerEntries(EntryRegistry registry) {
        for (Aspect aspect : SpiritCraftAspects.ASPECTS.values()) {
            registry.registerEntry(new AspectEntryStack(aspect));
        }
    }

    @Override
    public Identifier getPluginIdentifier() {
        return new Identifier(SpiritCraft.MODID, "spiritcraft_plugin");
    }
}
