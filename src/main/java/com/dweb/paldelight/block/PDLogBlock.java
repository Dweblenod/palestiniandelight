package com.dweb.paldelight.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class PDLogBlock extends RotatedPillarBlock {
    private final Supplier<BlockState> strippedVariant;
    
    public PDLogBlock(Properties properties, Supplier<BlockState> strippedVariant) {
        super(properties);
        this.strippedVariant = strippedVariant;
    }
    
    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if(toolAction == ToolActions.AXE_STRIP && context.getItemInHand().canPerformAction(ToolActions.AXE_STRIP))
            return this.strippedVariant.get();
        else
            return super.getToolModifiedState(state, context, toolAction, simulate);
    }
    
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }
    
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }
}
