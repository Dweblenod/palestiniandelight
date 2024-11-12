package com.dweb.paldelight.item;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SumacBerriesItem extends Item {

    public SumacBerriesItem(Properties properties) {
        super(properties.food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).alwaysEat().fast().build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide() && 0.15 >= level.random.nextFloat()) {
            entity.removeEffect(MobEffects.POISON);
        }


        return super.finishUsingItem(stack, level, entity);
    }
}
