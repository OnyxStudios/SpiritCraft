package dev.onyxstudios.spiritcraft.api.rei;

import dev.onyxstudios.spiritcraft.api.aspects.Aspect;
import dev.onyxstudios.spiritcraft.api.aspects.AspectRenderHelper;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.api.fractions.Fraction;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.Optional;

@Environment(EnvType.CLIENT)
public class AspectEntryStack extends DrawableHelper implements EntryStack {

    public Aspect aspect;

    public AspectEntryStack(Aspect aspect) {
        this.aspect = aspect;
    }

    @Override
    public Optional<Identifier> getIdentifier() {
        return Optional.of(aspect.getId());
    }

    @Override
    public Type getType() {
        return Type.RENDER;
    }

    @Override
    public Fraction getAccurateAmount() {
        return Fraction.ofWhole(1);
    }

    @Override
    public void setAmount(Fraction amount) {
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public EntryStack copy() {
        return this;
    }

    @Override
    public Object getObject() {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean equals(EntryStack stack, boolean ignoreTags, boolean ignoreAmount) {
        return stack == this;
    }

    @Override
    public boolean equalsIgnoreTagsAndAmount(EntryStack stack) {
        return true;
    }

    @Override
    public boolean equalsIgnoreTags(EntryStack stack) {
        return stack == this;
    }

    @Override
    public boolean equalsIgnoreAmount(EntryStack stack) {
        return stack == this;
    }

    @Override
    public boolean equalsAll(EntryStack stack) {
        return stack == this;
    }

    @Override
    public int getZ() {
        return getZOffset();
    }

    @Override
    public void setZ(int z) {
        setZOffset(z);
    }

    @Override
    public <T> EntryStack setting(Settings<T> settings, T value) {
        return this;
    }

    @Override
    public <T> EntryStack removeSetting(Settings<T> settings) {
        return this;
    }

    @Override
    public EntryStack clearSettings() {
        return this;
    }

    @Override
    public <T> EntryStack addSetting(Settings<T> settings, T value) {
        return this;
    }

    @Override
    public <T> T get(Settings<T> settings) {
        return settings.getDefaultValue();
    }

    @Override
    public void render(MatrixStack matrix, Rectangle bounds, int mouseX, int mouseY, float delta) {
        if(bounds.contains(mouseX, mouseY))
            AspectRenderHelper.renderAspectTooltip(matrix, aspect, mouseX, mouseY);

        AspectRenderHelper.renderGuiAspectModel(matrix, aspect, bounds.getX(), bounds.getY());
    }
}
