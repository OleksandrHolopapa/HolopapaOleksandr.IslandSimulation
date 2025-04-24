package org.javarush.Animals;

import org.javarush.Coordinates;
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
    private boolean moved = false;

    public Animal(double maxWeight, int maxPopulation, int maxSpeedOfMovement, double maxCanEat) {
        this.maxWeight = maxWeight;
        this.maxPopulation = maxPopulation;
        this.maxSpeedOfMovement = maxSpeedOfMovement;
        this.maxCanEat = maxCanEat;
        gender = chooseGender();
    }

    public Coordinates move(Coordinates coordinates, int length, int height) {
        Coordinates pos = new Coordinates(coordinates.getX(), coordinates.getY());
        int distance = random.nextInt(maxSpeedOfMovement);
        switch (random.nextInt(4)){
            case 0 -> pos.setY(Math.max(pos.getY()-distance, 1));
            case 1 -> pos.setX(Math.min(pos.getX()+distance, length));
            case 2 -> pos.setY(Math.min(pos.getY()+distance, height));
            case 3 -> pos.setX(Math.max(pos.getX()-distance, 1));
        }
        return pos;
    }

    public Gender getGender() {
        return gender;
    }

    public Gender chooseGender(){
        return random.nextInt(2)==0? Gender.MALE:Gender.FEMALE;
    }

    public boolean getMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
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
