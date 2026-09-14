package net.fabricmc.accessories;

import btw.community.accessories.AccessoriesAddon;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class GuiAccessoryButton extends GuiButton {

    /** Icon to draw on the button */
    private final ResourceLocation icon;
    private String tooltipText;

    public GuiAccessoryButton(int id, int x, int y, int width, int height, ResourceLocation icon, String tooltipText) {
        super(id, x, y, width, height, "");
        this.tooltipText = tooltipText;
        this.icon = icon;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (!this.drawButton) return;

        mc.getTextureManager().bindTexture(GuiButton.buttonTextures);

        // Check hover
        this.field_82253_i = mouseX >= xPosition &&
                mouseY >= yPosition &&
                mouseX <  xPosition + width &&
                mouseY <  yPosition + height;

        int hoverState = this.getHoverState(this.field_82253_i);

        GL11.glColor4f(1F, 1F, 1F, 1F);

        // Draw the standard GUI button halves
        this.drawTexturedModalRect(
                this.xPosition, this.yPosition,
                0, 46 + hoverState * 20,
                this.width / 2, this.height
        );
        this.drawTexturedModalRect(
                this.xPosition + this.width / 2, this.yPosition,
                200 - this.width / 2, 46 + hoverState * 20,
                this.width / 2, this.height
        );

        // Draw icon
        if (this.icon != null) {
            mc.getTextureManager().bindTexture(this.icon);

            GL11.glColor4f(1F, 1F, 1F, 1F);

            int iconSize = 16; // your icon should be 16×16 or scaled down

            int iconX = this.xPosition + (this.width - iconSize) / 2;
            int iconY = this.yPosition + (this.height - iconSize) / 2;

            this.drawCustomSizedTexture(iconX, iconY, 0, 0, iconSize, iconSize, 16, 16);
        }

        boolean hover = mouseX >= xPosition && mouseY >= yPosition &&
                mouseX < xPosition + width && mouseY < yPosition + height;

        // Draw hover tooltip (we only set a flag; the GUI draws it)
        if (hover) {
            this.drawTooltip(mc, xPosition, yPosition, 16, 16, tooltipText);
        }

    }

    public void drawTooltip(Minecraft mc, int buttonX, int buttonY, int buttonWidth, int buttonHeight, String text) {
        FontRenderer fontRenderer = mc.fontRenderer;
        List<String> lines = wrapTextByChars(text, fontRenderer);

        int maxWidth = 0;
        for (String line : lines) {
            int lineWidth = fontRenderer.getStringWidth(line);
            if (lineWidth > maxWidth) {
                maxWidth = lineWidth;
            }
        }

        int boxWidth = maxWidth + 8;
        int boxHeight = (lines.size() * 10) + 4;

        // Center vertically on button
        int boxY = buttonY + (buttonHeight / 2) - (boxHeight / 2);

        // Decide whether to render tooltip on the left or right of the button
        boolean shouldRenderLeft = buttonX + buttonWidth / 2 > mc.currentScreen.width / 2;

        int boxX;
        if (shouldRenderLeft) {
            // Place to the left of the button
            boxX = buttonX - boxWidth - 6;
        } else {
            // Place to the right of the button
            boxX = buttonX + buttonWidth + 6;
        }

        // Save OpenGL state
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        GL11.glPushMatrix();

        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        drawRect(boxX, boxY, boxX + boxWidth, boxY + boxHeight, 0xCC000000);

        GL11.glEnable(GL11.GL_TEXTURE_2D);

        int yOffset = 2;
        for (String line : lines) {
            fontRenderer.drawStringWithShadow(line, boxX + 4, boxY + yOffset, 0xFFFFFF);
            yOffset += 10;
        }

        GL11.glPopMatrix();
        GL11.glPopAttrib();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    private List<String> wrapTextByChars(String text, FontRenderer fontRenderer) {
        List<String> lines = new ArrayList<>();
        String[] words = text.split(" ");

        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            if (fontRenderer.getStringWidth(currentLine + word) > 200) {
                // Move to the next line if the word would exceed max width
                lines.add(currentLine.toString().trim());
                currentLine = new StringBuilder();
            }
            currentLine.append(word).append(" ");
        }

        // Add the last line if not empty
        if (!currentLine.toString().trim().isEmpty()) {
            lines.add(currentLine.toString().trim());
        }

        return lines;
    }


    public void setTooltipText(String tooltipText) {
        this.tooltipText = tooltipText;
    }
    public String getTooltipText() {
        return this.tooltipText;
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

    @Override
    public void mouseReleased(int par1, int par2) {
        super.mouseReleased(par1, par2);
    }
}
