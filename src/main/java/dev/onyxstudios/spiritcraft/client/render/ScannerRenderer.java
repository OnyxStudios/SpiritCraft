package dev.onyxstudios.spiritcraft.client.render;

import dev.onyxstudios.spiritcraft.api.aspects.AspectRenderHelper;
import dev.onyxstudios.spiritcraft.api.aspects.AspectStack;
import dev.onyxstudios.spiritcraft.items.ScannerItem;
import dev.onyxstudios.spiritcraft.registry.ModItems;
import dev.onyxstudios.spiritcraft.utils.ScanResult;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class ScannerRenderer {

    public static void renderScannerHitResult(MatrixStack matrix, float partialTicks) {
        MinecraftClient client = MinecraftClient.getInstance();
        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();

        if(client.player.getMainHandStack().isItemEqual(ModItems.SCANNER.getStackForRender()) && client.player.getOffHandStack().isEmpty()) {
            if(!client.options.getPerspective().isFirstPerson())
                return;

            ScanResult scanResult = new ScanResult(client.player, ScannerItem.MAX_REACH, partialTicks);

            matrix.push();
            if(scanResult.isScanned) {
                int x = (width / 2) - (client.textRenderer.getWidth(scanResult.name) / 2);
                int y = height / 2 - 32;
                client.textRenderer.draw(matrix, scanResult.name, x, y, 0xFFFFFF);

                y += 32;
                x = (width / 2) - (scanResult.aspects.length * 16);
                for (AspectStack stack : scanResult.aspects) {
                    if(stack.getCount() > 1) {
                        matrix.push();
                        String string = String.valueOf(stack.getCount());
                        matrix.translate(0, 0, 300);
                        client.textRenderer.draw(matrix, string, x + 22 - client.textRenderer.getWidth(string), y + 18, 0xFFFFFF);
                        matrix.pop();
                    }

                    AspectRenderHelper.renderGuiAspectModel(matrix, stack.getAspect(), x, y, 32);
                    x += 33;
                }
            }

            matrix.pop();
        }
    }
}
