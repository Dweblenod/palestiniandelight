package com.dweb.paldelight.item;

import com.dweb.paldelight.PalDelight;
import com.dweb.paldelight.block.PDBlocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Optional;

public class PDItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PalDelight.MOD_ID);
    
    public static final DeferredItem<Item> OLIVE = ITEMS.register("olive", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().fast().nutrition(1).saturationModifier(0.2f).build())));
    //TODO olive_pomace, consider adding it to compost datamap
    public static final DeferredItem<Item> OLIVE_OIL = ITEMS.register("olive_oil", () -> new BottledDrinkItem(SoundEvents.HONEY_DRINK, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.5f).build())));
    public static final DeferredItem<Item> SUMAC_BERRIES = ITEMS.register("sumac_berries", () -> new SumacBerriesItem(new Item.Properties()));
    public static final DeferredItem<Item> ROSE_WATER = ITEMS.register("rose_water", () -> new BottledDrinkItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.1f).build())));
    
    //TODO add comfort/nourishment if farmers installed
    public static final DeferredItem<Item> FATAYER = ITEMS.register("fatayer", () -> new Item(new Item.Properties().food(durationEatProperties(8, 0.8F, 24))));
    public static final DeferredItem<Item> KNAFEH = ITEMS.register("knafeh", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationModifier(0.6f).build())));
    public static final DeferredItem<Item> MAQLUBA = ITEMS.register("maqluba", () -> new Item(new Item.Properties().craftRemainder(Items.BOWL).food(new FoodProperties.Builder().nutrition(14).saturationModifier(0.8f).build())));
    
    public static final DeferredItem<BlockItem> OLIVE_LOG = blockItem(PDBlocks.OLIVE_LOG);
    public static final DeferredItem<BlockItem> STRIPPED_OLIVE_LOG = blockItem(PDBlocks.STRIPPED_OLIVE_LOG);
    public static final DeferredItem<BlockItem> OLIVE_WOOD = blockItem(PDBlocks.OLIVE_WOOD);
    public static final DeferredItem<BlockItem> STRIPPED_OLIVE_WOOD = blockItem(PDBlocks.STRIPPED_OLIVE_WOOD);
    public static final DeferredItem<BlockItem> OLIVE_PLANKS = blockItem(PDBlocks.OLIVE_PLANKS);
    public static final DeferredItem<BlockItem> ORNATE_OLIVE_PLANKS = blockItem(PDBlocks.ORNATE_OLIVE_PLANKS);
    public static final DeferredItem<BlockItem> OLIVE_LEAVES = blockItem(PDBlocks.OLIVE_LEAVES);
    public static final DeferredItem<BlockItem> OLIVE_SAPLING = blockItem(PDBlocks.OLIVE_SAPLING);
    public static final DeferredItem<BlockItem> SUMAC = blockItem(PDBlocks.SUMAC);
    
    public static DeferredItem<BlockItem> blockItem(DeferredBlock<? extends Block> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }
    
    public static FoodProperties durationEatProperties(int nutrition, float saturation, float eatDuration)
    {
        return new FoodProperties(nutrition, saturation, false, eatDuration, Optional.empty(), List.of());
    }
}
