package codyy.cheesequest.registry;

import codyy.cheesequest.CheeseQuest;
import codyy.cheesequest.common.blocks.QuestLionBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CheeseQuest.MOD_ID);

    public static final RegistryObject<Item> QUEST_LION = ITEMS.register("quest_lion", () -> new BlockItem(ModBlocks.QUEST_LION.get(), new Item.Properties().rarity(Rarity.EPIC)));
}
