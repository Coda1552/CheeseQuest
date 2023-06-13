package codyy.cheesequest;

import codyy.cheesequest.registry.*;
import dev.zanckor.api.filemanager.dialog.register.LoadDialog;
import dev.zanckor.api.filemanager.npc.entity_type.register.LoadDialogList;
import dev.zanckor.api.filemanager.npc.entity_type_tag.register.LoadTagDialogList;
import dev.zanckor.api.filemanager.quest.register.LoadQuest;
import dev.zanckor.mod.QuestApiMain;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.io.IOException;

@Mod(CheeseQuest.MOD_ID)
@Mod.EventBusSubscriber(modid = QuestApiMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CheeseQuest {
    public static final String MOD_ID = "cheesequest";

    public CheeseQuest() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        ModBlockEntities.BLOCK_ENTITY_TYPES.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModEntities.ENTITY_TYPES.register(bus);
        ModTabs.TABS.register(bus);

        bus.addListener(this::createAttributes);
    }

    @SubscribeEvent
    public static void registerTemplates(ServerAboutToStartEvent e) throws IOException {
        QuestApiMain.LOGGER.debug("Register template files");

        LoadQuest.registerQuest(e.getServer(), MOD_ID);
        LoadDialog.registerDialog(e.getServer(), MOD_ID);
        LoadDialogList.registerNPCDialogList(e.getServer(), MOD_ID);
        LoadTagDialogList.registerNPCTagDialogList(e.getServer(), MOD_ID);
    }

    private void createAttributes(EntityAttributeCreationEvent e) {
        e.put(ModEntities.QUEST_LION.get(), LivingEntity.createLivingAttributes().build());
    }
}
