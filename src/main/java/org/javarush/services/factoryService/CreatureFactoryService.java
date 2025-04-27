package org.javarush.services.factoryService;

import org.javarush.Animals.Herbivorous.Rabbit;
import org.javarush.Animals.Predators.Wolf;
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
            /*case BOA -> new Boa();
            case FOX -> new Fox();
            case BEAR -> new Bear();
            case EAGLE -> new Eagle();
            case HORSE -> new Horse();
            case DEER -> new Deer();*/
            case RABBIT -> new Rabbit(maxWeight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            /*case MOUSE -> new Mouse();
            case GOAT -> new Goat();
            case SHEEP -> new Sheep();
            case BOAR -> new Boar();
            case BUFFALO -> new Buffalo();
            case DUCK -> new Duck();
            case CATERPILLAR -> new Caterpillar();*/
            case PLANT -> new Plant(maxWeight, maxPopulation);
        };
    }
}
