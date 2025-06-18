package com.dweb.paldelight.item;

import com.dweb.paldelight.PalDelight;
import com.dweb.paldelight.block.PDBlocks;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodConstants;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.registry.ModEffects;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PDItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PalDelight.MOD_ID);
    
    private static final float DEFAULT_EAT_SECONDS = 1.6F;
    
    public static final DeferredItem<Item> OLIVE = ITEMS.register("olive", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().fast().nutrition(1).saturationModifier(0.2f).build())));
    //TODO olive_pomace, consider adding it to compost datamap
    public static final DeferredItem<Item> OLIVE_OIL = ITEMS.register("olive_oil", () -> new BottledDrinkItem(SoundEvents.HONEY_DRINK, new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.5f).build())));
    public static final DeferredItem<Item> SUMAC_BERRIES = ITEMS.register("sumac_berries", () -> new SumacBerriesItem(new Item.Properties()));
    //public static final DeferredItem<Item> ZAATAR_SPRIG = ITEMS.register("zaatar_sprig", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ROSE_WATER = ITEMS.register("rose_water", () -> new BottledDrinkItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.1f).build())));
    
    public static final DeferredItem<Item> FATAYER = ITEMS.register("fatayer", () -> new Item(new Item.Properties().food(eatProperties(8, 0.8F, 1.2F, ModEffects.COMFORT, FoodValues.SHORT_DURATION))));
    public static final DeferredItem<Item> KNAFEH = ITEMS.register("knafeh", () -> new Item(new Item.Properties().food(eatProperties(7, 0.6F, ModEffects.COMFORT, FoodValues.SHORT_DURATION))));
    public static final DeferredItem<Item> MAQLUBA = ITEMS.register("maqluba", () -> new Item(new Item.Properties().craftRemainder(Items.BOWL).food(eatProperties(14, 0.8F, ModEffects.NOURISHMENT, FoodValues.LONG_DURATION))));
    
    public static final DeferredItem<BlockItem> OLIVE_LOG = blockItem(PDBlocks.OLIVE_LOG);
    public static final DeferredItem<BlockItem> STRIPPED_OLIVE_LOG = blockItem(PDBlocks.STRIPPED_OLIVE_LOG);
    public static final DeferredItem<BlockItem> OLIVE_WOOD = blockItem(PDBlocks.OLIVE_WOOD);
    public static final DeferredItem<BlockItem> STRIPPED_OLIVE_WOOD = blockItem(PDBlocks.STRIPPED_OLIVE_WOOD);
    public static final DeferredItem<BlockItem> OLIVE_PLANKS = blockItem(PDBlocks.OLIVE_PLANKS);
    public static final DeferredItem<BlockItem> OLIVE_STAIRS = blockItem(PDBlocks.OLIVE_STAIRS);
    public static final DeferredItem<BlockItem> OLIVE_SLAB = blockItem(PDBlocks.OLIVE_SLAB);
    public static final DeferredItem<BlockItem> ORNATE_OLIVE_PLANKS = blockItem(PDBlocks.ORNATE_OLIVE_PLANKS);
    public static final DeferredItem<BlockItem> OLIVE_LEAVES = blockItem(PDBlocks.OLIVE_LEAVES);
    public static final DeferredItem<BlockItem> OLIVE_SAPLING = blockItem(PDBlocks.OLIVE_SAPLING);
    public static final DeferredItem<BlockItem> SUMAC = blockItem(PDBlocks.SUMAC);
    //public static final DeferredItem<BlockItem> ZAATAR = blockItem(PDBlocks.ZAATAR);
    
    public static DeferredItem<BlockItem> blockItem(DeferredBlock<? extends Block> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }
    
    public static FoodProperties eatProperties(int nutrition, float saturationMod, Holder<MobEffect> effectHolder, int effectDuration) {
        return eatProperties(nutrition, saturationMod, DEFAULT_EAT_SECONDS, effectHolder, effectDuration);
    }
    
    public static FoodProperties eatProperties(int nutrition, float saturationMod, float eatDuration, Holder<MobEffect> effectHolder, int effectDuration) {
        MobEffect effect = effectHolder.value();
        
        if (!ModList.get().isLoaded(FarmersDelight.MODID)) {
            List<MobEffect> farmersEffects = ModEffects.EFFECTS.getRegistry().get().stream().toList();
            
            if (farmersEffects.contains(effect))
                effect = null;
        }
        
        List<FoodProperties.PossibleEffect> possibleEffects = new ArrayList<>();
        
        if (effect != null)
            possibleEffects.add(new FoodProperties.PossibleEffect(() -> new MobEffectInstance(effectHolder, effectDuration, 0, false, false), 1.0F));
        
        float saturation = FoodConstants.saturationByModifier(nutrition, saturationMod);
        return new FoodProperties(nutrition, saturation, false, eatDuration, Optional.empty(), possibleEffects);
    }
}
