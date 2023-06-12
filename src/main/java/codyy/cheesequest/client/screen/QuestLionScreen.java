package codyy.cheesequest.client.screen;

import codyy.cheesequest.common.blocks.entities.QuestLionBlockEntity;
import codyy.cheesequest.common.entities.QuestLionEntity;
import codyy.cheesequest.registry.ModEntities;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

import javax.annotation.Nullable;

public class QuestLionScreen extends Screen {
    private float xMouse;
    private float yMouse;
    private int leftPos;
    private int topPos;

    public QuestLionScreen(Component pTitle) {
        super(pTitle);
    }


    @Override
    public void render(GuiGraphics pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        int i = this.leftPos;
        int j = this.topPos;

        renderDarkBg(pPoseStack);

        QuestLionEntity lion = ModEntities.QUEST_LION.get().create(minecraft.player.level());

        renderEntityInInventoryFollowsCursor(pPoseStack, i - 200, j + 120, 100, (float)(i - 200) - this.xMouse, (float)j - this.yMouse, lion);

        this.xMouse = (float)pMouseX;
        this.yMouse = (float)pMouseY;


    }

    private void renderDarkBg(GuiGraphics stack) {
        if (this.minecraft.level != null) {
            stack.fillGradient(0, 0, this.width, this.height, -1072689136, -804253680);
            MinecraftForge.EVENT_BUS.post(new ScreenEvent.BackgroundRendered(this, stack));
        } else {
            RenderSystem.setShaderColor(0.25F, 0.25F, 0.25F, 1.0F);
        }
    }

    public static void renderEntityInInventoryFollowsCursor(GuiGraphics p_275396_, int p_275688_, int p_275245_, int p_275535_, float p_275604_, float p_275546_, LivingEntity p_275689_) {
        float f = (float)Math.atan(p_275604_ / 40.0F);
        float f1 = (float)Math.atan(p_275546_ / 40.0F);
        renderEntityInInventoryFollowsAngle(p_275396_, p_275688_, p_275245_, p_275535_, f, f1, p_275689_);
    }

    public static void renderEntityInInventoryFollowsAngle(GuiGraphics p_275396_, int p_275688_, int p_275245_, int p_275535_, float angleXComponent, float angleYComponent, LivingEntity p_275689_) {
        Quaternionf quaternionf = (new Quaternionf()).rotateZ((float)Math.PI);
        Quaternionf quaternionf1 = (new Quaternionf()).rotateX(angleYComponent * 20.0F * ((float)Math.PI / 180F));
        quaternionf.mul(quaternionf1);
        float f2 = p_275689_.yBodyRot;
        float f3 = p_275689_.getYRot();
        float f4 = p_275689_.getXRot();
        float f5 = p_275689_.yHeadRotO;
        float f6 = p_275689_.yHeadRot;
        p_275689_.yBodyRot = 180.0F + angleXComponent * 20.0F;
        p_275689_.setYRot(180.0F + angleXComponent * 40.0F);
        p_275689_.setXRot(-angleYComponent * 20.0F);
        p_275689_.yHeadRot = p_275689_.getYRot();
        p_275689_.yHeadRotO = p_275689_.getYRot();
        renderEntity(p_275396_, p_275688_, p_275245_, p_275535_, quaternionf, quaternionf1, p_275689_);
        p_275689_.yBodyRot = f2;
        p_275689_.setYRot(f3);
        p_275689_.setXRot(f4);
        p_275689_.yHeadRotO = f5;
        p_275689_.yHeadRot = f6;
    }

    public static void renderEntity(GuiGraphics p_275613_, int p_275470_, int p_275319_, int p_275605_, Quaternionf p_275229_, @Nullable Quaternionf p_275230_, LivingEntity p_275237_) {
        PoseStack posestack = p_275613_.pose();
        posestack.pushPose();
        posestack.translate(0.0D, 0.0D, 1000.0D);
        RenderSystem.applyModelViewMatrix();
        posestack.pushPose();
        posestack.translate(p_275470_, p_275319_, -950.0D);
        posestack.mulPoseMatrix((new Matrix4f()).scaling((float)p_275605_, (float)p_275605_, (float)(-p_275605_)));
        posestack.mulPose(p_275229_);
        Lighting.setupForEntityInInventory();
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        if (p_275230_ != null) {
            p_275230_.conjugate();
            entityrenderdispatcher.overrideCameraOrientation(p_275230_);
        }

        entityrenderdispatcher.setRenderShadow(false);
        MultiBufferSource.BufferSource multibuffersource$buffersource = Minecraft.getInstance().renderBuffers().bufferSource();
        RenderSystem.runAsFancy(() -> {
            entityrenderdispatcher.render(p_275237_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, posestack, multibuffersource$buffersource, 15728880);
        });
        multibuffersource$buffersource.endBatch();
        entityrenderdispatcher.setRenderShadow(true);
        posestack.popPose();
        Lighting.setupFor3DItems();
        posestack.popPose();
        RenderSystem.applyModelViewMatrix();
    }

    @Override
    protected void init() {
        minecraft = Minecraft.getInstance();
        this.leftPos = this.width / 2;
        this.topPos = this.height / 2;
    }

    @Override
    public void tick() {
    }
}
