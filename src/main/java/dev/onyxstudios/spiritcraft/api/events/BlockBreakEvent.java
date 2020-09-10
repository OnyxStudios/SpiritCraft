package dev.onyxstudios.spiritcraft.api.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public interface BlockBreakEvent {

    public static final Event<BlockBreakEvent> EVENT = EventFactory.createArrayBacked(BlockBreakEvent.class, listeners -> (world, player, state, pos, direction) -> {
        for (BlockBreakEvent event : listeners) {
            event.onBreak(world, player, state, pos, direction);
        }
    });

    void onBreak(World world, PlayerEntity player, BlockState state, BlockPos pos, Direction direction);
}
