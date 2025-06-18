package com.dweb.paldelight.data;

import com.dweb.paldelight.PalDelight;
import com.dweb.paldelight.block.OliveLeaves;
import com.dweb.paldelight.item.PDItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

import static com.dweb.paldelight.block.PDBlocks.*;

public class PDBlockStateProvider extends BlockStateProvider {
    public PDBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PalDelight.MOD_ID, exFileHelper);
    }
    
    @Override
    protected void registerStatesAndModels() {
        log(OLIVE_LOG);
        log(STRIPPED_OLIVE_LOG);
        wood(OLIVE_WOOD, OLIVE_LOG);
        wood(STRIPPED_OLIVE_WOOD, STRIPPED_OLIVE_LOG);
        simpleBlockWithItem(OLIVE_PLANKS.get());
        stairsWithItem(OLIVE_STAIRS.get(), "olive", textureLocation("olive_planks"));
        slabWithItem(OLIVE_SLAB.get(), "olive", "olive_planks");
        simpleBlockWithItem(ORNATE_OLIVE_PLANKS.get());
        {
            ModelFile nonFruiting = cubeAll("olive_leaves");
            ModelFile fruiting = cubeAll("olive_leaves_fruiting");
            getVariantBuilder(OLIVE_LEAVES.get())
                    .partialState().with(OliveLeaves.FRUITING, true).modelForState().modelFile(fruiting).addModel()
                    .partialState().with(OliveLeaves.FRUITING, false).modelForState().modelFile(nonFruiting).addModel();
            simpleBlockItem(OLIVE_LEAVES.get(), nonFruiting);
        }
        flatItem(PDItems.OLIVE_SAPLING, PDBlockStateProvider::textureLocation);
        
        flatItem(PDItems.SUMAC, id -> textureLocation(id.withSuffix("_top_fruiting")));
    }
    
    public ModelFile cubeAll(String name) {
        return models().cubeAll(name, textureLocation(name));
    }
    
    public void flatItem(DeferredItem<? extends BlockItem> item, Function<ResourceLocation, ResourceLocation> textureProvider) {
        itemModels().withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                textureProvider.apply(item.getId()));
    }
    
    public void log(DeferredBlock<Block> log) {
        rotatedPillarPlusItem(log, blockId -> models().cubeColumn(blockId.getPath(), textureLocation(blockId), textureLocation(blockId).withSuffix("_top")));
    }
    
    public void wood(DeferredBlock<Block> wood, DeferredBlock<Block> log) {
        rotatedPillarPlusItem(wood, blockId -> models().cubeColumn(blockId.getPath(), textureLocation(log.getId()), textureLocation(log.getId())));
    }
    
    public void simpleBlockWithItem(Block block) {
        simpleBlock(block, cubeAll(block));
        simpleBlockItem(block, cubeAll(block));
    }
    
    public void simpleBlock(DeferredBlock<? extends Block> block, Function<ResourceLocation, ModelFile> modelProvider) {
        simpleBlock(block.get(), modelProvider.apply(block.getId()));
    }
    
    void stairsWithItem(Block block, String baseName, ResourceLocation texture)
    {
        ModelFile stairs = models().stairs(baseName + "_stairs", texture, texture, texture);
        ModelFile stairsInner = models().stairsInner(baseName + "_inner_stairs", texture, texture, texture);
        ModelFile stairsOuter = models().stairsOuter(baseName + "_outer_stairs", texture, texture, texture);
        stairsBlock((StairBlock) block, stairs, stairsInner, stairsOuter);
        simpleBlockItem(block, stairs);
    }
    
    void slabWithItem(Block block, String baseName, String textureName)
    {
        ResourceLocation texture = textureLocation(textureName);
        ModelFile slabBottom = models().slab(baseName + "_slab", texture, texture, texture);
        ModelFile slabTop = models().slabTop(baseName + "_slab_top", texture, texture, texture);
        ModelFile doubleSlab = models().getExistingFile(PalDelight.id(textureName));
        slabBlock((SlabBlock) block, slabBottom, slabTop, doubleSlab);
        simpleBlockItem(block, slabBottom);
    }
    
    @NotNull
    private static ResourceLocation textureLocation(ResourceLocation blockId) {
        return blockId.withPrefix(ModelProvider.BLOCK_FOLDER + "/");
    }
    
    @NotNull
    private static ResourceLocation textureLocation(String blockName) {
        return PalDelight.id(ModelProvider.BLOCK_FOLDER + "/" + blockName);
    }
    
    public void rotatedPillarPlusItem(DeferredBlock<Block> block, Function<ResourceLocation, ModelFile> modelProvider) {
        ModelFile model = modelProvider.apply(block.getId());
        axisBlock((RotatedPillarBlock) block.get(), model, model);
        simpleBlockItem(block.get(), model);
    }
}
