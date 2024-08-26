package mod.maxbogomol.fluffy_fur.mixin.client;

import mod.maxbogomol.fluffy_fur.client.playerskin.PlayerSkin;
import mod.maxbogomol.fluffy_fur.client.playerskin.PlayerSkinHandler;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin {

    @Inject(at = @At("RETURN"), method = "getFieldOfViewModifier", cancellable = true)
    private void fluffy_fur$getFieldOfViewModifier(CallbackInfoReturnable<Float> cir) {
        AbstractClientPlayer self = (AbstractClientPlayer) ((Object) this);
        ItemStack itemstack = self.getUseItem();
        if (self.isUsingItem()) {
            /*
            if (itemstack.is(FluffyFur.ARCANE_WOOD_BOW.get())) {
                float f = cir.getReturnValue();
                int i = self.getTicksUsingItem();
                float f1 = (float)i / 20.0F;
                if (f1 > 1.0F) {
                    f1 = 1.0F;
                } else {
                    f1 *= f1;
                }

                f *= 1.0F - f1 * 0.15F;
                //f = EagleShotArcaneEnchantment.getFOW(self, itemstack, f);
                //f = SplitArcaneEnchantment.getFOW(self, itemstack, f);
                cir.setReturnValue(net.minecraftforge.client.ForgeHooksClient.getFieldOfViewModifier(self, f));
            }*/
        }
    }

    @Inject(at = @At("RETURN"), method = "getSkinTextureLocation", cancellable = true)
    private void fluffy_fur$getSkinTextureLocation(CallbackInfoReturnable<ResourceLocation> cir) {
        AbstractClientPlayer self = (AbstractClientPlayer) ((Object) this);
        PlayerSkin skin = PlayerSkinHandler.getSkin(self);

        if (skin != null) {
            ResourceLocation skinTexture = skin.getSkin(self);
            if (skinTexture != null) {
                cir.setReturnValue(skinTexture);
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "getModelName", cancellable = true)
    private void fluffy_fur$getModelName(CallbackInfoReturnable<String> cir) {
        AbstractClientPlayer self = (AbstractClientPlayer) ((Object) this);
        PlayerSkin skin = PlayerSkinHandler.getSkin(self);

        if (skin != null) {
            if (skin.getSlim(self)) {
                cir.setReturnValue("slim");
            } else {
                cir.setReturnValue("default");
            }
        }
    }
}
