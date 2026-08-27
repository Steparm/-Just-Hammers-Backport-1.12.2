package com.stepm.justhammersbackport.registry;

import net.minecraftforge.fml.common.Loader;

public class ConditionalItems {
    
    private static final String[] COPPER_MODS = {
        "futuremc", "simpleores", "copper", "copperore", "orespawn",
        "aoa3", "nevermine", "divinerpg", "mekanism", "thermalfoundation",
        "tconstruct", "ic2", "immersiveengineering", "forestry", 
        "railcraft", "projectred", "bluepower", "techguns"
    };
    
    private static final String[] NETHERITE_MODS = {
        "futuremc", "netherite", "simpleores", "netheriteplus", "netheritepick",
        "orespawn", "aoa3", "nevermine", "divinerpg", "mekanism",
        "thermalfoundation", "tconstruct", "ic2", "immersiveengineering"
    };
    
    public static boolean isAnyCopperModLoaded() {
        for (String modId : COPPER_MODS) {
            if (Loader.isModLoaded(modId)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isAnyNetheriteModLoaded() {
        for (String modId : NETHERITE_MODS) {
            if (Loader.isModLoaded(modId)) {
                return true;
            }
        }
        return false;
    }
}