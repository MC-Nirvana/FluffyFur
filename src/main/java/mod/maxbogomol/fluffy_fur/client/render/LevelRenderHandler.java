package mod.maxbogomol.fluffy_fur.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.maxbogomol.fluffy_fur.client.particle.ICustomRenderParticle;
import mod.maxbogomol.fluffy_fur.registry.client.FluffyFurRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.fml.ModList;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelRenderHandler {
    @OnlyIn(Dist.CLIENT)
    public static Matrix4f MATRIX4F = null;
    @OnlyIn(Dist.CLIENT)
    public static List<ICustomRenderParticle> particleList = new ArrayList<>();

    public static void onLevelRender(RenderLevelStageEvent event) {
        PoseStack stack = event.getPoseStack();
        float partialTicks = event.getPartialTick();
        MultiBufferSource bufferDelayed = LevelRenderHandler.getDelayedRender();

        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_PARTICLES) {
            stack.pushPose();
            Vec3 pos = event.getCamera().getPosition();
            stack.translate(-pos.x, -pos.y, -pos.z);
            for (ICustomRenderParticle particle : particleList) {
                particle.render(stack, bufferDelayed, partialTicks);
            }
            stack.popPose();
            particleList.clear();
            MATRIX4F = new Matrix4f(RenderSystem.getModelViewMatrix());
        }

        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_WEATHER) {
            Matrix4f last = new Matrix4f(RenderSystem.getModelViewMatrix());
            if (MATRIX4F != null) RenderSystem.getModelViewMatrix().set(MATRIX4F);

            getDelayedRender().endBatch(FluffyFurRenderTypes.GLOWING_SPRITE);
            getDelayedRender().endBatch(FluffyFurRenderTypes.GLOWING);
            getDelayedRender().endBatch(FluffyFurRenderTypes.FLUID);

            RenderSystem.getModelViewMatrix().set(last);

            RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            getDelayedRender().endBatch(FluffyFurRenderTypes.DELAYED_PARTICLE);
            getDelayedRender().endBatch(FluffyFurRenderTypes.DELAYED_TERRAIN_PARTICLE);
            RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            getDelayedRender().endBatch(FluffyFurRenderTypes.GLOWING_PARTICLE);
            getDelayedRender().endBatch(FluffyFurRenderTypes.GLOWING_TERRAIN_PARTICLE);
            RenderSystem.defaultBlendFunc();
        }
    }

    static MultiBufferSource.BufferSource DELAYED_RENDER = null;

    public static MultiBufferSource.BufferSource getDelayedRender() {
        if (DELAYED_RENDER == null) {
            Map<RenderType, BufferBuilder> buffers = new HashMap<>();
            for (RenderType type : FluffyFurRenderTypes.renderTypes) {
                buffers.put(type, new BufferBuilder(ModList.get().isLoaded("rubidium") ? 32768 : type.bufferSize()));
            }
            DELAYED_RENDER = MultiBufferSource.immediateWithBuffers(buffers, new BufferBuilder(128));
        }
        return DELAYED_RENDER;
    }
}
