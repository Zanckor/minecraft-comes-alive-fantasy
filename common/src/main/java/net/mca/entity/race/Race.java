package net.mca.entity.race;

public enum Race {
    HUMAN,
    GOBLIN,
    ELF,
    DWARF,
    NETHERBORN,
    FAIRY,
    DRAGONBORN;

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
}
