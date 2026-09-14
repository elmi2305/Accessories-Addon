package net.fabricmc.accessories.mixin;

import btw.community.accessories.ACUtils;
import net.fabricmc.accessories.items.AccessoryItem;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(GuiIngame.class)
public class GuiIngameMixin extends Gui {
    @Shadow @Final private Minecraft mc;

    @Inject(method = "renderGameOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/TextureManager;bindTexture(Lnet/minecraft/src/ResourceLocation;)V", ordinal = 0, shift = At.Shift.AFTER))
    private void renderAccessoryCooldownBars(float partialTicks, boolean hasScreen, int mouseX, int mouseY, CallbackInfo ci) {
        Minecraft mc = this.mc;
        EntityPlayer player = mc.thePlayer;
        if (player == null) return;

        ScaledResolution res = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        int centerX = res.getScaledWidth() / 2;
        int centerY = res.getScaledHeight() / 2;

        List<ItemStack> cooldownAccessories = ACUtils.getAllAccessoriesWithCooldowns(player);
        if (cooldownAccessories.isEmpty()) return;

        int barWidth = 20;
        int barHeight = 2;
        int spacing = 2; // space between bars
        int startY = centerY + 7; // start just below crosshair

        int index = 0;
        for (ItemStack stack : cooldownAccessories) {
            if (!(stack.getItem() instanceof AccessoryItem acc)) continue;

            int maxCd = acc.getFinalCooldown(); // final cooldown
            int curCd = acc.getCooldown();      // remaining cooldown
            if (maxCd <= 0) continue;

            float progress = 1.0f - ((float) curCd / (float) maxCd); // 0 → empty, 1 → ready

            int x = centerX - (barWidth / 2);
            int y = startY + (index * (barHeight + spacing));

            int alpha = (int)(0x22 + (progress * 0x55)); // 0x22 → 0x77 max
            int color = (alpha << 24) | (acc.getColor() & 0xFFFFFF);

            // Background
            drawColoredRect(x, y, x + barWidth, y + barHeight, alpha, this.zLevel);

            // Fill (accessory color)
            drawColoredRect(x, y, x + (int)(barWidth * progress), y + barHeight, color, this.zLevel);

            index++;
        }
    }

    // Simple solid rectangle drawing helper
    @Unique
    private void drawColoredRect(int x1, int y1, int x2, int y2, int color, float zLevel) {
        float a = (color >> 24 & 255) / 255.0F;
        float r = (color >> 16 & 255) / 255.0F;
        float g = (color >> 8 & 255) / 255.0F;
        float b = (color & 255) / 255.0F;

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        Tessellator tess = Tessellator.instance;
        tess.startDrawingQuads();
        tess.setColorRGBA_F(r, g, b, a);
        tess.addVertex(x1, y2, zLevel);
        tess.addVertex(x2, y2, zLevel);
        tess.addVertex(x2, y1, zLevel);
        tess.addVertex(x1, y1, zLevel);
        tess.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }


}
