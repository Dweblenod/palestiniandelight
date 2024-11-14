package com.dweb.paldelight.data;

import com.dweb.paldelight.PalDelight;
import com.dweb.paldelight.block.PDBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class PDBlockTagProvider extends BlockTagsProvider {
    public PDBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, PalDelight.MOD_ID, existingFileHelper);
    }

    public static final TagKey<Block> OLIVE_LOGS = BlockTags.create(PalDelight.id("logs/olive"));

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(OLIVE_LOGS).add(PDBlocks.OLIVE_LOG.get(), PDBlocks.STRIPPED_OLIVE_LOG.get());
        tag(BlockTags.LOGS).addTag(OLIVE_LOGS);
    }
}
