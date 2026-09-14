package net.fabricmc.accessories.mixin;

import net.fabricmc.accessories.IPlayerAccessories;
import net.fabricmc.accessories.SlotAccessory;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiContainerCreative.class)
public abstract class GuiContainerCreativeMixin extends InventoryEffectRenderer {
    public GuiContainerCreativeMixin(Container par1Container) {
        super(par1Container);
    }
    private static final ResourceLocation CONTAINER_TEXTURE = new ResourceLocation("textures/gui/exampleBorder.png");

    @Inject(method = "drawGuiContainerBackgroundLayer", at = @At("RETURN"))
    private void drawAccessorySlotBackgrounds(float partialTicks, int mouseX, int mouseY, CallbackInfo ci) {
        // bind the same texture vanilla uses for slots
//        if(this.mc.thePlayer.capabilities.isCreativeMode) return;
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


}
