package net.mca.entity;

import java.util.function.Supplier;

import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.mca.MCA;
import net.mca.ProfessionsMCA;
import net.mca.entity.ai.ActivityMCA;
import net.mca.entity.ai.MemoryModuleTypeMCA;
import net.mca.entity.ai.SchedulesMCA;
import net.mca.entity.ai.relationship.Gender;
import net.mca.entity.race.Race;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public interface EntitiesMCA {

    DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(MCA.MOD_ID, RegistryKeys.ENTITY_TYPE);

    RegistrySupplier<EntityType<VillagerEntityMCA>> MALE_VILLAGER = registerVillager(Gender.MALE, Race.HUMAN);
    RegistrySupplier<EntityType<VillagerEntityMCA>> FEMALE_VILLAGER = registerVillager(Gender.FEMALE, Race.HUMAN);
    RegistrySupplier<EntityType<VillagerEntityMCA>> MALE_GOBLIN = registerVillager(Gender.MALE, Race.GOBLIN);
    RegistrySupplier<EntityType<VillagerEntityMCA>> FEMALE_GOBLIN = registerVillager(Gender.FEMALE, Race.GOBLIN);
    RegistrySupplier<EntityType<VillagerEntityMCA>> MALE_ELF = registerVillager(Gender.MALE, Race.ELF);
    RegistrySupplier<EntityType<VillagerEntityMCA>> FEMALE_ELF = registerVillager(Gender.FEMALE, Race.ELF);
    RegistrySupplier<EntityType<VillagerEntityMCA>> MALE_DWARF = registerVillager(Gender.MALE, Race.DWARF);
    RegistrySupplier<EntityType<VillagerEntityMCA>> FEMALE_DWARF = registerVillager(Gender.FEMALE, Race.DWARF);
    RegistrySupplier<EntityType<VillagerEntityMCA>> MALE_NETHERBORN = registerVillager(Gender.MALE, Race.NETHERBORN);
    RegistrySupplier<EntityType<VillagerEntityMCA>> FEMALE_NETHERBORN = registerVillager(Gender.FEMALE, Race.NETHERBORN);
    RegistrySupplier<EntityType<VillagerEntityMCA>> MALE_FAIRY = registerVillager(Gender.MALE, Race.FAIRY);
    RegistrySupplier<EntityType<VillagerEntityMCA>> FEMALE_FAIRY = registerVillager(Gender.FEMALE, Race.FAIRY);
    RegistrySupplier<EntityType<VillagerEntityMCA>> MALE_DRAGONBORN = registerVillager(Gender.MALE, Race.DRAGONBORN);
    RegistrySupplier<EntityType<VillagerEntityMCA>> FEMALE_DRAGONBORN = registerVillager(Gender.FEMALE, Race.DRAGONBORN);

    RegistrySupplier<EntityType<ZombieVillagerEntityMCA>> MALE_ZOMBIE_VILLAGER = registerZombieVillager(Gender.MALE);
    RegistrySupplier<EntityType<ZombieVillagerEntityMCA>> FEMALE_ZOMBIE_VILLAGER = registerZombieVillager(Gender.FEMALE);

    RegistrySupplier<EntityType<GrimReaperEntity>> GRIM_REAPER = register("grim_reaper", EntityType.Builder
            .create(GrimReaperEntity::new, SpawnGroup.MONSTER)
            .setDimensions(1, 2.6F)
            .makeFireImmune(), GrimReaperEntity::createAttributes
    );

    RegistrySupplier<EntityType<CribEntity>> CRIB = registerNonLiving("crib", EntityType.Builder
    		.<CribEntity>create(CribEntity::new, SpawnGroup.MISC)
            .setDimensions(1.2F, 1.0F)
            .makeFireImmune()
    );

    static RegistrySupplier<EntityType<VillagerEntityMCA>> registerVillager(Gender gender, Race race) {
        return register(gender.name().toLowerCase() + "_" + race.name().toLowerCase() + "_villager", EntityType.Builder
                .<VillagerEntityMCA>create((t, w) -> new VillagerEntityMCA(t, w, gender, race), SpawnGroup.AMBIENT)
                .setDimensions(0.6F, 2.0F), VillagerEntityMCA::createVillagerAttributes
        );
    }

    static RegistrySupplier<EntityType<ZombieVillagerEntityMCA>> registerZombieVillager(Gender gender) {
        return register(gender.name().toLowerCase() + "_zombie_villager", EntityType.Builder
                .<ZombieVillagerEntityMCA>create((t, w) -> new ZombieVillagerEntityMCA(t, w, gender), SpawnGroup.MONSTER)
                .setDimensions(0.6F, 2.0F), ZombieVillagerEntityMCA::createZombieAttributes
        );
    }

    static void bootstrap() {
        ENTITY_TYPES.register();
        MemoryModuleTypeMCA.bootstrap();
        ActivityMCA.bootstrap();
        SchedulesMCA.bootstrap();
        ProfessionsMCA.bootstrap();
    }
    
    static<T extends Entity> RegistrySupplier<EntityType<T>> registerNonLiving(String name, EntityType.Builder<T> builder) {
        Identifier id = new Identifier(MCA.MOD_ID, name);
        return ENTITY_TYPES.register(id, () -> {
            EntityType<T> result = builder.build(id.toString());
            return result;
        });
    }

    static <T extends LivingEntity> RegistrySupplier<EntityType<T>> register(String name, EntityType.Builder<T> builder, Supplier<DefaultAttributeContainer.Builder> attributes) {
        Identifier id = new Identifier(MCA.MOD_ID, name);
        return ENTITY_TYPES.register(id, () -> {
            EntityType<T> result = builder.build(id.toString());
            EntityAttributeRegistry.register(() -> result, attributes);

            return result;
        });
    }
}
