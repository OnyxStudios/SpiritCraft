package dev.onyxstudios.spiritcraft.utils.scanner;

import dev.onyxstudios.spiritcraft.api.aspects.Aspect;
import net.minecraft.text.Text;

public class ScanNotification {

    public Text text;
    public Aspect aspect;
    public long currTime;
    public long maxAge;
    public float y;

    public ScanNotification(Text text, Aspect aspect) {
        this(text, aspect, 25);
    }

    public ScanNotification(Text text, Aspect aspect, long maxAge) {
        this.text = text;
        this.aspect = aspect;
        this.currTime = System.currentTimeMillis();
        this.maxAge = maxAge;
    }
}
