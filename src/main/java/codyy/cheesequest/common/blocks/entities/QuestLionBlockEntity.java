package codyy.cheesequest.common.blocks.entities;

import codyy.cheesequest.CheeseQuest;
import codyy.cheesequest.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.util.Mth;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class QuestLionBlockEntity extends BlockEntity implements Nameable {
    public int time;
    public float rot;
    public float oRot;
    public float tRot;

    public QuestLionBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(ModBlockEntities.QUEST_LION.get(), p_155229_, p_155230_);
    }

    @Override
    public Component getName() {
        Component name = MutableComponent.create(new TranslatableContents("blockentity." + CheeseQuest.MOD_ID + ".quest_lion", null, TranslatableContents.NO_ARGS));

        return hasCustomName() ? getCustomName() : name;
    }

    public static void animationTick(Level pLevel, BlockPos pPos, BlockState pState, QuestLionBlockEntity pBlockEntity) {
        pBlockEntity.oRot = pBlockEntity.rot;
        Player player = pLevel.getNearestPlayer((double)pPos.getX() + 0.5D, (double)pPos.getY() + 0.5D, (double)pPos.getZ() + 0.5D, 3.0D, false);
        if (player != null) {
            double d0 = player.getX() - ((double)pPos.getX() + 0.5D);
            double d1 = player.getZ() - ((double)pPos.getZ() + 0.5D);
            pBlockEntity.tRot = (float) Mth.atan2(d1, d0);
        } else {
            pBlockEntity.tRot += 0.02F;
        }

        while(pBlockEntity.rot >= (float)Math.PI) {
            pBlockEntity.rot -= ((float)Math.PI * 2F);
        }

        while(pBlockEntity.rot < -(float)Math.PI) {
            pBlockEntity.rot += ((float)Math.PI * 2F);
        }

        while(pBlockEntity.tRot >= (float)Math.PI) {
            pBlockEntity.tRot -= ((float)Math.PI * 2F);
        }

        while(pBlockEntity.tRot < -(float)Math.PI) {
            pBlockEntity.tRot += ((float)Math.PI * 2F);
        }

        float f2;
        for(f2 = pBlockEntity.tRot - pBlockEntity.rot; f2 >= (float)Math.PI; f2 -= ((float)Math.PI * 2F)) {
        }

        while(f2 < -(float)Math.PI) {
            f2 += ((float)Math.PI * 2F);
        }

        pBlockEntity.rot += f2 * 0.4F;
        ++pBlockEntity.time;
    }

}
