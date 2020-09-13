package dev.onyxstudios.spiritcraft.api.components.spirit;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.api.aspects.Aspect;
import dev.onyxstudios.spiritcraft.api.aspects.AspectStack;
import dev.onyxstudios.spiritcraft.api.aspects.SpiritCraftAspects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;

public class SpiritComponent implements ISpiritComponent {

    public static final ComponentKey<SpiritComponent> SPIRIT = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(SpiritCraft.MODID, "spirit"), SpiritComponent.class);

    private AspectStack[] aspects;

    public SpiritComponent() {
        this.aspects = new AspectStack[] {
                new AspectStack(SpiritCraftAspects.AURA_ASPECT, 0),
                new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 0),
                new AspectStack(SpiritCraftAspects.HYDRA_ASPECT, 0),
                new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 0),
                new AspectStack(SpiritCraftAspects.ORDIN_ASPECT, 0),
                new AspectStack(SpiritCraftAspects.VALE_ASPECT, 0)
        };
    }

    public SpiritComponent(AspectStack... aspects) {
        this.aspects = aspects;
    }

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

    @Override
    public void useAspect(Aspect aspect, int amount) {
        for (int i = 0; i < aspects.length; i++) {
            if(aspects[i].getAspect() == aspect) {
                aspects[i].shrink(aspects[i].getCount() >= amount ? amount : aspects[i].getCount());
            }
        }
    }

    @Override
    public void addAspect(Aspect aspect, int amount) {
        for (int i = 0; i < aspects.length; i++) {
            if(aspects[i].getAspect() == aspect) {
                aspects[i].grow(amount);
            }
        }
    }

    @Override
    public AspectStack getAspectStack(Aspect aspect) {
        for (AspectStack stack : aspects) {
            if(aspect == stack.getAspect())
                return stack;
        }

        return null;
    }
}
