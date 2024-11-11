package com.dweb.palestiniandelight.item;

import com.dweb.palestiniandelight.PalestinianDelight;
import com.dweb.palestiniandelight.block.PDBlocks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PDItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PalestinianDelight.MOD_ID);

    public static final RegistryObject<Item> FATAYER = ITEMS.register("fatayer", () -> new CustomFoodDurationItem(24, new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(3f).fast().build())));

    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(PDBlocks.EXAMPLE_BLOCK.get(), new Item.Properties()));
}
