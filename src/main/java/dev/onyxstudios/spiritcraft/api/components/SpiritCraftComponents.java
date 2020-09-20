package dev.onyxstudios.spiritcraft.api.components;

import dev.onyxstudios.cca.api.v3.block.BlockComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.block.BlockComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import dev.onyxstudios.spiritcraft.api.components.research.ResearchComponent;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;

public class SpiritCraftComponents implements ItemComponentInitializer, BlockComponentInitializer, EntityComponentInitializer {

    @Override
    public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {
        //TODO Filter items for this component
        //registry.registerFor(item -> false, SpiritComponent.SPIRIT, (item, stack) -> new SpiritComponent());
    }

    @Override
    public void registerBlockComponentFactories(BlockComponentFactoryRegistry registry) {
        //TODO Filter BlockEntities for components
        //registry.registerFor(new Identifier(""), EssenceComponent.ESSENCE, (state, world, pos, side) -> new EssenceComponent(10));
        //registry.registerFor(new Identifier(""), SpiritComponent.SPIRIT, (state, world, pos, side) -> new SpiritComponent(LIST OF ASPECTS));
    }

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(ResearchComponent.RESEARCH, player -> new ResearchComponent(), RespawnCopyStrategy.ALWAYS_COPY);
    }
}
