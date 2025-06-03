package com.dweb.paldelight.world.gen;

import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public final class OliveTreeGrower {
    
    public static final TreeGrower OLIVE_TREE_GROWER = new TreeGrower(
            "olive",
            0.1F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(PDConfiguredFeatures.OLIVE_TREE),
            Optional.of(PDConfiguredFeatures.FANCY_OLIVE_TREE),
            Optional.of(PDConfiguredFeatures.OLIVE_TREE_BEES),
            Optional.of(PDConfiguredFeatures.FANCY_OLIVE_TREE_BEES)
    );
   
   
   /*protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean hasFlowers) {
      if (random.nextInt(10) == 0) {
         return hasFlowers ? PDConfiguredFeatures.FANCY_OLIVE_TREE_BEES : PDConfiguredFeatures.FANCY_OLIVE_TREE;
      } else {
         return hasFlowers ? PDConfiguredFeatures.OLIVE_TREE_BEES : PDConfiguredFeatures.OLIVE_TREE;
      }
   }*/
}