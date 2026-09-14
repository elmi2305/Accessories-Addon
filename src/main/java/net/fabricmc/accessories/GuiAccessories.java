package net.fabricmc.accessories;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.GuiInventory;
import net.minecraft.src.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class GuiAccessories extends GuiInventory {

    private final EntityPlayer player;
    private boolean accessoryButtonHover = false;
    private ResourceLocation guiTexture;

    public GuiAccessories(EntityPlayer player, ContainerAccessories container) {
        super(player);
        this.player = player;
        this.allowUserInput = false;
        this.inventorySlots = container;

        this.ySize = 166; // same height as inventory
    }

    @Override
    public void initGui() {
        super.initGui();

        // Add close button or accessory button if you want
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRenderer.drawString("Accessories", 8, 6, 0x404040);


        accessoryButtonHover = false;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partial, int mx, int my) {
        int slotCount = ((ContainerAccessories) this.inventorySlots).getAccessoryCount();
        guiTexture = new ResourceLocation("textures/gui/accessoriesInventoryGui_" + slotCount + ".png");
        mc.getTextureManager().bindTexture(guiTexture);
        int x = this.guiLeft;
        int y = this.guiTop;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);

        // Your accessory-slot mixin automatically draws slot backgrounds
    }
}
