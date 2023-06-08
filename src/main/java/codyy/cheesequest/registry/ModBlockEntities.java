package codyy.cheesequest.registry;

import codyy.cheesequest.CheeseQuest;
import codyy.cheesequest.common.blocks.entities.QuestLionBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CheeseQuest.MOD_ID);

    public static final RegistryObject<BlockEntityType<QuestLionBlockEntity>> QUEST_LION = BLOCK_ENTITY_TYPES.register("quest_lion", () -> BlockEntityType.Builder.of(QuestLionBlockEntity::new, ModBlocks.QUEST_LION.get()).build(null));

}
