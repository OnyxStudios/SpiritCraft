package dev.onyxstudios.spiritcraft.client.particles;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class MagicBubbleParticle extends AscendingParticle {

    public MagicBubbleParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z, 0.2f, 0.2f, 0.2f, velocityX, velocityY, velocityZ, 1.2f, spriteProvider, 1, 12, 0.0006D, true);
        this.setColor(240 / 255.0f, 224 / 255.0f, 255 / 255.0f);
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
            return new MagicBubbleParticle(clientWorld, x, y, z, vx, vy, vz, this.spriteProvider);
        }
    }
}
