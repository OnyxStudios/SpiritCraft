package dev.onyxstudios.spiritcraft.api.components;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.api.aspects.AspectStack;
import dev.onyxstudios.spiritcraft.api.aspects.SpiritCraftAspects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;

public class SpiritComponent implements ISpiritComponent {

    public static final ComponentKey<SpiritComponent> SPIRIT = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(SpiritCraft.MODID, "spirit"), SpiritComponent.class);

    private AspectStack[] aspects = new AspectStack[] {
            new AspectStack(SpiritCraftAspects.AURA_ASPECT, 0),
            new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 0),
            new AspectStack(SpiritCraftAspects.HYDRA_ASPECT, 0),
            new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 0),
            new AspectStack(SpiritCraftAspects.ORDIN_ASPECT, 0),
            new AspectStack(SpiritCraftAspects.VALE_ASPECT, 0)
    };

    @Override
    public void readFromNbt(CompoundTag compoundTag) {
        for (AspectStack stack : aspects) {
            stack.setCount(compoundTag.getInt(stack.getAspect().getId().toString()));
        }
    }

    @Override
    public void writeToNbt(CompoundTag compoundTag) {
        for (AspectStack stack : aspects) {
            compoundTag.putInt(stack.getAspect().getId().toString(), stack.getCount());
        }
    }

    @Override
    public AspectStack[] getAspects() {
        return aspects;
    }
}
