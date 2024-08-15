package mod.maxbogomol.fluffy_fur.client.render.item;

import com.mojang.blaze3d.vertex.PoseStack;
import mod.maxbogomol.fluffy_fur.FluffyFur;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class Item2DRenderer {
    public static final String[] HAND_MODEL_ITEMS = new String[]{"arcane_gold_scythe", "arcane_wood_scythe", "innocent_wood_scythe", "blaze_reap", "skin/soul_hunter_scythe", "skin/implosion_scythe"};

    @SubscribeEvent
    public static void onModelBakeEvent(ModelEvent.ModifyBakingResult event) {
        Map<ResourceLocation, BakedModel> map = event.getModels();
        for (String item : HAND_MODEL_ITEMS) {
            bakeModel(map, FluffyFur.MOD_ID, item);
        }
    }

    public static void bakeModel(Map<ResourceLocation, BakedModel> map, String modId, String item, CustomModelOverrideList itemOverrideList) {
        ResourceLocation modelInventory = new ModelResourceLocation(new ResourceLocation(modId, item), "inventory");
        ResourceLocation modelHand = new ModelResourceLocation(new ResourceLocation(modId, item + "_in_hand"), "inventory");

        BakedModel bakedModelDefault = map.get(modelInventory);
        BakedModel bakedModelHand = map.get(modelHand);
        BakedModel modelWrapper = new LargeItemModel(bakedModelDefault, bakedModelHand, itemOverrideList);
        map.put(modelInventory, modelWrapper);
    }

    public static void bakeModel(Map<ResourceLocation, BakedModel> map, String modId, String item) {
        bakeModel(map, modId, item, new CustomModelOverrideList());
    }

    public static class LargeItemModel implements BakedModel {
        public LargeItemModel(BakedModel bakedModelDefault, BakedModel bakedModelHand) {
            this.bakedModelDefault = bakedModelDefault;
            this.bakedModelHand = bakedModelHand;
            this.itemOverrideList = new CustomModelOverrideList();
        }

        public LargeItemModel(BakedModel bakedModelDefault, BakedModel bakedModelHand, CustomModelOverrideList itemOverrideList) {
            this.bakedModelDefault = bakedModelDefault;
            this.bakedModelHand = bakedModelHand;
            this.itemOverrideList = itemOverrideList;
        }

        @Override
        public List<BakedQuad> getQuads(@Nullable BlockState pState, @Nullable Direction pDirection, RandomSource pRandom) {
            return bakedModelDefault.getQuads(pState, pDirection, pRandom);
        }

        @Override
        public boolean useAmbientOcclusion() {
            return bakedModelDefault.useAmbientOcclusion();
        }

        @Override
        public boolean isGui3d() {
            return bakedModelDefault.isGui3d();
        }

        @Override
        public boolean usesBlockLight() {
            return bakedModelDefault.usesBlockLight();
        }

        @Override
        public boolean isCustomRenderer() {
            return bakedModelDefault.isCustomRenderer();
        }

        @Override
        public TextureAtlasSprite getParticleIcon() {
            return bakedModelDefault.getParticleIcon();
        }

        @Override
        public ItemOverrides getOverrides() {
            return itemOverrideList;
        }

        @Override
        public BakedModel applyTransform(ItemDisplayContext transformType, PoseStack poseStack, boolean applyLeftHandTransform) {
            BakedModel modelToUse = bakedModelDefault;
            if (transformType != ItemDisplayContext.GUI && transformType != ItemDisplayContext.GROUND  && transformType != ItemDisplayContext.FIXED){
                modelToUse = bakedModelHand;
            }
            return ForgeHooksClient.handleCameraTransforms(poseStack, modelToUse, transformType, applyLeftHandTransform);
        }

        private BakedModel bakedModelDefault;
        private BakedModel bakedModelHand;
        private CustomModelOverrideList itemOverrideList;
    }
}