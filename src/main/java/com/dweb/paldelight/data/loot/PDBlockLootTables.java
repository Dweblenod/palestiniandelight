package com.dweb.paldelight.data.loot;

import com.dweb.paldelight.PalDelight;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Map;
import java.util.Set;

import static com.dweb.paldelight.block.PDBlocks.*;

public class PDBlockLootTables extends BlockLootSubProvider {
    private static final float[] OLIVE_LEAVES_STICK_CHANCES = new float[]{0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F}; //copy of NORMAL_LEAVES_STICK_CHANCES

    PDBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(OLIVE_LOG.get());
        dropSelf(STRIPPED_OLIVE_LOG.get());
        add(OLIVE_LEAVES.get(), (block) -> createOliveLeavesDrops(block, OLIVE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        dropSelf(OLIVE_SAPLING.get());

        dropWhenSilkTouch(SUMAC.get()); //TODO perhaps use best tool instead of silk touch
    }

    protected LootTable.Builder createOliveLeavesDrops(Block leavesBlock, Block saplingBlock, float... chances) {
        return createSilkTouchOrShearsDispatchTable(leavesBlock, applyExplosionCondition(leavesBlock, LootItem.lootTableItem(saplingBlock))
                .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, chances))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .when(HAS_SHEARS.or(HAS_SILK_TOUCH).invert())
                .add(applyExplosionDecay(leavesBlock, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, OLIVE_LEAVES_STICK_CHANCES)))
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.entrySet().stream()
                .filter(entry -> entry.getKey().location().getNamespace().equals(PalDelight.MOD_ID))
                .map(Map.Entry::getValue).toList();
    }
}
