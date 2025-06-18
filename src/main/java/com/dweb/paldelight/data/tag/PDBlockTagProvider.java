package com.dweb.paldelight.data.tag;

import com.dweb.paldelight.PalDelight;
import com.dweb.paldelight.block.PDBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class PDBlockTagProvider extends BlockTagsProvider {
    public PDBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, PalDelight.MOD_ID, existingFileHelper);
    }
    
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(PDTags.Blocks.OLIVE_LOGS).add(PDBlocks.OLIVE_LOG.get(), PDBlocks.STRIPPED_OLIVE_LOG.get(), PDBlocks.OLIVE_WOOD.get(), PDBlocks.STRIPPED_OLIVE_WOOD.get());
        tag(BlockTags.LOGS_THAT_BURN).addTag(PDTags.Blocks.OLIVE_LOGS); //LOGS_THAT_BURN included in LOGS
        tag(BlockTags.OVERWORLD_NATURAL_LOGS).addTag(PDTags.Blocks.OLIVE_LOGS);
        
        tag(BlockTags.PLANKS).add(PDBlocks.OLIVE_PLANKS.get(), PDBlocks.ORNATE_OLIVE_PLANKS.get());
        tag(BlockTags.WOODEN_STAIRS).add(PDBlocks.OLIVE_STAIRS.get());
        tag(BlockTags.WOODEN_SLABS).add(PDBlocks.OLIVE_SLAB.get());
        tag(BlockTags.LEAVES).add(PDBlocks.OLIVE_LEAVES.get());
    }
}
