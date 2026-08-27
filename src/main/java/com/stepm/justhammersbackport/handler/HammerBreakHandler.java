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
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.stepm.justhammersbackport.item.HammerItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HammerBreakHandler {
    
    private static final Random RANDOM = new Random();
    
    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        EntityPlayer player = event.getPlayer();
        if (player == null || player.world.isRemote) return;
        
        ItemStack held = player.getHeldItemMainhand();
        if (held.isEmpty() || !(held.getItem() instanceof HammerItem)) return;
        
        HammerItem hammer = (HammerItem) held.getItem();
        
        if (player.isSneaking()) {
            return;
        }
        
        event.setCanceled(true);
        
        BlockPos origin = event.getPos();
        World world = event.getWorld();
        IBlockState originState = world.getBlockState(origin);
        
        if (originState.getBlockHardness(world, origin) < 0) return;
        if (originState.getBlock().getHarvestLevel(originState) > hammer.getToolMaterial().getHarvestLevel()) return;
        
        if (!canBreakBlock(originState, origin, world, hammer)) {
            breakBlock(world, origin, held, player);
            return;
        }
        
        int range = hammer.getRange();
        int depth = hammer.getDepth();
        
        EnumFacing sideHit = getSideHit(player);
        
        List<BlockPos> targets = new ArrayList<>();
        
        if (sideHit != null && range > 0) {
            EnumFacing.Axis axis = sideHit.getAxis();
            
            Iterable<BlockPos> blocks;
            if (axis == EnumFacing.Axis.X) {
                blocks = BlockPos.getAllInBox(origin.add(0, -range, -range), origin.add(0, range, range));
            } else if (axis == EnumFacing.Axis.Y) {
                blocks = BlockPos.getAllInBox(origin.add(-range, 0, -range), origin.add(range, 0, range));
            } else {
                blocks = BlockPos.getAllInBox(origin.add(-range, -range, 0), origin.add(range, range, 0));
            }
            
            blocks.forEach(pos -> {
                if (!pos.equals(origin)) {
                    targets.add(pos);
                }
            });
            
            List<BlockPos> depthTargets = new ArrayList<>();
            for (BlockPos pos : targets) {
                for (int d = 1; d < depth; d++) {
                    BlockPos depthPos;
                    if (axis == EnumFacing.Axis.X) {
                        int xOffset = sideHit == EnumFacing.EAST ? d : -d;
                        depthPos = pos.add(xOffset, 0, 0);
                    } else if (axis == EnumFacing.Axis.Y) {
                        int yOffset = sideHit == EnumFacing.UP ? d : -d;
                        depthPos = pos.add(0, yOffset, 0);
                    } else {
                        int zOffset = sideHit == EnumFacing.SOUTH ? d : -d;
                        depthPos = pos.add(0, 0, zOffset);
                    }
                    depthTargets.add(depthPos);
                }
            }
            targets.addAll(depthTargets);
            targets.add(origin);
        } else {
            targets.add(origin);
        }
        
        int blocksBroken = 0;
        int totalXp = 0;
        
        for (BlockPos pos : targets) {
            IBlockState state = world.getBlockState(pos);
            if (state.getBlock() == Blocks.AIR) continue;
            
            if (!canBreakBlock(state, pos, world, hammer)) {
                continue;
            }
            
            if (state.getBlockHardness(world, pos) < 0) continue;
            if (state.getBlock().getHarvestLevel(state) > hammer.getToolMaterial().getHarvestLevel()) continue;
            
            int xp = breakBlock(world, pos, held, player);
            if (xp >= 0) {
                blocksBroken++;
                totalXp += xp;
            }
        }
        
        if (totalXp > 0) {
            EntityXPOrb xpOrb = new EntityXPOrb(world, origin.getX() + 0.5, origin.getY() + 0.5, origin.getZ() + 0.5, totalXp);
            world.spawnEntity(xpOrb);
        }
        
        if (blocksBroken > 0) {
            int unbreaking = EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, held);
            int actualDamage = 0;
            
            for (int i = 0; i < blocksBroken; i++) {
                if (unbreaking <= 0 || RANDOM.nextInt(unbreaking + 1) != 0) {
                    actualDamage++;
                }
            }
            
            int newDamage = held.getItemDamage() + actualDamage;
            if (newDamage < held.getMaxDamage()) {
                held.setItemDamage(newDamage);
            } else {
                held.shrink(1);
            }
        }
    }
    
    private EnumFacing getSideHit(EntityPlayer player) {
        Vec3d start = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ);
        Vec3d look = player.getLookVec();
        Vec3d end = start.add(look.scale(5.0));
        
        RayTraceResult result = player.world.rayTraceBlocks(start, end, false, true, false);
        
        if (result != null && result.typeOfHit == RayTraceResult.Type.BLOCK) {
            return result.sideHit;
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
    
    private boolean canBreakBlock(IBlockState state, BlockPos pos, World world, HammerItem hammer) {
        Block block = state.getBlock();
        Material material = state.getMaterial();
        
        if (block == Blocks.BEDROCK) return false;
        
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