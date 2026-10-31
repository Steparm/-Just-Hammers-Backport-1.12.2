package com.stepm.justhammersbackport;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.stepm.justhammersbackport.compat.FutureMcCompat;
import com.stepm.justhammersbackport.handler.LavaProofHandler;
import com.stepm.justhammersbackport.handler.HammerBreakHandler;
import com.stepm.justhammersbackport.registry.ModItems;
import com.stepm.justhammersbackport.proxy.IProxy;

import net.minecraftforge.client.event.ModelRegistryEvent;

@Mod(modid = JusthammersbackportMod.MODID, 
     name = JusthammersbackportMod.NAME, 
     version = JusthammersbackportMod.VERSION)
public class JusthammersbackportMod {
    
    public static final String MODID = "justhammersbackport";
    public static final String NAME = "Just Hammers Backport";
    public static final String VERSION = "1.9.3";

    @Mod.Instance(MODID)
    public static JusthammersbackportMod instance;

    @SidedProxy(clientSide = "com.stepm.justhammersbackport.proxy.ClientProxy", 
                serverSide = "com.stepm.justhammersbackport.proxy.ServerProxy")
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModItems.preInit();
        
        MinecraftForge.EVENT_BUS.register(new HammerBreakHandler());
        MinecraftForge.EVENT_BUS.register(new LavaProofHandler());
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if (Loader.isModLoaded("futuremc")) {
            FutureMcCompat.init(event);
        }
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        proxy.serverLoad(event);
    }

    @Mod.EventBusSubscriber(modid = MODID)
    public static class RegistrationHandler {
        
        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            ModItems.registerAll(event.getRegistry());
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, value = Side.CLIENT)
    public static class ClientEventHandler {
        
        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event) {
            ModItems.registerModels(event);
        }
    }
}