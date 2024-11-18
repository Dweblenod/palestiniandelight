package com.dweb.paldelight;

import com.dweb.paldelight.block.PDBlocks;
import com.dweb.paldelight.item.PDCreativeTab;
import com.dweb.paldelight.item.PDItems;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(PalDelight.MOD_ID)
public class PalDelight {
    public static final String MOD_ID = "paldelight";
    
    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
    
    private static final Logger LOGGER = LogUtils.getLogger();
    
    public PalDelight() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        modEventBus.addListener(this::commonSetup);
        
        PDBlocks.BLOCKS.register(modEventBus);
        PDItems.ITEMS.register(modEventBus);
        PDCreativeTab.CREATIVE_MODE_TABS.register(modEventBus);
        
        MinecraftForge.EVENT_BUS.register(this);
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, PDConfig.SPEC);
    }
    
    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        /*LOGGER.info("HELLO FROM COMMON SETUP");

        if (PDConfig.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(PDConfig.magicNumberIntroduction + PDConfig.magicNumber);

        PDConfig.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));*/
    }
    
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    
    }
    
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        
        }
    }
}
