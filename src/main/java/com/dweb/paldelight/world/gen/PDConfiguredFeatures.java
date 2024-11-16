package com.dweb.paldelight.world.gen;

import com.dweb.paldelight.PalDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class PDConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OLIVE_TREE = createKey("olive_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OLIVE_TREE_BEES = createKey("olive_tree_bees");

    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_OLIVE_TREE = createKey("fancy_olive_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FANCY_OLIVE_TREE_BEES = createKey("fancy_olive_tree_bees");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ANCIENT_OLIVE_TREE = createKey("ancient_olive_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ANCIENT_OLIVE_TREE_BEES = createKey("ancient_olive_tree_bees");

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, PalDelight.id(name));
    }
}
