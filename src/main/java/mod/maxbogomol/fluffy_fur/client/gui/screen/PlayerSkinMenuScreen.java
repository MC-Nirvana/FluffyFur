package mod.maxbogomol.fluffy_fur.client.gui.screen;

import com.mojang.blaze3d.platform.InputConstants;
import mod.maxbogomol.fluffy_fur.client.playerskin.PlayerSkin;
import mod.maxbogomol.fluffy_fur.client.playerskin.PlayerSkinEffect;
import mod.maxbogomol.fluffy_fur.client.playerskin.PlayerSkinHandler;
import mod.maxbogomol.fluffy_fur.common.network.PacketHandler;
import mod.maxbogomol.fluffy_fur.common.network.playerskin.PlayerSkinChangePacket;
import mod.maxbogomol.fluffy_fur.registry.client.FluffyFurPlayerSkins;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class PlayerSkinMenuScreen extends Screen {

    public List<SkinEntry> skins = new ArrayList<>();
    public List<SkinEffectEntry> skinEffects = new ArrayList<>();

    public PlayerSkinMenuScreen() {
        super(Component.empty());
        initSkins();
    }

    public void initSkins() {
        skins.add(new SkinEntry(null, Component.literal("Standard")));
        skins.add(new SkinEntry(FluffyFurPlayerSkins.MAXBOGOMOL, Component.literal("MaxBogomol")));
        skins.add(new SkinEntry(FluffyFurPlayerSkins.ONIXTHECAT, Component.literal("OnixTheCat")));
        skins.add(new SkinEntry(FluffyFurPlayerSkins.NANACHI, Component.literal("Nanachi")));

        skinEffects.add(new SkinEffectEntry(null, Component.literal("Empty")));
        skinEffects.add(new SkinEffectEntry(FluffyFurPlayerSkins.PINK_HEARTS_EFFECT, Component.literal("Pink Hearts")));
    }

    @Override
    public void render(GuiGraphics gui, int mouseX, int mouseY, float partialTicks) {
        Font font = Minecraft.getInstance().font;
        int x = width / 2;
        int y = height / 2;

        int rowSize = 5;

        int offset = -4;
        offset = offset - ((skins.size() % rowSize) * 12);
        offset = offset - ((skinEffects.size() % rowSize) * 12);
        offset = offset / 2;
        offset = offset - 12;

        Component component = Component.translatable("gui.fluffy_fur.skin_menu.skins");
        FluffyFurMenuScreen.drawBlackBackground(gui, x, y + offset, font.width(component) + 8, mouseX, mouseY, partialTicks);
        gui.drawCenteredString(font, component, x, y + offset + 1, 16777215);

        offset = offset + 12;

        int i = 0;
        List<Integer> skinRows = new ArrayList<>();
        skinRows.add(0);
        for (SkinEntry skin : skins) {
            skinRows.set(skinRows.size() - 1, skinRows.get(skinRows.size() - 1) + font.width(skin.getName()) + 10);
            i++;
            if (i >= rowSize) {
                skinRows.add(0);
                i = 0;
            }
        }

        i = 0;
        int ii = 0;
        for (SkinEntry skin : skins) {
            int rowOffset = skinRows.get(ii);
            int size = font.width(skin.getName()) + 8;

            boolean selected = mouseX >= x - (rowOffset / 2) && mouseY >= y + offset && mouseX <= x - (rowOffset / 2) + size && mouseY < y + offset + 10;

            FluffyFurMenuScreen.drawBlackBackground(gui, x - (rowOffset / 2) + (size / 2), y + offset - (selected ? 1 : 0), size, selected ? 0.75f: 0.5f, mouseX, mouseY, partialTicks);
            gui.drawCenteredString(font, skin.getName(), x - (rowOffset / 2) + (size / 2), y + offset + 1 - (selected ? 1 : 0), 16777215);

            skinRows.set(ii, rowOffset - (size * 2 + 8));

            i++;
            if (i >= rowSize) {
                offset = offset + 12;
                i = 0;
                ii++;
            }
        }

        offset = offset + 18;

        component = Component.translatable("gui.fluffy_fur.skin_menu.skin_effects");
        FluffyFurMenuScreen.drawBlackBackground(gui, x, y + offset, font.width(component) + 8, mouseX, mouseY, partialTicks);
        gui.drawCenteredString(font, component, x, y + offset + 1, 16777215);

        offset = offset + 12;

        i = 0;
        List<Integer> effectRows = new ArrayList<>();
        effectRows.add(0);
        for (SkinEffectEntry effect : skinEffects) {
            effectRows.set(effectRows.size() - 1, effectRows.get(effectRows.size() - 1) + font.width(effect.getName()) + 10);
            i++;
            if (i >= rowSize) {
                effectRows.add(0);
                i = 0;
            }
        }

        i = 0;
        ii = 0;
        for (SkinEffectEntry effect : skinEffects) {
            int rowOffset = effectRows.get(ii);
            int size = font.width(effect.getName()) + 8;

            boolean selected = mouseX >= x - (rowOffset / 2) && mouseY >= y + offset && mouseX <= x - (rowOffset / 2) + size && mouseY < y + offset + 10;

            FluffyFurMenuScreen.drawBlackBackground(gui, x - (rowOffset / 2) + (size / 2), y + offset - (selected ? 1 : 0), size, selected ? 0.75f: 0.5f, mouseX, mouseY, partialTicks);
            gui.drawCenteredString(font, effect.getName(), x - (rowOffset / 2) + (size / 2), y + offset + 1 - (selected ? 1 : 0), 16777215);

            effectRows.set(ii, rowOffset - (size * 2 + 8));

            i++;
            if (i >= rowSize) {
                offset = offset + 12;
                i = 0;
                ii++;
            }
        }

        super.render(gui, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int x = width / 2;
        int y = height / 2;

        int rowSize = 5;

        int offset = -4;
        offset = offset - ((skins.size() % rowSize) * 12);
        offset = offset - ((skinEffects.size() % rowSize) * 12);
        offset = offset / 2;

        int i = 0;
        List<Integer> skinRows = new ArrayList<>();
        skinRows.add(0);
        for (SkinEntry skin : skins) {
            skinRows.set(skinRows.size() - 1, skinRows.get(skinRows.size() - 1) + font.width(skin.getName()) + 10);
            i++;
            if (i >= rowSize) {
                skinRows.add(0);
                i = 0;
            }
        }

        i = 0;
        int ii = 0;
        for (SkinEntry skin : skins) {
            int rowOffset = skinRows.get(ii);
            int size = font.width(skin.getName()) + 8;

            boolean selected = mouseX >= x - (rowOffset / 2f) && mouseY >= y + offset && mouseX <= x - (rowOffset / 2f) + size && mouseY < y + offset + 10;
            if (selected) {
                if (skin.getSkin() != null) {
                    PlayerSkinHandler.setSkinPacket(skin.getSkin());
                } else {
                    PlayerSkinHandler.setSkinPacket("");
                }
                PacketHandler.sendToServer(new PlayerSkinChangePacket(minecraft.player.position().add(0, minecraft.player.getBbHeight(), 0)));
                this.onClose();
                return true;
            }

            skinRows.set(ii, rowOffset - (size * 2 + 8));

            i++;
            if (i >= rowSize) {
                offset = offset + 12;
                i = 0;
                ii++;
            }
        }

        offset = offset + 30;

        i = 0;
        List<Integer> effectRows = new ArrayList<>();
        effectRows.add(0);
        for (SkinEffectEntry effect : skinEffects) {
            effectRows.set(effectRows.size() - 1, effectRows.get(effectRows.size() - 1) + font.width(effect.getName()) + 10);
            i++;
            if (i >= rowSize) {
                effectRows.add(0);
                i = 0;
            }
        }

        i = 0;
        ii = 0;
        for (SkinEffectEntry effect : skinEffects) {
            int rowOffset = effectRows.get(ii);
            int size = font.width(effect.getName()) + 8;

            boolean selected = mouseX >= x - (rowOffset / 2f) && mouseY >= y + offset && mouseX <= x - (rowOffset / 2f) + size && mouseY < y + offset + 10;
            if (selected) {
                if (effect.getEffect() != null) {
                    PlayerSkinHandler.setSkinEffectPacket(effect.getEffect());
                } else {
                    PlayerSkinHandler.setSkinEffectPacket("");
                }
                this.onClose();
                return true;
            }

            effectRows.set(ii, rowOffset - (size * 2 + 8));

            i++;
            if (i >= rowSize) {
                offset = offset + 12;
                i = 0;
                ii++;
            }
        }

        return  super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        InputConstants.Key mouseKey = InputConstants.getKey(keyCode, scanCode);

        if (this.minecraft.options.keyInventory.isActiveAndMatches(mouseKey)) {
            this.onClose();
            return true;
        }

        return (super.keyPressed(keyCode, scanCode, modifiers));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public static class SkinEntry {
        public PlayerSkin skin;
        public Component name;

        public SkinEntry(PlayerSkin skin, Component name) {
            this.skin = skin;
            this.name = name;
        }

        public PlayerSkin getSkin() {
            return skin;
        }

        public Component getName() {
            return name;
        }
    }

    public static class SkinEffectEntry {
        public PlayerSkinEffect effect;
        public Component name;

        public SkinEffectEntry(PlayerSkinEffect skin, Component name) {
            this.effect = skin;
            this.name = name;
        }

        public PlayerSkinEffect getEffect() {
            return effect;
        }

        public Component getName() {
            return name;
        }
    }
}