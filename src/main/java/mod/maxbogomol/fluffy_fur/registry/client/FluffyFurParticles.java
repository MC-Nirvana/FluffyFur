package mod.maxbogomol.fluffy_fur.registry.client;

import mod.maxbogomol.fluffy_fur.FluffyFur;
import mod.maxbogomol.fluffy_fur.client.particle.CubeParticleType;
import mod.maxbogomol.fluffy_fur.client.particle.GenericParticleType;
import mod.maxbogomol.fluffy_fur.client.particle.ItemParticleType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FluffyFurParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, FluffyFur.MOD_ID);

    public static RegistryObject<GenericParticleType> WISP = PARTICLES.register("wisp", GenericParticleType::new);
    public static RegistryObject<GenericParticleType> TINY_WISP = PARTICLES.register("tiny_wisp", GenericParticleType::new);
    public static RegistryObject<GenericParticleType> SPARKLE = PARTICLES.register("sparkle", GenericParticleType::new);
    public static RegistryObject<GenericParticleType> STAR = PARTICLES.register("star", GenericParticleType::new);
    public static RegistryObject<GenericParticleType> TINY_STAR = PARTICLES.register("tiny_star", GenericParticleType::new);
    public static RegistryObject<GenericParticleType> SQUARE = PARTICLES.register("square", GenericParticleType::new);
    public static RegistryObject<GenericParticleType> DOT = PARTICLES.register("dot", GenericParticleType::new);
    public static RegistryObject<GenericParticleType> CIRCLE = PARTICLES.register("circle", GenericParticleType::new);
    public static RegistryObject<GenericParticleType> TINY_CIRCLE = PARTICLES.register("tiny_circle", GenericParticleType::new);
    public static RegistryObject<GenericParticleType> HEART = PARTICLES.register("heart", GenericParticleType::new);
    public static RegistryObject<GenericParticleType> SMOKE = PARTICLES.register("smoke", GenericParticleType::new);
    public static RegistryObject<CubeParticleType> CUBE = PARTICLES.register("cube", CubeParticleType::new);
    public static RegistryObject<GenericParticleType> TRAIL = PARTICLES.register("trail", GenericParticleType::new);
    public static RegistryObject<ItemParticleType> ITEM = PARTICLES.register("item", ItemParticleType::new);

    public static void register(IEventBus eventBus) {
        PARTICLES.register(eventBus);
    }

    @Mod.EventBusSubscriber(modid = FluffyFur.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientRegistryEvents {
        @SubscribeEvent
        public static void registerParticles(RegisterParticleProvidersEvent event) {
            ParticleEngine particleEngine = Minecraft.getInstance().particleEngine;
            particleEngine.register(WISP.get(), GenericParticleType.Factory::new);
            particleEngine.register(TINY_WISP.get(), GenericParticleType.Factory::new);
            particleEngine.register(SPARKLE.get(), GenericParticleType.Factory::new);
            particleEngine.register(STAR.get(), GenericParticleType.Factory::new);
            particleEngine.register(TINY_STAR.get(), GenericParticleType.Factory::new);
            particleEngine.register(SQUARE.get(), GenericParticleType.Factory::new);
            particleEngine.register(DOT.get(), GenericParticleType.Factory::new);
            particleEngine.register(CIRCLE.get(), GenericParticleType.Factory::new);
            particleEngine.register(TINY_CIRCLE.get(), GenericParticleType.Factory::new);
            particleEngine.register(HEART.get(), GenericParticleType.Factory::new);
            particleEngine.register(SMOKE.get(), GenericParticleType.Factory::new);
            particleEngine.register(CUBE.get(), CubeParticleType.Factory::new);
            particleEngine.register(TRAIL.get(), GenericParticleType.Factory::new);
            particleEngine.register(ITEM.get(), ItemParticleType.Factory::new);
        }
    }
}