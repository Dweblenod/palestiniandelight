package com.dweb.paldelight.item;

import com.dweb.paldelight.PalDelight;
import com.dweb.paldelight.block.PDBlocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PDItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PalDelight.MOD_ID);

    public static final RegistryObject<Item> OLIVE = ITEMS.register("olive", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().fast().nutrition(1).saturationMod(0.2f).build())));
    //TODO olive_pomace, consider adding it to compost datamap
    public static final RegistryObject<Item> OLIVE_OIL = ITEMS.register("olive_oil", () -> new BottledDrinkItem(SoundEvents.HONEY_DRINK, new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(1f).build())));
    public static final RegistryObject<Item> SUMAC_BERRIES = ITEMS.register("sumac_berries", () -> new SumacBerriesItem(new Item.Properties()));
    public static final RegistryObject<Item> ROSE_WATER = ITEMS.register("rose_water", () -> new BottledDrinkItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).build())));

    public static final RegistryObject<Item> FATAYER = ITEMS.register("fatayer", () -> new CustomFoodDurationItem(24, new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(1.5f).build())));
    public static final RegistryObject<Item> KNAFEH = ITEMS.register("knafeh", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(3f).build())));

    //public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(PDBlocks.EXAMPLE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> OLIVE_LOG = blockItem(PDBlocks.OLIVE_LOG);
    public static final RegistryObject<BlockItem> STRIPPED_OLIVE_LOG = blockItem(PDBlocks.STRIPPED_OLIVE_LOG);

    public static RegistryObject<BlockItem> blockItem(RegistryObject<? extends Block> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
