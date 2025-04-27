package org.javarush.services.islandServises;

import org.javarush.Animals.Animal;
import org.javarush.Coordinates;
import org.javarush.Creature;
import org.javarush.Plants.Plant;
import org.javarush.services.factoryService.CreatureFactoryService;
import org.javarush.services.factoryService.Creatures;

import java.util.List;
import java.util.Map;

public class ReproductionService {
    public static void reproductionOnTheIsland(Map<Coordinates, Map<Class<? extends Creature>, List<Creature>>> gameField){
        for (Map.Entry<Coordinates, Map<Class<? extends Creature>, List<Creature>>> entry : gameField.entrySet()) {
            for (Map.Entry<Class<? extends Creature>, List<Creature>> entryMap : entry.getValue().entrySet()) {
                if(!entryMap.getValue().isEmpty()){
                    int numberOfNewCreatures;
                    if(entryMap.getKey().equals(Plant.class)) numberOfNewCreatures = Plant.reproduction(entryMap.getValue());
                    else numberOfNewCreatures = Animal.reproduction(entryMap.getValue());
                    String speciesName = entryMap.getKey().getSimpleName().toUpperCase();
                    for (int i = 0; i < numberOfNewCreatures; i++) {
                        entryMap.getValue().add(CreatureFactoryService.creatureFactory(Creatures.valueOf(speciesName)));
                    }
                }
            }
        }
    }

}
