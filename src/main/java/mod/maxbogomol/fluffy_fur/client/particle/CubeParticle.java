package mod.maxbogomol.fluffy_fur.client.particle;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import mod.maxbogomol.fluffy_fur.client.particle.options.GenericParticleOptions;
import mod.maxbogomol.fluffy_fur.registry.client.FluffyFurParticles;
import mod.maxbogomol.fluffy_fur.util.RenderUtils;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.util.Mth;

public class CubeParticle extends GenericParticle implements ICustomRenderParticle {

    public CubeParticle(ClientLevel level, GenericParticleOptions data, double x, double y, double z, double vx, double vy, double vz) {
        super(level, data, x, y, z, vx, vy, vz);
    }

    @Override
    public void render(VertexConsumer vertexConsumer, Camera camera, float partialTicks) {
        if (shouldRenderTraits) updateRenderTraits(partialTicks);
        FluffyFurParticles.addParticleList(this);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, float partialTicks) {
        double dX = Mth.lerp(partialTicks, this.xo, this.x);
        double dY = Mth.lerp(partialTicks, this.yo, this.y);
        double dZ = Mth.lerp(partialTicks, this.zo, this.z);

        float size = quadSize;
        float size1 = -(size / 2f);

        poseStack.pushPose();
        poseStack.translate(dX, dY, dZ);
        poseStack.mulPose(Axis.YP.rotation(Mth.lerp(partialTicks, oRoll, roll)));
        RenderUtils.litQuadCube(poseStack, buffer, size1, size1, size1, size, size, size, rCol, gCol, bCol, alpha);
        poseStack.popPose();
    }
}
