package dev.onyxstudios.spiritcraft.api.components.spirit;

import dev.onyxstudios.cca.api.v3.component.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.spiritcraft.api.aspects.Aspect;
import dev.onyxstudios.spiritcraft.api.aspects.AspectStack;

import java.util.Collection;

public interface ISpiritComponent extends ComponentV3, AutoSyncedComponent {

    /**
     * @return - A list of aspects that this component stores
     */
    Collection<AspectStack> getAspects();

    /**
     * Use a certain amount of an aspect
     * @param aspect - The aspect to use
     * @param amount - The amount of aspect to use
     */
    void useAspect(Aspect aspect, int amount);

    /**
     * Add to the amount of an aspect stored
     * @param aspect - The aspect to add to
     * @param amount - The amount of aspect to add
     */
    void addAspect(Aspect aspect, int amount);

    /**
     * Get an AspectStack for a certain aspect
     * @param aspect - The aspect to filter
     * @return - Returns an AspectStack of the aspect (Nullable)
     */
    AspectStack getAspectStack(Aspect aspect);

    /**
     * Get the amount stored for a certain aspect
     * @param aspect - The aspect to filter
     * @return - The amount of spirit stored
     */
    float getAspectCount(Aspect aspect);

    /**
     * Get the maximum capacity the component can store for each aspect
     * @return - The capacity of the item
     */
    int getCapacity();
}
