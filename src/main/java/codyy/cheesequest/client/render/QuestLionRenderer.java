package codyy.cheesequest.client.render;

import codyy.cheesequest.CheeseQuest;
import codyy.cheesequest.client.ModModelLayers;
import codyy.cheesequest.client.model.LionHeadGuiModel;
import codyy.cheesequest.common.entities.QuestLionEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;

public class QuestLionRenderer extends LivingEntityRenderer<QuestLionEntity, LionHeadGuiModel> {
    private static final ResourceLocation COW_LOCATION = new ResourceLocation(CheeseQuest.MOD_ID, "textures/entity/quest_lion.png");

    public QuestLionRenderer(EntityRendererProvider.Context p_173956_) {
        super(p_173956_, new LionHeadGuiModel(p_173956_.bakeLayer(ModModelLayers.LION_HEAD_GUI)), 0.7F);
    }

    public ResourceLocation getTextureLocation(QuestLionEntity pEntity) {
        return COW_LOCATION;
    }
}