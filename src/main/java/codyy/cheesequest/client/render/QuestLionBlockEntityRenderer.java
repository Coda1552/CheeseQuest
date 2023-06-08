package codyy.cheesequest.client.render;

import codyy.cheesequest.CheeseQuest;
import codyy.cheesequest.client.ModModelLayers;
import codyy.cheesequest.client.model.LionHeadModel;
import codyy.cheesequest.common.blocks.entities.QuestLionBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.Model;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class QuestLionBlockEntityRenderer implements BlockEntityRenderer<QuestLionBlockEntity> {
    private static final ResourceLocation LOC = new ResourceLocation(CheeseQuest.MOD_ID, "textures/entity/quest_lion.png");
    private final LionHeadModel headModel;

    public QuestLionBlockEntityRenderer(BlockEntityRendererProvider.Context pContext) {
        this.headModel = new LionHeadModel(pContext.bakeLayer(ModModelLayers.LION_HEAD));
    }

    @Override
    public void render(QuestLionBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        int lightLevel = getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos().above());
        float f = (float)pBlockEntity.time + pPartialTick;

        pPoseStack.pushPose();

        pPoseStack.scale(0.5F, 0.5F, 0.5F);
        pPoseStack.translate(1.0D, (Mth.sin(f * 0.1F) * 0.1F) - 0.15F, 1.0D);

        float f1;
        for (f1 = pBlockEntity.rot - pBlockEntity.oRot; f1 >= (float)Math.PI; f1 -= ((float)Math.PI * 2F)) {
        }

        while (f1 < -(float)Math.PI) {
            f1 += ((float)Math.PI * 2F);
        }

        float f2 = pBlockEntity.oRot + f1 * pPartialTick;
        pPoseStack.mulPose(Axis.YP.rotation(-f2 - 1.5708F));

        getModel(headModel, LOC, pPoseStack, pBufferSource, lightLevel, pPackedOverlay);

        pPoseStack.popPose();
    }

    private void getModel(Model model, ResourceLocation loc, PoseStack matrixStack, MultiBufferSource bufferIn, int lightLevel, int combinedOverlayIn) {
        VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderType.entityCutout(loc));
        model.renderToBuffer(matrixStack, ivertexbuilder, lightLevel, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    private int getLightLevel(Level world, BlockPos pos) {
        int blockLight = world.getBrightness(LightLayer.BLOCK, pos);
        int skyLight = world.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(blockLight, skyLight);
    }
}