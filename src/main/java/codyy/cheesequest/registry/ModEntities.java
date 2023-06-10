package codyy.cheesequest.registry;

import codyy.cheesequest.CheeseQuest;
import codyy.cheesequest.common.entities.QuestLionEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CheeseQuest.MOD_ID);

    public static final RegistryObject<EntityType<QuestLionEntity>> QUEST_LION = create("quest_lion", EntityType.Builder.of(QuestLionEntity::new, MobCategory.MISC).noSummon().sized(1.0f, 1.0f));

    private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return ENTITY_TYPES.register(name, () -> builder.build(CheeseQuest.MOD_ID + "." + name));
    }
}
