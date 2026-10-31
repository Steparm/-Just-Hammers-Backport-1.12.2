package com.stepm.justhammersbackport.handler;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.stepm.justhammersbackport.item.NetheriteEntityItem;
import com.stepm.justhammersbackport.registry.ModItems;

public class LavaProofHandler {
    
    private static boolean isNetheriteItem(ItemStack stack) {
        if (stack.isEmpty()) return false;
        
        return stack.getItem() == ModItems.NETHERITE_HAMMER ||
               stack.getItem() == ModItems.NETHERITE_IMPACT_HAMMER ||
               stack.getItem() == ModItems.NETHERITE_REINFORCED_HAMMER ||
               stack.getItem() == ModItems.NETHERITE_REINFORCED_IMPACT_HAMMER ||
               stack.getItem() == ModItems.NETHERITE_DESTRUCTOR_HAMMER;
    }
    
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof EntityItem && !(event.getEntity() instanceof NetheriteEntityItem)) {
            EntityItem oldEntity = (EntityItem) event.getEntity();
            ItemStack stack = oldEntity.getItem();
            
            if (isNetheriteItem(stack)) {
                NetheriteEntityItem newEntity = new NetheriteEntityItem(
                    oldEntity.world, 
                    oldEntity.posX, 
                    oldEntity.posY, 
                    oldEntity.posZ, 
                    stack.copy()
                );
                newEntity.motionX = oldEntity.motionX;
                newEntity.motionY = oldEntity.motionY;
                newEntity.motionZ = oldEntity.motionZ;
                newEntity.setPickupDelay(40);
                newEntity.setThrower(oldEntity.getThrower());
                
                oldEntity.setDead();
                event.getWorld().spawnEntity(newEntity);
                event.setCanceled(true);
            }
        }
    }
}