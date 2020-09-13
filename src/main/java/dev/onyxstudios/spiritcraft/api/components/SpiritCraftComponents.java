package dev.onyxstudios.spiritcraft.api.components;

import dev.onyxstudios.cca.api.v3.block.BlockComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.block.BlockComponentInitializer;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;

public class SpiritCraftComponents implements ItemComponentInitializer, BlockComponentInitializer {

    @Override
    public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {
        //TODO Filter items for this component
        //registry.registerFor(item -> false, SpiritComponent.SPIRIT, (item, stack) -> new SpiritComponent());
    }

    @Override
    public void registerBlockComponentFactories(BlockComponentFactoryRegistry registry) {
        //TODO Filter BlockEntities for this component
        //registry.registerFor(new Identifier(""), EssenceComponent.ESSENCE, (state, world, pos, side) -> new EssenceComponent(10));
    }
}
