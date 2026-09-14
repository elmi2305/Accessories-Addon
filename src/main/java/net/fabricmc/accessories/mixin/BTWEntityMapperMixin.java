package net.fabricmc.accessories.mixin;

import btw.community.accessories.AccessoriesEntityMapper;
import btw.entity.util.BTWEntityMapper;
import net.fabricmc.accessories.Accessories;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BTWEntityMapper.class)
public class BTWEntityMapperMixin {
    @Inject(method = "createModEntityMappings", at = @At("TAIL"),remap = false)
    private static void addCustomAccessoryMobs(CallbackInfo ci){
        AccessoriesEntityMapper.createModEntityMappings();
    }
}
