package com.dweb.paldelight.data;

import com.dweb.paldelight.PalDelight;
import com.dweb.paldelight.block.PDBlocks;
import com.dweb.paldelight.item.PDCreativeTab;
import com.dweb.paldelight.item.PDItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

public class PDLanguageProvider extends LanguageProvider {
    public PDLanguageProvider(PackOutput output) {
        super(output, PalDelight.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addItem(PDItems.OLIVE, "Olive");
        addTooltip(PDItems.OLIVE, "Rather bitter as is. But it holds a lot of potential.");
        addItem(PDItems.OLIVE_OIL, "Olive Oil");
        addItem(PDItems.SUMAC_BERRIES, "Sumac Berries");
        addTooltip(PDItems.SUMAC_BERRIES, "Medicinal! Has a small chance of curing poison upon consumption.");
        addItem(PDItems.ROSE_WATER, "Rose Water");

        addItem(PDItems.FATAYER, "Fatayer");
        addItem(PDItems.KNAFEH, "Knafeh");

        addBlock(PDBlocks.OLIVE_LOG, "Olive Log");
        addBlock(PDBlocks.STRIPPED_OLIVE_LOG, "Stripped Olive Log");
        addBlock(PDBlocks.OLIVE_LEAVES, "Olive Leaves");
        addBlock(PDBlocks.OLIVE_SAPLING, "Olive Sapling");
        addItem(PDItems.SUMAC, "Sumac");

        add(PDCreativeTab.TAB_TITLE, "Palestinian Delights");
    }

    protected void addTooltip(RegistryObject<? extends ItemLike> itemLike, String value)
    {
        addExtra(itemLike.get(), "tooltip", value);
    }

    //TODO not showing up
    protected void addExtra(ItemLike key, String type, String value) {
        add(key.asItem().getDescriptionId() + "." + type, value);
    }
}
