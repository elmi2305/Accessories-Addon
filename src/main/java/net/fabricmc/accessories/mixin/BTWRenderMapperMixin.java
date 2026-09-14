package net.fabricmc.accessories.mixin;

import btw.client.render.BTWRenderMapper;
import net.fabricmc.accessories.EntityFriendlySilverfish;
import net.minecraft.src.RenderManager;
import net.minecraft.src.RenderSilverfish;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BTWRenderMapper.class)
public class BTWRenderMapperMixin {
    @Inject(method = "initEntityRenderers", at = @At("TAIL"),remap = false)
    private static void addCustomAccessoryEntityRenders(CallbackInfo ci){
        RenderManager.addEntityRenderer(EntityFriendlySilverfish.class, new RenderSilverfish());
    }
}
