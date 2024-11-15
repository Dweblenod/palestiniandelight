package com.dweb.paldelight.data.loot;

import com.dweb.paldelight.PalDelight;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.Set;

import static com.dweb.paldelight.block.PDBlocks.*;

public class PDBlockLootTables extends BlockLootSubProvider {

    PDBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        //add(PDBlocks.EXAMPLE_BLOCK.get());
        dropSelf(OLIVE_LOG.get());
        dropSelf(STRIPPED_OLIVE_LOG.get());
        dropWhenSilkTouch(SUMAC.get()); //TODO perhaps use best tool instead of silk touch
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.entrySet().stream()
                .filter(entry -> entry.getKey().location().getNamespace().equals(PalDelight.MOD_ID))
                .map(Map.Entry::getValue).toList();
    }
}
