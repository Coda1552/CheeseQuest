package codyy.cheesequest.client.render;

import codyy.cheesequest.CheeseQuest;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class LionHeadRenderer extends EntityRenderer<Entity> {
    private static final ResourceLocation LOCATION = new ResourceLocation(CheeseQuest.MOD_ID, "textures/entity/quest_lion.png");

    public LionHeadRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(Entity pEntity) {
        return LOCATION;
    }
}
