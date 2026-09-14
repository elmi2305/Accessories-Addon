package net.fabricmc.accessories;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.KeyBinding;

/**
 * Holds custom Nightmare Mode key bindings.
 * Appended once to GameSettings via GameSettingsMixin.
 */
@Environment(EnvType.CLIENT)
public final class AccessoryKeybindings {

    public static KeyBinding accessorySpecialKey;

    private static boolean registered;

    private AccessoryKeybindings() {}

    /**
     * Returns true only the first time this is called (used to guard array expansion).
     */
    public static boolean markRegistered() {
        if (!registered) {
            registered = true;
            return true;
        }
        return false;
    }

    /**
     * All custom bindings to append.
     */
    public static KeyBinding[] all() {
        return new KeyBinding[]{accessorySpecialKey};
    }
}
