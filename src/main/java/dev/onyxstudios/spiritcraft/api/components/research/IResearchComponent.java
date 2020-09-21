package dev.onyxstudios.spiritcraft.api.components.research;

import dev.onyxstudios.cca.api.v3.component.AutoSyncedComponent;
import dev.onyxstudios.spiritcraft.api.aspects.Aspect;
import dev.onyxstudios.spiritcraft.api.aspects.AspectStack;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.List;

public interface IResearchComponent extends AutoSyncedComponent {

    /**
     * Check if the player has unlocked an Aspect
     * @param aspect - The Aspect to check for
     * @return - True if unlocked false if locked
     */
    boolean isAspectUnlocked(Aspect aspect);

    /**
     * Get the AspectStack for a stored Aspect
     * @param aspect - The aspect to filter
     * @return - The stored AspectStack (Nullable)
     */
    AspectStack getStackForAspect(Aspect aspect);

    /**
     * Use an ammount of a certain aspect. If amount > AspectStack.getCount() then the aspect amount will be set to 0
     * @param aspect - The aspect to filter
     * @param amount - The amount of aspect to use
     */
    void useAspect(Aspect aspect, int amount);

    /**
     * Unlock or Increase stack size of a certain aspect
     * @param stack - The aspect to add
     */
    void addAspect(AspectStack stack);

    /**
     * Check if a block has been scanned
     * @param block - The block to filter
     * @return - True if scanned, false if not
     */
    boolean isScanned(Block block);

    /**
     * Check if an item has been scanned, If item is an ItemBlock convert to block
     * @param item - The item filter
     * @return - True if scanned, false if not
     */
    boolean isScanned(Item item);

    /**
     * Check if an entity has been scanned, If ItemEntity check for items
     * @param entity - The entitty filter
     * @return - True if scanned, false if not
     */
    boolean isScanned(Entity entity);

    /**
     * Scan a block
     * @param block - The block you've scanned
     */
    void scanObject(Block block);

    /**
     * Scan an item
     * @param item - The block you've scanned
     */
    void scanObject(Item item);

    /**
     * Scan an entity
     * @param entity - The block you've scanned
     */
    void scanObject(Entity entity);

    /**
     * Get a list of all unlocked aspects
     * @return - Returns a List<Identifier> of all unlocked aspects
     */
    List<Identifier> getUnlockedAspects();

    /**
     * Get a list of all currently stored player aspects
     * @return - A list of all current player aspects
     */
    List<AspectStack> getPlayerAspects();

    /**
     * @return - A list of all scanned Blocks
     */
    List<Block> getScannedBlocks();

    /**
     * @return - A list of all scanned Items
     */
    List<Item> getScannedItems();

    /**
     * @return - A list of all scanned Entities
     */
    List<EntityType<?>> getScannedEntities();
}
