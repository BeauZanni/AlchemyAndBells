package org.beauzanni.aab.gui;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class AltarPantalla extends ContainerScreen<AltarContenedor> {
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("alchemyandbells", "textures/gui/altar.png");

    public AltarPantalla(AltarContenedor contenedor, PlayerInventory inventario, ITextComponent titulo) {
        super(contenedor, inventario, titulo);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void renderBg(MatrixStack matrix, float partialTicks, int mouseX, int mouseY) {
        this.minecraft.getTextureManager().bind(GUI_TEXTURE);
        blit(matrix, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
    }

    @Override
    public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrix);
        super.render(matrix, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrix, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(MatrixStack matrix, int mouseX, int mouseY) {
        this.font.draw(matrix, this.title.getString(), 8, 6, 4210752);
        this.font.draw(matrix, this.inventory.getDisplayName().getString(), 8, this.imageHeight - 94, 4210752);
    }
}

