package org.javarush.Animals;

import org.javarush.Coordinates;
import org.javarush.Creature;
import org.javarush.Gender;
import org.javarush.services.TableService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Animal implements Creature {
    private static List<String> possibilityToEatTable;

    static {
        try {
            possibilityToEatTable = Files.readAllLines(Path.of("src/main/resources/eatingPossibilitiesTable.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Random random = new Random();
    private final double maxWeight;
    private double weight;
    private final int maxPopulation;
    private final int maxSpeedOfMovement;
    private final double maxCanEat;
    private final Gender gender;
    private boolean moved = false;

    public Animal(double maxWeight, int maxPopulation, int maxSpeedOfMovement, double maxCanEat) {
        this.maxWeight = maxWeight;
        weight = maxWeight;
        this.maxPopulation = maxPopulation;
        this.maxSpeedOfMovement = maxSpeedOfMovement;
        this.maxCanEat = maxCanEat;
        gender = chooseGender();
    }

    public void eat(Map<Class<? extends Creature>, List<Creature>> value){
        for (Map.Entry<Class<? extends Creature>, List<Creature>> entry : value.entrySet()) {
            String simpleName = entry.getKey().getSimpleName();
            int possibilityToEatValue = TableService.getPossibilityToEatValue(this.toString(), simpleName);
            if(possibilityToEatValue>0&&(!entry.getValue().isEmpty())){
                if(random.nextInt(100)<=possibilityToEatValue){
                    double creatureWeigh = entry.getValue().getFirst().getWeight();
                    entry.getValue().removeLast();
                    this.weight += Math.min(creatureWeigh, this.maxCanEat);
                    System.out.println(this+" eat "+simpleName);
                }
                break;
            }
        }
        this.weight -= Math.max(maxCanEat, maxWeight*0.5);
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


    public static int reproduction(List<Creature> creaturesOfTheSameSpecies) {
        int maleCount = 0;
        for (Creature creature : creaturesOfTheSameSpecies) {
            if(((Animal)creature).gender.equals(Gender.MALE)) maleCount++;
        }
        int realNumberOfNewCreature = Math.min(maleCount, creaturesOfTheSameSpecies.size() - maleCount);
        int possibleNumberOfNewCreature = creaturesOfTheSameSpecies.getFirst().getMaxPopulation()-creaturesOfTheSameSpecies.size();
        return Math.min(possibleNumberOfNewCreature, realNumberOfNewCreature);
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
