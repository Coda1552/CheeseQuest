package codyy.cheesequest.client;

import codyy.cheesequest.CheeseQuest;
import codyy.cheesequest.client.model.LionHeadModel;
import codyy.cheesequest.client.render.QuestLionBlockEntityRenderer;
import codyy.cheesequest.registry.ModBlockEntities;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CheeseQuest.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {

    @SubscribeEvent
    public static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions e) {
        e.registerLayerDefinition(ModModelLayers.LION_HEAD, LionHeadModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerModelLayers(EntityRenderersEvent.RegisterRenderers e) {
        e.registerBlockEntityRenderer(ModBlockEntities.QUEST_LION.get(), QuestLionBlockEntityRenderer::new);
    }
}
