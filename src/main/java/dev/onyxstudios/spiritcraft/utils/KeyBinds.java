package dev.onyxstudios.spiritcraft.utils;

import dev.onyxstudios.spiritcraft.registry.ModItems;
import dev.onyxstudios.spiritcraft.registry.ModPackets;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class KeyBinds {

    public static KeyBinding SHOVEL_ROTATE;

    public static void register() {
        SHOVEL_ROTATE = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.spiritcraft.shovel_rotate", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, "category.spiritcraft.keybinds"));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(SHOVEL_ROTATE.wasPressed() && client.player != null && client.player.getMainHandStack().isItemEqualIgnoreDamage(new ItemStack(ModItems.ELEMENTAL_SHOVEL))) {
                client.player.networkHandler.sendPacket(new CustomPayloadC2SPacket(ModPackets.PACKET_ROTATE_SHOVEL, new PacketByteBuf(Unpooled.buffer())));
            }
        });
    }
}
