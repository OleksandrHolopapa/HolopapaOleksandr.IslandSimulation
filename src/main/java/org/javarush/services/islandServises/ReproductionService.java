package org.javarush.services.islandServises;

import org.javarush.Animals.Animal;
import org.javarush.Coordinates;
import org.javarush.Creature;
import org.javarush.Plants.Grass;
import org.javarush.services.factoryService.CreatureFactoryService;
import org.javarush.services.factoryService.Creatures;

import java.util.List;
import java.util.Map;

public class ReproductionService {
    private static int periodsToAnimalReproduce = 3;
    public static void reproductionOnTheIsland(Map<Coordinates, Map<Class<? extends Creature>, List<Creature>>> gameField){
        periodsToAnimalReproduce--;
        for (Map.Entry<Coordinates, Map<Class<? extends Creature>, List<Creature>>> entry : gameField.entrySet()) {
            for (Map.Entry<Class<? extends Creature>, List<Creature>> entryMap : entry.getValue().entrySet()) {
                if(!entryMap.getValue().isEmpty()){
                    if(entryMap.getKey().equals(Grass.class)) {
                        ((Grass)entryMap.getValue().getLast()).reproduction();
                    }
                    else if(periodsToAnimalReproduce==0){
                        int numberOfNewCreatures = Animal.reproduction(entryMap.getValue());
                        String speciesName = entryMap.getKey().getSimpleName().toUpperCase();
                        for (int i = 0; i < numberOfNewCreatures; i++) {
                            entryMap.getValue().add(CreatureFactoryService.creatureFactory(Creatures.valueOf(speciesName)));
                        }
                    }
                }
            }
        }
        if(periodsToAnimalReproduce ==0) periodsToAnimalReproduce =3;
    }
}