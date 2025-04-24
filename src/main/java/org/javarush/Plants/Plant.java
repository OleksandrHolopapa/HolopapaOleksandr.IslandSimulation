package org.javarush.Plants;

import org.javarush.Creature;

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

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
