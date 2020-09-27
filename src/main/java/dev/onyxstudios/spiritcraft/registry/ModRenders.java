package dev.onyxstudios.spiritcraft.registry;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.api.events.client.RenderHudEvent;
import dev.onyxstudios.spiritcraft.api.events.client.RenderInWorldEvent;
import dev.onyxstudios.spiritcraft.client.particles.MagicBubbleParticle;
import dev.onyxstudios.spiritcraft.client.particles.MagicFadeParticle;
import dev.onyxstudios.spiritcraft.client.render.ElementalShovelRenderer;
import dev.onyxstudios.spiritcraft.client.render.WandHudRenderer;
import dev.onyxstudios.spiritcraft.client.render.scanner.ScannerInfoHudRenderer;
import dev.onyxstudios.spiritcraft.client.render.scanner.ScannerRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

@Environment(EnvType.CLIENT)
public class ModRenders {

    //For one time renders
    private static Map<BiConsumer<MatrixStack, VertexConsumerProvider>, Map.Entry<Long, Integer>> scheduledRenders = new HashMap<>();

    public static final Identifier DEFAULT_ROD_TEXTURE = new Identifier(SpiritCraft.MODID, "item/wand_rod");
    public static final Identifier DEFAULT_CAP_TEXTURE = new Identifier(SpiritCraft.MODID, "item/wand_caps");
    private static Map<Item, Identifier> wandRods = new HashMap<>();
    private static Map<Item, Identifier> wandCaps = new HashMap<>();

    public static DefaultParticleType MAGIC_BUBBLE_TYPE = FabricParticleTypes.simple();
    public static DefaultParticleType MAGIC_FADE_TYPE = FabricParticleTypes.simple();

    public static void register() {
        registerParticle(new Identifier(SpiritCraft.MODID, "magic_bubble"), MAGIC_BUBBLE_TYPE, MagicBubbleParticle.Factory::new);
        registerParticle(new Identifier(SpiritCraft.MODID, "magic_fade"), MAGIC_FADE_TYPE, MagicFadeParticle.Factory::new);
        registerWandRod(ModItems.WOODEN_WAND, new Identifier(SpiritCraft.MODID, "item/wooden_wand_rod"));
        registerWandRod(ModItems.ELDERWOOD_WAND, new Identifier(SpiritCraft.MODID, "item/elderwood_wand_rod"));
        registerWandRod(ModItems.SPIRITWOOD_WAND, new Identifier(SpiritCraft.MODID, "item/spiritwood_wand_rod"));
        registerWandCaps(ModItems.IRON_CAP, new Identifier(SpiritCraft.MODID, "item/iron_wand_cap"));
        registerWandCaps(ModItems.GOLD_CAP, new Identifier(SpiritCraft.MODID, "item/gold_wand_cap"));
        registerWandCaps(ModItems.SPIRIUM_CAP, new Identifier(SpiritCraft.MODID, "item/spirium_wand_cap"));


        RenderInWorldEvent.EVENT.register((world, player, stack, vertexConsumer, camera) -> ElementalShovelRenderer.render(world, player, stack, vertexConsumer, camera));
        RenderHudEvent.EVENT.register((matrix, partialTicks) -> {
            ScannerRenderer.renderScannerHitResult(matrix, partialTicks);
            ScannerInfoHudRenderer.renderInfoHud(matrix, partialTicks);
            ScannerInfoHudRenderer.renderDisplayHud(matrix, partialTicks);
            WandHudRenderer.renderDisplayHud(matrix, partialTicks);
        });

        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            for (Identifier identifier : wandRods.values()) {
                registry.register(identifier);
            }

            for (Identifier identifier : wandCaps.values()) {
                registry.register(identifier);
            }

            registry.register(DEFAULT_ROD_TEXTURE);
            registry.register(DEFAULT_CAP_TEXTURE);
        });
    }
    public static void renderSchedules(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider) {
        for (BiConsumer<MatrixStack, VertexConsumerProvider> consumer : scheduledRenders.keySet()) {
            consumer.accept(matrixStack, vertexConsumerProvider);
        }

        scheduledRenders.entrySet().removeIf(entry -> TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - TimeUnit.MILLISECONDS.toSeconds(entry.getValue().getKey()) > entry.getValue().getValue());
    }

    public static void scheduleRender(BiConsumer<MatrixStack, VertexConsumerProvider> consumer, int duration) {
        scheduledRenders.put(consumer, new AbstractMap.SimpleEntry<>(System.currentTimeMillis(), duration));
    }

    public static void registerWandRod(Item item, Identifier texture) {
        wandRods.put(item, texture);
    }

    public static void registerWandCaps(Item item, Identifier texture) {
        wandCaps.put(item, texture);
    }

    public static Identifier getWandRod(Item item) {
        return wandRods.getOrDefault(item, DEFAULT_ROD_TEXTURE);
    }

    public static Identifier getWandCaps(Item item) {
        return wandCaps.getOrDefault(item, DEFAULT_CAP_TEXTURE);
    }

    private static void registerParticle(Identifier id, ParticleType particleType, ParticleFactoryRegistry.PendingParticleFactory factory) {
        Registry.register(Registry.PARTICLE_TYPE, id, particleType);
        ParticleFactoryRegistry.getInstance().register(particleType, factory);
    }
}
