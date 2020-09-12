package dev.onyxstudios.spiritcraft.api.aspects;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class SpiritCraftAspects {

    public static Map<Identifier, Aspect> ASPECTS = new HashMap<>();

    //Prime Aspects
    public static Aspect AURA_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID, "aura_aspect"), 0xc3af49);
    public static Aspect SOLARIS_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID, "solaris_aspect"), 0xf4503a);
    public static Aspect HYDRA_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID, "hydra_aspect"), 0x3accf4);
    public static Aspect TELLUS_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID, "tellus_aspect"), 0x07c700);
    public static Aspect ORDIN_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID, "ordin_aspect"), 0xfffafc);
    public static Aspect VALE_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID, "vale_aspect"), 0x242424);


    /**
     * To be called internally in SpiritCraft to initialize all aspect textures
     * No mod besides Spiritcraft should call this method
     */
    @Environment(EnvType.CLIENT)
    public static void registerResources() {
        ModelLoadingRegistry.INSTANCE.registerAppender((manager, consumer) -> {
            for (Identifier id : ASPECTS.keySet()) {
                consumer.accept(new ModelIdentifier(new Identifier(id.getNamespace(), "aspects/" + id.getPath()), "inventory"));
            }
        });
    }
}
