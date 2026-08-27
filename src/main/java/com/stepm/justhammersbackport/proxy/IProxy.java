package com.stepm.justhammersbackport.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public interface IProxy {
    default void preInit(FMLPreInitializationEvent event) {}
    default void init(FMLInitializationEvent event) {}
    default void postInit(FMLPostInitializationEvent event) {}
    default void serverLoad(FMLServerStartingEvent event) {}
}