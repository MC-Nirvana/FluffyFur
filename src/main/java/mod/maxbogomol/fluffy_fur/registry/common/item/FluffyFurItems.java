package mod.maxbogomol.fluffy_fur.registry.common.item;

import mod.maxbogomol.fluffy_fur.FluffyFur;
import mod.maxbogomol.fluffy_fur.common.item.TestShrimpItem;
import mod.maxbogomol.fluffy_fur.registry.common.block.FluffyFurBlocks;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FluffyFurItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FluffyFur.MOD_ID);

    public static final RegistryObject<Item> TEST_SHRIMP = ITEMS.register("test_shrimp", () -> new TestShrimpItem(new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> THING = ITEMS.register("thing", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> MAXBOGOMOL_PLUSH = ITEMS.register("maxbogomol_plush", () -> new BlockItem(FluffyFurBlocks.MAXBOGOMOL_PLUSH.get(), new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> ONIXTHECAT_PLUSH = ITEMS.register("onixthecat_plush", () -> new BlockItem(FluffyFurBlocks.ONIXTHECAT_PLUSH.get(), new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static void composterItem(float chance, ItemLike item) {
        ComposterBlock.add(chance, item);
    }

    public static void makeBow(Item item) {
        ItemProperties.register(item, new ResourceLocation("pull"), (stack, level, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
            }
        });

        ItemProperties.register(item, new ResourceLocation("pulling"), (stack, level, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
    }
}