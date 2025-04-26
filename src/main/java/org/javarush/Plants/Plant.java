package org.javarush.Plants;

import org.javarush.Creature;

import java.util.List;

public class Plant implements Creature {
    private double maxWeight;
    private int maxPopulation;

    public Plant(double maxWeight, int maxPopulation) {
        this.maxWeight = maxWeight;
        this.maxPopulation = maxPopulation;
    }

    public double getWeight() {
        return maxWeight;
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
