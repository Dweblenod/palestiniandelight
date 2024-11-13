package com.dweb.paldelight.data.loot;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public final class PDLootTableProvider {
    public static LootTableProvider providers(PackOutput output) {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(PDBlockLootTables::new, LootContextParamSets.BLOCK))
        );
    }
}
