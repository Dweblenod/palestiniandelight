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
        //based off ConsumableItem in FD
        ItemStack containerStack = stack.getCraftingRemainingItem();
        
        if (stack.getFoodProperties(entity) != null) {
            super.finishUsingItem(stack, level, entity);
        } else {
            if (entity instanceof ServerPlayer player) {
                CriteriaTriggers.CONSUME_ITEM.trigger(player, stack);
                player.awardStat(Stats.ITEM_USED.get(this));
                
                if (!player.isCreative())
                    stack.shrink(1);
            }
        }
        
        if (stack.isEmpty()) {
            return containerStack;
        } else {
            if (entity instanceof Player player) {
                if (!player.isCreative() && !player.getInventory().add(containerStack)) {
                    player.drop(containerStack, false); //throw on ground if cant add to inventory
                }
            }
            
            return stack;
        }
    }
}
