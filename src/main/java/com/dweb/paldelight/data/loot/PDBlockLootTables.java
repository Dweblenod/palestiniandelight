package com.dweb.paldelight.data.loot;

import com.dweb.paldelight.PalDelight;
import com.dweb.paldelight.block.OliveLeaves;
import com.dweb.paldelight.item.PDItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Map;
import java.util.Set;

import static com.dweb.paldelight.block.PDBlocks.*;

public class PDBlockLootTables extends BlockLootSubProvider {
    private static final float[] OLIVE_LEAVES_RESOURCES_CHANCES = new float[]{0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F}; //copy of NORMAL_LEAVES_STICK_CHANCES
    
    PDBlockLootTables(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }
    
    @Override
    protected void generate() {
        dropSelf(OLIVE_LOG.get());
        dropSelf(STRIPPED_OLIVE_LOG.get());
        dropSelf(OLIVE_WOOD.get());
        dropSelf(STRIPPED_OLIVE_WOOD.get());
        dropSelf(OLIVE_PLANKS.get());
        dropSelf(ORNATE_OLIVE_PLANKS.get());
        add(OLIVE_LEAVES.get(), (block) -> createOliveLeavesDrops(block, OLIVE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        dropSelf(OLIVE_SAPLING.get());
        
        dropWhenSilkTouch(SUMAC.get()); //TODO perhaps use best tool instead of silk touch
    }
    
    protected LootTable.Builder createOliveLeavesDrops(Block leavesBlock, Block saplingBlock, float... chances) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        Holder<Enchantment> fortuneHolder = registrylookup.getOrThrow(Enchantments.FORTUNE);
        LootItemCondition.Builder isFruitingBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(OLIVE_LEAVES.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OliveLeaves.FRUITING, true));
        
        return createSilkTouchOrShearsDispatchTable(leavesBlock, applyExplosionCondition(leavesBlock, LootItem.lootTableItem(saplingBlock))
                .when(BonusLevelTableCondition.bonusLevelFlatChance(fortuneHolder, chances)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_SHEARS.or(this.hasSilkTouch()).invert())
                        .add(applyExplosionDecay(leavesBlock, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).when(BonusLevelTableCondition.bonusLevelFlatChance(fortuneHolder, OLIVE_LEAVES_RESOURCES_CHANCES)))
                )
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(isFruitingBuilder)
                        .add(applyExplosionDecay(leavesBlock, LootItem.lootTableItem(PDItems.OLIVE.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))).when(BonusLevelTableCondition.bonusLevelFlatChance(fortuneHolder, OLIVE_LEAVES_RESOURCES_CHANCES)))
                );
    }
    
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.entrySet().stream()
                .filter(entry -> entry.getKey().location().getNamespace().equals(PalDelight.MOD_ID))
                .map(Map.Entry::getValue).toList();
    }
}
