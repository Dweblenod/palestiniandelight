package com.dweb.palestiniandelight.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CustomFoodDurationItem extends Item {
    private final int duration;

    public CustomFoodDurationItem(int duration, Properties properties) {
        super(properties);
        this.duration = duration;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return duration;
    }
}
