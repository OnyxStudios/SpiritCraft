package dev.onyxstudios.spiritcraft.registry;

import com.mojang.serialization.Codec;
import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.blocks.tree.ElderwoodTrunkPlacer;
import dev.onyxstudios.spiritcraft.blocks.tree.SpiritwoodTrunkPlacer;
import dev.onyxstudios.spiritcraft.mixins.BuiltinBiomesAccessor;
import dev.onyxstudios.spiritcraft.mixins.SetBaseBiomesLayerAccessor;
import dev.onyxstudios.spiritcraft.mixins.VanillaLayeredBiomeSourceAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilders;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class ModBiomes {

    public static RegistryKey<Biome> SPIRIT_BIOME_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(SpiritCraft.MODID, "spirit_forest"));
    public static Biome SPIRIT_BIOME;

    public static ConfiguredFeature<TreeFeatureConfig, ?> ELDERWOOD_FEATURE = Feature.TREE.configure(
            new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(ModBlocks.ELDERWOOD_LOG.getDefaultState()),
                    new SimpleBlockStateProvider(ModBlocks.ELDERWOOD_LEAVES.getDefaultState()),
                    new LargeOakFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(4), 4),
                    new ElderwoodTrunkPlacer(7, 8, 0),
                    new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))
            )
                    .ignoreVines()
                    .heightmap(Heightmap.Type.MOTION_BLOCKING)
                    .build()
    );

    public static ConfiguredFeature<TreeFeatureConfig, ?> ELDERWOOD_OAK_FEATURE = Feature.TREE.configure(
            new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new SimpleBlockStateProvider(ModBlocks.ELDERWOOD_LEAVES.getDefaultState()),
                    new LargeOakFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(4), 4),
                    new LargeOakTrunkPlacer(3, 11, 0),
                    new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))
            ).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build());

    public static ConfiguredFeature<TreeFeatureConfig, ?> SPIRITWOOD_FEATURE = Feature.TREE.configure(
            new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(ModBlocks.SPIRITWOOD_LOG.getDefaultState()),
                    new SimpleBlockStateProvider(ModBlocks.SPIRITWOOD_LEAVES.getDefaultState()),
                    new LargeOakFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(4), 4),
                    new SpiritwoodTrunkPlacer(7, 0, 0),
                    new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))
            )
                    .ignoreVines()
                    .heightmap(Heightmap.Type.MOTION_BLOCKING)
                    .build()
    );

    public static TrunkPlacerType<? extends TrunkPlacer> ELDERWOOD_TRUNK_PLACER;
    public static TrunkPlacerType<? extends TrunkPlacer> SPIRITWOOD_TRUNK_PLACER;

    public static ConfiguredFeature<?, ?> AURA_CRYSTAL_FEATURE = createOreFeature(ModBlocks.AURA_CRYSTAL_BLOCK, 4, 2, 30);
    public static ConfiguredFeature<?, ?> SOLARIS_CRYSTAL_FEATURE = createOreFeature(ModBlocks.SOLARIS_CRYSTAL_BLOCK, 4, 2, 30);
    public static ConfiguredFeature<?, ?> TELLUS_CRYSTAL_FEATURE = createOreFeature(ModBlocks.TELLUS_CRYSTAL_BLOCK, 4, 2, 30);
    public static ConfiguredFeature<?, ?> HYDRA_CRYSTAL_FEATURE = createOreFeature(ModBlocks.HYDRA_CRYSTAL_BLOCK, 4, 2, 30);
    public static ConfiguredFeature<?, ?> ORDIN_CRYSTAL_FEATURE = createOreFeature(ModBlocks.ORDIN_CRYSTAL_BLOCK, 4, 2, 30);
    public static ConfiguredFeature<?, ?> VALE_CRYSTAL_FEATURE = createOreFeature(ModBlocks.VALE_CRYSTAL_BLOCK, 4, 2, 30);

    public static void register() {
        registerFeatures();
        SPIRIT_BIOME = createSpiritForest(0.1F, 0.1F);
        Registry.register(BuiltinRegistries.BIOME, SPIRIT_BIOME_KEY.getValue(), SPIRIT_BIOME);
        BuiltinBiomesAccessor.getRawIdMap().put(BuiltinRegistries.BIOME.getRawId(SPIRIT_BIOME), SPIRIT_BIOME_KEY);

        List<RegistryKey<Biome>> biomes = new ArrayList<>(VanillaLayeredBiomeSourceAccessor.getBiomes());
        biomes.add(SPIRIT_BIOME_KEY);
        VanillaLayeredBiomeSourceAccessor.setBiomes(biomes);

        SetBaseBiomesLayerAccessor.setTemperateBiomes(ArrayUtils.add(SetBaseBiomesLayerAccessor.getTemperateBiomes(), BuiltinRegistries.BIOME.getRawId(SPIRIT_BIOME)));
    }

    private static void registerFeatures() {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(SpiritCraft.MODID, "elderwood_oak_feature"), ELDERWOOD_OAK_FEATURE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(SpiritCraft.MODID, "elderwood_feature"), ELDERWOOD_FEATURE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(SpiritCraft.MODID, "spiritwood_feature"), SPIRITWOOD_FEATURE);

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(SpiritCraft.MODID, "aura_crystal_feature"), AURA_CRYSTAL_FEATURE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(SpiritCraft.MODID, "solaris_crystal_feature"), SOLARIS_CRYSTAL_FEATURE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(SpiritCraft.MODID, "tellus_crystal_feature"), TELLUS_CRYSTAL_FEATURE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(SpiritCraft.MODID, "hydra_crystal_feature"), HYDRA_CRYSTAL_FEATURE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(SpiritCraft.MODID, "ordin_crystal_feature"), ORDIN_CRYSTAL_FEATURE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(SpiritCraft.MODID, "vale_crystal_feature"), VALE_CRYSTAL_FEATURE);

        ELDERWOOD_TRUNK_PLACER = createTrunkPlacerType(ElderwoodTrunkPlacer.CODEC);
        ELDERWOOD_TRUNK_PLACER = Registry.register(Registry.TRUNK_PLACER_TYPE, new Identifier(SpiritCraft.MODID, "elderwood_trunk_placer"), ELDERWOOD_TRUNK_PLACER);


        SPIRITWOOD_TRUNK_PLACER = createTrunkPlacerType(SpiritwoodTrunkPlacer.CODEC);
        SPIRITWOOD_TRUNK_PLACER = Registry.register(Registry.TRUNK_PLACER_TYPE, new Identifier(SpiritCraft.MODID, "spiritwood_trunk_placer"), SPIRITWOOD_TRUNK_PLACER);
    }

    public static void registerWorldGen(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, AURA_CRYSTAL_FEATURE);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, SOLARIS_CRYSTAL_FEATURE);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, TELLUS_CRYSTAL_FEATURE);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, HYDRA_CRYSTAL_FEATURE);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ORDIN_CRYSTAL_FEATURE);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, VALE_CRYSTAL_FEATURE);
    }

    private static ConfiguredFeature<?, ?> createOreFeature(Block block, int veinSize, int perChunk, int maxHeight) {
        return Feature.ORE.configure(new OreFeatureConfig(
                OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                block.getDefaultState(),
                veinSize
        )).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0, maxHeight)).spreadHorizontally().repeat(perChunk));
    }

    public static Biome createSpiritForest(float depth, float scale) {
        SpawnSettings.Builder spawnBuilder = createDefaultSpawnSettings();
        GenerationSettings.Builder builder = new GenerationSettings.Builder().surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        DefaultBiomeFeatures.addDefaultUndergroundStructures(builder);
        DefaultBiomeFeatures.addDefaultLakes(builder);
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultDisks(builder);
        DefaultBiomeFeatures.addDefaultGrass(builder);
        DefaultBiomeFeatures.addDefaultFlowers(builder);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        builder.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ELDERWOOD_OAK_FEATURE);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ELDERWOOD_FEATURE);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, SPIRITWOOD_FEATURE);

        return new Biome.Builder()
                .precipitation(Biome.Precipitation.RAIN)
                .category(Biome.Category.FOREST)
                .depth(depth)
                .scale(scale)
                .temperature(0.85f)
                .downfall(0.6f)
                .effects(
                        new BiomeEffects.Builder()
                                .waterColor(0x32afed)
                                .waterFogColor(329011)
                                .grassColor(0x3dbfe3)
                                .fogColor(12638463)
                                .skyColor(getSkyColor(0.85f))
                                .build()
                )
                .spawnSettings(spawnBuilder.build())
                .generationSettings(builder.build())
                .build();
    }

    public static TrunkPlacerType<?> createTrunkPlacerType(Codec codec) {
        try {
            Constructor constructor = TrunkPlacerType.class.getDeclaredConstructor(Codec.class);
            constructor.setAccessible(true);
            return (TrunkPlacerType<? extends TrunkPlacer>) constructor.newInstance(codec);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static int getSkyColor(float temperature) {
        float f = temperature / 3.0F;
        f = MathHelper.clamp(f, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }

    private static SpawnSettings.Builder createDefaultSpawnSettings() {
        SpawnSettings.Builder builder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(builder);
        DefaultBiomeFeatures.addBatsAndMonsters(builder);
        return builder;
    }
}
