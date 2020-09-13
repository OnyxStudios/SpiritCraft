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

    public Aspect getAspect() {
        return aspect;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void grow() {
        this.count++;
    }

    public void shrink() {
        this.count--;
    }
}