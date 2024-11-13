package com.dweb.paldelight.item;

import com.dweb.paldelight.PalDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class PDCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PalDelight.MOD_ID);

    public static final String TAB_TITLE = "item_group.paldelight.paldelight";
    public static final RegistryObject<CreativeModeTab> PD_TAB = CREATIVE_MODE_TABS.register("paldelight", () -> CreativeModeTab.builder()
            .title(Component.translatable(TAB_TITLE))
            .icon(() -> PDItems.OLIVE.get().getDefaultInstance())
            .displayItems(PDCreativeTab::addDisplayItems).build()
    );

    private static void addDisplayItems(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output) {
        output.accept(PDItems.OLIVE.get());
        output.accept(PDItems.OLIVE_OIL.get());
        output.accept(PDItems.SUMAC_BERRIES.get());
        output.accept(PDItems.ROSE_WATER.get());

        output.accept(PDItems.FATAYER.get());
        output.accept(PDItems.KNAFEH.get());

        output.accept(PDItems.OLIVE_LOG.get());
        output.accept(PDItems.STRIPPED_OLIVE_LOG.get());
    }
}
