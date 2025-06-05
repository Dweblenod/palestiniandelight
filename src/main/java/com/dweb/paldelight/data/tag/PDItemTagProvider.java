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
import net.minecraft.world.item.Items;
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
        tag(OLIVE_FORGE).add(PDItems.OLIVE.get()).replace(false);
        tag(OLIVE_C).add(PDItems.OLIVE.get()).replace(false);
        tag(OLIVE_OIL).add(PDItems.OLIVE_OIL.get()).replace(false);
        tag(COOKING_OIL).add(PDItems.OLIVE_OIL.get()).replace().replace(false);
        tag(RAW_MUTTON).add(Items.MUTTON).replace(false);
        tag(FATAYER_FILLING)
                .addTag(RAW_MUTTON)
                .addOptionalTag(cPath("rawmutton"))
                .addOptionalTag(cPath("cheese/cheese"))
                .addOptionalTag(cPath("cheese"))
                .addOptionalTag(cPath("vegetables/spinach"))
                .addOptionalTag(cPath("crops/spinach"));
    }
    
    public static final TagKey<Item> OLIVE_FORGE = forgeTag("vegetables/olive"); //legacy Pams
    public static final TagKey<Item> OLIVE_C = cTag("olive"); //not based off anything
    public static final TagKey<Item> OLIVE_OIL = cTag("olive_oil");
    public static final TagKey<Item> COOKING_OIL = cTag("cooking_oil"); //used in Pams
    public static final TagKey<Item> RAW_MUTTON = cTag("foods/raw_mutton"); //used in FD
    public static final TagKey<Item> FATAYER_FILLING = palTag("fatayer_filling");
    
    private static ResourceLocation forgePath(String name) {
        return ResourceLocation.fromNamespaceAndPath("forge", name);
    }
    
    private static TagKey<Item> forgeTag(String name) {
        return ItemTags.create(forgePath(name));
    }
    
    private static ResourceLocation cPath(String name) {
        return ResourceLocation.fromNamespaceAndPath("c", name);
    }
    
    private static TagKey<Item> cTag(String name) {
        return ItemTags.create(cPath(name));
    }
    
    private static TagKey<Item> palTag(String name) {
        return ItemTags.create(PalDelight.id(name));
    }
}
