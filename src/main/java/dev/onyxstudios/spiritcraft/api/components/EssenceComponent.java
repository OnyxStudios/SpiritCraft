package dev.onyxstudios.spiritcraft.api.components;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.api.aspects.Aspect;
import dev.onyxstudios.spiritcraft.api.aspects.SpiritCraftAspects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;

public class EssenceComponent implements IEssenceComponent {

    public static final ComponentKey<EssenceComponent> ESSENCE = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(SpiritCraft.MODID, "essence"), EssenceComponent.class);

    private Aspect aspect;
    private int capacity, amount;

    public EssenceComponent(int capacity) {
        this.capacity = capacity;
        this.amount = 0;
    }

    public EssenceComponent(Aspect aspect, int capacity) {
        this.aspect = aspect;
        this.capacity = capacity;
        this.amount = 0;
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        if(aspect != null)
            tag.putString("aspect", this.aspect.getId().toString());
        tag.putInt("capacity", this.capacity);
        tag.putInt("amount", this.amount);
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        if(tag.contains("aspect"))
            this.aspect = SpiritCraftAspects.getAspect(new Identifier(tag.getString("aspect")));

        this.capacity = tag.getInt("capacity");
        this.amount = tag.getInt("amount");
    }

    @Override
    public Aspect getAspect() {
        return aspect;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getAmount() {
        return amount;
    }
}
