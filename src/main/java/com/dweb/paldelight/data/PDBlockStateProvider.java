package com.dweb.paldelight.data;

import com.dweb.paldelight.PalDelight;
import com.dweb.paldelight.item.PDItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

import static com.dweb.paldelight.block.PDBlocks.OLIVE_LOG;
import static com.dweb.paldelight.block.PDBlocks.STRIPPED_OLIVE_LOG;

public class PDBlockStateProvider extends BlockStateProvider {
    public PDBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PalDelight.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        log(OLIVE_LOG);
        log(STRIPPED_OLIVE_LOG);

        //simpleBlock(PDBlocks.SUMAC, id -> models().cross(id.getPath(), textureLocation(id)).renderType("cutout"));
        //simpleBlock(PDBlocks.SUMAC, id -> models().cross(id.getLocation().getPath(), textureLocation(id.getLocation())).renderType("cutout"));
        flatItem(PDItems.SUMAC, id -> textureLocation(id.withSuffix("_top_fruiting")));
    }

    public void flatItem(RegistryObject<? extends BlockItem> item, Function<ResourceLocation, ResourceLocation> textureProvider)
    {
        itemModels().withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                textureProvider.apply(item.getId()));
    }

    public void log(RegistryObject<Block> block) {
        rotatedPillarPlusItem(block, blockId -> models().cubeColumn(blockId.getPath(), textureLocation(blockId), textureLocation(blockId).withSuffix("_top")));
    }

    @NotNull
    private static ResourceLocation textureLocation(ResourceLocation blockId) {
        return blockId.withPrefix(ModelProvider.BLOCK_FOLDER + "/");
    }

    public void rotatedPillarPlusItem(RegistryObject<Block> block, Function<ResourceLocation, ModelFile> modelProvider) {
        ModelFile model = modelProvider.apply(block.getId());
        axisBlock((RotatedPillarBlock) block.get(), model, model);
        simpleBlockItem(block.get(), model);
    }
}
