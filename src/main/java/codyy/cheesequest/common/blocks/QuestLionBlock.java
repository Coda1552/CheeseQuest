package codyy.cheesequest.common.blocks;

import codyy.cheesequest.CheeseQuest;
import codyy.cheesequest.client.screen.QuestLionScreen;
import codyy.cheesequest.common.blocks.entities.QuestLionBlockEntity;
import codyy.cheesequest.common.entities.QuestLionEntity;
import codyy.cheesequest.registry.ModBlockEntities;
import codyy.cheesequest.registry.ModEntities;
import dev.zanckor.example.client.event.StartDialog;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.EnchantmentTableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import oshi.hardware.SoundCard;

import java.io.IOException;

public class QuestLionBlock extends BaseEntityBlock {
    private final String dialogId = "cheesequest.collect_items_dialog";

    public QuestLionBlock(Properties builder) {
        super(builder);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.QUEST_LION.get().create(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack stack = pPlayer.getItemInHand(pHand);

        if (stack.isEmpty() && pLevel.isClientSide) {
            //Minecraft.getInstance().setScreen(new QuestLionScreen(Component.translatable("blockentity." + CheeseQuest.MOD_ID + ".quest_lion")));
            pLevel.playSound(pPlayer, pPos, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);

            QuestLionEntity lion = ModEntities.QUEST_LION.get().create(pPlayer.level());

            try {
                StartDialog.loadDialog(pPlayer, dialogId, lion);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return InteractionResult.CONSUME;
        }
        else {
            return InteractionResult.SUCCESS;
        }
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide ? createTickerHelper(pBlockEntityType, ModBlockEntities.QUEST_LION.get(), QuestLionBlockEntity::animationTick) : null;
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return 10;
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.125, 0.1875, 0.125, 0.875, 0.9375, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.25, 0.75, 0.1875, 0.75), BooleanOp.OR);

        return shape;
    }

}
