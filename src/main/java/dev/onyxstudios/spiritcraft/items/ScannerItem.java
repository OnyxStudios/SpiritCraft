package dev.onyxstudios.spiritcraft.items;

import dev.onyxstudios.spiritcraft.api.aspects.AspectStack;
import dev.onyxstudios.spiritcraft.api.components.research.IResearchComponent;
import dev.onyxstudios.spiritcraft.api.components.research.ResearchComponent;
import dev.onyxstudios.spiritcraft.client.render.ScannerInfoHudRenderer;
import dev.onyxstudios.spiritcraft.registry.ModItems;
import dev.onyxstudios.spiritcraft.registry.ModSounds;
import dev.onyxstudios.spiritcraft.utils.ScanResult;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class ScannerItem extends BaseItem {

    public static int MAX_REACH = 4;
    public ScanResult scanResult;

    public ScannerItem() {
        super(new Item.Settings().group(ModItems.GROUP).maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if(hand == Hand.MAIN_HAND && player.getOffHandStack().isEmpty()) {
            this.scanResult = new ScanResult(player, MAX_REACH, 1);

            if(!scanResult.isScanned) {
                player.setCurrentHand(hand);
            }
        }

        return super.use(world, player, hand);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if(world.isClient) {
            ((PlayerEntity) user).playSound(ModSounds.ASPECT_SCAN, SoundCategory.MASTER, 0.2f, 1);
            IResearchComponent component = ResearchComponent.RESEARCH.get((PlayerEntity) user);

            if (scanResult != null && !scanResult.isScanned && remainingUseTicks <= 2) {
                if(scanResult.canScan) {
                    boolean[] discovered = new boolean[scanResult.aspects.length];
                    for (int i = 0; i < scanResult.aspects.length; i++) {
                        discovered[i] = component.isAspectUnlocked(scanResult.aspects[i].getAspect());
                    }

                    scanResult.tryScan();
                    for (int i = 0; i < scanResult.aspects.length; i++) {
                        AspectStack aspectStack = scanResult.aspects[i];
                        boolean newDiscovery = discovered[i];

                        TranslatableText name = new TranslatableText(Util.createTranslationKey("aspect", aspectStack.getAspect().getId()));
                        MutableText text = new LiteralText("Gained " + aspectStack.getCount() + " research point(s) for ").append(name);
                        ScannerInfoHudRenderer.addNotification(text, aspectStack.getAspect());

                        if(!newDiscovery) {
                            MutableText discoveredText = new LiteralText("You have discovered ").append(name).append("!");
                            ScannerInfoHudRenderer.addNotification(discoveredText.setStyle(Style.EMPTY.withColor(Formatting.GOLD)), aspectStack.getAspect());
                        }
                    }
                }else {
                    if(scanResult.pos != null || scanResult.entityId >= 0) {
                        if(scanResult.unknownParent != null){
                            ScannerInfoHudRenderer.addNotification(new LiteralText("To Understand this you need to understand the sources of " + scanResult.unknownParent.getDescriptor()).setStyle(Style.EMPTY.withColor(Formatting.RED)), null);
                        }else {
                            ScannerInfoHudRenderer.addNotification(new LiteralText("Insufficient knowledge to scan this!").setStyle(Style.EMPTY.withColor(Formatting.RED)), null);
                        }
                    }
                }

                if(scanResult.isScanned)
                    ((PlayerEntity) user).playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.MASTER, 0.6f, 1);
            }
        }
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return super.getUseAction(stack);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 25;
    }
}
