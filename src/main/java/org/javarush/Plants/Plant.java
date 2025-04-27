package org.javarush.Plants;

import lombok.Getter;
import org.javarush.Creature;

import java.util.List;

public class Plant implements Creature {
    @Getter
    private final double weight;
    private final int maxPopulation;

    public Plant(double weight, int maxPopulation) {
        this.weight = weight;
        this.maxPopulation = maxPopulation;
    }

    @Override
    public int getMaxPopulation() {
        return maxPopulation;
    }

    public static int reproduction(List<Creature> creaturesOfTheSameSpecies) {
        int possibleNumberOfNewCreature = creaturesOfTheSameSpecies.getFirst().getMaxPopulation()-creaturesOfTheSameSpecies.size();
        return Math.min(creaturesOfTheSameSpecies.size(), possibleNumberOfNewCreature);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
