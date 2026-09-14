package net.fabricmc.accessories.mixin;

import btw.community.accessories.AccessoriesAddon;
import net.fabricmc.accessories.*;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiInventory.class)
public abstract class GuiInventoryMixin extends GuiContainer {
    private static final ResourceLocation CONTAINER_TEXTURE = new ResourceLocation("textures/gui/exampleBorder.png");
    private static final ResourceLocation BUTTON_TEXTURE = new ResourceLocation("textures/gui/accessoryButton.png");


    public GuiInventoryMixin(Container inventorySlotsIn) {
        super(inventorySlotsIn);
    }
    @Inject(method = "drawGuiContainerBackgroundLayer", at = @At("RETURN"))
    private void drawAccessorySlotBackgrounds(float partialTicks, int mouseX, int mouseY, CallbackInfo ci) {
        // bind the same texture vanilla uses for slots
        this.mc.getTextureManager().bindTexture(CONTAINER_TEXTURE);
        GL11.glColor4f(1F, 0.8F, 0.8F, 0.8F);

        for (Object slot : this.inventorySlots.inventorySlots) {
            if (slot instanceof SlotAccessory) {
                int x = this.guiLeft + ((SlotAccessory) slot).xDisplayPosition-5;
                int y = this.guiTop  + ((SlotAccessory) slot).yDisplayPosition-5;
                this.drawCustomSizedTexture(x, y, 0, 0, 27,27, 27, 27);
            }
        }
    }

    @Inject(method = "actionPerformed", at = @At("TAIL"))
    private void allowClickingButtonToOpenGuiScreen(GuiButton par1GuiButton, CallbackInfo ci){
        if (par1GuiButton.id == 23) {
            // The server creates the container and sends the open-window packet
            // back to us. Creating it here would make every inventory click roll
            // back because the server still has the player inventory open.
            AccessoriesAddon.requestOpenAccessories(this.mc.thePlayer);
        }
    }
    @Unique
    public void drawCustomSizedTexture(int x, int y, int u, int v, int width, int height, int textureWidth, int textureHeight) {
        float f = 1.0F / textureWidth;
        float f1 = 1.0F / textureHeight;
        Tessellator tessellator = Tessellator.instance;

        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x, y + height, zLevel, (u) * f, (v + height) * f1);
        tessellator.addVertexWithUV(x + width, y + height, zLevel, (u + width) * f, (v + height) * f1);
        tessellator.addVertexWithUV(x + width, y, zLevel, (u + width) * f, (v) * f1);
        tessellator.addVertexWithUV(x, y, zLevel, (u) * f, (v) * f1);
        tessellator.draw();
    }


    @Inject(method = "initGui", at = @At("TAIL"))
    private void createAccessoryGuiButton(CallbackInfo ci){
        if ((Object) this instanceof GuiAccessories) {
            return;
        }
        int x = this.guiLeft + this.xSize - 25;
        int y = this.guiTop + 5;

//        GuiSmallButton var6 = new GuiSmallButton(23, this.width / 2, this.height / 6 - 12 + 24 * (var1 >> 1), var5, this.options.getKeyBinding(var5));
        GuiButton var6 = new GuiAccessoryButton(23, x, y, 20, 20, BUTTON_TEXTURE, "Accessories");
        this.buttonList.add(var6);
    }

}
