package org.javarush.Plants;

import org.javarush.Creature;

public class Plant implements Creature {
    private double weight;
    private final int maxPopulation;

    public Plant(double weight, int maxPopulation) {
        this.weight = weight;
        this.maxPopulation = maxPopulation;
    }

    @Override
    public int getMaxPopulation() {
        return maxPopulation;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight>0? weight:0;
    }
}