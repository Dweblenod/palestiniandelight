package com.dweb.palestiniandelight.item;

import com.dweb.palestiniandelight.PalestinianDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class PDCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PalestinianDelight.MOD_ID);

    public static final RegistryObject<CreativeModeTab> PD_TAB = CREATIVE_MODE_TABS.register("pd_tab", () -> CreativeModeTab.builder()
            .icon(() -> PDItems.FATAYER.get().getDefaultInstance())
            .displayItems(PDCreativeTab::addDisplayItems).build()
    );

    private static void addDisplayItems(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output) {
        output.accept(PDItems.FATAYER.get());

    }
}
