package org.javarush.Plants;

import lombok.Getter;
import lombok.Setter;

public class Grass extends Plant{
    @Getter
    @Setter
    private double eatenGrass = 0;
    private final int count;
    @Getter
    private double totalWeight;

    public Grass(double weight, int maxPopulation) {
        super(weight, maxPopulation);
        count = (int)weight/2;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight>0? totalWeight:0;
    }

    public void reproduction(){
        double dif = this.totalWeight-getWeight();
        totalWeight = getWeight();
        totalWeight +=eatenGrass;
        eatenGrass=dif;
        setWeight(totalWeight);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}