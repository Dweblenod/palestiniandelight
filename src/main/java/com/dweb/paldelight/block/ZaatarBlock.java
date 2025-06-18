package com.dweb.paldelight.block;

import com.dweb.paldelight.item.PDItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

/*@MethodsReturnNonnullByDefault
public class ZaatarBlock extends BushBlock implements BonemealableBlock {
    public static final MapCodec<ZaatarBlock> CODEC = simpleCodec(ZaatarBlock::new);
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    
    public ZaatarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(BlockStateProperties.AGE_3, 0));
    }
    
    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }
    
    @SuppressWarnings("deprecation")
    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);
        
        if (level.isClientSide)
            return;
        
        if (level.random.nextFloat() <= 0.075F && state.getValue(AGE) != 3)
            level.setBlock(pos, state.setValue(AGE, state.getValue(AGE) + 1), UPDATE_ALL);
    }
    
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (state.getValue(AGE) == 3) {
            popResource(level, pos, new ItemStack(PDItems.ZAATAR_SPRIG.get()));
            
            level.playSound(null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 1.0F);
            
            level.setBlock(pos, state.setValue(AGE, 0), UPDATE_ALL);
            
            return InteractionResult.SUCCESS;
        }
        
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }
    
    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return blockState.getValue(AGE) != 3; //only if not fully grown
    }
    
    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }
    
    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        popResource(level, pos, new ItemStack(this));
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(AGE);
    }
}*/
