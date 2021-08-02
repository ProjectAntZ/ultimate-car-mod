package de.maxhenkel.car.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import de.maxhenkel.car.Main;
import de.maxhenkel.car.blocks.tileentity.TileEntitySplitTank;
import de.maxhenkel.corelib.inventory.ScreenBase;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;

import java.util.ArrayList;
import java.util.List;

public class GuiSplitTank extends ScreenBase<ContainerSplitTank> {

    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(Main.MODID, "textures/gui/gui_split_tank.png");

    private Inventory playerInv;
    private TileEntitySplitTank tile;

    public GuiSplitTank(ContainerSplitTank containerSplitTank, Inventory playerInv, Component title) {
        super(GUI_TEXTURE, containerSplitTank, playerInv, title);
        this.playerInv = playerInv;
        this.tile = containerSplitTank.getSplitTank();

        imageWidth = 176;
        imageHeight = 166;
    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {
        super.renderLabels(matrixStack, mouseX, mouseY);

        // Title
        font.draw(matrixStack, playerInv.getDisplayName().getVisualOrderText(), 8, this.imageHeight - 96 + 2, FONT_COLOR);

        if (mouseX >= leftPos + 50 && mouseX <= leftPos + 16 + 50) {
            if (mouseY >= topPos + 8 && mouseY <= topPos + 57 + 8) {
                List<FormattedCharSequence> list = new ArrayList<>();
                list.add(new TranslatableComponent("tooltip.mix", tile.getCurrentMix()).getVisualOrderText());
                renderTooltip(matrixStack, list, mouseX - leftPos, mouseY - topPos);
            }
        }

        if (mouseX >= leftPos + 120 && mouseX <= leftPos + 16 + 120) {
            if (mouseY >= topPos + 8 && mouseY <= topPos + 57 + 8) {
                List<FormattedCharSequence> list = new ArrayList<>();
                list.add(new TranslatableComponent("tooltip.glycerin", tile.getCurrentGlycerin()).getVisualOrderText());
                renderTooltip(matrixStack, list, mouseX - leftPos, mouseY - topPos);
            }
        }

        if (mouseX >= leftPos + 141 && mouseX <= leftPos + 16 + 141) {
            if (mouseY >= topPos + 8 && mouseY <= topPos + 57 + 8) {
                List<FormattedCharSequence> list = new ArrayList<>();
                list.add(new TranslatableComponent("tooltip.bio_diesel", tile.getCurrentBioDiesel()).getVisualOrderText());
                renderTooltip(matrixStack, list, mouseX - leftPos, mouseY - topPos);
            }
        }

        if (mouseX >= leftPos + 79 && mouseX <= leftPos + 24 + 79) {
            if (mouseY >= topPos + 34 && mouseY <= topPos + 17 + 34) {
                List<FormattedCharSequence> list = new ArrayList<>();
                list.add(new TranslatableComponent("tooltip.progress", ((int) (getProgress() * 100F))).getVisualOrderText());
                renderTooltip(matrixStack, list, mouseX - leftPos, mouseY - topPos);
            }
        }
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        super.renderBg(matrixStack, partialTicks, mouseX, mouseY);
        drawProgress(matrixStack);
        drawMix(matrixStack);
        drawBioDiesel(matrixStack);
        drawGlycerin(matrixStack);
    }

    public void drawGlycerin(PoseStack matrixStack) {
        float perc = getGlycerin();

        int texX = 192;
        int texY = 17;
        int texW = 16;
        int texH = 57;
        int targetX = 120;
        int targetY = 8;

        int scHeight = (int) (texH * (1 - perc));
        int i = this.leftPos;
        int j = this.topPos;
        blit(matrixStack, i + targetX, j + targetY + scHeight, texX, texY + scHeight, texW, texH - scHeight);
    }

    public void drawBioDiesel(PoseStack matrixStack) {
        float perc = getBioDiesel();

        int texX = 208;
        int texY = 17;
        int texW = 16;
        int texH = 57;
        int targetX = 141;
        int targetY = 8;

        int scHeight = (int) (texH * (1 - perc));
        int i = this.leftPos;
        int j = this.topPos;
        blit(matrixStack, i + targetX, j + targetY + scHeight, texX, texY + scHeight, texW, texH - scHeight);
    }

    public void drawMix(PoseStack matrixStack) {
        float perc = getMix();

        int texX = 176;
        int texY = 17;
        int texW = 16;
        int texH = 57;
        int targetX = 50;
        int targetY = 8;

        int scHeight = (int) (texH * (1 - perc));
        int i = this.leftPos;
        int j = this.topPos;
        blit(matrixStack, i + targetX, j + targetY + scHeight, texX, texY + scHeight, texW, texH - scHeight);
    }

    public void drawProgress(PoseStack matrixStack) {
        float perc = getProgress();

        int texX = 176;
        int texY = 0;
        int texW = 24;
        int texH = 17;
        int targetX = 79;
        int targetY = 34;

        int scWidth = (int) (texW * perc);
        int i = this.leftPos;
        int j = this.topPos;
        blit(matrixStack, i + targetX, j + targetY, texX, texY, scWidth, texH);
    }

    public float getMix() {
        return ((float) tile.getCurrentMix()) / ((float) tile.maxMix);
    }

    public float getBioDiesel() {
        return ((float) tile.getCurrentBioDiesel()) / ((float) tile.maxBioDiesel);
    }

    public float getGlycerin() {
        return ((float) tile.getCurrentGlycerin()) / ((float) tile.maxGlycerin);
    }

    public float getProgress() {
        if (tile.getTimeToGenerate() == 0) {
            return 0;
        }

        int time = tile.generatingTime - tile.getTimeToGenerate();
        return ((float) time) / ((float) tile.generatingTime);
    }

}
