package dev.onyxstudios.spiritcraft.api.aspects;

import net.minecraft.util.Identifier;

public class Aspect {

    protected Identifier id;
    protected int color;
    protected String descriptor;
    protected Aspect[] parents;

    /**
     * Create a new aspect type
     * @param id - An identifier used to register the aspect and it's resources (Icon)
     * @param color - The fluid & item color of the aspect
     * @param descriptor - A descriptor/Key about the aspect
     * @param parents - Aspects used to make this aspect
     */
    public Aspect(Identifier id, int color, String descriptor, Aspect... parents) {
        this.id = id;
        this.color = color;
        this.descriptor = descriptor;
        this.parents = parents;
        SpiritCraftAspects.ASPECTS.put(id, this);
    }

    public Identifier getId() {
        return id;
    }

    public int getColor() {
        return color;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public Aspect[] getParents() {
        return parents;
    }
}
