package dev.onyxstudios.spiritcraft.items.tools;

import dev.onyxstudios.spiritcraft.SpiritCraft;
import dev.onyxstudios.spiritcraft.client.render.SCRenderLayers;
import dev.onyxstudios.spiritcraft.registry.ModBlocks;
import dev.onyxstudios.spiritcraft.registry.ModRenders;
import dev.onyxstudios.spiritcraft.registry.ModSounds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.PickaxeItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ElementalPickaxe extends PickaxeItem {

    public static final int MAX_DEPTH = 16;
    public static final int MAX_RANGE = 16;

    public ElementalPickaxe() {
        super(SCToolMaterials.ELEMENTAL, (int) SCToolMaterials.ELEMENTAL.getAttackDamage(), SCToolMaterials.ELEMENTAL.getMiningSpeedMultiplier(), new Item.Settings().group(SpiritCraft.GROUP));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        handleScanning(context.getWorld(), context.getPlayer(), context.getHand());
        return super.useOnBlock(context);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        handleScanning(world, player, hand);
        return super.use(world, player, hand);
    }

    public void handleScanning(World world, PlayerEntity player, Hand hand) {
        List<BlockPos> orePos = new ArrayList<>();
        for (int y = 0; y <= MAX_DEPTH; y++) {
            for (int x = -MAX_RANGE / 2; x < MAX_RANGE / 2; x++) {
                for (int z = -MAX_RANGE / 2; z < MAX_RANGE / 2; z++) {
                    BlockPos currentPos = player.getBlockPos().add(x, -y, z);
                    if(ModBlocks.ORES.contains(world.getBlockState(currentPos).getBlock())) {
                        orePos.add(currentPos);
                    }
                }
            }
        }

        if(!orePos.isEmpty() && world.isClient) {
            for (BlockPos pos : orePos) {
                ModRenders.scheduleRender((stack, vertexConsumerProvider) -> {
                    Vec3d camera = MinecraftClient.getInstance().gameRenderer.getCamera().getPos();
                    stack.push();
                    stack.translate(pos.getX() + 0.5 - camera.getX(), pos.getY() + 0.25 - camera.getY(), pos.getZ() + 0.5 - camera.getZ());
                    stack.multiply(MinecraftClient.getInstance().gameRenderer.getCamera().getRotation());
                    stack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0f));
                    VertexConsumer vertexBuilder = vertexConsumerProvider.getBuffer(SCRenderLayers.getElementalInstance());
                    vertexBuilder.vertex(stack.peek().getModel(), -0.5f, -0.25f, 0).texture(0, 0).next();
                    vertexBuilder.vertex(stack.peek().getModel(), 0.5f, -0.25f, 0).texture(0, 1).next();
                    vertexBuilder.vertex(stack.peek().getModel(), 0.5f, 0.75f, 0).texture(1, 1).next();
                    vertexBuilder.vertex(stack.peek().getModel(), -0.5f, 0.75f, 0).texture(1, 0).next();
                    stack.pop();
                }, 5);
            }
        }

        player.getStackInHand(hand).damage(5, player, playerEntity -> {});
        player.playSound(ModSounds.ELEMENTAL_PICKAXE_SCAN, SoundCategory.BLOCKS, 0.2f, 1.0f);
    }
}
