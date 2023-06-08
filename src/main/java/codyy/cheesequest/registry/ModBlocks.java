package codyy.cheesequest.registry;

import codyy.cheesequest.CheeseQuest;
import codyy.cheesequest.common.blocks.QuestLionBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CheeseQuest.MOD_ID);

    public static final RegistryObject<Block> QUEST_LION = BLOCKS.register("quest_lion", () -> new QuestLionBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
}
