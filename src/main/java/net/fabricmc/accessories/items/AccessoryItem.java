package net.fabricmc.accessories.items;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import org.spongepowered.asm.mixin.MixinEnvironment;

import java.util.ArrayList;
import java.util.List;

public class AccessoryItem extends Item {
    protected List<String> tooltipLines = new ArrayList<String>();
    private int cooldown = -1;
    private int finalCooldown = -1;
    private int color = -1;


    public AccessoryItem(int id) {
        super(id);
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    public AccessoryItem addTooltip(String line) {
        this.tooltipLines.add(line);
        return this;
    }
    public AccessoryItem setFinalCooldown(int ticks){
        this.finalCooldown = ticks * 2;
        this.cooldown = ticks * 2;
        return this;
    }
    public int getFinalCooldown(){
        return this.finalCooldown;
    }
    public void setCooldown(int ticks){
        this.cooldown = ticks;
    }
    public int getCooldown(){
        return this.cooldown;
    }
    public AccessoryItem setCooldownColor(int hexCode){
        this.color = hexCode;
        return this;
    }
    public int getColor(){
        return this.color;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
        list.addAll(tooltipLines);
        super.addInformation(stack,player,list,advanced);
    }
    public String getModId() {
        return "Accessories Addon";
    }

}
