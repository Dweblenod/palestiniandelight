package com.dweb.paldelight.data.tag;

import com.dweb.paldelight.PalDelight;
import com.dweb.paldelight.item.PDItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class PDItemTagProvider extends ItemTagsProvider {
    public PDItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, PalDelight.MOD_ID, existingFileHelper);
    }
    
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        copy(PDTags.Blocks.OLIVE_LOGS, PDTags.Items.OLIVE_LOGS);
        copy(BlockTags.PLANKS, ItemTags.PLANKS);
        tag(OLIVE).add(PDItems.OLIVE.get());
    }
    
    public static final TagKey<Item> OLIVE = forgeTag("vegetables/olive");
    
    private static TagKey<Item> forgeTag(String name) {
        return ItemTags.create(new ResourceLocation("forge", name));
    }
}
