package net.mca.entity;

import net.mca.entity.ai.relationship.Gender;
import net.mca.entity.race.IRaceEntityMCAF;
import net.mca.entity.race.Race;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class RacedVillagerEntityMCAF extends VillagerEntityMCA implements IRaceEntityMCAF {
    private final Race RACE;

    public RacedVillagerEntityMCAF(EntityType<VillagerEntityMCA> type, World w, Gender gender, Race race) {
        super(type, w, gender);

        this.RACE = race;
    }

    @Override
    public Race getRaceType() {
        return RACE;
    }

    @Override
    public Race calculateDNA(Race raceType) {
        return null;
    }

    @Override
    public Race calculateDNA(IRaceEntityMCAF father) {
        return calculateDNA(father.getRaceType());
    }
}