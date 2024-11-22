package net.mca.entity.race;

public interface IRaceEntityMCAF {

    Race getRaceType();

    /**
     * For procreation, calculate the DNA of the child based on the parents' DNA.
     *
     * @param raceType RaceType of the father. The mother is the instance of this class.
     * @return RaceType of the child.
     */
    Race calculateDNA(Race raceType);

    /**
     * For procreation, calculate the DNA of the child based on the parents' DNA.
     *
     * @param father RaceType of the father. The mother is the instance of this class.
     * @return RaceType of the child.
     */
    Race calculateDNA(IRaceEntityMCAF father);
}