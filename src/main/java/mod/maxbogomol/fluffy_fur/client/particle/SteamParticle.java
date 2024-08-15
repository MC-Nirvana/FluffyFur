package mod.maxbogomol.fluffy_fur.client.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import mod.maxbogomol.fluffy_fur.client.config.ClientConfig;
import mod.maxbogomol.fluffy_fur.client.render.WorldRenderHandler;
import mod.maxbogomol.fluffy_fur.utils.RenderUtils;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;

public class SteamParticle extends GenericParticle {
    public SteamParticle(ClientLevel world, GenericParticleData data, double x, double y, double z, double vx, double vy, double vz) {
        super(world, data, x, y, z, vx, vy, vz);
    }

    @Override
    protected int getLightColor(float partialTicks) {
        return 0xF000F0;
    }

    @Override
    public void render(VertexConsumer b, Camera info, float pticks) {
        super.render(ClientConfig.BETTER_LAYERING.get() ? WorldRenderHandler.getDelayedRender().getBuffer(RenderUtils.GLOWING_PARTICLE) : b, info, pticks);
    }
}