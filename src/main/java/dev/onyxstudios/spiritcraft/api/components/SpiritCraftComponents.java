package dev.onyxstudios.spiritcraft.api.components;

import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;

public class SpiritCraftComponents implements ItemComponentInitializer {

    @Override
    public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {
        registry.registerFor(item -> false, SpiritComponent.SPIRIT, (item, stack) -> new SpiritComponent());
    }
}
