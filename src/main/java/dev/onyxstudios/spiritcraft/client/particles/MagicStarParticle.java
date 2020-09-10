package dev.onyxstudios.spiritcraft.client.particles;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class MagicStarParticle extends SpriteBillboardParticle {

    public SpriteProvider spriteProvider;

    public MagicStarParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z, velocityX, velocityY, velocityZ);
        this.setMaxAge(18);
        this.scale(0.75f);
        this.spriteProvider = spriteProvider;
        this.setSpriteForAge(spriteProvider);
    }

    public void tick() {
        if (this.age++ >= this.maxAge) {
            this.markDead();
        }
        this.setSpriteForAge(this.spriteProvider);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double x, double y, double z, double vx, double vy, double vz) {
            return new MagicStarParticle(clientWorld, x, y, z, vx, vy, vz, this.spriteProvider);
        }
    }
}
