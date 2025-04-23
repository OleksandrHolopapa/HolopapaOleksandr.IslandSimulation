package org.javarush.Animals;

import lombok.Setter;
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
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
