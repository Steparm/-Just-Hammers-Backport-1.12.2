package com.stepm.justhammersbackport.proxy;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.stepm.justhammersbackport.handler.TooltipHandler;

public class ClientProxy implements IProxy {
    
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        OBJLoader.INSTANCE.addDomain("justhammersbackport");
        MinecraftForge.EVENT_BUS.register(new TooltipHandler());
    }
}