package codyy.cheesequest.registry;

import codyy.cheesequest.CheeseQuest;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CheeseQuest.MOD_ID);

    private static final CreativeModeTab.DisplayItemsGenerator GENERATOR = (p_270422_, p_259433_) -> {
        for (var items : ModItems.ITEMS.getEntries()) {
            p_259433_.accept(items.get());
        }
    };

    public static final RegistryObject<CreativeModeTab> CHEESE_QUEST_TAB = TABS.register("cheese_quest_tab", () -> new CreativeModeTab.Builder(CreativeModeTab.Row.TOP, 1).title(Component.translatable("itemGroup." + CheeseQuest.MOD_ID)).displayItems(GENERATOR).noScrollBar().build());
}
