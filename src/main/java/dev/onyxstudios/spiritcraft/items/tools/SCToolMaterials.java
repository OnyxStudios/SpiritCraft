package dev.onyxstudios.spiritcraft.items.tools;

import dev.onyxstudios.spiritcraft.registry.ModItems;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public enum  SCToolMaterials implements ToolMaterial {
    SPIRIUM(3,500,2.5F,7,22, Ingredient.ofItems(ModItems.SPIRIUM_INGOT)),
    NULL(4, 150, 4, 9, 10, Ingredient.ofItems(ModItems.NULL_INGOT)),
    ELEMENTAL(3, 1500, 3, 8, 18, Ingredient.ofItems(ModItems.SPIRIUM_INGOT));

    private int miningLevel;
    private int durability;
    private float attackDamage;
    private float miningSpeed;
    private int enchantability;
    private Ingredient repairIngredient;

    SCToolMaterials (int miningLevel, int durability, float attackDamage, float miningSpeed, int enchantability, Ingredient repairIngredient) {
        this.miningLevel = miningLevel;
        this.durability = durability;
        this. attackDamage = attackDamage;
        this.miningSpeed = miningSpeed;
        this.enchantability = enchantability;
        this. repairIngredient = repairIngredient;
    }

    @Override
    public int getMiningLevel() {
        return miningLevel;
    }

    @Override
    public int getDurability() {
        return  durability;
    }

    @Override
    public float getAttackDamage() {
        return  attackDamage;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return miningSpeed;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }
}
