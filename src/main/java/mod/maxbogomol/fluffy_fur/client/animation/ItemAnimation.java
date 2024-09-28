package mod.maxbogomol.fluffy_fur.client.animation;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemAnimation {
    @OnlyIn(Dist.CLIENT)
    public void setupAnim(HumanoidModel model, Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @OnlyIn(Dist.CLIENT)
    public void setupAnimRight(HumanoidModel model, Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @OnlyIn(Dist.CLIENT)
    public void setupAnimLeft(HumanoidModel model, Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @OnlyIn(Dist.CLIENT)
    public void renderArmWithItem(LivingEntity livingEntity, ItemStack itemStack, ItemDisplayContext displayContext, HumanoidArm arm, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {

    }

    @OnlyIn(Dist.CLIENT)
    public void renderArmWithItem(AbstractClientPlayer player, float partialTicks, float pitch, InteractionHand hand, float swingProgress, ItemStack stack, float equippedProgress, PoseStack poseStack, MultiBufferSource buffer, int combinedLight) {

    }

    public boolean isOnlyItemUse() {
        return true;
    }

    public static boolean isRightHand(Player player, InteractionHand hand) {
        if (player.getMainArm() == HumanoidArm.LEFT) {
            return hand != InteractionHand.MAIN_HAND;
        }
        return hand == InteractionHand.MAIN_HAND;
    }

    public static boolean isSameHand(HumanoidArm mainArm, HumanoidArm arm, InteractionHand hand) {
        if (mainArm == arm) {
            return hand == InteractionHand.MAIN_HAND;
        }
        return hand == InteractionHand.OFF_HAND;
    }

    public static boolean isItemUse(LivingEntity player) {
        return (player.isUsingItem() && player.getUseItemRemainingTicks() > 0);
    }
}
