package dev.onyxstudios.spiritcraft.api.components;

import dev.onyxstudios.cca.api.v3.component.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.spiritcraft.api.aspects.Aspect;

public interface IEssenceComponent extends ComponentV3, AutoSyncedComponent {

    Aspect getAspect();
    int getCapacity();
    int getAmount();
}
