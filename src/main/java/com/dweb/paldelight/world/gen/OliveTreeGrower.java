package com.dweb.paldelight.world.gen;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class OliveTreeGrower extends AbstractTreeGrower {

   protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean hasFlowers) {
      if (random.nextInt(10) == 0) {
         return hasFlowers ? PDConfiguredFeatures.FANCY_OLIVE_TREE_BEES : PDConfiguredFeatures.FANCY_OLIVE_TREE;
      } else {
         return hasFlowers ? PDConfiguredFeatures.OLIVE_TREE_BEES : PDConfiguredFeatures.OLIVE_TREE;
      }
   }
}