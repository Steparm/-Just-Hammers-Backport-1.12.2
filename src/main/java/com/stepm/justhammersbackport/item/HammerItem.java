package com.stepm.justhammersbackport.item;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class HammerItem extends ItemPickaxe {
    
    private final int range;
    private final int depth;
    private final ToolMaterial toolMaterial;
    
    public HammerItem(ToolMaterial material, int range, int depth) {
        super(material);
        this.toolMaterial = material;
        this.range = range;
        this.depth = depth;
        this.attackSpeed = -2.8F;
    }
    
    public int getRange() {
        return range;
    }
    
    public int getDepth() {
        return depth;
    }
    
    public float getMiningSpeed() {
        return this.efficiency;
    }
    
    public float getAttackDamage() {
        return this.attackDamage;
    }
    
    public ToolMaterial getToolMaterial() {
        return this.toolMaterial;
    }
    
    @Override
    public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
        Block block = state.getBlock();
        if (block == Blocks.OBSIDIAN) {
            return this.toolMaterial.getHarvestLevel() >= 3;
        }
        return super.canHarvestBlock(state, stack);
    }
}