package com.stepm.justhammersbackport.registry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.stepm.justhammersbackport.JusthammersbackportMod;

public class ModCreativeTabs {
    
    public static final CreativeTabs TAB_HAMMER = new CreativeTabs("tab_hammer") {
        @SideOnly(Side.CLIENT)
        public ItemStack createIcon() {
            return new ItemStack(ModItems.DIAMOND_IMPACT_HAMMER);
        }
    };
}