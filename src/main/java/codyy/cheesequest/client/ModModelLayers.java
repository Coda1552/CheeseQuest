package codyy.cheesequest.client;

import codyy.cheesequest.CheeseQuest;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation LION_HEAD = create("lionhead");
    public static final ModelLayerLocation LION_HEAD_GUI = create("lionhead_gui");

    private static ModelLayerLocation create(String name) {
        return new ModelLayerLocation(new ResourceLocation(CheeseQuest.MOD_ID, name), "main");
    }
}
