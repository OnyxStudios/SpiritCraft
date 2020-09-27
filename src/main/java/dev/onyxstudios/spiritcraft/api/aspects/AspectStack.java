package dev.onyxstudios.spiritcraft.api.aspects;

public class AspectStack {

    protected Aspect aspect;
    protected float count;

    public AspectStack(Aspect aspect) {
        this(aspect, 1);
    }

    public AspectStack(Aspect aspect, float count) {
        this.aspect = aspect;
        this.count = count;
    }

    public Aspect getAspect() {
        return aspect;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public void grow(float amount) {
        this.count += amount;
    }

    public void shrink(float amount) {
        this.count -= amount;
        if(count < 0)
            count = 0;
    }

    public AspectStack copy() {
        return new AspectStack(aspect, count);
    }
}
