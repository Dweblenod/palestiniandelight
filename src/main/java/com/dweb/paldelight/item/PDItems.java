package com.dweb.paldelight.item;

import com.dweb.paldelight.PalDelight;
import com.dweb.paldelight.block.PDBlocks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PDItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PalDelight.MOD_ID);

    public static final RegistryObject<Item> OLIVE = ITEMS.register("olive", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().fast().nutrition(1).saturationMod(0.2f).build())));
    public static final RegistryObject<Item> SUMAC_BERRIES = ITEMS.register("sumac_berries", () -> new SumacBerriesItem(new Item.Properties()));

    public static final RegistryObject<Item> FATAYER = ITEMS.register("fatayer", () -> new CustomFoodDurationItem(24, new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(3f).build())));
    public static final RegistryObject<Item> KNAFEH = ITEMS.register("knafeh", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(8f).build())));

    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(PDBlocks.EXAMPLE_BLOCK.get(), new Item.Properties()));
}