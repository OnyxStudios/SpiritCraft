package dev.onyxstudios.spiritcraft;

import dev.onyxstudios.foml.obj.OBJLoader;
import dev.onyxstudios.spiritcraft.client.ClientTickHandler;
import dev.onyxstudios.spiritcraft.client.shaders.Shaders;
import dev.onyxstudios.spiritcraft.registry.ModBlocks;
import dev.onyxstudios.spiritcraft.registry.ModEntities;
import dev.onyxstudios.spiritcraft.registry.ModItems;
import dev.onyxstudios.spiritcraft.registry.ModRenders;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

public class SpiritCraftClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        OBJLoader.INSTANCE.registerDomain(SpiritCraft.MODID);
        ClientTickHandler.registerClientTick();
        ModEntities.registerRenders();
        ModBlocks.registerRenders();
        ModItems.registerRenders();
        ModRenders.registerRenders();

        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return new Identifier("spiritcraft_reload");
            }

            @Override
            public void apply(ResourceManager manager) {
                MinecraftClient.getInstance().submit(() -> Shaders.init());
            }
        });

        ModelLoadingRegistry.INSTANCE.registerAppender((resourceManager, consumer) -> consumer.accept(new ModelIdentifier(new Identifier(SpiritCraft.MODID, "base_crystal"), "")));
    }
}
