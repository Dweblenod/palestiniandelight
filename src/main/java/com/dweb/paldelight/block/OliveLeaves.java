package com.dweb.paldelight.block;

import com.dweb.paldelight.item.PDItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class OliveLeaves extends LeavesBlock {
    public static final BooleanProperty FRUITING = PDBlocks.FRUITING;
    //public static final IntegerProperty AGE = BlockStateProperties.AGE_25;

    public OliveLeaves(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FRUITING, false).setValue(DISTANCE, 7).setValue(PERSISTENT, false).setValue(WATERLOGGED, false));
        //this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(FRUITING, false));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if(level.random.nextFloat() <= 0.025F && airExposed(level, pos))
            level.setBlock(pos, state.setValue(FRUITING, true), UPDATE_ALL);

        super.randomTick(state, level, pos, random);
    }
    
    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return !state.getValue(PERSISTENT);
    }
    
    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(state.getValue(FRUITING))
        {
            popResource(level, pos, new ItemStack(PDItems.OLIVE.get()));

            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 0.5F, 1.3F);

            level.setBlock(pos, state.setValue(FRUITING, false), UPDATE_ALL);

            return InteractionResult.SUCCESS;
        }

        return super.use(state, level, pos, player, hand, hit);
    }

    /*@SuppressWarnings("deprecation")
    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if(state.getValue(PERSISTENT) || !airExposed(level, pos))
        {
            super.randomTick(state, level, pos, random);
            return;
        }

        if(level.random.nextFloat() <= 0.25F)
        {
            int startingAge = state.getValue(AGE);
            boolean increaseAge = startingAge < 25;
            boolean makeFruiting = startingAge >= 15;

            //will reset age to 0 if at the end
            //level.setBlock(pos, state.setValue(AGE, increaseAge ? startingAge + 1 : 0).setValue(FRUITING, makeFruiting), UPDATE_ALL);
        } else if(state.getValue(FRUITING))
        {
            level.setBlock(pos, state.setValue(FRUITING, false), UPDATE_ALL);

        }

        super.randomTick(state, level, pos, random);
    }*/

    public boolean airExposed(Level level, BlockPos pos)
    {
        for(Direction direction : Direction.values())
        {
            if(direction.equals(Direction.UP))
                continue; //dont want to drop olives upwards
            
            BlockState iterateState = level.getBlockState(pos.relative(direction));
            if(iterateState.isAir() || iterateState.canBeReplaced())
                return true;
        }

        return false;
    }
    
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction face)
    {
        return 5;
    }
    
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction face)
    {
        return 5;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FRUITING);
    }
}
