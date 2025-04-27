package org.javarush.services.islandServises;

import org.javarush.Animals.Animal;
import org.javarush.Coordinates;
import org.javarush.Creature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RelocationService {
    public static void movingOnTheIsland(Map<Coordinates, Map<Class<? extends Creature>, List<Creature>>> gameField, int length, int height){
        for (Map.Entry<Coordinates, Map<Class<? extends Creature>, List<Creature>>> entry : gameField.entrySet()) {
            for (Map.Entry<Class<? extends Creature>, List<Creature>> entryMap : entry.getValue().entrySet()) {
                for (int i = 0; i<entryMap.getValue().size(); i++) {
                    Creature creature = entryMap.getValue().get(i);
                    if((creature instanceof Animal animal)&&(!animal.getMoved())) {
                        Coordinates destinationCell = animal.move(entry.getKey(), length, height);
                        if((!destinationCell.equals(entry.getKey()))&&canGoToDestinationCell(destinationCell, animal, gameField)) {
                            animal.setMoved(true);
                            moving(entry.getKey(), destinationCell, animal, gameField);
                            i--;
                        }
                    }
                }
            }
        }
        updateAbilityToTravel(gameField);
    }

    private static boolean canGoToDestinationCell(Coordinates destinationCell, Animal animal, Map<Coordinates, Map<Class<? extends Creature>, List<Creature>>> gameField){
        Map<Class<? extends Creature>, List<Creature>> creaturesInDestinationCell = gameField.get(destinationCell);
        List<Creature> sameAnimalsInDestinationCell = creaturesInDestinationCell.get(animal.getClass());
        return (sameAnimalsInDestinationCell==null)||(sameAnimalsInDestinationCell.size()<animal.getMaxPopulation());
    }

    private static void moving(Coordinates currentCell, Coordinates destinationCell, Animal animal, Map<Coordinates, Map<Class<? extends Creature>, List<Creature>>> gameField){
        Map<Class<? extends Creature>, List<Creature>> creaturesInCurrentCell = gameField.get(currentCell);
        List<Creature> sameAnimalsInCurrentCell = creaturesInCurrentCell.get(animal.getClass());
        sameAnimalsInCurrentCell.remove(animal);
        Map<Class<? extends Creature>, List<Creature>> creaturesInDestinationCell = gameField.get(destinationCell);
        if(creaturesInDestinationCell.containsKey(animal.getClass())){
            List<Creature> sameAnimalsInDestinationCell = creaturesInDestinationCell.get(animal.getClass());
            sameAnimalsInDestinationCell.add(animal);
        }
        else {
            List<Creature> sameAnimalsInDestinationCell = new ArrayList<>();
            sameAnimalsInDestinationCell.add(animal);
            creaturesInDestinationCell.put(animal.getClass(), sameAnimalsInDestinationCell);
        }
    }

    private static void updateAbilityToTravel(Map<Coordinates, Map<Class<? extends Creature>, List<Creature>>> gameField){
        for (Map.Entry<Coordinates, Map<Class<? extends Creature>, List<Creature>>> entry : gameField.entrySet()) {
            for (Map.Entry<Class<? extends Creature>, List<Creature>> entryMap : entry.getValue().entrySet()) {
                for (Creature creature : entryMap.getValue()) {
                    if(creature instanceof Animal animal) animal.setMoved(false);
                }
            }
        }
    }
}
