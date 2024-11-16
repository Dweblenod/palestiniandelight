package com.dweb.paldelight.data;

import com.dweb.paldelight.block.PDBlocks;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

import static com.dweb.paldelight.world.gen.PDConfiguredFeatures.*;

public final class PDConfiguredFeatureProvider {
    public static final BeehiveDecorator BEEHIVE_DECORATOR = new BeehiveDecorator(0.02F);
    public static final int OLIVE_TREE_TRIES = 2;

    public static void register(BootstapContext<ConfiguredFeature<?, ?>> context) {
        //HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        context.register(OLIVE_TREE, new ConfiguredFeature<>(Feature.TREE, createStraightBlobOliveTree(false, 4, 2, 0).build()));
        context.register(OLIVE_TREE_BEES, new ConfiguredFeature<>(Feature.TREE, createStraightBlobOliveTree(true, 4, 2, 0).build()));

        context.register(FANCY_OLIVE_TREE, new ConfiguredFeature<>(Feature.TREE, createFancyOliveTree(false, 5, 2, 2).build()));
        context.register(FANCY_OLIVE_TREE_BEES, new ConfiguredFeature<>(Feature.TREE, createFancyOliveTree(true, 5, 2, 2).build()));

        context.register(ANCIENT_OLIVE_TREE, new ConfiguredFeature<>(Feature.TREE, createAncientOliveTree(false, 5, 3, 3).build()));
        context.register(ANCIENT_OLIVE_TREE_BEES, new ConfiguredFeature<>(Feature.TREE, createAncientOliveTree(true, 5, 3, 3).build()));
    }

    private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobOliveTree(boolean bees, int baseHeight, int heightRandA, int heightRandB) {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(PDBlocks.OLIVE_LOG.get()),
                new StraightTrunkPlacer(baseHeight, heightRandA, heightRandB),
                BlockStateProvider.simple(PDBlocks.OLIVE_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(OLIVE_TREE_TRIES), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1))
                .dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT)).decorators(bees ? List.of(BEEHIVE_DECORATOR) : List.of()).forceDirt().ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createFancyOliveTree(boolean bees, int baseHeight, int heightRandA, int heightRandB) {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(PDBlocks.OLIVE_LOG.get()),
                new FancyTrunkPlacer(baseHeight, heightRandA, heightRandB),
                BlockStateProvider.simple(PDBlocks.OLIVE_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(OLIVE_TREE_TRIES), ConstantInt.of(4), 4),
                new TwoLayersFeatureSize(1, 0, 2, OptionalInt.of(4)))
                .dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT)).decorators(bees ? List.of(BEEHIVE_DECORATOR) : List.of()).forceDirt().ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createAncientOliveTree(boolean bees, int baseHeight, int heightRandA, int heightRandB) {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(PDBlocks.OLIVE_LOG.get()),
                new GiantTrunkPlacer(baseHeight, heightRandA, heightRandB),
                BlockStateProvider.simple(PDBlocks.OLIVE_LEAVES.get()),
                new FancyFoliagePlacer(ConstantInt.of(OLIVE_TREE_TRIES), ConstantInt.of(4), 5),
                new TwoLayersFeatureSize(1, 0, 2, OptionalInt.of(4)))
                .dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT)).decorators(bees ? List.of(BEEHIVE_DECORATOR) : List.of()).forceDirt();
    }
}
