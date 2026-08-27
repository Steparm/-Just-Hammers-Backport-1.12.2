package com.stepm.justhammersbackport.item;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class NetheriteEntityItem extends EntityItem {
    
    public NetheriteEntityItem(World world, double x, double y, double z, ItemStack stack) {
        super(world, x, y, z, stack);
        this.isImmuneToFire = true;
    }
    
    public NetheriteEntityItem(World world) {
        super(world);
        this.isImmuneToFire = true;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        
        if (!this.world.isRemote) {
            if (this.isInLava()) {
                this.isDead = false;
                this.setFire(0);
                this.extinguish();
                this.hurtResistantTime = 60;
                
                if (this.motionY < 0) {
                    this.motionY = 0.2D;
                }
                
                this.motionX *= 0.8;
                this.motionZ *= 0.8;
            }
            
            if (this.isBurning()) {
                this.extinguish();
                this.setFire(0);
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source.isFireDamage() || 
            source == DamageSource.LAVA || 
            source == DamageSource.IN_FIRE || 
            source == DamageSource.ON_FIRE ||
            source == DamageSource.HOT_FLOOR) {
            return false;
        }
        return super.attackEntityFrom(source, amount);
    }

    @Override
    public void setFire(int seconds) {}

    @Override
    protected void dealFireDamage(int amount) {}
}