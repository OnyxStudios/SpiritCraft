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

    public void grow(int amount) {
        this.count += amount;
    }

    public void shrink(int amount) {
        this.count -= amount;
        if(count < 0)
            count = 0;
    }
}
