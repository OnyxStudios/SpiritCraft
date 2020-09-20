package dev.onyxstudios.spiritcraft.client.render;

import dev.onyxstudios.spiritcraft.api.aspects.Aspect;
import dev.onyxstudios.spiritcraft.api.aspects.AspectRenderHelper;
import dev.onyxstudios.spiritcraft.utils.ScanNotification;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Environment(EnvType.CLIENT)
public class ScannerInfoHudRenderer {

    private static List<ScanNotification> notifications = new ArrayList<>();

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
}
