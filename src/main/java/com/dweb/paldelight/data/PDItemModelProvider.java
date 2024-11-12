package com.dweb.paldelight.data;

import com.dweb.paldelight.PalDelight;
import com.dweb.paldelight.item.PDItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;


public final class PDItemModelProvider extends ItemModelProvider {

    public PDItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PalDelight.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(PDItems.OLIVE);
        simpleItem(PDItems.SUMAC_BERRIES);

        simpleItem(PDItems.FATAYER);
        simpleItem(PDItems.KNAFEH);
    }

    private ItemModelBuilder simpleItem(Supplier<? extends Item> item)
    {
        String itemIdPath = BuiltInRegistries.ITEM.getKey(item.get()).getPath();
        ResourceLocation itemName = PalDelight.id(itemIdPath);

        return withExistingParent(itemName.getPath(),
                new ResourceLocation("item/generated"))
                .texture("layer0", PalDelight.id(ModelProvider.ITEM_FOLDER + "/" + itemIdPath));
    }
}
