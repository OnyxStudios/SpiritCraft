package dev.onyxstudios.spiritcraft.items.tools;

import dev.onyxstudios.spiritcraft.registry.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;

public enum  SCToolMaterials implements ToolMaterial {
    SPIRIUM(3,500,7.0F,2.5F,22, Ingredient.ofItems(ModItems.SPIRIUM_INGOT)),
    NULL(4, 150, 8F, 3, 10, Ingredient.ofItems(ModItems.NULL_INGOT)),
    ELEMENTAL(3, 1500, 9F, 3, 18, Ingredient.ofItems(ModItems.SPIRIUM_INGOT));

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
