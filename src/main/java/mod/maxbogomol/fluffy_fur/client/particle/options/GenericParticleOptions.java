package mod.maxbogomol.fluffy_fur.client.particle.options;

import mod.maxbogomol.fluffy_fur.client.particle.SpriteParticleRenderType;
import mod.maxbogomol.fluffy_fur.client.particle.behavior.ParticleBehavior;
import mod.maxbogomol.fluffy_fur.client.particle.data.*;
import mod.maxbogomol.fluffy_fur.registry.client.FluffyFurRenderTypes;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;

import java.awt.*;

public class GenericParticleOptions implements ParticleOptions {

    ParticleType<?> type;

    public RenderType renderType = FluffyFurRenderTypes.GLOWING_PARTICLE;
    public ParticleRenderType particleRenderType = SpriteParticleRenderType.INSTANCE;

    public ParticleBehavior behavior = null;

    public static final ColorParticleData DEFAULT_COLOR = ColorParticleData.create(Color.WHITE, Color.WHITE).build();
    public static final GenericParticleData DEFAULT_GENERIC = GenericParticleData.create(1, 0).build();
    public static final SpinParticleData DEFAULT_SPIN = SpinParticleData.create(0).build();
    public static final LightParticleData DEFAULT_LIGHT = LightParticleData.GLOW;
    public static final SpriteParticleData DEFAULT_SPRITE = SpriteParticleData.RANDOM;

    public ColorParticleData colorData = DEFAULT_COLOR;
    public GenericParticleData transparencyData = DEFAULT_GENERIC;
    public GenericParticleData scaleData = DEFAULT_GENERIC;
    public SpinParticleData spinData = DEFAULT_SPIN;
    public LightParticleData lightData = DEFAULT_LIGHT;
    public SpriteParticleData spriteData = DEFAULT_SPRITE;

    public int lifetime = 20;
    public int additionalLifetime = 0;
    public float gravity = 0;
    public float additionalGravity = 0;
    public float friction = 0.98f;
    public float additionalFriction = 0;
    public boolean shouldCull = false;
    public boolean shouldRenderTraits = true;
    public boolean hasPhysics = true;

    public GenericParticleOptions(ParticleType<?> type) {
        this.type = type;
    }

    @Override
    public ParticleType<?> getType() {
        return type;
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf buffer) {

    }

    @Override
    public String writeToString() {
        return getClass().getSimpleName();
    }
}