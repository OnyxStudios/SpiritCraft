package dev.onyxstudios.spiritcraft.api.components.essence;

import dev.onyxstudios.cca.api.v3.component.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.spiritcraft.api.aspects.Aspect;

public interface IEssenceComponent extends ComponentV3, AutoSyncedComponent {

    /**
     * @return - The current Aspect being stored (Nullable)
     */
    Aspect getAspect();

    /**
     * @return - The maximum amount of essence that can be stored
     */
    int getCapacity();

    /**
     * @return - The amount of essence stored
     */
    int getAmount();

    /**
     * Set the aspect to be stored
     * @param aspect
     */
    void setAspect(Aspect aspect);

    /**
     * Set the essence stored amount
     * @param amount - The amount of essence stored
     */
    void setAmount(int amount);

    /**
     * Fill the Essence Container
     * @param amount - The amount of essence to fill
     * @return - The resulting extra essence
     */
    int fillAspect(int amount);

    /**
     * Drain from the Essence Container
     * @param amount - The amount of essence to drain
     * @return - The remaining essence it couldn't drain
     */
    int drainAspect(int amount);
}
