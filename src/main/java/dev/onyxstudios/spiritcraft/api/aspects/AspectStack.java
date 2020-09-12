package dev.onyxstudios.spiritcraft.api.aspects;

public class AspectStack {

    protected Aspect aspect;
    protected int count;

    public AspectStack(Aspect aspect) {
        this(aspect, 1);
    }

    public AspectStack(Aspect aspect, int count) {
        this.aspect = aspect;
        this.count = count;
    }
}
