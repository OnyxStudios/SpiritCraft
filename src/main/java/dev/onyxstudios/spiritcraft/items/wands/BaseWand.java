package dev.onyxstudios.spiritcraft.items.wands;

import dev.onyxstudios.spiritcraft.api.aspects.AspectStack;
import dev.onyxstudios.spiritcraft.api.components.spirit.ISpiritComponent;
import dev.onyxstudios.spiritcraft.api.components.spirit.SpiritComponent;
import dev.onyxstudios.spiritcraft.api.items.IWand;
import dev.onyxstudios.spiritcraft.api.items.IWandCaps;
import dev.onyxstudios.spiritcraft.items.BaseItem;
import dev.onyxstudios.spiritcraft.registry.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class BaseWand extends BaseItem implements IWand {

    public static final String WAND_CAP_NAME = "_attached";
    public static final String WAND_CAP_ID = "wand_caps";
    public static final String WAND_FOCI_ID = "wand_foci";
    private int capacity;
    private Item defaultCap = null;

    public BaseWand(int capacity, Item defaultCap) {
        super(new Item.Settings().maxCount(1).group(ModItems.GROUP));
        this.capacity = capacity;
        this.defaultCap = defaultCap;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        ISpiritComponent component = SpiritComponent.SPIRIT.getNullable(stack);

        if(component != null) {
            Item wandCapItem = getWandCap(stack);
            if(!(wandCapItem instanceof IWandCaps)) return;
            IWandCaps wandCap = (IWandCaps) wandCapItem;

            MutableText capacityText = new LiteralText("Capacity ").append(String.valueOf(component.getCapacity())).setStyle(Style.EMPTY.withColor(Formatting.GOLD));
            float efficiency = (1 + (-wandCap.getEfficiency())) * 100;
            MutableText costText = new LiteralText(" (").append(String.valueOf(efficiency == 0 ? 100 : efficiency)).append("% Spirit Cost)").setStyle(Style.EMPTY.withColor(Formatting.WHITE));
            tooltip.add(capacityText.append(costText));

            if(!Screen.hasShiftDown()) {
                LiteralText aspectText = new LiteralText("");

                Iterator<AspectStack> iterator = component.getAspects().iterator();
                while (iterator.hasNext()) {
                    AspectStack aspectStack = iterator.next();
                    aspectText.append(new LiteralText(String.valueOf((int) Math.floor(aspectStack.getCount()))).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(aspectStack.getAspect().getColor()))));

                    if(iterator.hasNext()) {
                        aspectText.append(new LiteralText(" | ").setStyle(Style.EMPTY.withColor(Formatting.WHITE)));
                    }
                }

                tooltip.add(aspectText);
            }else {
                for (AspectStack aspectStack : component.getAspects()) {
                    MutableText aspectText = new TranslatableText(Util.createTranslationKey("aspect", aspectStack.getAspect().getId()))
                            .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(aspectStack.getAspect().getColor())));
                    aspectText.append(new LiteralText(" x " + aspectStack.getCount()).setStyle(Style.EMPTY.withColor(Formatting.WHITE)));
                    tooltip.add(aspectText);
                }
            }
        }
    }

    @Override
    public Text getName() {
        if(MinecraftClient.getInstance().player != null) {
            return getName(MinecraftClient.getInstance().player.getMainHandStack());
        }

        return super.getName();
    }

    @Override
    public Text getName(ItemStack stack) {
        Item wandCap = getWandCap(stack);

        if(wandCap != null) {
            TranslatableText text = new TranslatableText(wandCap.getTranslationKey() + WAND_CAP_NAME);
            return text.append(" ").append(new TranslatableText(this.getTranslationKey(stack)));
        }

        return super.getName(stack);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.UNCOMMON;
    }

    @Override
    public Item getDefaultCap() {
        return this.defaultCap;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public Item getWandCap(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if(tag.contains(WAND_CAP_ID)) {
            return Registry.ITEM.get(new Identifier(tag.getString(WAND_CAP_ID)));
        }

        return null;
    }

    @Override
    public Item getWandFoci(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if(tag.contains(WAND_FOCI_ID)) {
            return Registry.ITEM.get(new Identifier(tag.getString(WAND_FOCI_ID)));
        }

        return null;
    }
}
