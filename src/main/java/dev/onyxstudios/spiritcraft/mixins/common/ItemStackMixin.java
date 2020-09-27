package dev.onyxstudios.spiritcraft.mixins.common;

import dev.onyxstudios.spiritcraft.api.items.IWand;
import dev.onyxstudios.spiritcraft.items.wands.BaseWand;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow
    @Final
    public Item item;

    @Inject(method = "<init>(Lnet/minecraft/item/ItemConvertible;I)V", at = @At("RETURN"))
    public void init(ItemConvertible item, int count, CallbackInfo ci) {
        addWandCaps();
    }

    @Inject(method = "<init>(Lnet/minecraft/nbt/CompoundTag;)V", at = @At("RETURN"))
    public void initCompound(CompoundTag tag, CallbackInfo ci) {
        addWandCaps();
    }

    private void addWandCaps() {
        ItemStack stack = ((ItemStack) (Object) this);
        CompoundTag tag = this.getOrCreateTag();

        if(stack.getItem() instanceof IWand && !tag.contains(BaseWand.WAND_CAP_ID)) {
            tag.putString(BaseWand.WAND_CAP_ID, Registry.ITEM.getId(((IWand) stack.getItem()).getDefaultCap()).toString());
            this.setTag(tag);
        }
    }

    @Shadow
    public abstract void setTag(CompoundTag tag);

    @Shadow
    public abstract CompoundTag getOrCreateTag();
}
