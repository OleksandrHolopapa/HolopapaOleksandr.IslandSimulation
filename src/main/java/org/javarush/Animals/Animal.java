package org.javarush.Animals;

import org.javarush.Creature;
import org.javarush.Gender;

import java.util.List;
import java.util.Random;

public class Animal implements Creature {
    private static List<String> possibilityToEatTable;
    private static final Random random = new Random();
    private double maxWeight;
    private int maxPopulation;
    private int maxSpeedOfMovement;
    private double maxCanEat;
    private Gender gender;

    public Animal(double maxWeight, int maxPopulation, int maxSpeedOfMovement, double maxCanEat) {
        this.maxWeight = maxWeight;
        this.maxPopulation = maxPopulation;
        this.maxSpeedOfMovement = maxSpeedOfMovement;
        this.maxCanEat = maxCanEat;
        gender = chooseGender();
    }

    public Gender getGender() {
        return gender;
    }

    public Gender chooseGender(){
        return random.nextInt(2)==0? Gender.MALE:Gender.FEMALE;
    }

    @Override
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
