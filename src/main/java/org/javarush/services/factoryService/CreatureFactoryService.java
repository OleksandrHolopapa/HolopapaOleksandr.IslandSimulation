package org.javarush.services.factoryService;

import org.javarush.Animals.Herbivorous.*;
import org.javarush.Animals.Predators.*;
import org.javarush.Creature;
import org.javarush.Plants.Grass;
import org.javarush.services.TableService;

public class CreatureFactoryService {
    public static Creature creatureFactory(Creatures creature) {
        String[] creatureInitStats = TableService.getCreatureInitStats(creature.toString());
        double weight = Double.parseDouble(creatureInitStats[1].trim());
        int maxPopulation = Integer.parseInt(creatureInitStats[2].trim());
        int maxSpeedOfMovement = Integer.parseInt(creatureInitStats[3].trim());
        double maxCanEat = Double.parseDouble(creatureInitStats[4].trim());
        return switch (creature) {
            case WOLF -> new Wolf(weight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case BOA -> new Boa(weight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case FOX -> new Fox(weight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case BEAR -> new Bear(weight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case EAGLE -> new Eagle(weight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case HORSE -> new Horse(weight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case DEER -> new Deer(weight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case RABBIT -> new Rabbit(weight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case MOUSE -> new Mouse(weight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case GOAT -> new Goat(weight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case SHEEP -> new Sheep(weight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case BOAR -> new Boar(weight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case BUFFALO -> new Buffalo(weight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case DUCK -> new Duck(weight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case CATERPILLAR -> new Caterpillar(weight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            case GRASS -> new Grass(weight, maxPopulation);
        };
    }
}