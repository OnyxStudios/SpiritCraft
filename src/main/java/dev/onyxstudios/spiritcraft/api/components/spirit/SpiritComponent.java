package dev.onyxstudios.spiritcraft.api.components.spirit;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.api.aspects.Aspect;
import dev.onyxstudios.spiritcraft.api.aspects.AspectStack;
import dev.onyxstudios.spiritcraft.api.aspects.SpiritCraftAspects;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Identifier;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SpiritComponent implements ISpiritComponent {

    public static final ComponentKey<ISpiritComponent> SPIRIT = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(SpiritCraft.MODID, "spirit"), ISpiritComponent.class);

    private Map<Aspect, AspectStack> aspects = new HashMap<>();
    private int capacity;

    public SpiritComponent(int capacity) {
        this.capacity = capacity;

        //Add all primal aspects by default
        aspects.put(SpiritCraftAspects.AURA_ASPECT, new AspectStack(SpiritCraftAspects.AURA_ASPECT, 20));
        aspects.put(SpiritCraftAspects.SOLARIS_ASPECT, new AspectStack(SpiritCraftAspects.SOLARIS_ASPECT, 20));
        aspects.put(SpiritCraftAspects.HYDRA_ASPECT, new AspectStack(SpiritCraftAspects.HYDRA_ASPECT, 5));
        aspects.put(SpiritCraftAspects.TELLUS_ASPECT, new AspectStack(SpiritCraftAspects.TELLUS_ASPECT, 25));
        aspects.put(SpiritCraftAspects.ORDIN_ASPECT, new AspectStack(SpiritCraftAspects.ORDIN_ASPECT, 15));
        aspects.put(SpiritCraftAspects.VALE_ASPECT, new AspectStack(SpiritCraftAspects.VALE_ASPECT, 2));
    }

    public SpiritComponent(int capacity, AspectStack... aspects) {
        this.capacity = capacity;
        for (AspectStack aspectStack : aspects) {
            this.aspects.put(aspectStack.getAspect(), aspectStack.copy());
        }
    }

    @Override
    public void readFromNbt(CompoundTag compoundTag) {
        ListTag listTag = compoundTag.getList("aspectsList", NbtType.COMPOUND);
        for (Tag tag : listTag) {
            CompoundTag aspectTag = (CompoundTag) tag;
            Aspect aspect = SpiritCraftAspects.getAspect(new Identifier(aspectTag.getString("id")));
            float count = aspectTag.getFloat("count");

            aspects.put(aspect, new AspectStack(aspect, count));
        }

        this.capacity = compoundTag.getInt("capacity");
    }

    @Override
    public void writeToNbt(CompoundTag compoundTag) {
        ListTag listTag = new ListTag();
        for (AspectStack stack : aspects.values()) {
            CompoundTag tag = new CompoundTag();
            tag.putString("id", stack.getAspect().getId().toString());
            tag.putFloat("count", stack.getCount());
            listTag.add(tag);
        }

        compoundTag.putInt("capacity", this.capacity);
        compoundTag.put("aspectsList", listTag);
    }

    @Override
    public Collection<AspectStack> getAspects() {
        return aspects.values();
    }

    @Override
    public void useAspect(Aspect aspect, int amount) {
        aspects.compute(aspect, (key, aspectStack) -> {
            aspectStack.shrink(aspectStack.getCount() >= amount ? amount : aspectStack.getCount());
            return aspectStack;
        });
    }

    @Override
    public void addAspect(Aspect aspect, int amount) {
        aspects.compute(aspect, (key, aspectStack) -> {
            aspectStack.grow(amount + aspectStack.getCount() > capacity ? capacity - aspectStack.getCount() : amount);
            return aspectStack;
        });
    }

    @Override
    public AspectStack getAspectStack(Aspect aspect) {
        return aspects.get(aspect);
    }

    @Override
    public float getAspectCount(Aspect aspect) {
        return aspects.get(aspect).getCount();
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }
}
