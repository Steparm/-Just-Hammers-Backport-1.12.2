package com.stepm.justhammersbackport.registry;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import com.stepm.justhammersbackport.JusthammersbackportMod;
import com.stepm.justhammersbackport.item.HammerItem;
import com.stepm.justhammersbackport.item.CoreItem;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    
    public static final List<Item> ITEMS = new ArrayList<>();
    public static final List<Item> CONDITIONAL_ITEMS = new ArrayList<>();
    public static final List<Item> NETHERITE_ITEMS = new ArrayList<>();
    public static final List<Item> COPPER_ITEMS = new ArrayList<>();
    
    public static HammerItem IRON_HAMMER;
    public static HammerItem STONE_HAMMER;
    public static HammerItem DIAMOND_HAMMER;
    public static HammerItem GOLD_HAMMER;
    
    public static HammerItem IRON_IMPACT_HAMMER;
    public static HammerItem STONE_IMPACT_HAMMER;
    public static HammerItem DIAMOND_IMPACT_HAMMER;
    public static HammerItem GOLDEN_IMPACT_HAMMER;
    
    public static HammerItem IRON_REINFORCED_HAMMER;
    public static HammerItem STONE_REINFORCED_HAMMER;
    public static HammerItem DIAMOND_REINFORCED_HAMMER;
    public static HammerItem GOLD_REINFORCED_HAMMER;
    
    public static HammerItem IRON_REINFORCED_IMPACT_HAMMER;
    public static HammerItem STONE_REINFORCED_IMPACT_HAMMER;
    public static HammerItem DIAMOND_REINFORCED_IMPACT_HAMMER;
    public static HammerItem GOLD_REINFORCED_IMPACT_HAMMER;
    
    public static HammerItem IRON_DESTRUCTOR_HAMMER;
    public static HammerItem STONE_DESTRUCTOR_HAMMER;
    public static HammerItem DIAMOND_DESTRUCTOR_HAMMER;
    public static HammerItem GOLD_DESTRUCTOR_HAMMER;
    
    public static HammerItem NETHERITE_HAMMER;
    public static HammerItem NETHERITE_IMPACT_HAMMER;
    public static HammerItem NETHERITE_REINFORCED_HAMMER;
    public static HammerItem NETHERITE_REINFORCED_IMPACT_HAMMER;
    public static HammerItem NETHERITE_DESTRUCTOR_HAMMER;
    
    public static HammerItem COPPER_HAMMER;
    public static HammerItem COPPER_IMPACT_HAMMER;
    public static HammerItem COPPER_REINFORCED_HAMMER;
    public static HammerItem COPPER_REINFORCED_IMPACT_HAMMER;
    public static HammerItem COPPER_DESTRUCTOR_HAMMER;
    
    public static CoreItem IMPACT_CORE;
    public static CoreItem REINFORCED_CORE;
    public static CoreItem DESTRUCTOR_CORE;
    public static CoreItem REINFORCED_IMPACT_CORE;
    
    public static final Item.ToolMaterial IRON_MAT = EnumHelper.addToolMaterial("IRON_HAMMER", 2, 825, 6.0F, 2.0F, 14);
    public static final Item.ToolMaterial STONE_MAT = EnumHelper.addToolMaterial("STONE_HAMMER", 1, 527, 4.0F, 1.0F, 5);
    public static final Item.ToolMaterial DIAMOND_MAT = EnumHelper.addToolMaterial("DIAMOND_HAMMER", 3, 4100, 8.0F, 3.0F, 10);
    public static final Item.ToolMaterial GOLD_MAT = EnumHelper.addToolMaterial("GOLD_HAMMER", 1, 280, 12.0F, 0.0F, 22);
    
    public static final Item.ToolMaterial IRON_IMPACT_MAT = EnumHelper.addToolMaterial("IRON_IMPACT", 2, 4080, 6.0F, 2.0F, 14);
    public static final Item.ToolMaterial STONE_IMPACT_MAT = EnumHelper.addToolMaterial("STONE_IMPACT", 1, 3490, 4.0F, 1.0F, 5);
    public static final Item.ToolMaterial DIAMOND_IMPACT_MAT = EnumHelper.addToolMaterial("DIAMOND_IMPACT", 3, 10640, 8.0F, 3.0F, 10);
    public static final Item.ToolMaterial GOLDEN_IMPACT_MAT = EnumHelper.addToolMaterial("GOLDEN_IMPACT", 1, 2990, 12.0F, 0.0F, 22);
    
    public static final Item.ToolMaterial IRON_REINFORCED_MAT = EnumHelper.addToolMaterial("IRON_REINFORCED", 2, 5710, 6.0F, 2.0F, 14);
    public static final Item.ToolMaterial STONE_REINFORCED_MAT = EnumHelper.addToolMaterial("STONE_REINFORCED", 1, 4810, 4.0F, 1.0F, 5);
    public static final Item.ToolMaterial DIAMOND_REINFORCED_MAT = EnumHelper.addToolMaterial("DIAMOND_REINFORCED", 3, 15540, 8.0F, 3.0F, 10);
    public static final Item.ToolMaterial GOLD_REINFORCED_MAT = EnumHelper.addToolMaterial("GOLD_REINFORCED", 1, 4070, 12.0F, 0.0F, 22);
    
    public static final Item.ToolMaterial IRON_REINFORCED_IMPACT_MAT = EnumHelper.addToolMaterial("IRON_REINFORCED_IMPACT", 2, 7730, 6.0F, 2.0F, 14);
    public static final Item.ToolMaterial STONE_REINFORCED_IMPACT_MAT = EnumHelper.addToolMaterial("STONE_REINFORCED_IMPACT", 1, 6540, 4.0F, 1.0F, 5);
    public static final Item.ToolMaterial DIAMOND_REINFORCED_IMPACT_MAT = EnumHelper.addToolMaterial("DIAMOND_REINFORCED_IMPACT", 3, 20840, 8.0F, 3.0F, 10);
    public static final Item.ToolMaterial GOLD_REINFORCED_IMPACT_MAT = EnumHelper.addToolMaterial("GOLD_REINFORCED_IMPACT", 1, 5550, 12.0F, 0.0F, 22);
    
    public static final Item.ToolMaterial IRON_DESTRUCTOR_MAT = EnumHelper.addToolMaterial("IRON_DESTRUCTOR", 2, 10160, 6.0F, 2.0F, 14);
    public static final Item.ToolMaterial STONE_DESTRUCTOR_MAT = EnumHelper.addToolMaterial("STONE_DESTRUCTOR", 1, 8670, 4.0F, 1.0F, 5);
    public static final Item.ToolMaterial DIAMOND_DESTRUCTOR_MAT = EnumHelper.addToolMaterial("DIAMOND_DESTRUCTOR", 3, 26540, 8.0F, 3.0F, 10);
    public static final Item.ToolMaterial GOLD_DESTRUCTOR_MAT = EnumHelper.addToolMaterial("GOLD_DESTRUCTOR", 1, 7430, 12.0F, 0.0F, 22);
    
    public static Item.ToolMaterial NETHERITE_MAT;
    public static Item.ToolMaterial NETHERITE_IMPACT_MAT;
    public static Item.ToolMaterial NETHERITE_REINFORCED_MAT;
    public static Item.ToolMaterial NETHERITE_REINFORCED_IMPACT_MAT;
    public static Item.ToolMaterial NETHERITE_DESTRUCTOR_MAT;
    
    public static Item.ToolMaterial COPPER_MAT;
    public static Item.ToolMaterial COPPER_IMPACT_MAT;
    public static Item.ToolMaterial COPPER_REINFORCED_MAT;
    public static Item.ToolMaterial COPPER_REINFORCED_IMPACT_MAT;
    public static Item.ToolMaterial COPPER_DESTRUCTOR_MAT;
    
    public static void preInit() {
        initBasicHammers();
        initImpactHammers();
        initReinforcedHammers();
        initReinforcedImpactHammers();
        initDestructorHammers();
        initCores();
        
        initConditionalMaterials();
        initNetheriteHammers();
        initCopperHammers();
    }
    
    private static void initBasicHammers() {
        IRON_HAMMER = registerHammer(new HammerItem(IRON_MAT, 1, 1), "ironhammer");
        STONE_HAMMER = registerHammer(new HammerItem(STONE_MAT, 1, 1), "stonehammer");
        DIAMOND_HAMMER = registerHammer(new HammerItem(DIAMOND_MAT, 1, 1), "diamondhammer");
        GOLD_HAMMER = registerHammer(new HammerItem(GOLD_MAT, 1, 1), "golghammer");
    }
    
    private static void initImpactHammers() {
        IRON_IMPACT_HAMMER = registerHammer(new HammerItem(IRON_IMPACT_MAT, 1, 3), "theironimpacthammer");
        STONE_IMPACT_HAMMER = registerHammer(new HammerItem(STONE_IMPACT_MAT, 1, 3), "stoneimpacthammer");
        DIAMOND_IMPACT_HAMMER = registerHammer(new HammerItem(DIAMOND_IMPACT_MAT, 1, 3), "diamondimpacthammer");
        GOLDEN_IMPACT_HAMMER = registerHammer(new HammerItem(GOLDEN_IMPACT_MAT, 1, 3), "golden_impact_hammer");
    }
    
    private static void initReinforcedHammers() {
        IRON_REINFORCED_HAMMER = registerHammer(new HammerItem(IRON_REINFORCED_MAT, 2, 1), "ironreinforcedhammer");
        STONE_REINFORCED_HAMMER = registerHammer(new HammerItem(STONE_REINFORCED_MAT, 2, 1), "stonereinforcedhammer");
        DIAMOND_REINFORCED_HAMMER = registerHammer(new HammerItem(DIAMOND_REINFORCED_MAT, 2, 1), "diamondreinforcedhammer");
        GOLD_REINFORCED_HAMMER = registerHammer(new HammerItem(GOLD_REINFORCED_MAT, 2, 1), "goldreinforcedhammer");
    }
    
    private static void initReinforcedImpactHammers() {
        IRON_REINFORCED_IMPACT_HAMMER = registerHammer(new HammerItem(IRON_REINFORCED_IMPACT_MAT, 2, 3), "ironreinforcedimpacthammer");
        STONE_REINFORCED_IMPACT_HAMMER = registerHammer(new HammerItem(STONE_REINFORCED_IMPACT_MAT, 2, 3), "stonereinforcedimpacthammer");
        DIAMOND_REINFORCED_IMPACT_HAMMER = registerHammer(new HammerItem(DIAMOND_REINFORCED_IMPACT_MAT, 2, 3), "diamondreinforcedimpacthammer");
        GOLD_REINFORCED_IMPACT_HAMMER = registerHammer(new HammerItem(GOLD_REINFORCED_IMPACT_MAT, 2, 3), "goldreinforcedimpacthammer");
    }
    
    private static void initDestructorHammers() {
        IRON_DESTRUCTOR_HAMMER = registerHammer(new HammerItem(IRON_DESTRUCTOR_MAT, 2, 5), "irondestructorhammer");
        STONE_DESTRUCTOR_HAMMER = registerHammer(new HammerItem(STONE_DESTRUCTOR_MAT, 2, 5), "stonedestructorhammer");
        DIAMOND_DESTRUCTOR_HAMMER = registerHammer(new HammerItem(DIAMOND_DESTRUCTOR_MAT, 2, 5), "diamonddestructorhammer");
        GOLD_DESTRUCTOR_HAMMER = registerHammer(new HammerItem(GOLD_DESTRUCTOR_MAT, 2, 5), "golddestructorhammer");
    }
    
    private static void initConditionalMaterials() {
        NETHERITE_MAT = EnumHelper.addToolMaterial("NETHERITE_HAMMER", 4, 5280, 9.0F, 4.0F, 15);
        NETHERITE_IMPACT_MAT = EnumHelper.addToolMaterial("NETHERITE_IMPACT", 4, 12990, 9.0F, 4.0F, 15);
        NETHERITE_REINFORCED_MAT = EnumHelper.addToolMaterial("NETHERITE_REINFORCED", 3, 19060, 8.0F, 4.0F, 10);
        NETHERITE_REINFORCED_IMPACT_MAT = EnumHelper.addToolMaterial("NETHERITE_REINFORCED_IMPACT", 3, 25540, 8.0F, 4.0F, 10);
        NETHERITE_DESTRUCTOR_MAT = EnumHelper.addToolMaterial("NETHERITE_DESTRUCTOR", 3, 32420, 8.0F, 4.0F, 10);
        
        COPPER_MAT = EnumHelper.addToolMaterial("COPPER_HAMMER", 2, 625, 5.0F, 2.0F, 14);
        COPPER_IMPACT_MAT = EnumHelper.addToolMaterial("COPPER_IMPACT", 2, 3730, 5.0F, 2.0F, 14);
        COPPER_REINFORCED_MAT = EnumHelper.addToolMaterial("COPPER_REINFORCED", 2, 5310, 5.0F, 2.0F, 14);
        COPPER_REINFORCED_IMPACT_MAT = EnumHelper.addToolMaterial("COPPER_REINFORCED_IMPACT", 2, 7260, 5.0F, 2.0F, 14);
        COPPER_DESTRUCTOR_MAT = EnumHelper.addToolMaterial("COPPER_DESTRUCTOR", 2, 9420, 5.0F, 2.0F, 14);
    }
    
    private static void initNetheriteHammers() {
        if (ConditionalItems.isAnyNetheriteModLoaded()) {
            NETHERITE_HAMMER = registerNetheriteHammer(new HammerItem(NETHERITE_MAT, 1, 1), "niezieritovyimolot");
            NETHERITE_IMPACT_HAMMER = registerNetheriteHammer(new HammerItem(NETHERITE_IMPACT_MAT, 1, 3), "netheriteimpacthammer");
            NETHERITE_REINFORCED_HAMMER = registerNetheriteHammer(new HammerItem(NETHERITE_REINFORCED_MAT, 2, 1), "netheritereinforcedhammer");
            NETHERITE_REINFORCED_IMPACT_HAMMER = registerNetheriteHammer(new HammerItem(NETHERITE_REINFORCED_IMPACT_MAT, 2, 3), "netheritereinforcedimpacthammer");
            NETHERITE_DESTRUCTOR_HAMMER = registerNetheriteHammer(new HammerItem(NETHERITE_DESTRUCTOR_MAT, 2, 5), "netheritedestructorhammer");
        }
    }
    
    private static void initCopperHammers() {
        if (ConditionalItems.isAnyCopperModLoaded()) {
            COPPER_HAMMER = registerCopperHammer(new HammerItem(COPPER_MAT, 1, 1), "copperhammer");
            COPPER_IMPACT_HAMMER = registerCopperHammer(new HammerItem(COPPER_IMPACT_MAT, 1, 3), "copperimpacthammer");
            COPPER_REINFORCED_HAMMER = registerCopperHammer(new HammerItem(COPPER_REINFORCED_MAT, 2, 1), "copperreinforcedhammer");
            COPPER_REINFORCED_IMPACT_HAMMER = registerCopperHammer(new HammerItem(COPPER_REINFORCED_IMPACT_MAT, 2, 3), "copperreinforcedimpacthammer");
            COPPER_DESTRUCTOR_HAMMER = registerCopperHammer(new HammerItem(COPPER_DESTRUCTOR_MAT, 2, 5), "copperdestructorhammer");
        }
    }
    
    private static void initCores() {
        IMPACT_CORE = registerCore(new CoreItem(), "impactcore");
        REINFORCED_CORE = registerCore(new CoreItem(), "reinforcedcore");
        DESTRUCTOR_CORE = registerCore(new CoreItem(), "destructorcore");
        REINFORCED_IMPACT_CORE = registerCore(new CoreItem(), "reinforcedimpactcore");
    }
    
    private static HammerItem registerHammer(HammerItem item, String name) {
        item.setTranslationKey(name);
        item.setRegistryName(new ResourceLocation(JusthammersbackportMod.MODID, name));
        item.setCreativeTab(ModCreativeTabs.TAB_HAMMER);
        ITEMS.add(item);
        return item;
    }
    
    private static HammerItem registerNetheriteHammer(HammerItem item, String name) {
        item.setTranslationKey(name);
        item.setRegistryName(new ResourceLocation(JusthammersbackportMod.MODID, name));
        item.setCreativeTab(ModCreativeTabs.TAB_HAMMER);
        NETHERITE_ITEMS.add(item);
        CONDITIONAL_ITEMS.add(item);
        return item;
    }
    
    private static HammerItem registerCopperHammer(HammerItem item, String name) {
        item.setTranslationKey(name);
        item.setRegistryName(new ResourceLocation(JusthammersbackportMod.MODID, name));
        item.setCreativeTab(ModCreativeTabs.TAB_HAMMER);
        COPPER_ITEMS.add(item);
        CONDITIONAL_ITEMS.add(item);
        return item;
    }
    
    private static CoreItem registerCore(CoreItem item, String name) {
        item.setTranslationKey(name);
        item.setRegistryName(new ResourceLocation(JusthammersbackportMod.MODID, name));
        item.setCreativeTab(ModCreativeTabs.TAB_HAMMER);
        ITEMS.add(item);
        return item;
    }
    
    public static void registerAll(IForgeRegistry<Item> registry) {
        registry.registerAll(ITEMS.toArray(new Item[0]));
        for (Item item : CONDITIONAL_ITEMS) {
            registry.register(item);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event) {
        for (Item item : ITEMS) {
            registerModel(item);
        }
        for (Item item : CONDITIONAL_ITEMS) {
            registerModel(item);
        }
    }
    
    @SideOnly(Side.CLIENT)
    private static void registerModel(Item item) {
        ResourceLocation registryName = item.getRegistryName();
        if (registryName == null) {
            return;
        }
        String path = registryName.getPath();
        ModelLoader.setCustomModelResourceLocation(item, 0, 
            new ModelResourceLocation(JusthammersbackportMod.MODID + ":" + path, "inventory"));
    }
}