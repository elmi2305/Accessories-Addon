package net.fabricmc.accessories.mixin;

import btw.inventory.container.InventoryContainer;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.GuiInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiContainer.class)
public class GuiContainerMixin {
//    @Redirect(method = "mouseClicked", at = @At(value = "FIELD", target = "Lnet/minecraft/src/GuiContainer;xSize:I"))
//    private int increaseSize(GuiContainer container){
//        if(container instanceof GuiInventory) {return container.getxSize() + 40;}
//        return container.getxSize();
//    }

}
