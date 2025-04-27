package org.javarush.services.factoryService;

import org.javarush.Animals.Herbivorous.*;
import org.javarush.Animals.Predators.*;
import org.javarush.Creature;
import org.javarush.Plants.Plant;
import org.javarush.services.TableService;

public class CreatureFactoryService {
    public static Creature creatureFactory(Creatures creature) {
        String[] creatureInitStats = TableService.getCreatureInitStats(creature.toString());
        double maxWeight = Double.parseDouble(creatureInitStats[1].trim());
        int maxPopulation = Integer.parseInt(creatureInitStats[2].trim());
        int maxSpeedOfMovement = Integer.parseInt(creatureInitStats[3].trim());
        double maxCanEat = Double.parseDouble(creatureInitStats[4].trim());
        return switch (creature) {
            case WOLF -> new Wolf(maxWeight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case BOA -> new Boa(maxWeight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case FOX -> new Fox(maxWeight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case BEAR -> new Bear(maxWeight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case EAGLE -> new Eagle(maxWeight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case HORSE -> new Horse(maxWeight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case DEER -> new Deer(maxWeight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case RABBIT -> new Rabbit(maxWeight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case MOUSE -> new Mouse(maxWeight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case GOAT -> new Goat(maxWeight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case SHEEP -> new Sheep(maxWeight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case BOAR -> new Boar(maxWeight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case BUFFALO -> new Buffalo(maxWeight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case DUCK -> new Duck(maxWeight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case CATERPILLAR -> new Caterpillar(maxWeight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case PLANT -> new Plant(maxWeight, maxPopulation);
        };
    }
}
