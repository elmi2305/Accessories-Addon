package net.fabricmc.accessories.client;

import btw.util.status.BTWStatusCategories;
import api.util.status.StatusEffect;
import net.fabricmc.accessories.items.ACItems;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;

/** Only clothing and wearable necklaces are visible. Inventory icons never enter this rendering path. */
public final class AccessoryRenderer {
    private AccessoryRenderer() {}

    public static void render(AbstractClientPlayer player, ModelBiped model, float partialTick) {
        if (player.isInvisible()) return;
        int[] equipped = AccessoryAppearanceCache.get(player.entityId);
        if (equipped == null) return;
        boolean ears = has(equipped, ACItems.catEars), scarf = has(equipped, ACItems.counterScarf);
        boolean skirt = has(equipped, ACItems.skirt), glove = has(equipped, ACItems.mechanicalGlove);
        boolean skates = has(equipped, ACItems.iceSkates);
        boolean monster = has(equipped, ACItems.monsterNecklace);
        boolean tides = has(equipped, ACItems.pendantTides), sea = has(equipped, ACItems.pendantSea);
        boolean sun = has(equipped, ACItems.sunStone), moon = has(equipped, ACItems.moonStone);
        boolean celestial = has(equipped, ACItems.celestialStone);
        int boots = footwear(equipped);
        if (!ears && !scarf && !skirt && !glove && !skates && !monster && !tides && !sea
                && !sun && !moon && !celestial && boots < 0) return;

        GL11.glPushAttrib(GL11.GL_ENABLE_BIT | GL11.GL_CURRENT_BIT | GL11.GL_TEXTURE_BIT
                | GL11.GL_LIGHTING_BIT | GL11.GL_COLOR_BUFFER_BIT);
        try {
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_NORMALIZE);
            GL11.glEnable(GL11.GL_COLOR_MATERIAL);
            GL11.glColorMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_AMBIENT_AND_DIFFUSE);
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glColor4f(1, 1, 1, 1);
            if (ears) {
                boolean helmet = player.inventory.armorItemInSlot(3) != null;
                draw(model.bipedHead, WearableModels.EARS, 0, helmet ? 0 : 0.95f, helmet ? 1 : 0.855f, 1, 1);
            }
            // Match BTW body-size scaling and leave clearance for the armor shell.
            int fat = Math.min(4, player.getStatusForCategory(BTWStatusCategories.FAT).map(StatusEffect::getLevel).orElse(0));
            float width = 1 + fat * 0.125f, depth = 1 + fat * 0.625f;
            boolean chestplate = player.inventory.armorItemInSlot(2) != null;
            if (scarf) draw(model.bipedBody, WearableModels.SCARF, 0, -0.4f,
                    width * (chestplate ? 1 : 0.85f), 1, depth * (chestplate ? 1 : 0.72f));
            int waist = chestplate ? 2 : player.inventory.armorItemInSlot(1) != null ? 1 : 0;
            if (skirt) draw(model.bipedBody, WearableModels.SKIRTS[model.isRiding ? 1 : 0][waist],
                    0, 0, width, 1, depth);
            float chainWidth = width * (chestplate ? 1.25f : 1);
            float chainDepth = depth * (chestplate ? 1.5f : 1);
            if (monster) draw(model.bipedBody, WearableModels.NECKLACES[0], 0, 0, chainWidth, 1, chainDepth);
            if (tides) draw(model.bipedBody, WearableModels.NECKLACES[1], 0, 0, chainWidth, 1, chainDepth);
            if (sea) draw(model.bipedBody, WearableModels.NECKLACES[2], 0, 0, chainWidth, 1, chainDepth);
            if (sun) draw(model.bipedBody, WearableModels.NECKLACES[3], 0, 0, chainWidth, 1, chainDepth);
            if (moon) draw(model.bipedBody, WearableModels.NECKLACES[4], 0, 0, chainWidth, 1, chainDepth);
            if (celestial) draw(model.bipedBody, WearableModels.NECKLACES[5], 0, 0, chainWidth, 1, chainDepth);
            if (glove) {
                boolean armored = player.inventory.armorItemInSlot(2) != null;
                ModelBox arm = (ModelBox) model.bipedRightArm.cubeList.get(0);
                float center = armored ? -1 : (arm.posX1 + arm.posX2) / 2;
                float scaleX = armored ? 1 : (arm.posX2 - arm.posX1 + 0.7f) / 6.3f;
                draw(model.bipedRightArm, WearableModels.GLOVE, center, 0, scaleX, 1, armored ? 1 : 0.76f);
            }
            for (int side = 0; side < 2; side++) {
                ModelRenderer leg = side == 0 ? model.bipedRightLeg : model.bipedLeftLeg;
                // Ice Skates supply a complete boot upper, so do not stack another pair beneath them.
                if (boots >= 0 && !skates) draw(leg, WearableModels.BOOTS[boots], 0, 0, 1, 1, 1);
                if (skates) draw(leg, WearableModels.SKATES, 0, 0, 1, 1, 1);
            }
        } finally {
            GL11.glPopAttrib();
        }
    }

    private static boolean has(int[] ids, Item item) {
        for (int id : ids) if (id == item.itemID) return true;
        return false;
    }

    private static int footwear(int[] ids) {
        if (has(ids, ACItems.lavaWaders)) return 4;
        if (has(ids, ACItems.waterWalkingBoots)) return 3;
        if (has(ids, ACItems.spectreBoots)) return 2;
        if (has(ids, ACItems.hermesBoots)) return 1;
        if (has(ids, ACItems.springBoots)) return 0;
        return -1;
    }

    private static void draw(ModelRenderer anchor, WearableMesh mesh, float x, float y, float sx, float sy, float sz) {
        GL11.glPushMatrix();
        try {
            anchor.postRender(1 / 16f);
            GL11.glScalef(1 / 16f, 1 / 16f, 1 / 16f);
            GL11.glTranslatef(x, y, 0);
            GL11.glScalef(sx, sy, sz);
            mesh.render();
        } finally {
            GL11.glPopMatrix();
        }
    }
}
