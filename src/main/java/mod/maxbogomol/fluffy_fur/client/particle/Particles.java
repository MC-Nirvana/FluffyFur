package mod.maxbogomol.fluffy_fur.client.particle;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;

import java.util.Random;

public class Particles {
    public static class ParticleBuilder {
        static Random random = new Random();

        ParticleType<?> type;
        double vx = 0, vy = 0, vz = 0;
        double fvx = 0, fvy = 0, fvz = 0;
        double fdx = 0, fdy = 0, fdz = 0;
        double maxXSpeed = 0, maxYSpeed = 0, maxZSpeed = 0;
        double maxXDist = 0, maxYDist = 0, maxZDist = 0;

        float r1 = 1, g1 = 1, b1 = 1, a1 = 1, r2 = 1, g2 = 1, b2 = 1, a2 = 0;
        float scale1 = 1, scale2 = 0;
        int lifetime = 20;
        float spin = 0;
        boolean gravity = false;

        float randSpin = 0;

        protected ParticleBuilder(ParticleType<?> type) {
            this.type = type;
        }

        public ParticleBuilder setColor(float r, float g, float b) {
            setColor(r, g, b, this.a1, r, g, b, this.a2);
            return this;
        }

        public ParticleBuilder setColor(float r, float g, float b, float a) {
            setColor(r, g, b, a, r, g, b, a);
            return this;
        }

        public ParticleBuilder setColor(float r, float g, float b, float a1, float a2) {
            setColor(r, g, b, a1, r, g, b, a2);
            return this;
        }

        public ParticleBuilder setColor(float r1, float g1, float b1, float r2, float g2, float b2) {
            setColor(r1, g1, b1, this.a1, r2, g2, b2, this.a2);
            return this;
        }

        public ParticleBuilder setColor(float r1, float g1, float b1, float r2, float g2, float b2, float a) {
            setColor(r1, g1, b1, a, r2, g2, b2, a);
            return this;
        }

        public ParticleBuilder setColor(float r1, float g1, float b1, float a1, float r2, float g2, float b2, float a2) {
            this.r1 = r1; this.g1 = g1; this.b1 = b1; this.a1 = a1;
            this.r2 = r2; this.g2 = g2; this.b2 = b2; this.a2 = a2;
            return this;
        }

        public ParticleBuilder setAlpha(float a) {
            setAlpha(a, a);
            return this;
        }

        public ParticleBuilder setAlpha(float a1, float a2) {
            this.a1 = a1;
            this.a2 = a2;
            return this;
        }

        public ParticleBuilder setScale(float scale) {
            setScale(scale, scale);
            return this;
        }

        public ParticleBuilder setScale(float scale1, float scale2) {
            this.scale1 = scale1;
            this.scale2 = scale2;
            return this;
        }

        public ParticleBuilder enableGravity() {
            this.gravity = true;
            return this;
        }

        public ParticleBuilder disableGravity() {
            this.gravity = false;
            return this;
        }

        public ParticleBuilder setSpin(float angularVelocity) {
            this.spin = angularVelocity;
            return this;
        }

        public ParticleBuilder randomSpin(float angularVelocity) {
            this.randSpin = angularVelocity;
            return this;
        }

        public ParticleBuilder setLifetime(int lifetime) {
            this.lifetime = lifetime;
            return this;
        }

        public ParticleBuilder randomVelocity(double maxSpeed) {
            randomVelocity(maxSpeed, maxSpeed, maxSpeed);
            return this;
        }

        public ParticleBuilder randomVelocity(double maxHSpeed, double maxVSpeed) {
            randomVelocity(maxHSpeed, maxVSpeed, maxHSpeed);
            return this;
        }

        public ParticleBuilder randomVelocity(double maxXSpeed, double maxYSpeed, double maxZSpeed) {
            this.maxXSpeed = maxXSpeed;
            this.maxYSpeed = maxYSpeed;
            this.maxZSpeed = maxZSpeed;
            return this;
        }

        public ParticleBuilder addVelocity(double vx, double vy, double vz) {
            this.vx += vx;
            this.vy += vy;
            this.vz += vz;
            return this;
        }

        public ParticleBuilder setVelocity(double vx, double vy, double vz) {
            this.vx = vx;
            this.vy = vy;
            this.vz = vz;
            return this;
        }

        public ParticleBuilder randomOffset(double maxDistance) {
            randomOffset(maxDistance, maxDistance, maxDistance);
            return this;
        }

        public ParticleBuilder randomOffset(double maxHDist, double maxVDist) {
            randomOffset(maxHDist, maxVDist, maxHDist);
            return this;
        }

        public ParticleBuilder randomOffset(double maxXDist, double maxYDist, double maxZDist) {
            this.maxXDist = maxXDist;
            this.maxYDist = maxYDist;
            this.maxZDist = maxZDist;
            return this;
        }

        public ParticleBuilder spawn(Level world, double x, double y, double z) {
            double yaw = random.nextFloat() * Math.PI * 2, pitch = random.nextFloat() * Math.PI - Math.PI / 2,
                    xSpeed = random.nextFloat() * maxXSpeed, ySpeed = random.nextFloat() * maxYSpeed, zSpeed = random.nextFloat() * maxZSpeed;
            double vx = this.vx + Math.sin(yaw) * Math.cos(pitch) * xSpeed;
            double vy = this.vy + Math.sin(pitch) * ySpeed;
            double vz = this.vz + Math.cos(yaw) * Math.cos(pitch) * zSpeed;
            double yaw2 = random.nextFloat() * Math.PI * 2, pitch2 = random.nextFloat() * Math.PI - Math.PI / 2,
                    xDist = random.nextFloat() * maxXDist, yDist = random.nextFloat() * maxYDist, zDist = random.nextFloat() * maxZDist;
            double dx = Math.sin(yaw2) * Math.cos(pitch2) * xDist;
            double dy = Math.sin(pitch2) * yDist;
            double dz = Math.cos(yaw2) * Math.cos(pitch2) * zDist;

            GenericParticleData data = new GenericParticleData(type);
            data.r1 = r1;
            data.g1 = g1;
            data.b1 = b1;
            data.a1 = a1;
            data.r2 = r2;
            data.g2 = g2;
            data.b2 = b2;
            data.a2 = a2;
            data.scale1 = scale1;
            data.scale2 = scale2;
            data.lifetime = lifetime;
            data.spin = spin;
            data.gravity = gravity;
            data.spin = data.spin + (randSpin * ((random.nextFloat() - 0.5f) * 2));

            Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
            if (camera.getPosition().distanceToSqr(x + dx, y + dy, z + dz) <= 10240.0D) {
                world.addParticle(data, x + dx, y + dy, z + dz, vx, vy, vz);
            }

            return this;
        }

        public ParticleBuilder repeat(Level world, double x, double y, double z, int n) {
            for (int i = 0; i < n; i ++) spawn(world, x, y, z);
            return this;
        }

        public ParticleBuilder repeat(Level world, double x, double y, double z, int n, float chance) {
            for (int i = 0; i < n; i ++) {
                if (random.nextFloat() < chance) spawn(world, x, y, z);
            }
            return this;
        }
    }

    public static ParticleBuilder create(ParticleType<?> type) {
        return new ParticleBuilder(type);
    }

    public static ParticleBuilder create(RegistryObject<?> type) {
        return new ParticleBuilder((ParticleType<?>)type.get());
    }
}