package com.dweb.paldelight.data;

import com.dweb.paldelight.PalDelight;
import com.dweb.paldelight.data.loot.PDLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = PalDelight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PDDataGenerators {

    @SubscribeEvent
    public static void gatherDataEvent(GatherDataEvent event)
    {
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        var builtinEntries = generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(packOutput, event.getLookupProvider(), registrySetBuilder(), Set.of(PalDelight.MOD_ID)));
        CompletableFuture<HolderLookup.Provider> lookupProvider = builtinEntries.getRegistryProvider();

        generator.addProvider(event.includeServer(), PDLootTableProvider.providers(packOutput));
        generator.addProvider(event.includeServer(), new PDBlockTagProvider(packOutput, lookupProvider, fileHelper));

        generator.addProvider(event.includeClient(), new PDBlockStateProvider(packOutput, fileHelper));
        generator.addProvider(event.includeClient(), new PDItemModelProvider(packOutput, fileHelper));
        generator.addProvider(event.includeClient(), new PDLanguageProvider(packOutput));
    }

    private static RegistrySetBuilder registrySetBuilder()
    {
        return new RegistrySetBuilder().add(Registries.CONFIGURED_FEATURE, PDConfiguredFeatureProvider::register);
    }
}
