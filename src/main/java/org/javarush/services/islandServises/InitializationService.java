package org.javarush.services.islandServises;

import org.javarush.Coordinates;
import org.javarush.Creature;
import org.javarush.Plants.Grass;
import org.javarush.services.factoryService.CreatureFactoryService;
import org.javarush.services.factoryService.Creatures;

import java.util.*;

public class InitializationService {
    private static final Random random = new Random();

    public static Map<Coordinates, Map<Class<? extends Creature>, List<Creature>>> initIslandGameField(int length, int height){
        Map<Coordinates, Map<Class<? extends Creature>, List<Creature>>> gameField = new HashMap<>();
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= height; j++) {
                gameField.put(new Coordinates(i, j), fillCellWithCreatures());
            }
        }
        return gameField;
    }

    private static Map<Class<? extends Creature>, List<Creature>> fillCellWithCreatures(){
        Map<Class<? extends Creature>, List<Creature>> creaturesInCell = new HashMap<>();
        for(Creatures enumValue : Creatures.values()){
            if(creatureIsPresentInCell()){
                Creature islandCreature = CreatureFactoryService.creatureFactory(enumValue);
                List<Creature> sameTypeCreaturesInCell = getCreaturesList(islandCreature, enumValue);
                creaturesInCell.put(islandCreature.getClass(), sameTypeCreaturesInCell);
            }
        }
        return creaturesInCell;
    }

    private static boolean creatureIsPresentInCell() {
        return (!(random.nextInt(3) == 0));
    }

    private static List<Creature> getCreaturesList(Creature islandCreature, Creatures enumValue){
        List<Creature> creatureList = new ArrayList<>();
        creatureList.add(islandCreature);
        int populationOfSameTypeCreatures = getCreaturePopulationInCell(islandCreature.getMaxPopulation());
        if(islandCreature instanceof Grass grass) {
            grass.setTotalWeight(populationOfSameTypeCreatures*grass.getWeight());
            grass.setWeight(populationOfSameTypeCreatures*grass.getWeight());
        }
        else {
            for (int i = 1; i < populationOfSameTypeCreatures; i++) {
                creatureList.add(CreatureFactoryService.creatureFactory(enumValue));
            }
        }
        return creatureList;
    }

    private static int getCreaturePopulationInCell(int maxPopulation){
        return random.nextInt(maxPopulation);
    }
}