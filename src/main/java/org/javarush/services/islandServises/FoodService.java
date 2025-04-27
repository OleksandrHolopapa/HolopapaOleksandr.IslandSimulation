package org.javarush.services.islandServises;

import org.javarush.Animals.Animal;
import org.javarush.Coordinates;
import org.javarush.Creature;
import org.javarush.Plants.Plant;

import java.util.List;
import java.util.Map;

public class FoodService {
    public static void timeToEat (Map<Coordinates, Map<Class<? extends Creature>, List<Creature>>> gameField){
        for (Map.Entry<Coordinates, Map<Class<? extends Creature>, List<Creature>>> entry : gameField.entrySet()) {
            for (Map.Entry<Class<? extends Creature>, List<Creature>> entryMap : entry.getValue().entrySet()) {
                for (Creature creature : entryMap.getValue()) {
                    if(creature instanceof Animal animal) animal.eat(entry.getValue());
                }
            }
        }
    }

    public static void deathByStarvation(Map<Coordinates, Map<Class<? extends Creature>, List<Creature>>> gameField){
        for (Map.Entry<Coordinates, Map<Class<? extends Creature>, List<Creature>>> entry : gameField.entrySet()) {
            die(entry.getValue());
        }
    }

    private static void die(Map<Class<? extends Creature>, List<Creature>> creaturesInCell){
        for (Map.Entry<Class<? extends Creature>, List<Creature>> entry : creaturesInCell.entrySet()) {
            if(!entry.getKey().equals(Plant.class)){
                List<Creature> creatures = entry.getValue();
                for (int i = 0; i < creatures.size(); i++) {
                    Animal creature = (Animal) creatures.get(i);
                    if(creature.getWeight()<=0) {
                        creatures.remove(creature);
                        i--;
                    }
                }
            }
        }
    }


}
