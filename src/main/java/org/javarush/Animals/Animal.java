package org.javarush.Animals;

import lombok.Getter;
import lombok.Setter;
import org.javarush.Coordinates;
import org.javarush.Creature;
import org.javarush.services.TableService;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Animal implements Creature {
    private static final Random random = new Random();
    private double weight;
    private final int maxPopulation;
    private final int maxSpeedOfMovement;
    private final double maxCanEat;
    @Getter
    private final Gender gender;
    @Setter
    private boolean moved = false;

    public Animal(double weight, int maxPopulation, int maxSpeedOfMovement, double maxCanEat) {
        this.weight = weight;
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
                    double creatureWeigh = entry.getValue().getLast().getWeight();
                    entry.getValue().removeLast();
                    this.weight += Math.min(creatureWeigh, this.maxCanEat);
                }
                break;
            }
        }
        this.weight -= 1.2*maxCanEat;
    }

    public Coordinates move(Coordinates coordinates, int length, int height) {
        Coordinates destination = new Coordinates(coordinates.getX(), coordinates.getY());
        if(maxSpeedOfMovement<=0) return coordinates;
        int distance = random.nextInt(maxSpeedOfMovement);
        switch (random.nextInt(4)){
            case 0 -> destination.setY(Math.max(destination.getY()-distance, 1));
            case 1 -> destination.setX(Math.min(destination.getX()+distance, length));
            case 2 -> destination.setY(Math.min(destination.getY()+distance, height));
            case 3 -> destination.setX(Math.max(destination.getX()-distance, 1));
        }
        return destination;
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

    public Gender chooseGender(){
        return random.nextInt(2)==0? Gender.MALE:Gender.FEMALE;
    }

    public boolean getMoved() {
        return moved;
    }

    @Override
    public double getWeight() {
        return weight;
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
