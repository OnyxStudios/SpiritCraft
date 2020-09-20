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
    public static final ModelIdentifier UNKNOWN_LOC = new ModelIdentifier(new Identifier(SpiritCraft.MODID, "aspect/unknown_aspect"), "");

    //Prime Aspects
    public static Aspect AURA_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID, "aura_aspect"), 0xc3af49, "air");
    public static Aspect HYDRA_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID, "hydra_aspect"), 0x3accf4, "water");
    public static Aspect SOLARIS_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID, "solaris_aspect"), 0xf4503a, "fire");
    public static Aspect ORDIN_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID, "ordin_aspect"), 0xfffafc, "order");
    public static Aspect VALE_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID, "vale_aspect"), 0x242424, "entropy");
    public static Aspect TELLUS_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID, "tellus_aspect"), 0x07c700, "earth");

    //TIER 1
    public static Aspect FRIGAS_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"frigas_aspect"), 0xe1ffff, "ice", SOLARIS_ASPECT, VALE_ASPECT);
    public static Aspect RADIANT_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"radiant_aspect"), 0xcdc650, "light", AURA_ASPECT, SOLARIS_ASPECT);
    public static Aspect ITUS_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"itus_aspect"), 0xcdc650,"motion", AURA_ASPECT, ORDIN_ASPECT);
    public static Aspect STATERA_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"statera_aspect"), 0xa4a3c3, "exchange", ORDIN_ASPECT, VALE_ASPECT);
    public static Aspect INDUSTRIA_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"industria_aspect"), 0xc0ffff, "energy", SOLARIS_ASPECT, ORDIN_ASPECT);
    public static Aspect PERFURO_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"perfuro_aspect"), 0xfdfdfd, "weather",  AURA_ASPECT, HYDRA_ASPECT);
    public static Aspect INANIS_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"inanis_aspect"), 0x6b6b6b, "void", AURA_ASPECT, VALE_ASPECT);
    public static Aspect OSTIUM_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"ostium_aspect"), 0x8bf500, "poision",  HYDRA_ASPECT, VALE_ASPECT);
    public static Aspect VITA_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"vita_aspect"), 0xd20004, "life", HYDRA_ASPECT, TELLUS_ASPECT);
    public static Aspect CRYSTALLO_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"crystallo_aspect"), 0x7ffefe, "crystal",  ORDIN_ASPECT, TELLUS_ASPECT);

    //TIER 2
    public static Aspect CREATURA_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"creatura_aspect"), 0x9f6409, "beast", ITUS_ASPECT, VITA_ASPECT);
    public static Aspect ESURIES_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"esuries_aspect"), 0x9f0305, "hunger", INANIS_ASPECT, VITA_ASPECT);
    public static Aspect FOLIUM_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"folium_aspect"), 0x018c00, "plant", TELLUS_ASPECT, VITA_ASPECT);
    public static Aspect SEMITA_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"semita_aspect"), 0xdf575a, "travel", ITUS_ASPECT, TELLUS_ASPECT);
    public static Aspect GELATA_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"gelata_aspect"), 0x00ec00, "sticky", HYDRA_ASPECT, VITA_ASPECT);
    public static Aspect AES_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"aes_aspect"), 0xb4b4cc, "metal", TELLUS_ASPECT, CRYSTALLO_ASPECT);
    public static Aspect MORS_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"mors_aspect"), 0x867586, "death", VALE_ASPECT, VITA_ASPECT);
    public static Aspect MAGUS_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"magus_aspect"), 0x9600bf, "magic", INDUSTRIA_ASPECT, INANIS_ASPECT);
    public static Aspect REMEDIUM_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"remedium_aspect"), 0xfa2d32, "heal", ORDIN_ASPECT, VITA_ASPECT);
    public static Aspect UMBRA_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"umbra_aspect"), 0x646464, "darkness", RADIANT_ASPECT, INANIS_ASPECT);
    public static Aspect DECIPULA_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"decipula_aspect"), 0x776262, "trap", ITUS_ASPECT, VALE_ASPECT);
    public static Aspect VOLANT_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"volant_aspect"), 0xe1e1d1, "flight",AURA_ASPECT, ITUS_ASPECT);

    //TIER 3
    public static Aspect NOVIS_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"novis_aspect"), 0x7c4d7c, "strange", UMBRA_ASPECT, INANIS_ASPECT);
    public static Aspect SILVA_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"silva_aspect"), 0x866430,  "tree", AURA_ASPECT, FOLIUM_ASPECT);
    public static Aspect SPHAERAM_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"sphaeram_aspect"), 0xbe8ebe, "aura", AURA_ASPECT, MAGUS_ASPECT);
    public static Aspect CARNES_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID, "carnes_aspect"), 0xee478d, "flesh", CREATURA_ASPECT, MORS_ASPECT);
    public static Aspect IMMORTUOS_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"immortuos_aspect"), 0x3a4000, "undead", MORS_ASPECT, ITUS_ASPECT);
    public static Aspect SOL_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"sol_aspect"), 0x3e3e41, "spirit", MORS_ASPECT, VITA_ASPECT);
    public static Aspect CORRUPTIO_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"corruptio_aspect"), 0x7f007f, "corruption", VALE_ASPECT, MAGUS_ASPECT);

    //TIER 4
    public static Aspect ANIMO_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"animo_aspect"), 0xfdc0b1, "mind", SOLARIS_ASPECT, SOL_ASPECT);
    public static Aspect VISUS_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"visus_aspect"), 0x0ee1ff, "senses", AURA_ASPECT, SOL_ASPECT);

    //TIER 5
    public static Aspect VIR_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"vir_aspect"), 0xfed6bf, "human", CREATURA_ASPECT, ANIMO_ASPECT);

    //TIER 6
    public static Aspect VASA_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"vasa_aspect"), 0x3434c2, "tool", VIR_ASPECT, ORDIN_ASPECT);
    public static Aspect FURTA_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"furta_aspect"), 0xe3bc43,  "greed",  ESURIES_ASPECT, VIR_ASPECT);
    public static Aspect SEGES_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"seges_aspect"), 0xf4c17a, "crop", FOLIUM_ASPECT, VIR_ASPECT);
    public static Aspect INFODIO_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"infodio_aspect"), 0xd9cfd5, "mine",  VIR_ASPECT, TELLUS_ASPECT);

    //TIER 7
    public static Aspect CREO_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"creo_aspect"), 0x7b977b, "craft", VIR_ASPECT, VASA_ASPECT);
    public static Aspect OBFICINA_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"obficina_aspect"), 0x8080a0, "machine", VASA_ASPECT, ITUS_ASPECT);
    public static Aspect TONDEO_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"tondeo_aspect"), 0xeba980, "harvest",VASA_ASPECT, SEGES_ASPECT);
    public static Aspect LINTEUM_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"linteum_aspect"), 0xe9e9c1, "cloth", CREATURA_ASPECT, VASA_ASPECT);
    public static Aspect GLADIO_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"gladio_aspect"), 0xbe4e4e, "weaponry", VASA_ASPECT, SOLARIS_ASPECT);
    public static Aspect ARMATURA_ASPECT = new Aspect(new Identifier(SpiritCraft.MODID,"armatura_aspect"), 0x00bfbf, "armor", VASA_ASPECT, TELLUS_ASPECT);

    public static Aspect getAspect(Identifier id) {
        return ASPECTS.get(id);
    }

    public static Aspect[] getPrimalAspects() {
        return new Aspect[]{AURA_ASPECT, SOLARIS_ASPECT, TELLUS_ASPECT, HYDRA_ASPECT, ORDIN_ASPECT, VALE_ASPECT};
    }

    /**
     * To be called internally in SpiritCraft to initialize all aspect textures
     * No mod besides Spiritcraft should call this method
     */
    @Environment(EnvType.CLIENT)
    public static void registerResources() {
        ModelLoadingRegistry.INSTANCE.registerVariantProvider(resourceManager -> (id, context) -> {
            if(id.getPath().startsWith("aspect/")) {
                return context.loadModel(new Identifier(id.getNamespace(), id.getPath()));
            }

            return null;
        });

        ModelLoadingRegistry.INSTANCE.registerAppender((manager, consumer) -> {
            for (Identifier id : ASPECTS.keySet()) {
                ModelIdentifier modelId = new ModelIdentifier(new Identifier(id.getNamespace(), "aspect/" + id.getPath()), "");
                consumer.accept(modelId);
            }

            consumer.accept(UNKNOWN_LOC);
        });
    }
}
