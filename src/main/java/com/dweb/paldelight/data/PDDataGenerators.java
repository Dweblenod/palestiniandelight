package com.dweb.paldelight.data;

import com.dweb.paldelight.PalDelight;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PalDelight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PDDataGenerators {

    @SubscribeEvent
    public static void gatherDataEvent(GatherDataEvent event)
    {
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();

        generator.addProvider(event.includeClient(), new PDItemModelProvider(packOutput, fileHelper));
        generator.addProvider(event.includeClient(), new PDLanguageProvider(packOutput));
    }
}
