package dev.onyxstudios.spiritcraft.utils.scanner;

import dev.onyxstudios.spiritcraft.api.aspects.Aspect;
import dev.onyxstudios.spiritcraft.api.aspects.AspectMap;
import dev.onyxstudios.spiritcraft.api.aspects.AspectStack;
import dev.onyxstudios.spiritcraft.api.components.research.IResearchComponent;
import dev.onyxstudios.spiritcraft.api.components.research.ResearchComponent;
import dev.onyxstudios.spiritcraft.api.components.spirit.ISpiritComponent;
import dev.onyxstudios.spiritcraft.api.components.spirit.SpiritComponent;
import dev.onyxstudios.spiritcraft.api.nodes.INode;
import dev.onyxstudios.spiritcraft.registry.ModPackets;
import io.netty.buffer.Unpooled;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class ScanResult {

    private int maxReach;

    public BlockPos pos = null;
    public int entityId = -1;

    public boolean isScanned = false;
    public boolean canScan = true;

    public Text name;
    public AspectStack[] aspects;
    public Aspect unknownParent;

    public ScanResult(PlayerEntity player, int maxReach, float deltaTime) {
        IResearchComponent component = ResearchComponent.RESEARCH.get(player);
        this.maxReach = maxReach;

        EntityHitResult entityHitResult = raycastEntity(player);
        if (entityHitResult != null) {
            this.isScanned = component.isScanned(entityHitResult.getEntity());
            this.entityId = entityHitResult.getEntity().getEntityId();
            this.name = entityHitResult.getEntity().getDisplayName();
            checkCanScan(component, entityHitResult.getEntity(), null);
        } else {
            BlockHitResult blockHitResult = raycastBlock(player, deltaTime);
            BlockState state = player.world.getBlockState(blockHitResult.getBlockPos());
            this.pos = blockHitResult.getBlockPos();

            if(player.world.getBlockEntity(blockHitResult.getBlockPos()) instanceof INode) {
                this.isScanned = component.isScanned(blockHitResult.getBlockPos());
                checkCanScanNode(component, player.world.getBlockEntity(blockHitResult.getBlockPos()));
            }else {
                this.name = state.getBlock().getName();
                this.isScanned = component.isScanned(state.getBlock());
                checkCanScan(component, null, state.getBlock());
            }
        }
    }

    public void tryScan() {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        if (entityId >= 0 && pos == null) {
            buf.writeInt(entityId);
            MinecraftClient.getInstance().getNetworkHandler().sendPacket(new CustomPayloadC2SPacket(ModPackets.PACKET_SCAN_ENTITY, buf));
            this.isScanned = true;
        } else if (pos != null && !MinecraftClient.getInstance().world.isAir(pos)) {
            buf.writeBlockPos(pos);
            MinecraftClient.getInstance().getNetworkHandler().sendPacket(new CustomPayloadC2SPacket(ModPackets.PACKET_SCAN_BLOCK, buf));
            this.isScanned = true;
        }
    }

    private void checkCanScan(IResearchComponent component, Entity entity, Block block) {
        this.aspects = entity != null ? AspectMap.getAspects(entity) : AspectMap.getAspects(block);
        if (MinecraftClient.getInstance().player.isCreative() && this.aspects.length > 0) {
            canScan = true;
            return;
        }

        if (!isScanned) {
            if (aspects.length <= 0) canScan = false;
            for (AspectStack stack : aspects) {
                for (Aspect parent : stack.getAspect().getParents()) {
                    if (!component.isAspectUnlocked(parent)) {
                        canScan = false;
                        unknownParent = parent;
                    }
                }
            }
        }
    }

    private void checkCanScanNode(IResearchComponent component, BlockEntity node) {
        ISpiritComponent spiritComponent = SpiritComponent.SPIRIT.get(node);
        this.aspects = spiritComponent.getAspects().toArray(new AspectStack[spiritComponent.getAspects().size()]);
        if(MinecraftClient.getInstance().player.isCreative() && this.aspects.length > 0) {
            canScan = true;
            return;
        }

        if(!isScanned) {
            if(aspects.length <= 0) canScan = false;
            for (AspectStack stack : aspects) {
                for (Aspect parent : stack.getAspect().getParents()) {
                    if (!component.isAspectUnlocked(parent)) {
                        canScan = false;
                        unknownParent = parent;
                    }
                }
            }
        }
    }

    private BlockHitResult raycastBlock(PlayerEntity player, float deltaTime) {
        return (BlockHitResult) player.raycast(maxReach, deltaTime, true);
    }

    private EntityHitResult raycastEntity(PlayerEntity player) {
        Vec3d position = new Vec3d(player.getX(), player.getEyeY(), player.getZ());
        Vec3d look = player.getRotationVec(1.0F);
        Vec3d max = position.add(look.x * maxReach, look.y * maxReach, look.z * maxReach);
        Box searchBox = player.getBoundingBox().stretch(look.multiply(maxReach)).expand(1.0D, 1.0D, 1.0D);

        return ProjectileUtil.raycast(player, position, max, searchBox, entity -> true, maxReach * maxReach);
    }
}
