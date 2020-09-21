package dev.onyxstudios.spiritcraft.client.render.scanner;

import dev.onyxstudios.spiritcraft.api.aspects.Aspect;
import dev.onyxstudios.spiritcraft.api.aspects.AspectRenderHelper;
import dev.onyxstudios.spiritcraft.utils.scanner.ScanDisplay;
import dev.onyxstudios.spiritcraft.utils.scanner.ScanNotification;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Environment(EnvType.CLIENT)
public class ScannerInfoHudRenderer {

    private static List<ScanNotification> notifications = new ArrayList<>();
    private static List<ScanDisplay> displays = new ArrayList<>();

    public static void renderDisplayHud(MatrixStack matrix, float partialTicks) {
        float millisTicks = (partialTicks / 20) / 1000;
        if (!displays.isEmpty()) {
            MinecraftClient client = MinecraftClient.getInstance();
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();
            client.getItemRenderer().renderGuiItemIcon(Items.BOOK.getStackForRender(), width - 16, 0);

            float destX = width;
            float destY = -8;
            long time = System.currentTimeMillis();
            for (int i = 0; i < displays.size(); i++) {
                ScanDisplay display = displays.get(i);
                float startX = width / 2.0f + display.x;
                float startY = height / 2.0f + display.y;
                float controlX = startX + (startX / 2);
                float controlY = startY + 2;

                float t = (time - display.created + millisTicks) / (display.expired - display.created + millisTicks);
                float x = (1.0f - t) * (1.0f - t) * startX + 2 * (1.0f - t) * t * controlX + t * t * destX;
                float y = (1.0f - t) * (1.0f - t) * startY + 2 * (1.0f - t) * t * controlY + t * t * destY;

                AspectRenderHelper.renderGuiAspectModel(matrix, display.aspect, x, y, 32);

                if(x >= width && y <= 0)
                    displays.remove(display);
            }
        }
    }

    public static void renderInfoHud(MatrixStack matrix, float partialTicks) {
        MinecraftClient client = MinecraftClient.getInstance();
        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();

        int y = (int) (height * 0.85);
        for (ScanNotification notification : notifications) {
            int textWidth = client.textRenderer.getWidth(notification.text);
            int x = width - (textWidth + 20);
            client.textRenderer.draw(matrix, notification.text, x, y + notification.y, 0xFFFFFF);

            if(notification.aspect != null)
                AspectRenderHelper.renderGuiAspectModel(matrix, notification.aspect, x + textWidth + 1, (int) (y + notification.y - 3), 16);

            notification.y += 0.025f;
        }

        updateNotifications();
    }

    private static void updateNotifications() {
        for (int i = 0; i < notifications.size(); i++) {
            ScanNotification notification = notifications.get(i);
            float age = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - TimeUnit.MILLISECONDS.toSeconds(notification.currTime);

            if(age >= notification.maxAge) {
                notifications.remove(notification);
            }
        }
    }

    public static void addDisplayAspect(Aspect aspect) {
        displays.add(new ScanDisplay(aspect, 0.4f, 0.4f));
    }

    public static void addNotification(Text text, Aspect aspect) {
        int height = MinecraftClient.getInstance().getWindow().getScaledHeight();
        int dy = (int) (height * 0.85);

        float y = 0;
        if(!notifications.isEmpty()) {
            ScanNotification lastNotification = notifications.get(notifications.size() - 1);
            y = lastNotification.y - (MinecraftClient.getInstance().textRenderer.fontHeight + 8);

            if(dy + y + 9 > height)
                y = 0;
        }

        ScanNotification notification = new ScanNotification(text, aspect);
        notification.y = y;
        notifications.add(notification);
    }
}
