package com.stepm.justhammersbackport.handler;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.stepm.justhammersbackport.item.HammerItem;

import java.util.List;

@SideOnly(Side.CLIENT)
public class TooltipHandler {
    
    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (!(stack.getItem() instanceof HammerItem)) return;
        
        HammerItem hammer = (HammerItem) stack.getItem();
        List<String> tooltip = event.getToolTip();
        
        int range = hammer.getRange();
        int depth = hammer.getDepth();
        int size = range * 2 + 1;
        String area = size + "x" + size + "x" + depth;
        
        String areaLine = TextFormatting.GRAY + I18n.format("hammer.mines", area);
        if (tooltip.contains(areaLine)) {
            return;
        }
        
        tooltip.add("");
        tooltip.add(areaLine);
        
        int maxDamage = stack.getMaxDamage();
        int currentDamage = stack.getItemDamage();
        float remaining = maxDamage - currentDamage;
        float percentage = (remaining * 100.0f) / maxDamage;
        
        String durabilityText;
        if (maxDamage >= 1000) {
            float remainingK = remaining / 1000;
            float maxK = (float) maxDamage / 1000;
            durabilityText = String.format("%.2f/%.2fk %s (%.0f%%)", 
                remainingK, maxK, getStars(percentage), percentage);
        } else {
            durabilityText = String.format("%.0f/%.0f %s (%.0f%%)", 
                remaining, (float) maxDamage, getStars(percentage), percentage);
        }
        tooltip.add(TextFormatting.WHITE + durabilityText);
        
        tooltip.add("");
        tooltip.add(TextFormatting.GRAY + I18n.format("hammer.hand"));
        tooltip.add(TextFormatting.GRAY + I18n.format("hammer.damage", 
            TextFormatting.RED + String.format("%.1f", hammer.getAttackDamage())));
        tooltip.add(TextFormatting.GRAY + I18n.format("hammer.speed", 
            TextFormatting.GREEN + "1.2"));
        
        if (remaining <= 1) {
            tooltip.add("");
            tooltip.add(TextFormatting.DARK_RED + "" + TextFormatting.BOLD + I18n.format("hammer.broken"));
            tooltip.add(TextFormatting.RED + I18n.format("hammer.cannot_mine"));
        }
    }
    
    private String getStars(float percentage) {
        int fullStars = 0;
        
        if (percentage >= 80) fullStars = 5;
        else if (percentage >= 60) fullStars = 4;
        else if (percentage >= 40) fullStars = 3;
        else if (percentage >= 20) fullStars = 2;
        else if (percentage >= 1) fullStars = 1;
        
        int grayStars = 5 - fullStars;
        StringBuilder stars = new StringBuilder();
        
        if (fullStars >= 4) {
            stars.append(TextFormatting.GREEN);
        } else if (fullStars >= 2) {
            stars.append(TextFormatting.YELLOW);
        } else if (fullStars >= 1) {
            stars.append(TextFormatting.RED);
        } else {
            stars.append(TextFormatting.GRAY);
        }
        
        for (int i = 0; i < fullStars; i++) stars.append("*");
        stars.append(TextFormatting.GRAY);
        for (int i = 0; i < grayStars; i++) stars.append("*");
        
        return stars.toString();
    }
}