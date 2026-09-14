package net.fabricmc.accessories.mixin;

import net.fabricmc.accessories.AccessoryKeybindings;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.GameSettings;
import net.minecraft.src.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Environment(EnvType.CLIENT)
@Mixin(GameSettings.class)
public abstract class GameSettingsMixin {
//    @Environment(EnvType.CLIENT)
//    @Shadow
//    public KeyBinding[] keyBindings;
//
//    @Environment(EnvType.CLIENT)
//    @Inject(method = "<init>*", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/GameSettings;loadOptions()V"))
//    private void nightmaremode$injectCustomKeys(CallbackInfo ci) {
//        if (!AccessoryKeybindings.markRegistered()) return;
//
//        AccessoryKeybindings.accessorySpecialKey = new KeyBinding("key.accessories.special", Keyboard.KEY_Y);
//
//        KeyBinding[] custom = AccessoryKeybindings.all();
//        KeyBinding[] neu = Arrays.copyOf(keyBindings, keyBindings.length + custom.length);
//        System.arraycopy(custom, 0, neu, keyBindings.length, custom.length);
//        keyBindings = neu;
//    }
}
