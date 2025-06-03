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
import net.neoforged.neoforge.common.data.ExistingFileHelper;
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
        tag(OLIVE_FORGE).add(PDItems.OLIVE.get());
        tag(OLIVE_C).add(PDItems.OLIVE.get());
    }
    
    public static final TagKey<Item> OLIVE_FORGE = forgeTag("vegetables/olive"); //legacy pams
    public static final TagKey<Item> OLIVE_C = cTag("olive"); //not based off anything
    
    private static TagKey<Item> forgeTag(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", name));
    }
    
    private static TagKey<Item> cTag(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
    }
}
