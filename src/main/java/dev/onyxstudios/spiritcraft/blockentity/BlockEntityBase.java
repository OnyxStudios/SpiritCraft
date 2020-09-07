package dev.onyxstudios.spiritcraft.blockentity;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.Tickable;

public class BlockEntityBase extends BlockEntity implements Tickable, BlockEntityClientSerializable {

    public BlockEntityBase(BlockEntityType<?> type) {
        super(type);
    }

    @Override
    public void fromClientTag(CompoundTag compoundTag) {
        this.fromTag(this.getCachedState(), compoundTag);
    }

    @Override
    public CompoundTag toClientTag(CompoundTag compoundTag) {
        return this.toTag(compoundTag);
    }

    @Override
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return new BlockEntityUpdateS2CPacket(this.getPos(), 3, this.toTag(new CompoundTag()));
    }

    public void updateEntity() {
        this.markDirty();
        world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
    }

    @Override
    public void tick() {
    }
}
