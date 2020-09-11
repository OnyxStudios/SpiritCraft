package dev.onyxstudios.spiritcraft.mixins;

import dev.onyxstudios.spiritcraft.api.events.BlockBreakEvent;
import net.minecraft.block.BlockState;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.s2c.play.PlayerActionResponseS2CPacket;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerInteractionManager.class)
public abstract class ServerPlayerInteractionManagerMixin {

    @Shadow public ServerWorld world;

    @Inject(method = "processBlockBreakingAction",at = @At(
            target = "Lnet/minecraft/server/network/ServerPlayerInteractionManager;finishMining(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/network/packet/c2s/play/PlayerActionC2SPacket$Action;Ljava/lang/String;)V",
            value = "INVOKE",
            args = "ldc=destroyed",
            shift = At.Shift.BEFORE
    ), cancellable = true)
    public void processBlockBreakingAction(BlockPos pos, PlayerActionC2SPacket.Action action, Direction direction, int worldHeight, CallbackInfo ci) {
        String reason = "destroyed";
        ServerPlayerInteractionManager thisInst = (ServerPlayerInteractionManager) (Object) this;
        BlockState state = thisInst.world.getBlockState(pos);
        if (thisInst.tryBreakBlock(pos)) {
            BlockBreakEvent.EVENT.invoker().onBreak(world, thisInst.player, state, pos, direction);
            thisInst.player.networkHandler.sendPacket(new PlayerActionResponseS2CPacket(pos, thisInst.world.getBlockState(pos), action, true, reason));
        } else {
            thisInst.player.networkHandler.sendPacket(new PlayerActionResponseS2CPacket(pos, thisInst.world.getBlockState(pos), action, false, reason));
        }
        ci.cancel();
    }
}
