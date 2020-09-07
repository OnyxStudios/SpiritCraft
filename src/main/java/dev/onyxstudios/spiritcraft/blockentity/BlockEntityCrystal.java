package dev.onyxstudios.spiritcraft.blockentity;

import dev.onyxstudios.spiritcraft.registry.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;

import java.util.ArrayList;
import java.util.List;

public class BlockEntityCrystal extends BlockEntity {

    public List<BlockState> states = new ArrayList<>();

    public BlockEntityCrystal() {
        super(ModEntities.CRYSTAL_ENTITY);
    }
}
