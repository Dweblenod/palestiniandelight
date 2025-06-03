package com.dweb.paldelight;

import com.dweb.paldelight.block.PDBlocks;
import com.dweb.paldelight.item.PDCreativeTab;
import com.dweb.paldelight.item.PDItems;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(PalDelight.MOD_ID)
public class PalDelight {
    public static final String MOD_ID = "paldelight";
    
    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
    
    private static final Logger LOGGER = LogUtils.getLogger();
    
    public PalDelight(IEventBus eventBus, ModContainer modContainer) {
        eventBus.addListener(this::commonSetup);
        
        PDBlocks.BLOCKS.register(eventBus);
        PDItems.ITEMS.register(eventBus);
        PDCreativeTab.CREATIVE_MODE_TABS.register(eventBus);
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
    
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        
        }
    }
}
