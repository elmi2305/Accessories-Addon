package net.fabricmc.accessories.mixin;

import emi.dev.emi.emi.api.plugin.BTWPlugin;
import emi.dev.emi.emi.api.EmiRegistry;
import emi.dev.emi.emi.api.recipe.EmiInfoRecipe;
import emi.dev.emi.emi.api.stack.EmiStack;
import emi.shims.java.net.minecraft.text.Text;
import net.fabricmc.accessories.items.ACItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(BTWPlugin.class)
public class BTWPluginMixin {
    @Inject(method = "addInfoRecipes", at = @At("TAIL"), remap = false)
    private void addAccessoryDropInfoRecipes(EmiRegistry registry, CallbackInfo ci) {
        addInfoRecipe(registry, ACItems.netherFruit, "accessories.nether_fruit.info");
        addInfoRecipe(registry, ACItems.witherFruit, "accessories.wither_fruit.info");
        addInfoRecipe(registry, ACItems.dragonFruit, "accessories.dragon_fruit.info");
        addInfoRecipe(registry, ACItems.glassShard, "accessories.glass_shard.info");
        addInfoRecipe(registry, ACItems.witherDust, "accessories.wither_dust.info");
        addInfoRecipe(registry, ACItems.itemSoulSword, "accessories.soul_cleaver.info");
    }

    private static void addInfoRecipe(EmiRegistry registry, net.minecraft.src.Item item, String translationKey) {
        registry.addRecipe(new EmiInfoRecipe(
                List.of(EmiStack.of(item)),
                List.of(Text.translatable(translationKey)),
                null
        ));
    }
}
