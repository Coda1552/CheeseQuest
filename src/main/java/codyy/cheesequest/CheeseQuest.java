package codyy.cheesequest;

import codyy.cheesequest.registry.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CheeseQuest.MOD_ID)
public class CheeseQuest {
    public static final String MOD_ID = "cheesequest";

    public CheeseQuest() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlockEntities.BLOCK_ENTITY_TYPES.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModEntities.ENTITY_TYPES.register(bus);
        ModTabs.TABS.register(bus);

        bus.addListener(this::createAttributes);
    }

/*    private void createTab(BuildCreativeModeTabContentsEvent e) {
        e.registerCreativeModeTab(new ResourceLocation(MOD_ID, MOD_ID), p -> p.icon(() -> new ItemStack(ModItems.QUEST_LION.get()))
                .title(Component.translatable("itemGroup." + MOD_ID))
                .displayItems((enabledFeatures, entries) -> {
                    for (var items : ModItems.ITEMS.getEntries()) {
                        entries.accept(items.get());
                    }
                })
        );
    }*/

    private void createAttributes(EntityAttributeCreationEvent e) {
        e.put(ModEntities.QUEST_LION.get(), LivingEntity.createLivingAttributes().build());
    }
}
