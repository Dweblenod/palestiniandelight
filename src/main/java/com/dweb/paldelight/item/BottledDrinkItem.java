package com.dweb.paldelight.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class BottledDrinkItem extends Item {
    private final SoundEvent drinkSound;

    public BottledDrinkItem(Properties properties) {
        super(properties);
        this.drinkSound = SoundEvents.GENERIC_DRINK;
    }

    public BottledDrinkItem(SoundEvent drinkSound, Properties properties) {
        super(properties);
        this.drinkSound = drinkSound;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return drinkSound;
    }

    @Override
    public SoundEvent getEatingSound() {
        return drinkSound;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        super.finishUsingItem(stack, level, entity);
        if (entity instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, stack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        //turns into an empty bottle if not a creative mode player
        if (entity instanceof Player player && !player.isCreative()) {
            ItemStack emptyBottle = new ItemStack(Items.GLASS_BOTTLE);

            if (stack.isEmpty())
                return emptyBottle;

            //throw on ground if cant add to inventory
            if (player.getInventory().add(emptyBottle))
                player.drop(emptyBottle, false);
        }
        return super.finishUsingItem(stack, level, entity);
    }
}
