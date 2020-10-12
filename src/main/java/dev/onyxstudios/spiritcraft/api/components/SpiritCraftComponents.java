package dev.onyxstudios.spiritcraft.api.components;

import dev.onyxstudios.cca.api.v3.block.BlockComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.block.BlockComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import dev.onyxstudios.spiritcraft.api.components.research.ResearchComponent;
import dev.onyxstudios.spiritcraft.api.components.spirit.SpiritComponent;
import dev.onyxstudios.spiritcraft.api.items.IWand;
import dev.onyxstudios.spiritcraft.blockentity.BlockEntityNode;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;

public class SpiritCraftComponents implements ItemComponentInitializer, BlockComponentInitializer, EntityComponentInitializer {

    @Override
    public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {
        registry.registerFor(item -> item instanceof IWand, SpiritComponent.SPIRIT, (item, itemStack) -> new SpiritComponent(((IWand) item).getCapacity()));
    }

    @Override
    public void registerBlockComponentFactories(BlockComponentFactoryRegistry registry) {
        //TODO Filter BlockEntities for components
        //registry.registerFor(new Identifier(""), EssenceComponent.ESSENCE, (state, world, pos, side) -> new EssenceComponent(10));
        registry.registerFor(BlockEntityNode.class, SpiritComponent.SPIRIT, blockEntityNode -> new SpiritComponent(0, null));
    }

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(ResearchComponent.RESEARCH, player -> new ResearchComponent(), RespawnCopyStrategy.ALWAYS_COPY);
    }
}
