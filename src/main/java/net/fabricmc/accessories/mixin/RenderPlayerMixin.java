package net.fabricmc.accessories.mixin;

import net.fabricmc.accessories.client.AccessoryRenderer;
import net.minecraft.src.AbstractClientPlayer;
import net.minecraft.src.ModelBiped;
import net.minecraft.src.RenderPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderPlayer.class)
public abstract class RenderPlayerMixin {
    @Shadow private ModelBiped modelBipedMain;

    @Inject(method = "renderSpecials", at = @At("TAIL"))
    private void accessories$render(AbstractClientPlayer player, float partialTick, CallbackInfo ci) {
        AccessoryRenderer.render(player, modelBipedMain, partialTick);
    }
}
