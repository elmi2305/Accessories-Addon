package net.fabricmc.accessories.mixin;

import net.fabricmc.accessories.ContainerAccessories;
import net.fabricmc.accessories.GuiAccessories;
import net.fabricmc.accessories.IPlayerAccessories;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetClientHandler.class)
public class NetClientHandlerMixin {

    @Inject(method = "handleOpenWindow", at = @At("HEAD"), cancellable = true)
    private void onHandleOpenWindow(Packet100OpenWindow packet, CallbackInfo ci) {
        if (packet.inventoryType == 42) { // our custom GUI ID
            EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
            ContainerAccessories container = new ContainerAccessories(
                    player.inventory,
                    ((IPlayerAccessories) player).getAccessoryInventory(),
                    player,
                    packet.slotsCount
            );
            // Click packets are accepted only when this exactly matches the
            // container window ID created by the server.
            container.windowId = packet.windowId;
            player.openContainer = container;
            Minecraft.getMinecraft().displayGuiScreen(new GuiAccessories(player, container));
            ci.cancel();
        }
    }
}
