package com.dweb.paldelight.block;

import com.dweb.paldelight.PalDelight;
import com.dweb.paldelight.world.gen.OliveTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class PDBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(PalDelight.MOD_ID);
    
    //public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> OLIVE_LOG = BLOCKS.register("olive_log", () -> log(() -> PDBlocks.STRIPPED_OLIVE_LOG.get().defaultBlockState(), MapColor.COLOR_BROWN, MapColor.COLOR_BROWN));
    public static final DeferredBlock<Block> STRIPPED_OLIVE_LOG = BLOCKS.register("stripped_olive_log", () -> log(MapColor.COLOR_BROWN, MapColor.COLOR_BROWN));
    public static final DeferredBlock<Block> OLIVE_WOOD = BLOCKS.register("olive_wood", () -> log(() -> PDBlocks.STRIPPED_OLIVE_WOOD.get().defaultBlockState(), MapColor.COLOR_BROWN, MapColor.COLOR_BROWN));
    public static final DeferredBlock<Block> STRIPPED_OLIVE_WOOD = BLOCKS.register("stripped_olive_wood", () -> log(MapColor.COLOR_BROWN, MapColor.COLOR_BROWN));
    public static final DeferredBlock<Block> OLIVE_PLANKS = BLOCKS.register("olive_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final DeferredBlock<Block> ORNATE_OLIVE_PLANKS = BLOCKS.register("ornate_olive_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(OLIVE_PLANKS.get())));
    public static final DeferredBlock<Block> OLIVE_LEAVES = BLOCKS.register("olive_leaves", () -> new OliveLeaves(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isSuffocating(PDBlocks::never).isViewBlocking(PDBlocks::never).ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(PDBlocks::never)));
    public static final DeferredBlock<Block> OLIVE_SAPLING = BLOCKS.register("olive_sapling", () -> new SaplingBlock(OliveTreeGrower.OLIVE_TREE_GROWER, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    
    public static final DeferredBlock<Block> SUMAC = BLOCKS.register("sumac", () -> new SumacBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).sound(SoundType.GRASS).noCollission().offsetType(BlockBehaviour.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.DESTROY).randomTicks()));
    
    private static PDLogBlock log(Supplier<BlockState> strippedVariant, MapColor topMapColor, MapColor sideMapColor) {
        return new PDLogBlock(BlockBehaviour.Properties.of().mapColor(
                state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor
        ).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava(), strippedVariant);
    }
    
    private static RotatedPillarBlock log(MapColor topMapColor, MapColor sideMapColor) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(
                state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor
        ).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
    }
    
    private static boolean never(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }
    
    public static final BooleanProperty FRUITING = BooleanProperty.create("fruiting");
}
