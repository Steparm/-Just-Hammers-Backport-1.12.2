package com.stepm.justhammersbackport.compat;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.oredict.OreDictionary;

import com.stepm.justhammersbackport.registry.ModItems;

public class FutureMcCompat {
    
    public static void init(FMLInitializationEvent event) {
        addSmithingRecipe(
            new ItemStack(ModItems.DIAMOND_HAMMER),
            "ingotNetherite",
            new ItemStack(ModItems.NETHERITE_HAMMER)
        );
        
        addSmithingRecipe(
            new ItemStack(ModItems.DIAMOND_IMPACT_HAMMER),
            "ingotNetherite",
            new ItemStack(ModItems.NETHERITE_IMPACT_HAMMER)
        );
        
        addSmithingRecipe(
            new ItemStack(ModItems.DIAMOND_REINFORCED_HAMMER),
            "ingotNetherite",
            new ItemStack(ModItems.NETHERITE_REINFORCED_HAMMER)
        );
        
        addSmithingRecipe(
            new ItemStack(ModItems.DIAMOND_REINFORCED_IMPACT_HAMMER),
            "ingotNetherite",
            new ItemStack(ModItems.NETHERITE_REINFORCED_IMPACT_HAMMER)
        );
        
        addSmithingRecipe(
            new ItemStack(ModItems.DIAMOND_DESTRUCTOR_HAMMER),
            "ingotNetherite",
            new ItemStack(ModItems.NETHERITE_DESTRUCTOR_HAMMER)
        );
    }
    
    private static void addSmithingRecipe(ItemStack input, String materialOreDict, ItemStack output) {
        NBTTagCompound nbt = new NBTTagCompound();
        
        NBTTagCompound inputTag = new NBTTagCompound();
        input.writeToNBT(inputTag);
        nbt.setTag("input", inputTag);
        
        if (!OreDictionary.getOres(materialOreDict).isEmpty()) {
            NBTTagCompound materialTag = new NBTTagCompound();
            OreDictionary.getOres(materialOreDict).get(0).writeToNBT(materialTag);
            nbt.setTag("material", materialTag);
        }

        NBTTagCompound outputTag = new NBTTagCompound();
        output.writeToNBT(outputTag);
        nbt.setTag("output", outputTag);

        FMLInterModComms.sendMessage("futuremc", "add_smithing_recipe", nbt);
    }
}