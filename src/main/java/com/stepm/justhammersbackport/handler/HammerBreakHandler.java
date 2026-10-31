package com.stepm.justhammersbackport.handler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.stepm.justhammersbackport.item.HammerItem;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class HammerBreakHandler {
    
    private static final Random RANDOM = new Random();
    
    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        EntityPlayer player = event.getPlayer();
        if (player == null || player.world.isRemote) return;
        
        ItemStack held = player.getHeldItemMainhand();
        if (held.isEmpty() || !(held.getItem() instanceof HammerItem)) return;
        
        HammerItem hammer = (HammerItem) held.getItem();
        
        int maxDamage = held.getMaxDamage();
        int currentDamage = held.getItemDamage();
        int remaining = maxDamage - currentDamage;
        
        if (remaining <= 1) {
            event.setCanceled(true);
            player.sendStatusMessage(
                new TextComponentString(TextFormatting.DARK_RED + "" + TextFormatting.BOLD + "Hammer is broken! Repair it first."),
                true
            );
            return;
        }
        
        if (player.isSneaking()) {
            return;
        }
        
        event.setCanceled(true);
        
        BlockPos origin = event.getPos();
        World world = event.getWorld();
        IBlockState originState = world.getBlockState(origin);
        
        if (originState.getBlockHardness(world, origin) < 0) return;
        if (originState.getBlock().getHarvestLevel(originState) > hammer.getToolMaterial().getHarvestLevel()) return;
        
        if (!canBreakInRadius(originState)) {
            breakBlock(world, origin, held, player);
            return;
        }
        
        int range = hammer.getRange();
        int depth = hammer.getDepth();
        int blocksBroken = 0;
        int totalXp = 0;
        int totalDamage = 0;
        
        Set<BlockPos> targets = new HashSet<>();
        
        if (depth == 1) {
            EnumFacing sideHit = getSideHit(player, origin);
            
            if (sideHit != null) {
                EnumFacing.Axis axis = sideHit.getAxis();
                
                for (int dx = -range; dx <= range; dx++) {
                    for (int dz = -range; dz <= range; dz++) {
                        BlockPos targetPos;
                        if (axis == EnumFacing.Axis.Y) {
                            int yOffset = sideHit == EnumFacing.UP ? 0 : 0;
                            targetPos = new BlockPos(origin.getX() + dx, origin.getY() + yOffset, origin.getZ() + dz);
                        } else if (axis == EnumFacing.Axis.X) {
                            targetPos = new BlockPos(origin.getX(), origin.getY() + dz, origin.getZ() + dx);
                        } else {
                            targetPos = new BlockPos(origin.getX() + dx, origin.getY() + dz, origin.getZ());
                        }
                        targets.add(targetPos);
                    }
                }
            } else {
                for (int dx = -range; dx <= range; dx++) {
                    for (int dz = -range; dz <= range; dz++) {
                        targets.add(new BlockPos(origin.getX() + dx, origin.getY(), origin.getZ() + dz));
                    }
                }
            }
        } else {
            float pitch = player.rotationPitch;
            EnumFacing facing = player.getHorizontalFacing();
            
            for (int d = 0; d < depth; d++) {
                for (int dx = -range; dx <= range; dx++) {
                    for (int dz = -range; dz <= range; dz++) {
                        BlockPos targetPos;
                        
                        if (pitch >= 60) {
                            targetPos = new BlockPos(origin.getX() + dx, origin.getY() - d, origin.getZ() + dz);
                        } else if (pitch <= -60) {
                            targetPos = new BlockPos(origin.getX() + dx, origin.getY() + d, origin.getZ() + dz);
                        } else {
                            switch (facing) {
                                case NORTH:
                                    targetPos = new BlockPos(origin.getX() + dx, origin.getY() + dz, origin.getZ() - d);
                                    break;
                                case SOUTH:
                                    targetPos = new BlockPos(origin.getX() + dx, origin.getY() + dz, origin.getZ() + d);
                                    break;
                                case WEST:
                                    targetPos = new BlockPos(origin.getX() - d, origin.getY() + dz, origin.getZ() + dx);
                                    break;
                                case EAST:
                                    targetPos = new BlockPos(origin.getX() + d, origin.getY() + dz, origin.getZ() + dx);
                                    break;
                                default:
                                    targetPos = origin;
                            }
                        }
                        targets.add(targetPos);
                    }
                }
            }
        }
        
        for (BlockPos pos : targets) {
            IBlockState state = world.getBlockState(pos);
            if (state.getBlock() == Blocks.AIR) continue;
            if (!canBreakInRadius(state)) continue;
            if (state.getBlockHardness(world, pos) < 0) continue;
            if (state.getBlock().getHarvestLevel(state) > hammer.getToolMaterial().getHarvestLevel()) continue;
            
            int xp = breakBlock(world, pos, held, player);
            if (xp >= 0) {
                blocksBroken++;
                totalXp += xp;
                totalDamage++;
            }
        }
        
        if (totalXp > 0) {
            EntityXPOrb xpOrb = new EntityXPOrb(world, origin.getX() + 0.5, origin.getY() + 0.5, origin.getZ() + 0.5, totalXp);
            world.spawnEntity(xpOrb);
        }
        
        if (blocksBroken > 0) {
            int unbreaking = EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, held);
            int actualDamage = 0;
            
            for (int i = 0; i < totalDamage; i++) {
                if (unbreaking <= 0 || RANDOM.nextInt(unbreaking + 1) != 0) {
                    actualDamage++;
                }
            }
            
            int newDamage = held.getItemDamage() + actualDamage;
            int newRemaining = maxDamage - newDamage;
            
            if (newRemaining <= 1) {
                held.setItemDamage(maxDamage - 1);
                player.sendStatusMessage(
                    new TextComponentString(TextFormatting.DARK_RED + "" + TextFormatting.BOLD + "Hammer is almost broken! Repair it now."),
                    true
                );
            } else if (newDamage < maxDamage) {
                held.setItemDamage(newDamage);
            } else {
                held.setItemDamage(maxDamage - 1);
                player.sendStatusMessage(
                    new TextComponentString(TextFormatting.DARK_RED + "" + TextFormatting.BOLD + "Hammer is broken! Repair it first."),
                    true
                );
            }
        }
    }
    
    private EnumFacing getSideHit(EntityPlayer player, BlockPos origin) {
        Vec3d start = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ);
        Vec3d look = player.getLookVec();
        Vec3d end = start.add(look.scale(5.0));
        
        RayTraceResult result = player.world.rayTraceBlocks(start, end, false, true, false);
        
        if (result != null && result.typeOfHit == RayTraceResult.Type.BLOCK) {
            if (result.getBlockPos().equals(origin)) {
                return result.sideHit;
            }
        }
        return null;
    }
    
    private int breakBlock(World world, BlockPos pos, ItemStack tool, EntityPlayer player) {
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        
        if (block == Blocks.BEDROCK || block == Blocks.AIR) return -1;
        if (state.getBlockHardness(world, pos) < 0) return -1;
        
        HammerItem hammer = (HammerItem) tool.getItem();
        if (block.getHarvestLevel(state) > hammer.getToolMaterial().getHarvestLevel()) return -1;
        
        TileEntity tileentity = world.getTileEntity(pos);
        int fortuneLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, tool);
        int exp = block.getExpDrop(state, world, pos, fortuneLevel);
        
        boolean canHarvest = block.canHarvestBlock(world, pos, player);
        boolean removed = block.removedByPlayer(state, world, pos, player, canHarvest);
        
        if (removed) {
            if (canHarvest) {
                block.harvestBlock(world, player, pos, state, tileentity, tool);
            }
        } else {
            return -1;
        }
        
        return exp;
    }
    
    private boolean canBreakInRadius(IBlockState state) {
        Block block = state.getBlock();
        Material material = state.getMaterial();
        
        if (block == Blocks.BEDROCK || block == Blocks.AIR) return false;
        
        if (material == Material.ROCK || 
            material == Material.IRON || 
            material == Material.ANVIL || 
            material == Material.ICE ||
            material == Material.PACKED_ICE ||
            material == Material.PISTON ||
            block == Blocks.OBSIDIAN ||
            block == Blocks.ENDER_CHEST ||
            block == Blocks.COAL_ORE ||
            block == Blocks.IRON_ORE ||
            block == Blocks.GOLD_ORE ||
            block == Blocks.DIAMOND_ORE ||
            block == Blocks.EMERALD_ORE ||
            block == Blocks.LAPIS_ORE ||
            block == Blocks.REDSTONE_ORE ||
            block == Blocks.LIT_REDSTONE_ORE ||
            block == Blocks.QUARTZ_ORE) {
            return true;
        }
        
        return false;
    }
}