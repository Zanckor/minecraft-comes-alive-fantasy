package net.mca.entity.race;

import java.util.Random;

public enum Race {
    HUMAN,
    GOBLIN,
    ELF,
    DWARF,
    NETHERBORN,
    FAIRY,
    DRAGONBORN,
    NONE;

    public static Race getRaceType(String raceType, Race defaultRaceType) {
        for (Race type : Race.values()) {
            if (type.name().equalsIgnoreCase(raceType)) {
                return type;
            }
        }

        return defaultRaceType;
    }

    public static Race getRaceType(String raceType) {
        return getRaceType(raceType, HUMAN);
    }

    public static Race getRandom() {
        return Race.values()[new Random().nextInt(Race.values().length)];
    }

    public static Race valueOf(int ordinal) {
        return Race.values()[ordinal];
    }
}
