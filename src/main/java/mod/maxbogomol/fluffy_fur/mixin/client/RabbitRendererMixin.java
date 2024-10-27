package mod.maxbogomol.fluffy_fur.mixin.client;

import mod.maxbogomol.fluffy_fur.FluffyFur;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.RabbitRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Rabbit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RabbitRenderer.class)
public abstract class RabbitRendererMixin {
    @Unique
    private static final ResourceLocation RABBIT_NANACHI_LOCATION = new ResourceLocation(FluffyFur.MOD_ID,"textures/entity/rabbit/nanachi.png");

    @Inject(at = @At("HEAD"), method = "getTextureLocation*", cancellable = true)
    public void fluffy_fur$getTextureLocation(Rabbit entity, CallbackInfoReturnable<ResourceLocation> ci) {
        String s = ChatFormatting.stripFormatting(entity.getName().getString());
        if ("Nanachi".equals(s)) {
            ci.setReturnValue(RABBIT_NANACHI_LOCATION);
        }
    }
}
